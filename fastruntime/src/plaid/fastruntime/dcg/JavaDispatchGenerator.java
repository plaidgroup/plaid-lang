package plaid.fastruntime.dcg;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.errors.PlaidInternalException;
import plaid.fastruntime.reference.SimplePlaidJavaObject;

public class JavaDispatchGenerator implements Opcodes {
	private int classCounter = 0;
	private final DynClassLoader cl = new DynClassLoader();
	
	public PlaidJavaObject createPlaidJavaObject(Object javaObject) {
		final String name = "plaid/generatedDispatches/PlaidJavaObject$plaid$"+classCounter++;
		Class<?> javaClass = javaObject.getClass();
		String classDesc = org.objectweb.asm.Type.getDescriptor(javaClass);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
		
		// generate class
		Collection<String> ifaces = new ArrayList<String>();
		for (Method m : javaObject.getClass().getMethods()) {
			boolean isPublic = Modifier.isPublic(m.getModifiers());
			if (isPublic) {
				ifaces.add(NamingConventions.getGeneratedInterfaceInternalName(m.getName(), m.getGenericParameterTypes().length+1));
			}
		}
		cw.visit(50,
			     ACC_PUBLIC,
			     name,
			     null,
			     "plaid/fastruntime/AbstractPlaidState",
			     ifaces.toArray(new String[0]));
		
		// add methods 
		for ( Method m : javaObject.getClass().getMethods() ) {
			//System.out.println("add method: " + m.getName());
			int numArgs = m.getGenericParameterTypes().length + 1;
			boolean isPublic = Modifier.isPublic(m.getModifiers());
			org.objectweb.asm.commons.Method asmMethod = org.objectweb.asm.commons.Method.getMethod(m);
			if (isPublic) {
				MethodVisitor mv;
				{
					mv = cw.visitMethod(ACC_PUBLIC, 
							m.getName(), 
							NamingConventions.getMethodDescriptor(numArgs), null, null);
					mv.visitCode();
					
					Label l0 = new Label();
					Label l1 = new Label();
					Label l2 = new Label();
					mv.visitTryCatchBlock(l0, l1, l2, "java/lang/ClassCastException");
					mv.visitLabel(l0);
					mv.visitVarInsn(ALOAD, 1);
					mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
					mv.visitVarInsn(ASTORE, 3);
					mv.visitVarInsn(ALOAD, 3);
					mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
					mv.visitTypeInsn(CHECKCAST, classDesc);
					mv.visitVarInsn(ASTORE, 4);
					mv.visitVarInsn(ALOAD, 2);
					mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
					mv.visitVarInsn(ASTORE, 5);
					mv.visitVarInsn(ALOAD, 5);
					mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
					//TODO: mutliple arguments
					//TODO: check for static overloading
					Type[] args = asmMethod.getArgumentTypes();
					mv.visitTypeInsn(CHECKCAST, args[0].getDescriptor());
					mv.visitVarInsn(ASTORE, 6);
					mv.visitVarInsn(ALOAD, 4);
					mv.visitVarInsn(ALOAD, 6);
					mv.visitMethodInsn(INVOKEVIRTUAL, classDesc, asmMethod.getName(), asmMethod.getDescriptor());
					mv.visitMethodInsn(INVOKESTATIC, "plaid/compiler/Util", "unit", "()Lplaid/fastruntime/PlaidObject;");
					mv.visitLabel(l1);
					mv.visitInsn(ARETURN);
					mv.visitLabel(l2);
					mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/ClassCastException"});
					mv.visitVarInsn(ASTORE, 3);
					mv.visitTypeInsn(NEW, "plaid/fastruntime/errors/PlaidInternalException");
					mv.visitInsn(DUP);
					mv.visitLdcInsn("Java state method called on non-java object.");
					mv.visitVarInsn(ALOAD, 3);
					mv.visitMethodInsn(INVOKESPECIAL, "plaid/fastruntime/errors/PlaidInternalException", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;)V");
					mv.visitInsn(ATHROW);
					mv.visitMaxs(4, 7);
					mv.visitEnd();
				}
			}
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
		PlaidState result = null;
		try {
			result =  (PlaidState)cl.createClass(name, cw).newInstance();
		} catch (InstantiationException e) {
			throw new PlaidInternalException("Count not construct dispatch object.", e);
		} catch (IllegalAccessException e) {
			throw new PlaidInternalException("Count not construct dispatch object because " +
					"constructor was not accessible.", e);
		} catch (ClassCastException e) {
			throw new PlaidInternalException("Failed to cast generated dispatch to PlaidState", e);
		}
		SimplePlaidJavaObject toReturn = new SimplePlaidJavaObject(result, null, javaObject);
		return toReturn;
	}
	

	private class DynClassLoader extends ClassLoader {
		public Class<?> createClass(String name, ClassWriter cw) {
			byte[] b = cw.toByteArray();
			return defineClass(name.replace("/", "."), b, 0, b.length);
		}
	}
}
