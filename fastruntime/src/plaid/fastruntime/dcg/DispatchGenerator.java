package plaid.fastruntime.dcg;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.errors.PlaidInternalException;


public final class DispatchGenerator implements Opcodes {
	private int classCounter = 0;
	
	public PlaidState createStateInstance(ObjectValue ov) {
		final String name = "plaid/generatedDispatches/DispatchClass$plaid$"+classCounter++;
					
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
		
		
		// generate class
		Collection<String> ifaces = new ArrayList<String>();
		for (MethodInfo m : ov.getMethods()) {
			ifaces.add(NamingConventions.getGeneratedInterfaceInternalName(m.getName(), m.numArgs()));
		}
		for(FieldInfo f: ov.getFields()) {
			String getterName = NamingConventions.getGetterName(f.getName());
			ifaces.add(NamingConventions.getGeneratedInterfaceInternalName(getterName, 0));
		}
		cw.visit(50,
			     ACC_PUBLIC,
			     name,
			     null,
			     "plaid/fastruntime/reference/AbstractPlaidState",
			     ifaces.toArray(new String[0]));
		
		// add methods 
		for ( MethodInfo m : ov.getMethods() ) {
			//System.out.println("add method: " + m.getName());
			MethodVisitor mv;
			mv = cw.visitMethod(ACC_PUBLIC, 
					m.getName(), 
					m.getMethodDescriptor(),
					null,
					null); // TODO: add exception
					mv.visitCode();
					// add receiver

					mv.visitVarInsn(ALOAD, 1);
					// add parameters
					for (int x = 2; x <= m.numArgs()+1; x++ ) {
						mv.visitVarInsn(ALOAD, x);
					}
					mv.visitMethodInsn(INVOKESTATIC, m.getStaticClassInternalName(), m.getName(), m.getMethodDescriptor());
					mv.visitInsn(ARETURN);
					mv.visitMaxs(2,3);
					mv.visitEnd();			
		}
		
		// add fields 
		for ( FieldInfo f : ov.getFields() ) {
			//System.out.println("add method: " + m.getName());
			MethodVisitor mv;
			mv = cw.visitMethod(ACC_PUBLIC, 
					NamingConventions.getGetterName(f.getName()),
					NamingConventions.getMethodDescriptor(1),
					null,
					null); // TODO: add exception

			mv.visitCode();
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidObject", "getStorage", "()[Lplaid/fastruntime/PlaidObject;");
			int index = ov.getFieldIndex(f.getName());
			mv.visitIntInsn(BIPUSH, index);
			mv.visitInsn(AALOAD);
			mv.visitInsn(ARETURN);
			mv.visitMaxs(2,2);
			mv.visitEnd();
					
			//TODO: add setters
		}
		
		// add constructor
		{
			MethodVisitor mv;
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Lplaid/fastruntime/ObjectValue;)V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKESPECIAL, "plaid/fastruntime/reference/AbstractPlaidState", "<init>", "(Lplaid/fastruntime/ObjectValue;)V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(2, 2);
			mv.visitEnd();
		}

		// done
		cw.visitEnd();
		PlaidState result = null;
		try {
			byte[] b = cw.toByteArray();
			Class<?> plaidStateClass = ClassInjector.defineClass(name, cw.toByteArray(), 0, b.length);
			Constructor<?> cstr =  plaidStateClass.getConstructor(ObjectValue.class);
			result = (PlaidState)cstr.newInstance(ov);
		} catch(NoSuchMethodException e) {
			throw new PlaidInternalException("Could not construct dispatch object.", e);
		}  catch (ClassCastException e) {
			throw new PlaidInternalException("Failed to cast generated dispatch to PlaidState", e);
		} catch (IllegalArgumentException e) {
			throw new PlaidInternalException("Could not construct dispatch object.", e);
		} catch (InstantiationException e) {
			throw new PlaidInternalException("Could not construct dispatch object.", e);
		} catch (IllegalAccessException e) {
			throw new PlaidInternalException("Could not construct dispatch object.", e);
		} catch (InvocationTargetException e) {
			throw new PlaidInternalException("Could not construct dispatch object.", e);
		}
		return result;
	}
}
