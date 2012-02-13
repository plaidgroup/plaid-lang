package plaid.fastruntime.dcg;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidStorage;
import plaid.fastruntime.errors.PlaidInternalException;

import com.google.monitoring.runtime.instrumentation.asm.Type;

import fj.P2;
import fj.data.List;

public class StorageGenerator implements Opcodes {
	
	/*
	 * @param fields, <isSettable, fieldName>
	 */
	public PlaidStorage createStorage(List<P2<Boolean, String>> fields) {
		PlaidStorage result = null;
		try {
			Class<?> storageClass = createStorageClass(fields);
			Constructor<?> storageConstructor = 
				storageClass.getConstructor(PlaidObject.class, PlaidObject.class);
			result = (PlaidStorage)storageConstructor.newInstance(null, null);
		} catch (InstantiationException e) {
			throw new PlaidInternalException("Could not construct dispatch object.", e);
		} catch (IllegalAccessException e) {
			throw new PlaidInternalException("Could not construct dispatch object because " +
					"constructor was not accessible.", e);
		} catch (NoSuchMethodException e) {
			throw new PlaidInternalException("Could not construct dispatch object because " +
					"constructor was not found.", e);
		} catch (ClassCastException e) {
			throw new PlaidInternalException("Failed to cast generated storage to PlaidStorage", e);
		} catch (VerifyError e) {
			throw new PlaidInternalException("Failed to verify storage object", e);
		} catch (IllegalArgumentException e) {
			throw new PlaidInternalException("Illegal arguments to Plaid Constructor", e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public Class<?> createStorageClass(List<P2<Boolean, String>> fields) {
		
		String className = NamingConventions.getGeneratedStorageInternalName(fields);
		try {
			Class<?> storageClass = this.getClass().getClassLoader().loadClass(className);
			System.out.println(className + " interface already exists");
			return storageClass;
		} catch(ClassNotFoundException e) {
			System.out.println(className + " interface doesn't exist");
			
			ClassWriter cw = new ClassWriter(0);
			FieldVisitor fv;
			MethodVisitor mv;
			
			InterfaceGenerator ig = new InterfaceGenerator();
			
			ArrayList<String> interfaceInternalNames = new ArrayList<String>();
			interfaceInternalNames.add("plaid/fastruntime/PlaidStorage");
			for(P2<Boolean,String> field : fields)
			{
				Class<?> getInterface = ig.createInterfaceAsClass("get" + field._2(), 0);
				interfaceInternalNames.add(Type.getInternalName(getInterface));
				if(field._1()) { // if settable
					Class<?> setInterface = ig.createInterfaceAsClass("set" + field._2(), 1);
					interfaceInternalNames.add(Type.getInternalName(setInterface));
				}
			}
			String[] s = new String[0];
			cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER,
					className, null,
					"java/lang/Object",
					interfaceInternalNames.toArray(s));

			StringBuilder constructorDescBuilder = new StringBuilder("(");
			for(P2<Boolean,String> field : fields)
			{
				fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, field._2(),
						"Lplaid/fastruntime/PlaidObject;", null, null);
				fv.visitEnd();
				constructorDescBuilder.append("Lplaid/fastruntime/PlaidObject;");
			}
			constructorDescBuilder.append(")V");
			{
				mv = cw.visitMethod(
						ACC_PUBLIC,
						"<init>",
						constructorDescBuilder.toString(),
						null, null);
				mv.visitCode();
				
				mv.visitVarInsn(ALOAD, 0);
				mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>",
				"()V");
				int count = 1;
				for(P2<Boolean,String> field : fields)
				{
					mv.visitVarInsn(ALOAD, 0);
					mv.visitVarInsn(ALOAD, count);
					mv.visitFieldInsn(PUTFIELD,
							className, field._2(),
					"Lplaid/fastruntime/PlaidObject;");
					count++;
				}
				
				mv.visitInsn(RETURN);
				mv.visitMaxs(2, count);
				mv.visitEnd();
			}
			for(P2<Boolean,String> field : fields)
			{
				{
					mv = cw.visitMethod(ACC_PUBLIC, "get" + field._2(),
							"()Lplaid/fastruntime/PlaidObject;", null, null);
					mv.visitCode();
					mv.visitVarInsn(ALOAD, 0);
					mv.visitFieldInsn(GETFIELD,
							className, field._2(),
					"Lplaid/fastruntime/PlaidObject;");
					mv.visitInsn(ARETURN);
					mv.visitMaxs(1, 1);
					mv.visitEnd();
				}
				if(field._1()) { // if settable
					mv = cw.visitMethod(ACC_PUBLIC, "set" + field._2(),
							"(Lplaid/fastruntime/PlaidObject;)V", null, null);
					mv.visitCode();
					mv.visitVarInsn(ALOAD, 0);
					mv.visitVarInsn(ALOAD, 1);
					mv.visitFieldInsn(PUTFIELD,
							className, field._2(),
					"Lplaid/fastruntime/PlaidObject;");
					mv.visitInsn(RETURN);
					mv.visitMaxs(2, 2);
					mv.visitEnd();
				}
			}
			cw.visitEnd();
			
			byte[] storageBytes = cw.toByteArray();
			Class<?> storageClass = ClassInjector.defineClass(className, storageBytes, 0, storageBytes.length);
			return storageClass;
		}
	}
}
