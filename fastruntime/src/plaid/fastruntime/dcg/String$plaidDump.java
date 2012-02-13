package plaid.fastruntime.dcg;
import java.util.*;
import org.objectweb.asm.*;
public class String$plaidDump implements Opcodes {

public static byte[] dump () throws Exception {

ClassWriter cw = new ClassWriter(0);
FieldVisitor fv;
MethodVisitor mv;
AnnotationVisitor av0;

cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "plaid/lang/String$plaid", null, "plaid/fastruntime/reference/AbstractPlaidState", new String[] { "plaid/generated/Iplus$plaid$1$plaid", "plaid/generated/Ieqeq$plaid$1$plaid", "plaid/generated/IstartsWith$1$plaid", "plaid/generated/IendsWith$1$plaid", "plaid/generated/ItoLowerCase$0$plaid", "plaid/generated/Isubstring$2$plaid" });

{
mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Lplaid/fastruntime/ObjectValue;)V", null, null);
mv.visitCode();
mv.visitVarInsn(ALOAD, 0);
mv.visitVarInsn(ALOAD, 1);
mv.visitMethodInsn(INVOKESPECIAL, "plaid/fastruntime/reference/AbstractPlaidState", "<init>", "(Lplaid/fastruntime/ObjectValue;)V");
mv.visitInsn(RETURN);
mv.visitMaxs(2, 2);
mv.visitEnd();
}
{
mv = cw.visitMethod(ACC_PUBLIC, "plus$plaid", "(Lplaid/fastruntime/PlaidObject;Lplaid/fastruntime/PlaidObject;)Lplaid/fastruntime/PlaidObject;", null, null);
mv.visitCode();
Label l0 = new Label();
Label l1 = new Label();
Label l2 = new Label();
mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
mv.visitLabel(l0);
mv.visitVarInsn(ALOAD, 1);
mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
mv.visitTypeInsn(CHECKCAST, "java/lang/String");
mv.visitVarInsn(ASTORE, 3);
mv.visitVarInsn(ALOAD, 2);
mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
mv.visitTypeInsn(CHECKCAST, "java/lang/String");
mv.visitVarInsn(ASTORE, 4);
mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
mv.visitInsn(DUP);
mv.visitVarInsn(ALOAD, 3);
mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V");
mv.visitVarInsn(ALOAD, 4);
mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
mv.visitVarInsn(ASTORE, 5);
mv.visitTypeInsn(NEW, "plaid/fastruntime/reference/SimplePlaidJavaObject");
mv.visitInsn(DUP);
mv.visitVarInsn(ALOAD, 0);
mv.visitInsn(ACONST_NULL);
mv.visitVarInsn(ALOAD, 5);
mv.visitMethodInsn(INVOKESPECIAL, "plaid/fastruntime/reference/SimplePlaidJavaObject", "<init>", "(Lplaid/fastruntime/PlaidState;Lplaid/fastruntime/PlaidStorage;Ljava/lang/Object;)V");
mv.visitLabel(l1);
mv.visitInsn(ARETURN);
mv.visitLabel(l2);
mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/Exception"});
mv.visitVarInsn(ASTORE, 3);
mv.visitTypeInsn(NEW, "plaid/fastruntime/errors/PlaidIllegalArgumentException");
mv.visitInsn(DUP);
mv.visitLdcInsn("string concatenation failed");
mv.visitVarInsn(ALOAD, 3);
mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "getCause", "()Ljava/lang/Throwable;");
mv.visitMethodInsn(INVOKESPECIAL, "plaid/fastruntime/errors/PlaidIllegalArgumentException", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;)V");
mv.visitInsn(ATHROW);
mv.visitMaxs(5, 6);
mv.visitEnd();
}
{
mv = cw.visitMethod(ACC_PUBLIC, "substring", "(Lplaid/fastruntime/PlaidObject;Lplaid/fastruntime/PlaidObject;Lplaid/fastruntime/PlaidObject;)Lplaid/fastruntime/PlaidObject;", null, null);
mv.visitCode();
mv.visitInsn(ACONST_NULL);
mv.visitInsn(ARETURN);
mv.visitMaxs(1, 4);
mv.visitEnd();
}
{
mv = cw.visitMethod(ACC_PUBLIC, "toLowerCase", "(Lplaid/fastruntime/PlaidObject;)Lplaid/fastruntime/PlaidObject;", null, null);
mv.visitCode();
mv.visitInsn(ACONST_NULL);
mv.visitInsn(ARETURN);
mv.visitMaxs(1, 2);
mv.visitEnd();
}
{
mv = cw.visitMethod(ACC_PUBLIC, "endsWith", "(Lplaid/fastruntime/PlaidObject;Lplaid/fastruntime/PlaidObject;)Lplaid/fastruntime/PlaidObject;", null, null);
mv.visitCode();
Label l0 = new Label();
Label l1 = new Label();
Label l2 = new Label();
mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
mv.visitLabel(l0);
mv.visitVarInsn(ALOAD, 1);
mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
mv.visitTypeInsn(CHECKCAST, "java/lang/String");
mv.visitVarInsn(ASTORE, 3);
mv.visitVarInsn(ALOAD, 2);
mv.visitTypeInsn(CHECKCAST, "plaid/fastruntime/PlaidJavaObject");
mv.visitMethodInsn(INVOKEINTERFACE, "plaid/fastruntime/PlaidJavaObject", "getJavaObject", "()Ljava/lang/Object;");
mv.visitTypeInsn(CHECKCAST, "java/lang/String");
mv.visitVarInsn(ASTORE, 4);
mv.visitVarInsn(ALOAD, 3);
mv.visitVarInsn(ALOAD, 4);
mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "endsWith", "(Ljava/lang/String;)Z");
mv.visitVarInsn(ISTORE, 5);
mv.visitTypeInsn(NEW, "plaid/fastruntime/reference/SimplePlaidJavaObject");
mv.visitInsn(DUP);
mv.visitVarInsn(ALOAD, 0);
mv.visitInsn(ACONST_NULL);
mv.visitVarInsn(ILOAD, 5);
mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
mv.visitMethodInsn(INVOKESPECIAL, "plaid/fastruntime/reference/SimplePlaidJavaObject", "<init>", "(Lplaid/fastruntime/PlaidState;Lplaid/fastruntime/PlaidStorage;Ljava/lang/Object;)V");
mv.visitLabel(l1);
mv.visitInsn(ARETURN);
mv.visitLabel(l2);
mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/Exception"});
mv.visitVarInsn(ASTORE, 3);
mv.visitTypeInsn(NEW, "plaid/fastruntime/errors/PlaidIllegalArgumentException");
mv.visitInsn(DUP);
mv.visitLdcInsn("string concatenation failed");
mv.visitVarInsn(ALOAD, 3);
mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "getCause", "()Ljava/lang/Throwable;");
mv.visitMethodInsn(INVOKESPECIAL, "plaid/fastruntime/errors/PlaidIllegalArgumentException", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;)V");
mv.visitInsn(ATHROW);
mv.visitMaxs(5, 6);
mv.visitEnd();
}
{
mv = cw.visitMethod(ACC_PUBLIC, "startsWith", "(Lplaid/fastruntime/PlaidObject;Lplaid/fastruntime/PlaidObject;)Lplaid/fastruntime/PlaidObject;", null, null);
mv.visitCode();
mv.visitInsn(ACONST_NULL);
mv.visitInsn(ARETURN);
mv.visitMaxs(1, 3);
mv.visitEnd();
}
{
mv = cw.visitMethod(ACC_PUBLIC, "eqeq$plaid", "(Lplaid/fastruntime/PlaidObject;Lplaid/fastruntime/PlaidObject;)Lplaid/fastruntime/PlaidObject;", null, null);
mv.visitCode();
mv.visitInsn(ACONST_NULL);
mv.visitInsn(ARETURN);
mv.visitMaxs(1, 3);
mv.visitEnd();
}
{
mv = cw.visitMethod(ACC_PUBLIC, "doAddFive", "(Lplaid/fastruntime/PlaidObject;Lplaid/fastruntime/PlaidObject;)Lplaid/fastruntime/PlaidObject;", null, null);
mv.visitCode();
mv.visitVarInsn(ALOAD, 1);
mv.visitVarInsn(ALOAD, 2);
mv.visitMethodInsn(INVOKESTATIC, "plaid/lang/StaticMethod", "doAddFive", "(Lplaid/fastruntime/PlaidObject;Lplaid/fastruntime/PlaidObject;)Lplaid/fastruntime/PlaidObject;");
mv.visitInsn(ARETURN);
mv.visitMaxs(2, 3);
mv.visitEnd();
}
cw.visitEnd();

return cw.toByteArray();
}
}
