package plaid.fastruntime.dcg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClassFileWriter {
	public static void writeFile(byte[] classBytes, File dir, File classfile) {
		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			classfile.createNewFile();
			FileOutputStream fos = new FileOutputStream(classfile);
			fos.write(classBytes);
			fos.close();
		} 
		catch(FileNotFoundException ex)
	    {
			System.out.println("FileNotFoundException : " + ex);
		}
		catch(IOException ioe)
		{
			System.out.println("When trying to write file " + classfile.getName() + " caught IOException : " + ioe.getMessage());
			ioe.printStackTrace(System.out);
		}
	}
}
