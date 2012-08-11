package plaid.fastruntime.dcg;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.CheckClassAdapter;

import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.errors.PlaidInternalException;
import plaid.fastruntime.reference.SimplePlaidJavaObject;

public class JavaDispatchGenerator implements Opcodes {
	private int classCounter = 0;
	
	private final Map<Class<?>, PlaidDispatch> javaStateCache = new HashMap<Class<?>,PlaidDispatch>();
	
	/**
	 * Add PlaidState to cache. Used for preloading state cache with frequently used states.
	 * @param key Java class
	 * @param value PlaidState that used as proxy for Java class' methods
	 */
	public void preloadPlaidState(Class<?> key, PlaidDispatch value) {
		this.javaStateCache.put(key, value);
	}
	
	public void saveStaticJavaObject(String javaClass)  {
		ClassLoader cl = this.getClass().getClassLoader();
		try {
			Class<?> theClass = cl.loadClass(javaClass);
			saveStaticJavaObject(theClass);
		} catch (ClassNotFoundException e) {
			throw new PlaidIllegalOperationException("Java Class " + javaClass + " not found to generate static methods", e);
		}
	}
	
	public void saveStaticJavaObject(Class<?> javaClass) {
		final String internalClassName = org.objectweb.asm.Type.getInternalName(javaClass);
		final String name = "plaid/generated/" + internalClassName;
		final String subPath = name.substring(0, name.length()-javaClass.getSimpleName().length());
		
		//TODO: check if file already exists in file system
		
		byte[] b = createStaticJavaBytes(javaClass);
		File classFile = new File("../generated/bin/" + name + ".class");
		//System.out.println("ready to write file " + classFile.getPath());
		ClassFileWriter.writeFile(b, new File("../generated/bin/" + subPath), classFile);
	}
	
	public Class<?> getStaticJavaClass(Class<?> javaClass) {
		final String internalClassName = org.objectweb.asm.Type.getInternalName(javaClass);
		final String name = "plaid/generated/" + internalClassName;
		//TODO: check if file already exists in file system
		
		byte[] b = createStaticJavaBytes(javaClass);
		Class<?> clazz = ClassInjector.defineClass(name, b, 0, b.length);
		return clazz;
	}
	
	public byte[] createStaticJavaBytes(Class<?> javaClass) {
		final String internalClassName = org.objectweb.asm.Type.getInternalName(javaClass);
		final String name = "plaid/generated/" + internalClassName;
		
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
		cw.visit(50, ACC_PUBLIC, name, null, "java/lang/Object", null);
		
		Map<MethodSig, List<org.objectweb.asm.commons.Method>> methodMap = collectMethods(javaClass, true);
		for ( MethodSig m : methodMap.keySet() ) {
			writeJavaMethod(javaClass, true, internalClassName, cw, methodMap, m);
		}
		// done
		cw.visitEnd();
		PrintWriter pw = new PrintWriter(System.out);
		CheckClassAdapter.verify(new ClassReader(cw.toByteArray()), false, pw);
		byte[] b = cw.toByteArray();
		return b;
	}
	
