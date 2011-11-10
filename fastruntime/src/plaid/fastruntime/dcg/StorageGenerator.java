package plaid.fastruntime.dcg;

import static plaid.fastruntime.dcg.DynamicClassLoader.DYNAMIC_CLASS_LOADER;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidStorage;
import plaid.fastruntime.errors.PlaidInternalException;

import fj.data.List;
import fj.P2;

public class StorageGenerator implements Opcodes {
	
	public PlaidStorage createStorage(List<P2<Boolean, String>> fields) {
		
		String name = "plaid/generated/Example";

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER,
				name, null,
				"java/lang/Object",
				new String[] { "plaid/fastruntime/PlaidStorage" });

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
						name, field._2(),
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
						name, field._2(),
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
						name, field._2(),
				"Lplaid/fastruntime/PlaidObject;");
				mv.visitInsn(RETURN);
				mv.visitMaxs(2, 2);
				mv.visitEnd();
			}
		}
		cw.visitEnd();

		PlaidStorage result = null;
		try {
			System.out.println("1");
			Class<?> storageClass = 
				DYNAMIC_CLASS_LOADER.createClass(name, cw.toByteArray());
			System.out.println("2");
			Constructor<?> storageConstructor = 
				storageClass.getConstructor(PlaidObject.class, PlaidObject.class);
			System.out.println("3");
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
}
