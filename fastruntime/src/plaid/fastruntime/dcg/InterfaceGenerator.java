package plaid.fastruntime.dcg;

import static plaid.fastruntime.NamingConventions.getGeneratedInterfaceFilePath;
import static plaid.fastruntime.NamingConventions.getGeneratedInterfaceInternalName;

import java.io.File;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import plaid.fastruntime.NamingConventions;

public class InterfaceGenerator implements Opcodes{
	
	/**
	 * Generates an interface with exactly one method according to the naming conventions listed
	 * in the NamingConventions class. 
	 * @param methodName the method to include in the interface
	 * @param numargs, the number of arguments taken by the method including the receiver if applicable
	 */
	public void saveInterfaceFile(String methodName, int numargs) {
		//TODO: Add interface caching and check if interface is in cache

		byte[] interfacebytes = createInterfaceAsBytes(methodName, numargs);
		ClassFileWriter.writeFile(interfacebytes, 
				new File(NamingConventions.GENERATED_DIR), 
				new File(getGeneratedInterfaceFilePath(methodName, numargs)));
	}

	public byte[] createInterfaceAsBytes(String methodName, int numargs) {
		final String className =  getGeneratedInterfaceInternalName(methodName, numargs);
		
		ClassWriter cw = new ClassWriter(0);
		
		cw.visit(V1_5, 
				ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, 
				className, null, "java/lang/Object", null);
		cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, methodName, 
				NamingConventions.getMethodDescriptor(numargs+1), 
				null, null).visitEnd();
		cw.visitEnd();
		return cw.toByteArray();
	}
	
	public synchronized Class<?> createInterfaceAsClass (String methodName, int numargs) {
		final String className =  getGeneratedInterfaceInternalName(methodName, numargs);
		final String qualifiedName = className.replace("/", "."); 
		try {
			ClassLoader cl = this.getClass().getClassLoader();
			Class<?> interfaceClass = cl.loadClass(qualifiedName);
			//System.out.println(qualifiedName + " interface already exists");
			return interfaceClass;
		} catch(ClassNotFoundException e) {
			//System.out.println(qualifiedName + " interface doesn't exist");
			byte[] interfacebytes = createInterfaceAsBytes(methodName, numargs);
			Class<?> interfaceClass = ClassInjector.defineClass(qualifiedName, interfacebytes, 0, interfacebytes.length);
			return interfaceClass;
		}
	}
}

