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
 
package plaid.compilerjava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import plaid.compilerjava.AST.CompilationUnit;

public class CompilerCore {
	private CompilerConfiguration cc;
	
	public CompilerCore(CompilerConfiguration cc) {
		this.cc = cc;
	}
	
	
	public List<CompilationUnit> compile() throws FileNotFoundException {
		try {
			
			if (cc.getInputFiles().size() > 0 && !cc.getInputDir().isEmpty()) {
				throw new RuntimeException("Cannot compile a directory and input files"); //TODO: throw PlaidCompilerException
			}
			//TODO In compilation check if input files are plaid files, check if they are in the right directory
			
			if(!cc.getInputDir().isEmpty())
				ConvertInputDirToInputFiles(new File(cc.getInputDir()));
			
			if ( cc.getInputFiles().size() == 1) {
				System.out.println("compiling " + cc.getInputFiles().get(0).getName());
			} else if  (cc.getInputFiles().size() > 1){
				System.out.println("compiling " + cc.getInputFiles().size() + " files");
			}
			
			// open the file
			List<CompilationUnit> cus = new ArrayList<CompilationUnit>();
			for (File f : cc.getInputFiles()) {
				CompilationUnit cu = plaid.compilerjava.ParserCore.parse(new FileInputStream(f));
				cu.setSourceFile(f);
				cus.add(cu);
			}
			
			// create the output file
			try {
				if (cc.isVerbose()) System.out.println("GENERATING CODE");
				List<File> allFiles = new ArrayList<File>();
				for(CompilationUnit cu : cus) {
					List<File> fileList = cu.codegen(cc);
					if ( cc.isVerbose() ) {
						for(File f : fileList) {
							FileReader fr = new FileReader(f);
							int charRead;
							while((charRead = fr.read()) != -1) {
								System.out.print((char)charRead);
							}
						}
					}
					allFiles.addAll(fileList);
				}
				
				if ( cc.isKeepTemporaryFiles() == false ) {
					for( File f : allFiles ) {
						f.deleteOnExit();
					}
				}
				
				if ( cc.isInvokeCompiler() ) {
					JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
					StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
					Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjectsFromFiles(allFiles);
	
					// invoke the compiler
					CompilationTask task = compiler.getTask(null, null, null, null, null, fileObjects);
					Boolean resultCode = task.call();
					if (cc.isVerbose()) System.out.println("result = " + resultCode.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return cus;
		} catch (Exception e) {
			if (cc.compilerStackTraceEnabled()) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			else {
				System.err.println(e.getMessage());
				System.err.println("Enable stack trace for more information.");
				throw new RuntimeException("Enable the compiler stack trace to get more information.");
			}
		}
    }

	/**
	 * Adds plaid files in this directory and its sub-directories to the compiler
	 * configuration input file list.
	 * @param dir The directory containing plaid files.
	 */
	private void ConvertInputDirToInputFiles(File dir) {
		if (!dir.isDirectory()) {
			throw new RuntimeException("Input directory " + dir.getName() + " is malformed."); 
		}
		for (File file : dir.listFiles()) {
			if (file.isFile() && file.getName().endsWith(".plaid")) {
				cc.addInputFile(file);
			} else if (file.isDirectory()) {
				ConvertInputDirToInputFiles(file);
			}
		}
	}
	
	private static void version() {
		System.out.println("PlaidCompiler Version 0.1 [ALPHPA]");
	}
	
	private static void usage() {
		// TODO: add output options
		System.out.println("usage: plaidc [OPTIONS] <FILES>");
		System.out.println("");
		System.out.println("Options:");
		System.out.println(" -h|--help            This message.");
		System.out.println(" -v|--version         This message.");
		System.out.println(" -o|--output          The direct to put generated files.");
		System.out.println(" -k|--keepTempFiles   Do not delete temporary created files.");
		System.out.println(" -n|--nocompile       Do not compile generated java source.");
		System.out.println(" -g|--debug           Generate debugging information.");
		System.out.println(" -V|--verbose         Verbose compiler output");
		System.out.println(" -d|--directory       Input directory");
		System.out.println("");
	}
	
	private static CompilerConfiguration parseParameters(String args[]) {
		CompilerConfiguration cc = new CompilerConfiguration();

		if ( args.length == 0 ) {
			usage();
			System.exit(-1);
		}
		
		for ( Iterator<String> it = (Arrays.asList(args)).iterator(); it.hasNext();) {
			String value = it.next();
			if ( value.startsWith("-")) {
				if ( value.equals("-h") || value.equals("--help")) {
					usage();
					System.exit(0);
				} else if ( value.equals("-v") || value.equals("--version") ) {
					version();
					System.exit(0);
				} else if ( value.equals("-o") || value.equals("--output")) {
					if ( it.hasNext()) {
						cc.setOutputDir(it.next());
					} else {
						System.out.println("ERROR: you must specify the output directory.");
						usage();
						System.exit(-1);
					}
				} else if ( value.equals("-k") || value.equals("--keekTemp")) {
					cc.setKeepTemporaryFiles(true);
				} else if ( value.equals("-g") || value.equals("--debug")) {
					cc.setDebugMode(true);
				} else if ( value.equals("-V") || value.equals("--verbose")) {
					cc.setVerbose(true);
				} else if (value.equals("-n") || value.equals("-nocompile")) {
					cc.setInvokeCompiler(false);
				}
				else if ( value.equals("-d") || value.equals("--directory")) {
					if ( it.hasNext()) {
						cc.setInputDir(it.next());
					} else {
						System.out.println("ERROR: you must specify the input directory.");
						usage();
						System.exit(-1);
					}
				} else {
					System.out.println("ERROR: found invalid command line option : " + value);
					usage();
					System.exit(-1);
				}
			} else {
				// suck up the files
				File file = new File(value);
				cc.addInputFile(file);
			}
		}
		
		return cc;
	}

	public static void main(String args[]) {
		CompilerConfiguration cc = parseParameters(args);

		CompilerCore c = new CompilerCore(cc);
		try {
			c.compile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