	/* ONLY CALL FROM UTIL - use javaToPlaid method */
	public PlaidObject createPlaidJavaObject(Object javaObject) {
		final String name = "plaid/generatedDispatches/PlaidJavaObject$plaid$"+classCounter++;
		Class<?> javaClass = javaObject.getClass();
		PlaidDispatch result = null;
		

		if( !javaStateCache.containsKey(javaClass)) {
			while(!Modifier.isPublic(javaClass.getModifiers())) {
				javaClass = javaClass.getSuperclass();
			}
			String internalClassName = org.objectweb.asm.Type.getInternalName(javaClass);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
			
			// sort public methods into static overload sets
			Map<MethodSig, List<org.objectweb.asm.commons.Method>> methodMap = collectMethods(javaClass, false);
			
			// collect all necessary interfaces and ensure they have been generated
			Collection<String> ifaces = new ArrayList<String>();
			for (MethodSig mSig : methodMap.keySet()) {
				String methodName = NamingConventions.getGeneratedIdentifier(mSig.name);
				ifaces.add(NamingConventions.getGeneratedInterfaceInternalName(methodName, mSig.numArgs));
				Util.INTERFACE_GEN.createInterfaceAsClass(methodName,mSig.numArgs);
			}
			
			// generate class
			cw.visit(50,
				     ACC_PUBLIC,
				     name,
				     null,
				     "plaid/fastruntime/reference/AbstractPlaidJavaDispatch",
				     ifaces.toArray(new String[0]));
			
			// add methods 
			for ( MethodSig m : methodMap.keySet() ) {
				writeJavaMethod(javaClass, false, internalClassName, cw, methodMap, m);
			}
			
			//instantiate method - s
			{
				MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC,"instantiate", "()Lplaid/fastruntime/PlaidObject;", null, null); 
				mv.visitCode();
				mv.visitTypeInsn(NEW, "plaid/fastruntime/errors/PlaidIllegalOperationException");
				mv.visitInsn(DUP);
				mv.visitLdcInsn("Java States cannot be instantiated.");
				mv.visitMethodInsn(INVOKESPECIAL, "plaid/fastruntime/errors/PlaidIllegalOperationException", "<init>", "(Ljava/lang/String;)V");
				mv.visitInsn(ATHROW);
				mv.visitMaxs(0, 0); //calculated
				mv.visitEnd();
			
			}
			// add constructor
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "plaid/fastruntime/reference/AbstractPlaidJavaDispatch","<init>", "()V");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0,0);
			mv.visitEnd();
	
			// done
			cw.visitEnd();
			try {
				byte[] b = cw.toByteArray();
				
				Class<?> plaidStateClass = ClassInjector.defineClass(name, b, 0, b.length);
				result =  (PlaidDispatch)plaidStateClass.newInstance();
				javaStateCache.put(javaClass, result);
			} catch (InstantiationException e) {
				throw new PlaidInternalException("Could not construct dispatch object.", e);
			} catch (IllegalAccessException e) {
				throw new PlaidInternalException("Could not construct dispatch object because " +
						"constructor was not accessible.", e);
			} catch (ClassCastException e) {
				throw new PlaidInternalException("Failed to cast generated dispatch to PlaidState", e);
			}
		} else {
			result = this.javaStateCache.get(javaClass);
		}
		
		PlaidObject toReturn = new SimplePlaidJavaObject(result,null,javaObject);
		return toReturn;
	}


	private Map<MethodSig, List<org.objectweb.asm.commons.Method>> collectMethods(Class<?> javaClass, boolean wantStatic) {
		Map<MethodSig,List<org.objectweb.asm.commons.Method>> methodMap = new HashMap<MethodSig,List<org.objectweb.asm.commons.Method>>();
		for (Method m : javaClass.getMethods()) {
			if (Modifier.isPublic(m.getModifiers())  && (wantStatic == Modifier.isStatic(m.getModifiers())) ) {
				MethodSig mSig = new MethodSig(m.getName(), m.getGenericParameterTypes().length);
				org.objectweb.asm.commons.Method asmMethod = org.objectweb.asm.commons.Method.getMethod(m);
				if (methodMap.containsKey(mSig)) {
					methodMap.get(mSig).add(asmMethod);
				} else {
					List<org.objectweb.asm.commons.Method> mList = new ArrayList<org.objectweb.asm.commons.Method>();
					mList.add(asmMethod);
					methodMap.put(mSig, mList);
				}	
			}
		}
		if(wantStatic) {
			for (Constructor<?> ctr : javaClass.getConstructors()) {
				if (Modifier.isPublic(ctr.getModifiers())) {
					MethodSig mSig = new MethodSig(NamingConventions.GENERATED_CONSTRUCTOR, ctr.getGenericParameterTypes().length);
					org.objectweb.asm.commons.Method asmMethod = org.objectweb.asm.commons.Method.getMethod(ctr);
					if (methodMap.containsKey(mSig)) {
						methodMap.get(mSig).add(asmMethod);
					} else {
						List<org.objectweb.asm.commons.Method> mList = new ArrayList<org.objectweb.asm.commons.Method>();
						mList.add(asmMethod);
						methodMap.put(mSig, mList);
					}
				}
			}
		}
		return methodMap;
	}


	private void writeJavaMethod(Class<?> javaClass, boolean isStatic, String internalClassName,
			ClassWriter cw, Map<MethodSig, List<org.objectweb.asm.commons.Method>> methodMap, MethodSig m) {
		int extraArgs = 0;
		if(!isStatic) {
			extraArgs = 1;
		}
		int firstArgRegister = 0;
		if(!isStatic) {
			firstArgRegister = 2;
		}
		int numArgs = m.numArgs + extraArgs;
		int modifiers = ACC_PUBLIC;
		if(isStatic) {
			modifiers+=ACC_STATIC;
		}
		boolean isConstructor = isStatic && m.name.equals(NamingConventions.GENERATED_CONSTRUCTOR);
		List<org.objectweb.asm.commons.Method> overloadSet = methodMap.get(m);
		
		final String methodName = isStatic ? m.name : NamingConventions.getGeneratedIdentifier(m.name);
		
		MethodVisitor mv;
		{
			mv = cw.visitMethod(modifiers, 
					methodName, 
					NamingConventions.getMethodDescriptor(numArgs), null, null);
			mv.visitCode();
			
			Label l0 = new Label();
			Label l1 = new Label();
			Label l2 = new Label();
			mv.visitTryCatchBlock(l0, l1, l2, "java/lang/ClassCastException");
			mv.visitLabel(l0);
			
			if(!isStatic) {
				//load Java receiver
				mv.visitVarInsn(ALOAD, 1);
				mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
				mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
				mv.visitTypeInsn(CHECKCAST, internalClassName);
			}
			
			//load java arguments 
			if (overloadSet.size() == 1) {
				org.objectweb.asm.commons.Method asmMethod = overloadSet.get(0);
				
				if (isConstructor) { //create and duplicate object - one for constructor second to return
					mv.visitTypeInsn(NEW, internalClassName);
					mv.visitInsn(DUP);
				}
				
				Type[] args = asmMethod.getArgumentTypes();
				for (int i = 0; i < args.length; i++) {
					mv.visitVarInsn(ALOAD, i+firstArgRegister);
					
					
					Type argType = args[i];
					
					if (isPrimitiveType(argType)) { //covert Plaid objects to primitives (fails if cannot be done)
						mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
						
						mv.visitFieldInsn(GETSTATIC, "plaid/fastruntime/PlaidJavaObject$JavaPrimitive", 
								asmTypeToPrimitive(argType).field, "Lplaid/fastruntime/PlaidJavaObject$JavaPrimitive;"); 
						mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "asPrimitive", 
											"(Lplaid/fastruntime/PlaidJavaObject$JavaPrimitive;)Ljava/lang/Object;");
						unbox(argType, mv);
					} else { //get java object and cast to the arg type (fails if cannot be done)
						mv.visitInsn(DUP);
						Label skip = new Label();
						mv.visitTypeInsn(INSTANCEOF, "plaid/fastruntime/PlaidJavaObject");
						mv.visitJumpInsn(IFEQ, skip);
						mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
						
						mv.visitLabel(skip);
						mv.visitTypeInsn(CHECKCAST, argType.getInternalName());
					}
				}
				
				//invoke java method
				int invocationtype = INVOKEVIRTUAL;
				if(isStatic) {
					invocationtype = INVOKESTATIC;
				}
				if(isConstructor){
					invocationtype = INVOKESPECIAL;
				}
				
				mv.visitMethodInsn(invocationtype, internalClassName, asmMethod.getName(), asmMethod.getDescriptor());
				
				
				//returned value is primitive (or void if not a constructor), box it (void becomes unit)
				Type returnType = asmMethod.getReturnType();
				if (isPrimitiveType(returnType) || (isVoidType(returnType) && !isConstructor)) {
					box(asmMethod.getReturnType(), mv);
				}
			} else {
				//choose which method to call with reflection (may want to trying something different in the future)
				
				//static methods need the class name
				if (isStatic)
					mv.visitLdcInsn(Type.getType(NamingConventions.internalNameToDescriptor(internalClassName)));
					
				//non-constructors need the method name
				if (!isConstructor)
					mv.visitLdcInsn(m.name);
				
				//create array for arguments
				mv.visitIntInsn(BIPUSH, m.numArgs);
				mv.visitTypeInsn(ANEWARRAY, "plaid/fastruntime/PlaidJavaObject");
				for (int i = 0; i < m.numArgs; i++) {
					mv.visitInsn(DUP); //duplicate array
					mv.visitIntInsn(BIPUSH, i);
					mv.visitVarInsn(ALOAD, i+firstArgRegister);
					mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
					mv.visitInsn(AASTORE);
				}
				
				if(!isStatic) { //call overloading of instance method
					mv.visitMethodInsn(INVOKESTATIC, "plaid/fastruntime/Util", 
							   "overloadedInstanceMethod", NamingConventions.OVERLOAD_INSTANCE_METHOD_DESC);
				} else if (isConstructor) {
					mv.visitMethodInsn(INVOKESTATIC, "plaid/fastruntime/Util", 
							   "overloadedConstructor", NamingConventions.OVERLOAD_CONSTRUCTOR_METHOD_DESC);
				} else {	
					mv.visitMethodInsn(INVOKESTATIC, "plaid/fastruntime/Util", 
							   "overloadedStaticMethod", NamingConventions.OVERLOAD_STATIC_METHOD_DESC);
				}
			}

			mv.visitInsn(DUP);
			mv.visitTypeInsn(INSTANCEOF, "plaid/fastruntime/PlaidObject");
			mv.visitJumpInsn(IFNE, l1);
			//wrap Java object into a PlaidJavaObject
			mv.visitMethodInsn(INVOKESTATIC, "plaid/fastruntime/Util", 
							   "javaToPlaid", "(Ljava/lang/Object;)Lplaid/fastruntime/PlaidObject;");
			mv.visitLabel(l1);
			mv.visitInsn(ARETURN);
			mv.visitLabel(l2);
			mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/ClassCastException"});
			mv.visitVarInsn(ASTORE, numArgs+1);
			mv.visitTypeInsn(NEW, "plaid/fastruntime/errors/PlaidIllegalOperationException");
			mv.visitInsn(DUP);
			mv.visitLdcInsn("Invalid argument type passed to java method " + javaClass.getSimpleName() + "." + m.name);
			mv.visitVarInsn(ALOAD, numArgs+1);
			mv.visitMethodInsn(INVOKESPECIAL, "plaid/fastruntime/errors/PlaidIllegalOperationException", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;)V");
			mv.visitInsn(ATHROW);
			mv.visitMaxs(0, 0); //calculated
			mv.visitEnd();
		}
	}
	
	/**
	 * put return type into a form that plaid can use
	 * ****WARNING**** longs currently use a lossy conversion
	 * 
	 */
	private void box(Type type,MethodVisitor mv) {
		if (type.equals(Type.BOOLEAN_TYPE)) {
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
		} else if (type.equals(Type.DOUBLE_TYPE)) {
			mv.visitTypeInsn(NEW, "java/lang/Double");
			mv.visitInsn(DUP_X2);
			mv.visitInsn(DUP_X2); //two copies of Double object below the double primitive (2 slots)
			mv.visitInsn(POP); //pop Double object at top of stack so that double is at top
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Double", "<init>", "(D)V");
		} else if (type.equals(Type.FLOAT_TYPE)) {
			mv.visitInsn(F2D);
			mv.visitTypeInsn(NEW, "java/lang/Double");
			mv.visitInsn(DUP_X2);
			mv.visitInsn(DUP_X2); //two copies of Double object below the double primitive (2 slots)
			mv.visitInsn(POP); //pop Double object at top of stack so that double is at top
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Double", "<init>", "(D)V");
		} else if (type.equals(Type.INT_TYPE) || type.equals(Type.SHORT_TYPE) || type.equals(Type.BYTE_TYPE)) {
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
		} else if (type.equals(Type.LONG_TYPE) ) {
			mv.visitTypeInsn(NEW, "java/lang/Long");
			mv.visitInsn(DUP_X2);
			mv.visitInsn(DUP_X2);
			mv.visitInsn(POP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Long", "<init>", "(J)V");
		} else if (type.equals(Type.CHAR_TYPE)) {  //create a string with the character
			mv.visitInsn(ICONST_1);
			mv.visitIntInsn(NEWARRAY, T_CHAR);
			mv.visitInsn(DUP_X1);
			mv.visitInsn(SWAP);
			mv.visitInsn(ICONST_0);
			mv.visitInsn(SWAP);
			mv.visitInsn(CASTORE);
			mv.visitTypeInsn(NEW, "java/lang/String");
			mv.visitInsn(DUP_X1);
			mv.visitInsn(SWAP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/String", "<init>", "([C)V");
		} else if (type.equals(Type.VOID_TYPE)) {
			mv.visitInsn(ACONST_NULL);
		} else {
			throw new PlaidInternalException("unimplemented primitive Java type (box) " + type.getDescriptor());
		}
	}
	/**
	 * 
	 * covert to Java primitive
	 * ***WARNING - does not check for lossy conversions for numeric types
	 * ***WARNING - Plaid characters expected to be a string of a single character
	 */
	private void unbox(Type type, MethodVisitor mv) {
		if (type.getSort() == Type.BOOLEAN) {
			mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z");
		} else if (type.getSort() == Type.INT){
			mv.visitTypeInsn(CHECKCAST, "java/lang/Number");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Number", "intValue", "()I");
		} else if (type.getSort() == Type.LONG){
			mv.visitTypeInsn(CHECKCAST, "java/lang/Number");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Number", "longValue", "()J");
		} else if (type.getSort() == Type.DOUBLE){
			mv.visitTypeInsn(CHECKCAST, "java/lang/Number");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Number", "doubleValue", "()D");
		} else if (type.getSort() == Type.BYTE){
			mv.visitTypeInsn(CHECKCAST, "java/lang/Number");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Number", "byteValue", "()B");
		} else if (type.getSort() == Type.SHORT){
			mv.visitTypeInsn(CHECKCAST, "java/lang/Number");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Number", "shortValue", "()S");
		} else if (type.getSort() == Type.FLOAT){
			mv.visitTypeInsn(CHECKCAST, "java/lang/Number");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Number", "floatValue", "()F");
		} else if (type.getSort() == Type.CHAR){
			mv.visitTypeInsn(CHECKCAST, "java/lang/String");
			mv.visitInsn(ICONST_0);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "charAt", "(I)C");
		} else {
			throw new PlaidInternalException("unimplemented primitive Java type (unbox) " + type.getDescriptor());
		}
	}
	
	public static boolean isPrimitiveType(Type type) {
		switch (type.getSort()) {
		case Type.ARRAY:
		case Type.VOID:
		case Type.OBJECT: return false;
		default: return true;
		}
	}
	
	public static boolean isVoidType(Type type) {
		return type.getSort() == Type.VOID;
	}
	
	public static PlaidJavaObject.JavaPrimitive asmTypeToPrimitive(Type type) {
		switch (type.getSort()) {
		case Type.BOOLEAN: return PlaidJavaObject.JavaPrimitive.BOOLEAN;
		case Type.BYTE: return PlaidJavaObject.JavaPrimitive.BYTE;
		case Type.CHAR: return PlaidJavaObject.JavaPrimitive.CHAR;
		case Type.DOUBLE: return PlaidJavaObject.JavaPrimitive.DOUBLE;
		case Type.FLOAT: return PlaidJavaObject.JavaPrimitive.FLOAT;
		case Type.INT: return PlaidJavaObject.JavaPrimitive.INT;
		case Type.LONG: return PlaidJavaObject.JavaPrimitive.LONG;
		case Type.SHORT: return PlaidJavaObject.JavaPrimitive.SHORT;
		default: throw new PlaidIllegalOperationException("asm type " + type.toString() + " is not a primitive java type");
		}
	}
	
	private class MethodSig {
		public String name;
		public int numArgs;
		
		public MethodSig(String name, int numArgs) {
			this.name = name;
			this.numArgs = numArgs;
		}
		
		public boolean equals(Object o) {
			if (o instanceof MethodSig)
				return this.equals((MethodSig) o);
			else
				return false;
		}
	
		public boolean equals(MethodSig o) {
			return this.name.equals(o.name) &&
				this.numArgs == o.numArgs;
		}
		public String toString() {
			return this.name + "(" + this.numArgs + ")";
		}
		
		public int hashCode() {
			return name.hashCode() + numArgs;
		}
	}
}
