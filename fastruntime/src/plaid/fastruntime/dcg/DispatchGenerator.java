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

import static plaid.fastruntime.NamingConventions.getGeneratedMemberName;


public final class DispatchGenerator implements Opcodes {
	private int classCounter = 0;
	
	public PlaidDispatch createStateInstance(ObjectValue ov) {
		final String name = "plaid/generatedDispatches/DispatchClass$plaid$"+classCounter++;
					
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
		
		
		// generate class
		Collection<String> ifaces = new ArrayList<String>();
		for (MethodInfo m : ov.getMethods()) {
			ifaces.add(NamingConventions.getGeneratedInterfaceInternalName(getGeneratedMemberName(m), m.numArgs()));
			Util.INTERFACE_GEN.createInterfaceAsClass(getGeneratedMemberName(m), m.numArgs());
		}
		for(FieldInfo f: ov.getFields()) {
			String getterName = NamingConventions.getGetterName(getGeneratedMemberName(f));
			ifaces.add(NamingConventions.getGeneratedInterfaceInternalName(getterName, 0));
			Util.INTERFACE_GEN.createInterfaceAsClass(getterName, 0);
			if(f.isSettable()) {
				String setterName = NamingConventions.getSetterName(getGeneratedMemberName(f));
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
			//System.out.println("add method: " + getGeneratedMemberName(m));
			MethodVisitor mv;
			if(m.isStaticallyDefined()) {
				mv = cw.visitMethod(ACC_PUBLIC, 
						getGeneratedMemberName(m), 
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
				mv.visitMethodInsn(INVOKESTATIC, NamingConventions.getGeneratedInternal(m), getGeneratedMemberName(m), m.getMethodDescriptor());
				mv.visitInsn(ARETURN);
				mv.visitMaxs(2,3);
				mv.visitEnd();
			} else { //dynamically defined
				
				String helperName = NamingConventions.getGetterName(getGeneratedMemberName(m));
				String helperDescriptor = NamingConventions.getMethodDescriptor(1);
						
						
				//first define getter
				MethodVisitor mvHelper;
				mvHelper = cw.visitMethod(ACC_PUBLIC, 
						helperName,
						helperDescriptor,
						null,
						null); // TODO: add exception

				mvHelper.visitCode();
				mvHelper.visitVarInsn(ALOAD, 1);
				mvHelper.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidObject", "getStorage", "()[Lplaid/fastruntime/PlaidObject;");
				int index = ov.getStorageIndex(m.getName());
				mvHelper.visitIntInsn(BIPUSH, index);
				mvHelper.visitInsn(AALOAD);
				mvHelper.visitInsn(ARETURN);
				mvHelper.visitMaxs(2,2);
				mvHelper.visitEnd();
				
				mv = cw.visitMethod(ACC_PUBLIC, 
						getGeneratedMemberName(m),
						m.getMethodDescriptor(), null, null);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitVarInsn(ALOAD, 1);
				mv.visitMethodInsn(INVOKEVIRTUAL, name, helperName, helperDescriptor);
				final String lambdaType = "plaid/fastruntime/PlaidLambda$" + (m.numArgs() + 1); // normal arguments + receiver
				mv.visitTypeInsn(CHECKCAST, lambdaType);
				// add parameters
				for (int x = 1; x <= m.numArgs()+1; x++ ) {
					mv.visitVarInsn(ALOAD, x);
				}
				mv.visitMethodInsn(INVOKEVIRTUAL, lambdaType, "invoke$plaid", m.getMethodDescriptor());
				mv.visitInsn(ARETURN);
				mv.visitMaxs(3, 4);
				mv.visitEnd();
			}
		}
		
		// add fields 
		for ( FieldInfo f : ov.getFields() ) {
			//System.out.println("add method: " + getGeneratedMemberName(m));
			MethodVisitor mv;
			mv = cw.visitMethod(ACC_PUBLIC, 
					NamingConventions.getGetterName(getGeneratedMemberName(f)),
					NamingConventions.getMethodDescriptor(1),
					null,
					null); // TODO: add exception

			mv.visitCode();
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidObject", "getStorage", "()[Lplaid/fastruntime/PlaidObject;");
			int index = ov.getStorageIndex(f.getName());
			mv.visitIntInsn(BIPUSH, index);
			mv.visitInsn(AALOAD);
			mv.visitInsn(ARETURN);
			mv.visitMaxs(2,2);
			mv.visitEnd();
			
			if(f.isSettable()){
				mv = cw.visitMethod(ACC_PUBLIC, 
						NamingConventions.getSetterName(getGeneratedMemberName(f)),
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
			//ClassInjector.writeClass(cw.toByteArray(), "../exampleoutput/" + name  + ".class");
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
