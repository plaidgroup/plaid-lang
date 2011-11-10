package plaid.fastruntime.dcg;

import static plaid.fastruntime.dcg.NamingConventions.getGeneratedInterfaceFilePath;
import static plaid.fastruntime.dcg.NamingConventions.getGeneratedInterfaceInternalName;
import static plaid.fastruntime.dcg.NamingConventions.getGeneratedInterfaceName;
import static plaid.fastruntime.dcg.NamingConventions.getIdentifierName;

import java.io.File;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

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
					new File(NamingConventions.GENERATED_INTERFACES_DIR), 
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
		byte[] interfacebytes = createInterfaceAsBytes(methodName, numargs);
		return DynamicClassLoader.DYNAMIC_CLASS_LOADER.createClass(className, interfacebytes);
	}
}

