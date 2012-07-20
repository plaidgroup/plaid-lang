package plaid.fastruntime.util;

public class ClassNameCounter {
	private int counter;
	private final String mainDeclName;
	private final String outputDirectory;
	private final String packageDir;
	private final String packageQI;
	private static final String sep = System.getProperty("file.separator");
	
	public ClassNameCounter(String mainDeclName,
			String outputDirectory, String packageDir) {
		super();
		this.counter = 1;
		this.mainDeclName = mainDeclName;
		this.outputDirectory = outputDirectory;
		this.packageDir = packageDir;
		this.packageQI = this.packageDir.replace(sep, ".");
	}
	
	public void writeCode(String classBodyCode) {
		String outputCode = "package " + packageQI + ";\n\n" +
				"public class " + 
				mainDeclName + "$" + counter + "{\n" +
				classBodyCode + 
				"}";
		FileGen.createOutputFile(mainDeclName + "$" + counter, outputDirectory, outputCode, packageDir);
		counter++;
	}
	
	//does not increment the counter
	public String nextFullyQualifiedClassName() {
		return packageQI + "." + mainDeclName + "$" + counter;
	}
	
}
