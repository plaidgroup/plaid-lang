package plaid.compiler.javatobytecode;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class _native {
	
	public static boolean invokeJavac(String outputDir, String classpath, List<File> files) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjectsFromFiles(files);

		List<String> optionList = new ArrayList<String>();
		optionList.addAll(Arrays.asList("-target", "1.5"));
		// Set compiler's classpath to be same as the runtime's
		optionList.addAll(Arrays.asList("-classpath", classpath));
		// TODO: Add a separate compiler flag for this.
		optionList.addAll(Arrays.asList("-d", outputDir));
		
		// Invoke the compiler
		CompilationTask task = compiler.getTask(null, null, null, optionList, null, fileObjects);
		return task.call();
	}
}
