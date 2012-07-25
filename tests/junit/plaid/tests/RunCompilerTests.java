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
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import plaid.fastruntime.NamingConventions;


@RunWith(LabeledParameterized.class)
public class RunCompilerTests {
	
	private final String testName;
	private final String mainClass;
	private final List<String> mainArgs;
	private final String expectedOutput;
	private final boolean compareOutput;
	private final boolean shouldSucceed;
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
    private final static String ERRORS_KEY = "errors";
    private final static String FILE_KEY = "file";
    private final static String CODE_KEY = "code";
    private final static String LINE_KEY = "line";
    
    
	private static final List<String> RUNPLAID_CLASSPATH = new ArrayList<String>();
	static {
		try {
			RUNPLAID_CLASSPATH.add(new File(OUTPUT_DIR).getCanonicalPath());
			RUNPLAID_CLASSPATH.add(new File("../fastruntime/bin").getCanonicalPath());
			RUNPLAID_CLASSPATH.add(new File("../fastruntime/lib/asm-debug-all-3.3.1.jar").getCanonicalPath());
			RUNPLAID_CLASSPATH.add(new File("../generated/bin").getCanonicalPath());
			RUNPLAID_CLASSPATH.add(new File("../frontend/bin").getCanonicalPath());
			RUNPLAID_CLASSPATH.add(new File("../faststdlib/bin").getCanonicalPath());
			
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static final List<String> BYTECODE_CLASSPATH = new ArrayList<String>();
	static {
		try {
			BYTECODE_CLASSPATH.add(new File(OUTPUT_DIR).getCanonicalPath());
			BYTECODE_CLASSPATH.add(new File("../generated/bin").getCanonicalPath());
			BYTECODE_CLASSPATH.add(new File("../faststdlib/bin").getCanonicalPath());
			BYTECODE_CLASSPATH.add(new File("../fastruntime/bin").getCanonicalPath());
			
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
	
	private static final List<String> PLAID_PATH = new ArrayList<String>();
	static {
		try {
			PLAID_PATH.add(new File("../faststdlib/pld").getCanonicalPath());
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
			boolean compareOutput, boolean shouldSucceed) {
		super();
		this.testName = testName;
		this.mainClass = mainClass;
		this.classPath = classPath;
		this.mainArgs = mainArgs;
		this.expectedOutput = expectedOutput;
		this.compareOutput = compareOutput;
		this.shouldSucceed = shouldSucceed;
	}

	@Test
	public void test() {
		ProcessRunner runner  = new ProcessRunner(this.mainClass, this.classPath, this.mainArgs);
		
		try {
			String actualOutput = runner.run();
			if(this.compareOutput) {
				String reformattedExpectedOutput = this.expectedOutput.replaceAll("\n", System.getProperty("line.separator"));
				Assert.assertEquals(reformattedExpectedOutput, actualOutput);
			}
		} catch(Throwable e) {
			if(this.shouldSucceed) {
				Assert.fail(this.testName + ": " + e.getMessage());
			}
		} 
	}
	

	private static List<Config> getConfigFiles(String srcdir)
	{
		List<Config> configFiles = new ArrayList<Config>();
		getConfigFilesHelper(new File(srcdir), configFiles);
		return configFiles;
	}
	
	private static void getConfigFilesHelper(File dir, List<Config> configFileList) {
		File configFile = null;
		List<File> plaidFiles = new ArrayList<File>();
		for(File subFile : dir.listFiles()) {
			if (subFile.isDirectory()) {
				getConfigFilesHelper(subFile, configFileList);
			} else if (subFile.isFile() && subFile.getName() != null &&
					subFile.getName().equals(CONFIG_FILENAME)) {
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
		
		File masterConfig = new File("config.json");
		FileInputStream masterInput = new FileInputStream(masterConfig);
		JSONTokener masterTokener = new JSONTokener(masterInput);
		JSONArray masterArray = new JSONArray(masterTokener);
		List<Config> configs = new ArrayList<Config>();
		for(int count = 0; count < masterArray.length(); count++) {		
			String srcdir = masterArray.getString(count);
			configs.addAll(getConfigFiles(srcdir));
		}
		
		System.out.println("Found " + configs.size() + " configuration files.");
		for(Config config : configs) {
			File configFile = config.getConfigFile();
			FileInputStream input = new FileInputStream(configFile);
			JSONTokener tokener = new JSONTokener(input);
			JSONObject configObj = new JSONObject(tokener);
			
			if (configObj.has(FRONTEND_KEY)) {
				JSONArray frontEndTests = configObj.getJSONArray(FRONTEND_KEY);
				
				for(int i = 0; i < frontEndTests.length(); i++) {
					JSONObject frontEndTest = frontEndTests.getJSONObject(i);
					
					boolean shouldTypecheck = frontEndTest.getBoolean(TYPECHECK_KEY);
					boolean shouldCodeGen = frontEndTest.getBoolean(CODEGEN_KEY);
					boolean shouldAeminium = frontEndTest.getBoolean(AEMINIUM_KEY);
					String output = "Compilation FAILED!\n";
					if (frontEndTest.getBoolean(SUCCEEDS_KEY)) {
						output = "Compilation Succeeded!\n";
					}
					if (frontEndTest.has(ERRORS_KEY)) {
						output = errorsOutput(frontEndTest.getJSONArray(ERRORS_KEY)) + output;
						
					}
					
					//-n -t "/Users/jssunshi/Dev/plaid-lang/tests/src" -b -d 0 -i "/Users/jssunshi/Dev/plaid-lang/tests/pld/plaid/fastexamples/"
					
					ArrayList<String> compilerArgsList = new ArrayList<String>();
					compilerArgsList.add("-d");
					compilerArgsList.add("0");
					compilerArgsList.add("-t");
					compilerArgsList.add(new File(GENERATE_JAVA_DIR).getAbsolutePath());
					compilerArgsList.add("-o");
					compilerArgsList.add(new File(OUTPUT_DIR).getAbsolutePath());
					if (!shouldTypecheck)
						compilerArgsList.add("-n");
					if (!shouldCodeGen)
						compilerArgsList.add("-c");
					if (shouldAeminium)
						compilerArgsList.add("-a");
					compilerArgsList.add("-e"); //concise errors
					for(String path : PLAID_PATH) {
						compilerArgsList.add("-p");
						compilerArgsList.add(path);
					}
					for(String path : BYTECODE_CLASSPATH) {
						compilerArgsList.add("-bc");
						compilerArgsList.add(path);
					}
					for (File plaidFile : config.getPlaidFiles()) {
						compilerArgsList.add(plaidFile.getAbsolutePath());
					}
					allParams.add(
							getParams(frontEndTest.getString(NAME_KEY), //testName
									"plaid.compiler.main", //mainClass
									compilerArgsList, //mainArgs
									COMPILEPLAID_CLASSPATH, //classPath
									output, //expectedOutput
									true, //compareOutput
									true) //shouldSucceed
							);
					
				}
				
			}
			if (configObj.has(BACKEND_KEY)) {
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
				compilerArgsList.add(new File(GENERATE_JAVA_DIR).getAbsolutePath());
				compilerArgsList.add("-o");
				compilerArgsList.add(new File(OUTPUT_DIR).getAbsolutePath());
				if (!shouldTypecheck)
					compilerArgsList.add("-n");
				if (shouldAeminium)
					compilerArgsList.add("-a");
				compilerArgsList.add("-e"); //concise errors
				for(String path : PLAID_PATH) {
					compilerArgsList.add("-p");
					compilerArgsList.add(path);
				}
				for(String path : BYTECODE_CLASSPATH) {
					compilerArgsList.add("-bc");
					compilerArgsList.add(path);
				}
				for (File plaidFile : config.getPlaidFiles()) {
					compilerArgsList.add(plaidFile.getAbsolutePath());
				}
				//NOTE: test will fail if there are warnings.
				allParams.add(
						getParams("Backend compiling " + configFile.getParentFile().getCanonicalPath(), //testName
								"plaid.compiler.main", //mainClass
								compilerArgsList, //mainArgs
								COMPILEPLAID_CLASSPATH, //classPath
								"Compilation Succeeded!\n", //expectedOutput
								true, //compareOutput
								true) //shouldSucceed
						);
				
			
			
				JSONArray backEndTests = backend.getJSONArray(TESTS_KEY);
				//run the main methods of the given backendTests;
				
				for(int i = 0; i < backEndTests.length(); i++) {
					JSONObject test = backEndTests.getJSONObject(i);	
					
					
					String mainClass = NamingConventions.getGeneratedFQN((String)test.get(RunCompilerTests.CLASSNAME_KEY));
					String expectedOutput = (String)test.get(RunCompilerTests.OUTPUT_KEY);
					
					allParams.add(
							getParams(test.getString(NAME_KEY), //testName
									mainClass, //mainClass
									new ArrayList<String>(),//mainArgs
									RUNPLAID_CLASSPATH,//classPath
									expectedOutput,
									true,//compareOutput
									true)//shouldSucceed
							);
				}
			
			
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
			boolean shouldSucceed) {
		Object[] paramsTest = new Object[7];
		paramsTest[0] = testName; //name
		paramsTest[1] = mainClass; //mainClass
		paramsTest[2] = mainArgs; //mainArgs
		paramsTest[3] = classPath; //classPath
		paramsTest[4] = expectedOutput; //expectedOutput
		paramsTest[5] = compareOutput; //compareOutput
		paramsTest[6] = shouldSucceed; //shouldSucceed;
		return paramsTest;
	}
	
	public static String errorsOutput(JSONArray errorsArray) throws JSONException {
		String toRet = "";
		for (int i = 0 ; i < errorsArray.length(); i ++) {
			JSONObject error = errorsArray.getJSONObject(i);
			toRet = toRet + error.getString(FILE_KEY) +
							":" + error.getInt(LINE_KEY) +
							"->" + error.getString(CODE_KEY) + "\n";
			
		}
		
		return toRet;
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