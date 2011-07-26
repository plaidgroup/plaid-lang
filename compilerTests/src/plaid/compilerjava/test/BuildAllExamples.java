/**
 * Copyright (c) 2010 The Plaid Group (see AUTHORS file)
 * 
 * This file is part of Plaid Programming Language.
 *
 * Plaid Programming Language is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *  Plaid Programming Language is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package plaid.compilerjava.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.CompilerCore;
import uk.ac.lkl.common.util.testing.LabelledParameterized;

@RunWith(LabelledParameterized.class)
public class BuildAllExamples {
	private List<File> files;
	
	public BuildAllExamples(List<File> files) {
		this.files = files;
	}
	
	private static HashSet<String> skipList = new HashSet<String>();
	
	static{
		skipList.add("destructiveRead"); //Exception at runtime - something to do with destructive read construct
		skipList.add("stateVarsTest");//Implicit this dereferences are not supported.
		skipList.add("tree");//java.lang.RuntimeException: Pattern match exhausted. AssignmentFragment
		skipList.add("turing");//Implicit this dereferences are not supported.
		skipList.add("typeTests");//Method 'root.getTypeDecls' not found in package 'plaid.ast'.
		
		
	}
	
	@Test
	public void compile() throws Exception {
		CompilerConfiguration cc = new CompilerConfiguration();
		cc.setOutputDir("coreOutput");
		for (File f : this.files)
			cc.addInputFile(f);
		cc.setKeepTemporaryFiles(true);
		cc.setInvokeCompiler(false);
		cc.setDebugMode(false);
		cc.setPrettyPrint(true);
		//cc.setVerbose(true);
		String sep = System.getProperty("file.separator");
		cc.addToPlaidPath("bin" + sep);
		
		// TODO: In the long run it's a better idea to provide a pre-compiled stdlib.jar
		//       and include it here.
		cc.addToPlaidPath(".." + sep + "stdlib" + sep + "bin" + sep);
		
		CompilerCore compiler = new CompilerCore(cc);
		compiler.compile();
	}

	@Parameters
	public static Collection<Object[]> inputFiles() {
		Collection<Object[]> results = new ArrayList<Object[]>();
	    String currentdir = System.getProperty("user.dir") + System.getProperty("file.separator"); // should be work space by default
	    File cur = new File(currentdir);
	    assertTrue( cur.isDirectory() );
	    for (File f : cur.listFiles()) {
			if ( f.getName().equals("coreExamples")) {
				List<List<File>> files = new ArrayList<List<File>>();
				findPlaidDirectories(f, files);
				for (List<File> dirFiles : files) {
					if (!dirFiles.isEmpty()) {
						List<File> iFiles = new ArrayList<File>();
						for (int i = 0; i < dirFiles.size(); i ++) 
							iFiles.add(new File(dirFiles.get(i).getAbsolutePath().substring(currentdir.length())));
						results.add(new Object[] { iFiles } );
					}
				}
			}
		}
	    return results;
	}
	
	private static void findPlaidDirectories(File f, List<List<File>> files) {
		assertTrue( f.isDirectory() );
		//collect .plaid files from this directory
		List<File> thisDirFiles = new  ArrayList<File>();
		getPlaidFilesFromDirectory(f,thisDirFiles);
		files.add(thisDirFiles);
		//collect .plaid files from sub directories
		for (File file : f.listFiles()) {
			if ( file.isDirectory()) {
				List<List<File>> subDirFiles = new ArrayList<List<File>>();
				findPlaidDirectories(file, subDirFiles);
				files.addAll(subDirFiles);
			}
		}
	}
	
	private static void getPlaidFilesFromDirectory(File f, List<File> files) {
		assertTrue( f.isDirectory() );
		for (File file : f.listFiles()) {
			if ( file.isFile()  && file.getName().endsWith(".plaid")) {
				files.add(file);
			}
		}
	}
	
	
	private static void findPlaidFile(File f, List<File> files, boolean canSkip) {
		if ( f.isFile() ) {
			if ( f.getName().endsWith(".plaid")) {
				files.add(f);
			}
		} else if ( f.isDirectory() ) {
			if(canSkip && skipList.contains(f.getName())){
				return;
			}
			for (File file : f.listFiles()) {
				findPlaidFile(file, files,canSkip);
			}
		}
	}
	
	private static void findPlaidFile(File f, List<File> files) {
		findPlaidFile(f,files,false);
	}
}
