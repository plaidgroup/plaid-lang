package plaid.fastruntime.dcg;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;
import plaid.fastruntime.errors.PlaidInternalException;
import plaid.fastruntime.reference.SimplePlaidJavaObject;

public class JavaDispatchGenerator implements Opcodes {
	private int classCounter = 0;
	
	private final Map<Class<?>, PlaidState> javaStateCache = new HashMap<Class<?>,PlaidState>();
	
	/**
	 * Add PlaidState to cache. Used for preloading state cache with frequently used states.
	 * @param key Java class
	 * @param value PlaidState that used as proxy for Java class' methods
	 */
	public void preloadPlaidState(Class<?> key, PlaidState value) {
		this.javaStateCache.put(key, value);
	}
	
	
	/* ONLY CALL FROM UTIL - use javaToPlaid method */
	public PlaidObject createPlaidJavaObject(Object javaObject) {
		final String name = "plaid/generatedDispatches/PlaidJavaObject$plaid$"+classCounter++;
		Class<?> javaClass = javaObject.getClass();
		PlaidState result = null;
		
		if( !javaStateCache.containsKey(javaClass)) {
		
			String internalClassName = org.objectweb.asm.Type.getInternalName(javaClass);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
			
			// sort public methods into static overload sets
			Map<MethodSig,List<Method>> methodMap = new HashMap<MethodSig,List<Method>>();
			for (Method m : javaObject.getClass().getMethods()) {
				if (Modifier.isPublic(m.getModifiers()) ) {// && m.getName().equals("set")) {
					MethodSig mSig = new MethodSig(m.getName(), m.getGenericParameterTypes().length);
					if (methodMap.containsKey(mSig)) {
						methodMap.get(mSig).add(m);
					} else {
						List<Method> mList = new ArrayList<Method>();
						mList.add(m);
						methodMap.put(mSig, mList);
					}	
				}
			}
			
			// collect all necessary interfaces and ensure they have been generated
			Collection<String> ifaces = new ArrayList<String>();
			for (MethodSig mSig : methodMap.keySet()) {
				ifaces.add(mSig.interfaceName());
				Util.INTERFACE_GEN.createInterfaceAsClass(mSig.name,mSig.numArgs);
			}
			
			// generate class
			cw.visit(50,
				     ACC_PUBLIC,
				     name,
				     null,
				     "plaid/fastruntime/reference/AbstractPlaidState",
				     ifaces.toArray(new String[0]));
			
			// add methods 
			for ( MethodSig m : methodMap.keySet() ) {
				//System.out.println("add method: " + m.getName());
				int numArgs = m.numArgs + 1;
				List<Method> overloadSet = methodMap.get(m);
					
				MethodVisitor mv;
				{
					mv = cw.visitMethod(ACC_PUBLIC, 
							m.name, 
							NamingConventions.getMethodDescriptor(numArgs), null, null);
					mv.visitCode();
					
					Label l0 = new Label();
					Label l1 = new Label();
					Label l2 = new Label();
					mv.visitTryCatchBlock(l0, l1, l2, "java/lang/ClassCastException");
					mv.visitLabel(l0);
					
					//load Java receiver
					mv.visitVarInsn(ALOAD, 1);
					mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
					mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
					mv.visitTypeInsn(CHECKCAST, internalClassName);
					
					//load java arguments 
					if (overloadSet.size() == 1) {
						org.objectweb.asm.commons.Method asmMethod = org.objectweb.asm.commons.Method.getMethod(overloadSet.get(0));
						Type[] args = asmMethod.getArgumentTypes();
						for (int i = 0; i < args.length; i++) {
							mv.visitVarInsn(ALOAD, i+2);
							mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
							
							Type argType = args[i];
							
							if (isPrimitiveType(argType)) { //covert Plaid objects to primitives (fails if cannot be done)
								mv.visitFieldInsn(GETSTATIC, "plaid/fastruntime/PlaidJavaObject$JavaPrimitive", 
										asmTypeToPrimitive(argType).field, "Lplaid/fastruntime/PlaidJavaObject$JavaPrimitive;"); 
								mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "asPrimitive", 
													"(Lplaid/fastruntime/PlaidJavaObject$JavaPrimitive;)Ljava/lang/Object;");
								unbox(argType, mv);
							} else { //get java object and cast to the arg type (fails if cannot be done)
								mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
								mv.visitTypeInsn(CHECKCAST, argType.getInternalName());
							}
						}
						
						//invoke java method
						mv.visitMethodInsn(INVOKEVIRTUAL, internalClassName, asmMethod.getName(), asmMethod.getDescriptor());
						
						//returned value is primitive (or void), box it (void becomes unit)
						Type returnType = asmMethod.getReturnType();
						if (isPrimitiveType(returnType) || isVoidType(returnType)) {
							box(asmMethod.getReturnType(), mv);
						}
					} else {
						//choose which method to call with reflection (may want to trying something different in the future)
						
						//because I didn't want to have to deal with arrays
						if (m.numArgs > 6) 
							throw new PlaidInternalException("handling of java static overloaded methods with > 6 parameters not implemented");
						
						mv.visitLdcInsn(m.name); //
						for (int i = 0; i < m.numArgs; i++) {
							mv.visitVarInsn(ALOAD, i+2);
							mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
							//mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
						}
						mv.visitMethodInsn(INVOKESTATIC, "plaid/fastruntime/Util", 
								   "staticOverloadingCall", NamingConventions.staticOverloadCallMethodDescriptor(m.numArgs));
					}
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
			
			//instantiate method - creates SimplePlaidJavaObject with this as the dispatch object, null as the storage object, and javaObj as the rep
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
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object","<init>", "()V");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0,0);
			mv.visitEnd();
	
			// done
			cw.visitEnd();
			try {
				byte[] b = cw.toByteArray();
				ClassInjector.writeClass(b, "/Users/kbn/Documents/test.class");
				
				Class<?> plaidStateClass = ClassInjector.defineClass(name, b, 0, b.length);
				result =  (PlaidState)plaidStateClass.newInstance();
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
	
	private void box(Type type,MethodVisitor mv) {
		if (type.equals(Type.BOOLEAN_TYPE)) {
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
		} else if (type.equals(Type.DOUBLE_TYPE)) {
			mv.visitTypeInsn(NEW, "java/lang/Double");
			mv.visitInsn(DUP_X1);
			mv.visitInsn(SWAP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Double", "<init>", "(D)V");
		} else if (type.equals(Type.INT_TYPE)) {
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
		} else if (type.equals(Type.VOID_TYPE)) {
			mv.visitMethodInsn(INVOKESTATIC, "plaid/fastruntime/Util", "unit", "()Lplaid/fastruntime/PlaidObject;");
		} else {
			throw new PlaidInternalException("unimplemented primitive Java type (box) " + type.getDescriptor());
		}
	}
	
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
		
		public String interfaceName() {
			return NamingConventions.getGeneratedInterfaceInternalName(this.name,this.numArgs);
		}
		public String toString() {
			return this.name + "(" + this.numArgs + ")";
		}
		
		public int hashCode() {
			return name.hashCode() + numArgs;
		}
	}
}
