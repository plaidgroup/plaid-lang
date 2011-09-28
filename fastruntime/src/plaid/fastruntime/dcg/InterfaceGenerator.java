package plaid.fastruntime.dcg;

import static plaid.fastruntime.dcg.NamingConventions.getGeneratedInterfaceInternalName;
import static plaid.fastruntime.dcg.NamingConventions.getGeneratedInterfaceName;
import static plaid.fastruntime.dcg.NamingConventions.getIdentifierName;
import static plaid.fastruntime.dcg.NamingConventions.getGeneratedInterfaceFilePath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class InterfaceGenerator implements Opcodes{
	
	private static final String METHOD_DESCRIPTOR = "(Lplaid/fastruntime/PlaidObject;)Lplaid/fastruntime/PlaidObject;";
	
	public void createInterface(String methodName, int numargs) {
		//TODO: Add interface caching and check if interface is in cache
		
		//check if interface already exists in file system
		try {
			this.getClass().getClassLoader().loadClass(getGeneratedInterfaceName(methodName));
			System.out.println("Interface already exists");
			//TODO: Check if interface exists without catching an exception
		} catch(ClassNotFoundException e) {
			final String className =  getGeneratedInterfaceInternalName(methodName);
			
			//TODO: See if it is okay to use 0 as argument to ClassWriter constructor when generating an interface
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS + ClassWriter.COMPUTE_FRAMES);
			
			cw.visit(V1_5, 
					ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, 
					className, null, "java/lang/Object", null);
			cw.visitMethod(ACC_PUBLIC, getIdentifierName(methodName), 
					METHOD_DESCRIPTOR, null, null).visitEnd(); 
			//TODO: Adjust method descriptor to number of arguments
			cw.visitEnd();
			byte[] classBytes = cw.toByteArray();
			File interfaceDir = new File(NamingConventions.GENERATED_INTERFACES_DIR);
			File interfaceClassfile = new File(getGeneratedInterfaceFilePath(methodName));
			try {
				if (!interfaceDir.exists()) {
					interfaceDir.mkdirs();
				}
				interfaceClassfile.createNewFile();
				FileOutputStream fos = new FileOutputStream(interfaceClassfile);
				fos.write(classBytes);
				fos.close();
				System.out.println("Wrote file");
			} 
			catch(FileNotFoundException ex)
		    {
				System.out.println("FileNotFoundException : " + ex);
			}
			catch(IOException ioe)
			{
				System.out.println("IOException : " + ioe);
			}
			
		}
	}
}

