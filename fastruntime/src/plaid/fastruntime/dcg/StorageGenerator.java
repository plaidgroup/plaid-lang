package plaid.fastruntime.dcg;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class StorageGenerator implements Opcodes {

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER,
				"plaid/fastruntime/dcg/test/PlaidStorageExample", null,
				"java/lang/Object",
				new String[] { "plaid/fastruntime/PlaidStorage" });

		{
			fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "x",
					"Lplaid/fastruntime/PlaidObject;", null, null);
			fv.visitEnd();
		}
		{
			fv = cw.visitField(ACC_PRIVATE, "y",
					"Lplaid/fastruntime/PlaidObject;", null, null);
			fv.visitEnd();
		}
		{
			mv = cw.visitMethod(
					ACC_PUBLIC,
					"<init>",
					"(Lplaid/fastruntime/PlaidObject;Lplaid/fastruntime/PlaidObject;)V",
					null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>",
			"()V");
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitFieldInsn(PUTFIELD,
					"plaid/fastruntime/dcg/test/PlaidStorageExample", "x",
			"Lplaid/fastruntime/PlaidObject;");
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 2);
			mv.visitFieldInsn(PUTFIELD,
					"plaid/fastruntime/dcg/test/PlaidStorageExample", "y",
			"Lplaid/fastruntime/PlaidObject;");
			mv.visitInsn(RETURN);
			mv.visitMaxs(2, 3);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "getY",
					"()Lplaid/fastruntime/PlaidObject;", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD,
					"plaid/fastruntime/dcg/test/PlaidStorageExample", "y",
			"Lplaid/fastruntime/PlaidObject;");
			mv.visitInsn(ARETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "setY",
					"(Lplaid/fastruntime/PlaidObject;)V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitFieldInsn(PUTFIELD,
					"plaid/fastruntime/dcg/test/PlaidStorageExample", "y",
			"Lplaid/fastruntime/PlaidObject;");
			mv.visitInsn(RETURN);
			mv.visitMaxs(2, 2);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "getX",
					"()Lplaid/fastruntime/PlaidObject;", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD,
					"plaid/fastruntime/dcg/test/PlaidStorageExample", "x",
			"Lplaid/fastruntime/PlaidObject;");
			mv.visitInsn(ARETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}
