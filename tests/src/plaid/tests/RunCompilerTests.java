package plaid.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;


@RunWith(LabeledParameterized.class)
public class RunCompilerTests {
	
	private final String testName;
	private final String mainClass;
	private final List<String> mainArgs;
	private final String expectedOutput;
	private final boolean compareOutput;
	private final boolean shouldSucceed;
	private final String errorMessage;
	private final List<String> classPath;
	
	private final static String CONFIG_FILENAME = "testconfig.json";
	private final static String SRC_DIR = "pld";
	private final static String GENERATE_JAVA_DIR = "src";
	private final static String OUTPUT_DIR = "bin";
	
	
	private final static String NAME_KEY ="name";
    private final static String CLASSNAME_KEY = "className";
    private final static String OUTPUT_KEY = "output";
    private final static String FRONTEND_KEY = "frontend";
    private final static String BACKEND_KEY = "backend";
    private final static String TYPECHECK_KEY = "typecheck";
    private final static String AEMINIUM_KEY = "aeminium";
    private final static String CODEGEN_KEY = "codegen";
    private final static String SUCCEEDS_KEY = "succeeds";
    private final static String TESTS_KEY = "tests";
    
    private final static String FILE_SEP = System.getProperty("file.seperator");
    
	private static final List<String> RUNPLAID_CLASSPATH = new ArrayList<String>();
	static {
		try {
			RUNPLAID_CLASSPATH.add(new File("bin").getCanonicalPath());
			RUNPLAID_CLASSPATH.add(new File("../fastruntime/bin").getCanonicalPath());
			RUNPLAID_CLASSPATH.add(new File("../fastruntime/lib/asm-debug-all-3.3.1.jar").getCanonicalPath());
			RUNPLAID_CLASSPATH.add(new File("../fastruntime/lib/functionaljava.jar").getCanonicalPath());
			RUNPLAID_CLASSPATH.add(new File("../generated/bin").getCanonicalPath());
			RUNPLAID_CLASSPATH.add(new File("../frontend/bin").getCanonicalPath());
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static final List<String> COMPILEPLAID_CLASSPATH = new ArrayList<String>();
	static {
		try {
			COMPILEPLAID_CLASSPATH.add(new File("../frontend/bin").getCanonicalPath());
			COMPILEPLAID_CLASSPATH.add(new File("../runtime/bin").getCanonicalPath());
			COMPILEPLAID_CLASSPATH.add(new File("../stdlib/bin").getCanonicalPath());
			COMPILEPLAID_CLASSPATH.add(new File("../ast/bin").getCanonicalPath());
			COMPILEPLAID_CLASSPATH.add(new File("../parser/bin").getCanonicalPath());
			COMPILEPLAID_CLASSPATH.add(new File("../fastruntime/bin").getCanonicalPath());
			COMPILEPLAID_CLASSPATH.add(new File("../fastruntime/lib/asm-debug-all-3.3.1.jar").getCanonicalPath());
			COMPILEPLAID_CLASSPATH.add(new File("../fastruntime/lib/functionaljava.jar").getCanonicalPath());
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * First argument is used as label in junit output.
	 */
	public RunCompilerTests(String testName, String mainClass, 
			List<String> mainArgs, List<String> classPath, String expectedOutput,
			boolean compareOutput, boolean shouldSucceed, String errorMessage) {
		super();
		this.testName = testName;
		this.mainClass = mainClass;
		this.classPath = classPath;
		this.mainArgs = mainArgs;
		this.expectedOutput = expectedOutput;
		this.compareOutput = compareOutput;
		this.shouldSucceed = shouldSucceed;
		this.errorMessage = errorMessage;
	}

	@Test
	public void test() {
		ProcessRunner runner  = new ProcessRunner(this.mainClass, this.classPath, this.mainArgs);
		
		try {
			String actualOutput = runner.run();
			System.out.println(actualOutput);
			if(this.compareOutput) {
				Assert.assertEquals(this.expectedOutput, actualOutput);
			}
		} catch(Throwable e) {
			if(this.shouldSucceed) {
				Assert.fail(this.testName + ": " + e.getMessage());
			}
		} 
	}
	

	private static List<Config> getConfigFiles()
	{
		List<Config> configFiles = new ArrayList<Config>();
		getConfigFilesHelper(new File(SRC_DIR), configFiles);
		System.out.println("Found " + configFiles.size() + " configuration files.");
		return configFiles;
	}
	
	private static void getConfigFilesHelper(File dir, List<Config> configFileList) {
		// System.out.println("Looking in " + dir.getPath());
		File configFile = null;
		List<File> plaidFiles = new ArrayList<File>();
		for(File subFile : dir.listFiles()) {
			if (subFile.isDirectory()) {
				getConfigFilesHelper(subFile, configFileList);
			} else if (subFile.isFile() && subFile.getName() != null &&
					subFile.getName().equals(CONFIG_FILENAME)) {
				System.out.println("Found a configuration file in " + dir.getPath());
				configFile = subFile;
			} else if (subFile.isFile() && subFile.getName() != null &&
					subFile.getName().endsWith(".plaid")) {
				plaidFiles.add(subFile);
			}
		}
		if (configFile != null && plaidFiles.size() > 0) {
			configFileList.add(new Config(configFile, plaidFiles));
		}
	}
	
	private static class Config {
		private final File configFile;
		private final List<File> plaidFiles;
		
		public Config(File configFile, List<File> plaidFiles) {
			super();
			this.configFile = configFile;
			this.plaidFiles = plaidFiles;
		}

		public File getConfigFile() {
			return configFile;
		}

		public List<File> getPlaidFiles() {
			return plaidFiles;
		}
	}
	
	@Parameters
	public static Collection<Object[]> getParams() throws Exception {
		// create collection for parameters
		Collection<Object[]> allParams = new ArrayList<Object[]>();
		

		
		//search for configuration files
		System.out.println("Searching for configuration files");
		List<Config> configs = getConfigFiles();
		for(Config config : configs) {
			File configFile = config.getConfigFile();
			FileInputStream input = new FileInputStream(configFile);
			JSONTokener tokener = new JSONTokener(input);
			JSONObject configObj = new JSONObject(tokener);
			JSONArray frontEndTests = configObj.getJSONArray(FRONTEND_KEY);
			for(int i = 0; i < frontEndTests.length(); i++) {
				JSONObject frontEndTest = frontEndTests.getJSONObject(i);
				
			}
			JSONObject backend = configObj.getJSONObject(BACKEND_KEY);
			//run the compiler main method with the configuration associated with all of the backend tests
			//File plaidSrcDir = configFile.getParentFile();
			//System.out.println("Directory : " + plaidSrcDir.getPath());
			boolean shouldTypecheck = backend.getBoolean(TYPECHECK_KEY);
			boolean shouldAeminium = backend.getBoolean(AEMINIUM_KEY);
			
			//-n -t "/Users/jssunshi/Dev/plaid-lang/tests/src" -b -d 0 -i "/Users/jssunshi/Dev/plaid-lang/tests/pld/plaid/fastexamples/"
			
			ArrayList<String> compilerArgsList = new ArrayList<String>();
			compilerArgsList.add("-d");
			compilerArgsList.add("0");
			compilerArgsList.add("-t");
			compilerArgsList.add(new File("src").getAbsolutePath());
			compilerArgsList.add("-o");
			compilerArgsList.add(new File("bin").getAbsolutePath());
			if (!shouldTypecheck)
				compilerArgsList.add("-n");
			if (!shouldAeminium)
				compilerArgsList.add("-a");
			for(String path : RUNPLAID_CLASSPATH) {
				compilerArgsList.add("-p");
				compilerArgsList.add(path);
			}
			for (File plaidFile : config.getPlaidFiles()) {
				compilerArgsList.add(plaidFile.getAbsolutePath());
			}
			allParams.add(
					getParams("Backend compiling " + configFile.getParentFile().getCanonicalPath(), //testName
							"plaid.compiler.main", //mainClass
							compilerArgsList, //mainArgs
							COMPILEPLAID_CLASSPATH, //classPath
							"", //expectedOutput
							false, //compareOutput
							true, //shouldSucceed
							"") //errorMessage
					);
			
			JSONArray backEndTests = backend.getJSONArray(TESTS_KEY);
			//run the main methods of the given backendTests;
			
			for(int i = 0; i < backEndTests.length(); i++) {
				JSONObject test = backEndTests.getJSONObject(i);	
				
				
				String mainClass = (String)test.get(RunCompilerTests.CLASSNAME_KEY);
				String expectedOutput = (String)test.get(RunCompilerTests.OUTPUT_KEY);
				
				allParams.add(
						getParams(test.getString(NAME_KEY), //testName
								mainClass, //mainClass
								new ArrayList<String>(),//mainArgs
								RUNPLAID_CLASSPATH,//classPath
								expectedOutput,
								true,//compareOutput
								true,//shouldSucceed
								"")//errorMessage
						);
			}
			
			
			
		}
		
		File srcDir = new File(SRC_DIR);
		File[] dirContents = srcDir.listFiles();
		for(File subFile : dirContents) {
			if(subFile.isDirectory()) {
				
			}
		}
		
		return allParams;
	}
	
	private static Object[] getParams(String testName, String mainClass, List<String> mainArgs,
			 List<String> classPath,
			 String expectedOutput, boolean compareOutput, 
			boolean shouldSucceed, String errorMessage) {
		Object[] paramsTest = new Object[8];
		paramsTest[0] = testName; //name
		paramsTest[1] = mainClass; //mainClass
		paramsTest[2] = mainArgs; //mainArgs
		paramsTest[3] = classPath; //classPath
		paramsTest[4] = expectedOutput; //expectedOutput
		paramsTest[5] = compareOutput; //compareOutput
		paramsTest[6] = shouldSucceed; //shouldSucceed;
		paramsTest[7] = errorMessage; //errorMessage
		return paramsTest;
	}
	
	private static class ProcessRunner {
		private final String mainClass;
		private final List<String> classPath;
		private final List<String> mainArgs;
		
		ProcessRunner(String mainClass, List<String> classPath, List<String> mainArgs) {
			this.mainClass = mainClass;
			this.classPath = classPath;
			this.mainArgs = mainArgs;
		}
		
		private String getClassPath() {
			StringBuilder sb = new StringBuilder();
			for (String s : this.classPath ) {
				sb.append(s);
				sb.append(System.getProperty("path.separator"));
			}
			
			return sb.toString() + System.getProperty("java.class.path");
		}
		
		public String run() throws Exception {
			final String classpath = getClassPath();
			final List<String> processArgs = new ArrayList<String>();
			processArgs.add("java");
			processArgs.add("-Xms512m");
			processArgs.add("-Xmx1024m");
			processArgs.add("-cp");
			processArgs.add(classpath);
			processArgs.add(mainClass);
			processArgs.addAll(mainArgs);
			
			// start program 
			ProcessBuilder pb = new ProcessBuilder(processArgs);
			pb.redirectErrorStream(false);
			Process proc = pb.start();
			
			AsyncReader ar = new AsyncReader(proc.getInputStream());
			AsyncReader error = new AsyncReader(proc.getErrorStream());
			ar.start();
			error.start();
			// wait for process 
			proc.waitFor();
			
			// wait for reader
			ar.join();
			error.join();
			String errorOutput = error.getOutput();
			if(errorOutput.length() > 0) {
				throw new TestFailedException(error.getOutput());
			}
			return ar.getOutput();
		}
		
		private class AsyncReader extends Thread {
			private final InputStream stream;
			private final StringBuilder output = new StringBuilder();
			
			AsyncReader(InputStream stream) {
				this.stream = stream;
			}
			
			public String getOutput() {
				return output.toString();
			}
			
			
			@Override
			public void run() {
				try {
					while ( true ) {
						int c = stream.read();
						
						if ( c  == -1 ) { return; }

						output.append(Character.toString((char) c));

					}
				} catch (Exception e) {
					
				}
			}
		}
	}
}