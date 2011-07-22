package plaid.fastruntime.dcg;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public final class Generator implements Opcodes {
	private int classCounter = 0;
	private final DynClassLoader cl = new DynClassLoader();
	
	public Object createClass(final Map<Class<? extends MethodSpecification>, Class<? extends MethodImplementation>> map) {
		final String name = "plaid/innerClass"+classCounter++;
		Object result = null;
					
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
		
		// generate class
		Collection<String> ifaces = new ArrayList<String>();
		for ( Class<? extends MethodSpecification> i : map.keySet()) {
			ifaces.add(Type.getInternalName(i));
		}
		cw.visit(50,
			     ACC_PUBLIC,
			     name,
			     null,
			     "java/lang/Object",
			     ifaces.toArray(new String[0]));
		
		// add methods 
		for ( Entry<Class<? extends MethodSpecification>, Class<? extends MethodImplementation>> entry : map.entrySet() ) {
			//System.out.println("using interface: " + entry.getKey().getName());
			for ( Method m : entry.getKey().getMethods() ) {
				//System.out.println("add method: " + m.getName());
				MethodVisitor mv;
				mv = cw.visitMethod(ACC_PUBLIC, 
							        m.getName(), 
							        Type.getMethodDescriptor(m),
							        null,
							        null); // TODO: add exception
				mv.visitCode();
				Method target = null;
				for ( Method tm : entry.getValue().getMethods() ) {
					// TODO: should check parameters
					if ( tm.getName().equals(m.getName())) {
						target = tm;
					}
				}
				// add receiver
				//System.out.println("  add receiver");
				mv.visitVarInsn(Opcodes.ALOAD, 0);
				// add parameters
				for (int x = 1; x <= m.getParameterTypes().length ; x++ ) {
					//System.out.println("  add parameter: " + m.getParameterTypes()[x-1]);
					mv.visitVarInsn(Opcodes.ALOAD, x);
				}
				mv.visitMethodInsn(INVOKESTATIC, Type.getInternalName(entry.getValue()), target.getName(), Type.getMethodDescriptor(target));
				mv.visitInsn(RETURN);
				mv.visitMaxs(1+m.getParameterTypes().length, 1+m.getParameterTypes().length);
				mv.visitEnd();			
			}
		}
		
		
		// add constructor
		MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object","<init>", "()V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(1, 1);
		mv.visitEnd();

		// done
		cw.visitEnd();
		//CheckClassAdapter.verify(new ClassReader(cw.toByteArray()), true, new PrintWriter(System.out));

		try {
			result =  cl.createClass(name, cw).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	

	private class DynClassLoader extends ClassLoader {
		public Class<?> createClass(String name, ClassWriter cw) {
			byte[] b = cw.toByteArray();
			return defineClass(name.replace("/", "."), b, 0, b.length);
		}
	}
}
