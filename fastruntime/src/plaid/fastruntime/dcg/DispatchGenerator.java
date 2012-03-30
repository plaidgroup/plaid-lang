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
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidInternalException;


public final class DispatchGenerator implements Opcodes {
	private int classCounter = 0;
	
	public PlaidDispatch createStateInstance(ObjectValue ov) {
		final String name = "plaid/generatedDispatches/DispatchClass$plaid$"+classCounter++;
					
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
		
		
		// generate class
		Collection<String> ifaces = new ArrayList<String>();
		for (MethodInfo m : ov.getMethods()) {
			ifaces.add(NamingConventions.getGeneratedInterfaceInternalName(m.getName(), m.numArgs()));
			Util.INTERFACE_GEN.createInterfaceAsClass(m.getName(), m.numArgs());
		}
		for(FieldInfo f: ov.getFields()) {
			String getterName = NamingConventions.getGetterName(f.getName());
			ifaces.add(NamingConventions.getGeneratedInterfaceInternalName(getterName, 0));
			Util.INTERFACE_GEN.createInterfaceAsClass(getterName, 0);
			if(f.isSettable()) {
				String setterName = NamingConventions.getSetterName(f.getName());
				ifaces.add(NamingConventions.getGeneratedInterfaceInternalName(setterName, 1));
				Util.INTERFACE_GEN.createInterfaceAsClass(setterName, 1);
			}
		}
		cw.visit(50,
			     ACC_PUBLIC,
			     name,
			     null,
			     "plaid/fastruntime/reference/AbstractPlaidDispatch",
			     ifaces.toArray(new String[0]));
		
		// add methods 
		for ( MethodInfo m : ov.getMethods() ) {
			//System.out.println("add method: " + m.getName());
			MethodVisitor mv;
			if(m.isStaticallyDefined()) {
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
			} else { //dynamically defined
				mv = cw.visitMethod(ACC_PUBLIC, 
						m.getName(),
						m.getMethodDescriptor(), null, null);
				mv.visitCode();
				mv.visitVarInsn(ALOAD, 1);
				mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidObject", "getDispatch", "()Lplaid/fastruntime/PlaidState;");
				mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidState", "getObjectValue", "()Lplaid/fastruntime/ObjectValue;");
				mv.visitLdcInsn(m.getMemberDefinitionName());
				mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/ObjectValue", "getMemberDefinitionIndex", "(Ljava/lang/String;)I");
				mv.visitVarInsn(ISTORE, 3);
				mv.visitVarInsn(ALOAD, 1);
				mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidObject", "getMemberDefs", "()[Ljava/lang/Object;");
				mv.visitVarInsn(ILOAD, 3);
				mv.visitInsn(AALOAD);
				final String interfaceInternalName = NamingConventions.getGeneratedInterfaceInternalName(m.getName(), m.numArgs());
				mv.visitTypeInsn(CHECKCAST, interfaceInternalName);
				mv.visitVarInsn(ALOAD, 1);
				mv.visitVarInsn(ALOAD, 2);
				mv.visitMethodInsn(INVOKEINTERFACE, interfaceInternalName, m.getName(), m.getMethodDescriptor());
				mv.visitInsn(ARETURN);
				mv.visitMaxs(3, 4);
				mv.visitEnd();
			}
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
			
			if(f.isSettable()){
				mv = cw.visitMethod(ACC_PUBLIC, 
						NamingConventions.getSetterName(f.getName()),
						NamingConventions.getMethodDescriptor(2), null, null);
				mv.visitCode();
				mv.visitVarInsn(ALOAD, 1);
				mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidObject", "getStorage", "()[Lplaid/fastruntime/PlaidObject;");
				mv.visitIntInsn(BIPUSH, index);
				mv.visitVarInsn(ALOAD, 2);
				mv.visitInsn(AASTORE);
				mv.visitMethodInsn(INVOKESTATIC, "plaid/fastruntime/Util", "unit", "()Lplaid/fastruntime/PlaidObject;");
				mv.visitInsn(ARETURN);
				mv.visitMaxs(3, 3);
				mv.visitEnd();
			}
					
			//TODO: add setters
		}
		
		// add constructor
		{
			MethodVisitor mv;
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Lplaid/fastruntime/ObjectValue;)V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKESPECIAL, "plaid/fastruntime/reference/AbstractPlaidDispatch", "<init>", "(Lplaid/fastruntime/ObjectValue;)V");
			mv.visitInsn(RETURN);
			mv.visitMaxs(2, 2);
			mv.visitEnd();
		}

		// done
		cw.visitEnd();
		PlaidDispatch result = null;
		try {
			byte[] b = cw.toByteArray();
			Class<?> plaidStateClass = ClassInjector.defineClass(name, cw.toByteArray(), 0, b.length);
			//ClassInjector.writeClass(cw.toByteArray(), "exampleoutput/" + name  + ".class");
			Constructor<?> cstr =  plaidStateClass.getConstructor(ObjectValue.class);
			result = (PlaidDispatch)cstr.newInstance(ov);
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
