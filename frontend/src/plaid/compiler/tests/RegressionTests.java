package plaid.compiler.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public final class RegressionTests {
	private static String CONFIG_FILENAME       = "run.properties";
	private static String MAIN_CLASS            = "Main-Class";
	private static String CLASS_PATH            = "Class-Path";
	private static String EXPECTED_OUTPUT_VALUE = "Expected-Output-Value";
	private static String EXPECTED_OUTPUT_FILE  = "Expected-Output-File";

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
				System.out.println("run");
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
	
	private static class TestJob {
		private final File directory;
		private final Collection<File> files = new HashSet<File>();
		private final List<String> classpath = new LinkedList<String>();
		
		public TestJob(File directory, Collection<File> files) {
			this.directory = directory;
			this.files.addAll(files);
		}
		
		public List<String> getClasspath() {
			return classpath;
		}
		
		public void addClasspath(String path) {
			classpath.add(path);
		}
		
		public Collection<String> getFileNames() {
			final Collection<String> fileNames = new ArrayList<String>();
			
			for ( File file : files ) {
				try {
					fileNames.add(file.getCanonicalPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return fileNames;
		}
		
		public String toString() {
			return "TestJob(" + this.directory.getAbsolutePath() + ")";
		}
	}
	
	private final TestJob job;
	
	public RegressionTests(final TestJob job) {
		this.job = job;
	}
	
	protected String[] createCommandLine() {
		List<String> commandLine = new ArrayList<String>();
		
		// enable codegen if in codegen sub directory (no typecheck for this code)
		if ( !job.directory.getAbsolutePath().toLowerCase().contains("codegen") ){
			commandLine.add("-c");
		} else
			commandLine.add("-n");
		
		// enable aeminium if in aeminium sub directory
		if ( job.directory.getAbsolutePath().toLowerCase().contains("aeminium") ){
			commandLine.add("-a");
		}
		
		// debug output
		//commandLine.add("-d");
		//commandLine.add("3");
		
		// add classpath
		commandLine.add("-p");
		commandLine.add("../generated/bin");
		commandLine.add("-p");
		commandLine.add("../faststdlib/bin");
		commandLine.add("-p");
		commandLine.add("../fastruntime/bin");
		commandLine.add("-p");
		commandLine.add("../faststdlib/pld");
		for ( String path : job.getClasspath() ) {
			commandLine.add("-p");
			commandLine.add(path);
		}
		
		// add input files 
		commandLine.addAll(job.getFileNames());
		
		return commandLine.toArray(new String[0]);
	}
	
	@Test
	public void runTest() throws Exception {
	    final String[] args = createCommandLine();
	    final boolean failing = job.directory.getAbsolutePath().toLowerCase().contains("failing");
	    System.out.println("Compile " + this.job.toString() + "  " + Arrays.toString(args));
	    
	    try {
	    	plaid.compiler.main.main(args);
	    	if ( failing == true ) {
	    		Assert.fail();
	    	}
	    } catch (Throwable e) {
	    	plaid.runtime.PlaidRuntime.getRuntime().shutdown();
	    	if ( failing == false ) {
		    	System.out.println("FAILED " + e.toString());
	    	    Assert.fail();
	    	}
	    } finally {
	    	System.gc();
	    }
	    
		// try to execute commands if we generated code for them 
	    // TODO: remove false to activate this code 
		if ( false ){
			Properties config = new Properties();
			config.load(new FileInputStream(job.directory + System.getProperty("file.separator") + CONFIG_FILENAME));
			
			final String mainClass = config.getProperty(MAIN_CLASS).trim();
			System.out.println(mainClass);
			
			String expectedOuput = "";
			if ( config.getProperty(EXPECTED_OUTPUT_VALUE) != null ) {
				expectedOuput = config.getProperty(EXPECTED_OUTPUT_VALUE);
			} else if (config.getProperty(EXPECTED_OUTPUT_FILE) != null) {
				FileReader fr = new FileReader(new File(job.directory + System.getProperty("file.separator") + config.getProperty(EXPECTED_OUTPUT_FILE).trim() ));
				
				StringBuilder sb = new StringBuilder();
				int c =  fr.read();
				while ( c != -1 ) {
					sb.append(Character.toString((char)c));
					c = fr.read();
				}
				expectedOuput = sb.toString();
			}
			System.out.println("Expected Output '" + expectedOuput +"'");

			List<String> classPath = job.getClasspath();
			if ( config.getProperty(CLASS_PATH) != null ) {
				classPath.add(0, config.getProperty(CLASS_PATH).trim());
			}				
				
			ProcessRunner pr = new ProcessRunner(mainClass, classPath);
			final String output = pr.run();
			System.out.println("Test Output     '" + output +"'");
			
			if ( expectedOuput.equals(output) == false ) {
				Assert.fail("Expetced output does not match test output");
			}
		}
	}
	
	@Parameters
	public static Collection<Object[]> inputFiles() throws IOException {
		final List<TestJob> jobs = new ArrayList<TestJob>();
		final File cwd = new File(".").getCanonicalFile();
		
		final String examplesPath = cwd.getCanonicalPath() + System.getProperty("file.separator") + "examples";
		jobs.addAll(buildTestJobs(new File(examplesPath), cwd.getCanonicalPath()));

		final String aeminiumExamplesPath = new File(cwd.getCanonicalPath() + System.getProperty("file.separator") + "../AeminiumExamples/pld").getCanonicalPath();
		jobs.addAll(buildTestJobs(new File(aeminiumExamplesPath), aeminiumExamplesPath));
		
		final List<Object[]> parameters = new ArrayList<Object[]>();
		
		for ( TestJob job : jobs) {
			final Object[] arg = { job };
			parameters.add(arg);
		}
		
		Collections.sort(parameters, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				return String.CASE_INSENSITIVE_ORDER.compare(((TestJob)o1[0]).directory.getAbsolutePath(), 
															 ((TestJob)o2[0]).directory.getAbsolutePath());
			}
		}); 
		
		return parameters;
	}
	
	protected static List<TestJob> buildTestJobs(final File file, final String classpath) {
		final List<TestJob> jobs = new ArrayList<TestJob>();
		
		if ( file.exists() ) {
			final Collection<File> subDirs = subDirectories(file);
			if ( subDirs.isEmpty() ) {
				// try to find Plaid file and create jobs 
				File[] plaidFiles = file.listFiles(new FilenameFilter() {
				    public boolean accept(File dir, String name) {
				        return name.endsWith(".plaid");
				    }
				});
				if ( plaidFiles.length > 0 ) {
					final TestJob job = new TestJob(file, Arrays.asList(plaidFiles));
					job.addClasspath(classpath);
					jobs.add(job);
				}
			} else {
				// search sub directories for jobs
				for ( File subDir : subDirs ) {
					jobs.addAll(buildTestJobs(subDir, classpath));
				}
			}
		}
		
		return jobs;
	}
	
	protected static Collection<File> subDirectories(File dir) {
		final Collection<File> subDirs = new ArrayList<File>();
		
		for ( File file : dir.listFiles() ) {
			if ( file.isDirectory() ) {
				subDirs.add(file);
			}
		}
		
		return subDirs;
	}
}
