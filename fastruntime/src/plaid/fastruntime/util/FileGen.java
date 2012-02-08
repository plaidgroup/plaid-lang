package plaid.fastruntime.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileGen {

	/**
	 * @param qid in directory format, e.g.  java.lang.System should be java/lang/System
	 */
	public static File createOutputFile(String name, String outputDirectory, String code, String qid) {
		String directory, file;
		
		try {
			directory = outputDirectory + System.getProperty("file.separator")+ qid;
			file = name + ".java";
			File outputDir = new File(directory);
			outputDir.mkdirs();
			File output = new File(directory + System.getProperty("file.separator") + file);
			output.createNewFile();
			PrintWriter printOut = new PrintWriter(output);
			printOut.print(code);
			printOut.flush();
			
			return output;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}