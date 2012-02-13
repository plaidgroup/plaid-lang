package plaid.compiler.tests;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public final class RegressionTests {
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
				fileNames.add(file.getAbsolutePath());
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

		// enable codegen if in codegen sub directory
		if ( !job.directory.getAbsolutePath().toLowerCase().contains("codegen") ){
			commandLine.add("-c");
		}
		
		// enable aeminium if in aeminium sub directory
		if ( job.directory.getAbsolutePath().toLowerCase().contains("aeminium") ){
			commandLine.add("-a");
		}
		
		// debug output
		//commandLine.add("-d");
		//commandLine.add("3");
		
		// add classpath
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
	}
	
	@Parameters
	public static Collection<Object[]> inputFiles() {
		final List<TestJob> jobs = new ArrayList<TestJob>();
		final File cwd = new File(".");
		
		final String examplesPath = cwd.getAbsolutePath() + System.getProperty("file.separator") + "examples";
		jobs.addAll(buildTestJobs(new File(examplesPath), cwd.getAbsolutePath()));

		final String aeminiumExamplesPath = cwd.getAbsolutePath() + System.getProperty("file.separator") + "../AeminiumExamples/pld";
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
