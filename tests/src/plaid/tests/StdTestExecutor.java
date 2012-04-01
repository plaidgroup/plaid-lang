package plaid.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidState;
import plaid.runtime.PlaidTag;
import plaid.runtime.annotations.RepresentsState;

@RunWith(LabeledParameterized.class)
public class StdTestExecutor {
	protected final static Collection<TestCase> NO_TESTCASES = Collections.emptyList();
	protected final TestCase testCase;
	protected final static ClassLoader cl; static {
		Collection<URL> urls = new ArrayList<URL>();
		for ( String path : System.getProperty("java.class.path").trim().split(System.getProperty("path.separator")) ) {
			try {
				urls.add(new URL("file://" + path));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		cl = new URLClassLoader(urls.toArray(new URL[0]));
	}

	static {
		plaid.runtime.PlaidRuntime.getRuntime().init();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
				plaid.runtime.PlaidRuntime.getRuntime().shutdown();				
			}
		}));
	}
	
	
	public StdTestExecutor(TestCase testCase) throws IOException {
		this.testCase = testCase;
	}

	@Test
	public void run() {
		//System.out.println("Test RUN: " + testCase);
		if ( !testCase.run() ) {
			fail("Failed to run test : " + testCase.toString() );
		}
	}
	
	@Parameters
	public static Collection<TestCase[]> getTests() throws IOException {
		final Collection<TestCase[]> tests = new ArrayList<TestCase[]>();

		for (TestCase tc : findTestFactories() ) {
			tests.add(new TestCase[] {tc});
		}
		
		return tests;
	}
	
	protected static Collection<TestCase> findTestFactories() throws IOException {
		Collection<TestCase> tests = new ArrayList<TestCase>();
		

		for ( String path : System.getProperty("java.class.path").trim().split(System.getProperty("path.separator")) ) {
			File current = new File(path);
			if ( !current.exists() ) continue;
			if ( current.isDirectory() ) {
				if ( current.getAbsolutePath().startsWith(System.getProperty("user.dir"))) {
					findTestFactories(current, current, tests);
				}
			} else if ( current.isFile() && (   current.getCanonicalPath().endsWith(".jar") 
					                         || current.getCanonicalPath().endsWith(".zip")) ) {
				//System.err.println("We do not support JAR files at the moment.");
			} else {
				//System.err.println("Found invalid classpath entry: " + path);
			}
			//System.out.println("path -> " + path);
		}

		return tests;
	}
	
	protected static void findTestFactories(final File root, File file, Collection<TestCase> tests) throws IOException {
		if ( file.isDirectory() ) {
			System.out.println("checking directory " + file.getCanonicalPath());
			for ( File f : file.listFiles() ) {
				findTestFactories(root, f, tests);
			}
		} else if ( file.isFile() && file.getName().endsWith(".class") && !file.getAbsolutePath().contains("$")) {
			//System.out.println(file);
			final String relPath = file.getCanonicalPath().substring(root.getCanonicalPath().length()+1, 
																     file.getCanonicalPath().length() - ".class".length());
			final String binaryName = relPath.replace(System.getProperty("file.separator"), ".");
			tests.addAll(getTestsFromFactory(binaryName));
		}
	}

	protected static Collection<TestCase> getTestsFromFactory(String klazzName) {
		System.out.println("checking " + klazzName + " for tests");
		try {
			Class<?> klazz = cl.loadClass(klazzName);
			if ( klazz != null && klazz.getAnnotation(RepresentsState.class) != null ) {
				// find tag 
				final String tagField = klazz.getSimpleName() + "$Tag$plaid";
				if ( klazz.getField(tagField) != null && Modifier.isStatic(klazz.getField(tagField).getModifiers()) ) {
					PlaidTag fieldTag = (PlaidTag) klazz.getField(tagField).get(null);
					for ( PlaidTag tag : fieldTag.getHierarchy()) {
						if ( tag.getPath().equals("plaid.lang.testing.TestFactory")) {
							System.out.println("\tfound test factory : " + klazzName);
							return loadTestCases(klazzName);
						}
					}
					System.out.println("\tTestFactory is not in state hierarchy");
				} else {
					System.out.println("\tNo $Tag$plaid field, or is not static");
				}
			} else {
				System.out.println("\tCould not load class or no RepresentsState annotation");
			}
		} catch (NoSuchFieldException e) {
			// no tag?
			System.out.println("no $Tag$plaid for class " + klazzName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NO_TESTCASES;
	}
	
	protected static Collection<TestCase> loadTestCases(String klazzName) {
		Collection<TestCase> tests = new ArrayList<TestCase>();

		try {
			PlaidObject obj = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().loadClass(klazzName);
			PlaidObject listConverter = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().loadClass("plaid.lang.globals.plaidListToJavaList");
			if ( obj instanceof PlaidState ) {
				//System.out.println("got state for " + klazzName);
				PlaidObject instance = ((PlaidState)obj).instantiate();
				PlaidObject createTests = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("createTests", instance);
				PlaidObject result = plaid.runtime.Util.call(createTests, plaid.runtime.Util.unit());
				PlaidObject convertedList = plaid.runtime.Util.call(listConverter, result);
				if ( convertedList instanceof PlaidJavaObject) {
					PlaidJavaObject  javaList = (PlaidJavaObject)convertedList; 
					@SuppressWarnings("unchecked")
					Collection<PlaidObject> testCaseList = (Collection<PlaidObject>)(javaList.getJavaObject());
					System.out.println(testCaseList.size() + " elements in test case list");
					for (PlaidObject po : testCaseList ) {
						PlaidObject getName = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("getName", po);
						PlaidObject poName = plaid.runtime.Util.call(getName, plaid.runtime.Util.unit());
						if ( poName instanceof PlaidJavaObject ) {
							tests.add(new TestCase(((PlaidJavaObject)poName).getJavaObject().toString(), po));
							//System.out.println("found TestCase : " + ((PlaidJavaObject)poName).getJavaObject().toString());
						} else {
							System.out.println("\tCould not convert object in test list into a TestCase, it was a " + poName.getClass().getName());
						}
					}
				} else {
					System.out.println("\tCould not convert result of calling createTests to a list");
				}
			} else {
				System.out.println(klazzName + " could not be loaded as a plaid type");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("\t found " + tests.size() + " tests");
		return tests;
	}
	
	protected static class TestCase {
		protected final PlaidObject pobj;
		protected final String name;
		
		TestCase(String name, PlaidObject pobj) {
			this.name = name;
			this.pobj = pobj;
		}
		
		public boolean run() {
			try {
				PlaidObject run = plaid.runtime.PlaidRuntime.getRuntime().getClassLoader().lookup("run", pobj);
				PlaidObject result = plaid.runtime.Util.call(run, plaid.runtime.Util.unit());
				if ( result instanceof PlaidObject ) {
					for ( PlaidTag tag : result.getTags().keySet() ) {
						if ( tag.getPath().equals("plaid.lang.True")) {
							return true;
						}
					}
					return false;
				} else {
					return false;
				}
			} catch (Exception ex) {
				ex.printStackTrace(System.out);
				return false;
			}
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
}
