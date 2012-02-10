package plaid.fastruntime.dcg;

import static plaid.fastruntime.NamingConventions.getGeneratedInterfaceFilePath;
import static plaid.fastruntime.NamingConventions.getGeneratedInterfaceInternalName;
import static plaid.fastruntime.NamingConventions.getGeneratedInterfaceName;
import static plaid.fastruntime.NamingConventions.getIdentifierName;
import static plaid.fastruntime.dcg.DynamicClassLoader.DYNAMIC_CLASS_LOADER;

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
		
		//check if interface already exists in file system
		try {
			this.getClass().getClassLoader().loadClass(getGeneratedInterfaceName(methodName, numargs));
			System.out.println("Interface already exists");
			//TODO: Check if interface exists without catching an exception
		} catch(ClassNotFoundException e) {
			byte[] interfacebytes = createInterfaceAsBytes(methodName, numargs);
			ClassFileWriter.writeFile(interfacebytes, 
					new File(NamingConventions.GENERATED_DIR), 
					new File(getGeneratedInterfaceFilePath(methodName, numargs)));
		}
	}

	public byte[] createInterfaceAsBytes(String methodName, int numargs) {
		final String className =  getGeneratedInterfaceInternalName(methodName, numargs);
		
		ClassWriter cw = new ClassWriter(0);
		
		cw.visit(V1_5, 
				ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, 
				className, null, "java/lang/Object", null);
		cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, getIdentifierName(methodName), 
				NamingConventions.getMethodDescriptor(numargs), 
				null, null).visitEnd();
		cw.visitEnd();
		return cw.toByteArray();
	}
	
	public Class<?> createInterfaceAsClass (String methodName, int numargs) {
		final String className =  getGeneratedInterfaceInternalName(methodName, numargs);

		try {
			Class<?> interfaceClass = DYNAMIC_CLASS_LOADER.loadClass(className);
			System.out.println(className + " interface already exists");
			return interfaceClass;
		} catch(ClassNotFoundException e) {
			System.out.println(className + " interface doesn't exist");
			byte[] interfacebytes = createInterfaceAsBytes(methodName, numargs);
			Class<?> interfaceClass = DYNAMIC_CLASS_LOADER.createClass(className, interfacebytes);
			return interfaceClass;
		}
	}
}

