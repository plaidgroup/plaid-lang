package plaid.tests;

import java.io.File;
import java.io.FileInputStream;
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
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class RunCompilerTests {
	
	private final String testName;
	private final String expectedOutput;
	private final String actualOutput;
	private final boolean compareOutput;
	private final boolean succeeded;
	private final boolean shouldSucceed;
	private final String errorMessage;
	
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
    
    private final static int NUM_KEYS = 7;
    private final static String FILE_SEP = System.getProperty("file.seperator");
    
    
	
	public RunCompilerTests(String testName, String expectedOutput,
			String actualOutput, boolean compareOutput, boolean succeeded,
			boolean shouldSucceed, String errorMessage) {
		super();
		this.testName = testName;
		this.expectedOutput = expectedOutput;
		this.actualOutput = actualOutput;
		this.compareOutput = compareOutput;
		this.succeeded = succeeded;
		this.shouldSucceed = shouldSucceed;
		this.errorMessage = errorMessage;
	}

	@Test
	public void test() {
		if(this.compareOutput && this.succeeded) {
			Assert.assertEquals(testName + ": Expected output didn't match actual output.\n " +
					"Actual output:\n" + this.actualOutput + "\n" +
					"Expected output:\n" + this.expectedOutput,
					this.expectedOutput, this.actualOutput);
		} else if (this.succeeded == this.shouldSucceed){
			Assert.assertTrue(true);
		} else { // succeeded and shouldSucceed do not match
			Assert.fail(this.errorMessage);
		}
	
	}
	

	private static List<File> getConfigFiles()
	{
		List<File> configFiles = new ArrayList<File>();
		getConfigFilesHelper(new File(SRC_DIR), configFiles);
		System.out.println("Found " + configFiles.size() + " configuration files.");
		return configFiles;
	}
	
	private static void getConfigFilesHelper(File dir, List<File> configFileList) {
		// System.out.println("Looking in " + dir.getPath());
		for(File subFile : dir.listFiles()) {
			if (subFile.isDirectory()) {
				getConfigFilesHelper(subFile, configFileList);
			} else if (subFile.isFile() && subFile.getName() != null &&
					subFile.getName().equals(CONFIG_FILENAME)) {
				System.out.println("Found a configuration file in " + dir.getPath());
				configFileList.add(subFile);
			}
		}
	}
	
	@Parameters
	public static Collection<Object[]> getParams() throws Exception {
		// create collection for parameters
		Collection<Object[]> allParams = new ArrayList<Object[]>();
		
		//setup shared classpath for all tests
		List<String> classPath = new ArrayList<String>();
		classPath.add("bin");
		classPath.add("fastruntime" + FILE_SEP + "bin");
		classPath.add("generated" + FILE_SEP + "bin");
		classPath.add("frontend" + FILE_SEP + "bin");
		
		//search for configuration files
		System.out.println("Searching for configuration files");
		List<File> configFiles = getConfigFiles();
		for(File configFile : configFiles) {
			FileInputStream input = new FileInputStream(configFile);
			JSONTokener tokener = new JSONTokener(input);
			JSONObject config = new JSONObject(tokener);
			JSONArray frontEndTests = config.getJSONArray(FRONTEND_KEY);
			for(int i = 0; i < frontEndTests.length(); i++) {
				JSONObject frontEndTest = frontEndTests.getJSONObject(i);
				
			}
			JSONObject backend = config.getJSONObject(BACKEND_KEY);
			//run the compiler main method with the configuration associated with all of the backend tests
			//File plaidSrcDir = configFile.getParentFile();
			//System.out.println("Directory : " + plaidSrcDir.getPath());
			
			JSONArray backEndTests = backend.getJSONArray(TESTS_KEY);
			//run the main methods of the given backendTests;
			boolean succeeded = true; //TODO: make this false if compiler fails
			String errorMessage = ""; //TODO: write error message if compiler fails
			
			for(int i = 0; i < backEndTests.length(); i++) {
				JSONObject test = backEndTests.getJSONObject(i);	
				Object[] params = new Object[NUM_KEYS];
				
				
				String mainClass = (String)test.get(RunCompilerTests.CLASSNAME_KEY);
				String expectedOutput = (String)test.get(RunCompilerTests.OUTPUT_KEY);
				
				ProcessRunner pr = new ProcessRunner(mainClass, classPath);
				final String actualOutput = pr.run();
				
				params[0] = test.getString(NAME_KEY); //name
				params[1] = expectedOutput; //expectedOutput
				params[2] = actualOutput; //actualOutput
				params[3] = true; //compareOutput
				params[4] = succeeded; //succeeded
				params[5] = true; //shouldSucceed;
				params[6] = errorMessage; //errorMessage
				allParams.add(params);
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
	
	private static class ProcessRunner {
		private final String mainClass;
		private final List<String> classPath;
		
		ProcessRunner(String mainClass, List<String> classPath) {
			this.mainClass = mainClass;
			this.classPath = classPath;
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
			
			// start program 
			ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpath, mainClass);
			pb.redirectErrorStream(true);
			Process proc = pb.start();
			
			AsyncReader ar = new AsyncReader(proc.getInputStream());
			ar.start();
			
			// wait for process 
			proc.waitFor();
			
			// wait for reader
			ar.join();
			
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