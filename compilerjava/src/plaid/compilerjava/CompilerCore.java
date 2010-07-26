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
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import plaid.compilerjava.AST.CompilationUnit;
import plaid.compilerjava.AST.Decl;
import plaid.compilerjava.AST.FieldDecl;
import plaid.compilerjava.AST.MethodDecl;
import plaid.compilerjava.AST.StateDecl;
import plaid.compilerjava.util.FieldRep;
import plaid.compilerjava.util.MemberRep;
import plaid.compilerjava.util.MethodRep;
import plaid.compilerjava.util.PackageRep;
import plaid.compilerjava.util.StateRep;
import plaid.runtime.PlaidException;
import plaid.runtime.annotations.RepresentsField;
import plaid.runtime.annotations.RepresentsMethod;
import plaid.runtime.annotations.RepresentsState;

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
			
			if(!cc.getInputDir().isEmpty())
				ConvertInputDirToInputFiles(new File(cc.getInputDir()));
			
			System.out.println("compiling " + cc.getInputFiles().size() + " files to " + cc.getOutputDir());
			
			// open the file(s)
			List<CompilationUnit> cus = new ArrayList<CompilationUnit>();
			for (File f : cc.getInputFiles()) {
				System.out.println("parsing " + f.getName() + "...");
				CompilationUnit cu = plaid.compilerjava.ParserCore.parse(new FileInputStream(f));
				cu.setSourceFile(f);
				cus.add(cu);
				
				fileSystemChecks(cu, f.toString());
				
			}
			
			//Build up a representation of plaidpath
			PackageRep plaidpath = new PackageRep("$TOPLEVEL$");
			Stack<File> directoryWorklist = new Stack<File>();
			for (String base : cc.getPlaidpath()) {

				File baseDir = new File(base);
				if (baseDir.getAbsolutePath().contains("..")) { //realtive paths need to be processed
					String absPath = baseDir.getAbsolutePath();
					while (absPath.contains("..")) {
						int dotDotLoc = absPath.indexOf("..");
						String prefix = absPath.substring(0,dotDotLoc-1);  //don't include previous separator
						String suffix = absPath.substring(dotDotLoc + 2);
						String sep = suffix.substring(0,1);
						absPath = prefix.substring(0,prefix.lastIndexOf(sep)) + suffix;  //get rid of .. and previous dir  
					}
					
					baseDir = new File(absPath); //update the file to have the correct path without ".."'s
				
				}
				if (!baseDir.isDirectory()) throw new RuntimeException("plaidpath entry " + base + " is not a directory");
				else {
					directoryWorklist.push(baseDir);
					String absoluteBase = baseDir.getAbsolutePath() + System.getProperty("file.separator");
					File currentDirectory;
					URLClassLoader defLoader = null;
					while(!directoryWorklist.isEmpty()) {
						currentDirectory = directoryWorklist.pop();
						for (File file : currentDirectory.listFiles()) {
							if (file.isFile()) {
								if (file.getName().endsWith(".class")) { //load in the hopes that this is a compiled .plaid file
									String filepath = file.getAbsolutePath();
									String className = filepath.substring(absoluteBase.length(), filepath.length() - 6).replace(System.getProperty("file.separator"), ".");
									if (defLoader == null) {
										URL[] loaderDir = { new URL("file://" + absoluteBase) };
										defLoader = new URLClassLoader(loaderDir, Thread.currentThread().getContextClassLoader());
									}
//									System.out.println("Trying to load \"" + className + "\"...");
//									System.out.println("  filepath = \"" + filepath + "\"");
//									System.out.println("  absoluteBase = \"" + absoluteBase + "\"");
									try {
										Class<?> classRep = defLoader.loadClass(className);
//										if (classRep.getAnnotations().length == 0) System.out.println("Not Finding annotations");
										for (Annotation a : classRep.getAnnotations()) {
											String thePackage = null;
											MemberRep member = null;
											
											if (a instanceof RepresentsField) {
												RepresentsField f = (RepresentsField) a;
												thePackage = f.inPackage();
												member = new FieldRep(f.name());
											} else if (a instanceof RepresentsMethod) {
												RepresentsMethod m = (RepresentsMethod) a;
												thePackage = m.inPackage();
												member = new MethodRep(m.name());
											} else if (a instanceof RepresentsState) {
												RepresentsState s = (RepresentsState) a;
												thePackage = s.inPackage();
												StateRep state = new StateRep(s.name());
												for (String sm : s.members().split(",")) state.addMember(sm);
												member = state;
											}
											if (member != null) { //if this was a plaid generated java file
												if (!className.startsWith(thePackage))
													throw new RuntimeException("Package " + thePackage + "of member " + member.getName() + " does not match file path " + className + ".");
											
												plaidpath.addMember(thePackage, member);
//												System.out.println("added " + thePackage + "." + member.getName());
											}
										}
									} catch (NoClassDefFoundError e) {
										e.printStackTrace();
									}
								}
							} else if (file.isDirectory()) directoryWorklist.add(file);
						}
					}					
				}
			}
			
			//we want to remove the stuff we're trying to compile so that we don't make assumptions based on
			//the previous form of the source files
			//but also want a complete picture for resolving imports and thence QIs
			for (CompilationUnit c : cus) {
				String cPackage = c.getPackageString();
				for (Decl d : c.getDecls()) {
					String memberName = d.getName();
					if (plaidpath.memberExists(cPackage, memberName)) {  //indicate that this is outdated and will be updated soon
						plaidpath.lookupMember(cPackage, memberName).startRecompilation(); 
					} else { //add shell for use in import resolution
						MemberRep newMem = null;
						if (d instanceof FieldDecl) newMem = new FieldRep(memberName);
						else if (d instanceof MethodDecl) newMem = new MethodRep(memberName);
						else if (d instanceof StateDecl) newMem = new StateRep(memberName);
						else throw new RuntimeException("New type of MemberRep not accounted for");
						
						//will be replaced later
						newMem.startRecompilation();
						plaidpath.addMember(cPackage, newMem);
					}
					
				}	
			}
			
			Queue<StateRep> dependants = new LinkedList<StateRep>();
			for (CompilationUnit c : cus) {
				String cPackage = c.getPackageString();
				
				//expand imports
				List<String> declaredMembers = new ArrayList<String>();
				for (Decl d : c.getDecls()) declaredMembers.add(d.getName());
				c.getImports().checkAndExpandImports(plaidpath, declaredMembers, cPackage);
				
				//fill out plaidpath with declared members (shell info only)
				for (Decl d : c.getDecls()) {
					MemberRep rep = d.generateHeader(plaidpath, c.getImports(), cPackage);
					if (rep instanceof StateRep && ((StateRep) rep).hasNeeds()) {
						dependants.add((StateRep) rep);  //keep track of ones we need to return to
					}
					plaidpath.addMember(cPackage, rep);
				}
			}
			
			while (!dependants.isEmpty()) {
				StateRep s = dependants.remove();
				List<String> newNeeds = new ArrayList<String>();
				for (String path : s.getNeeds()) {
					if (plaidpath.memberExists(path)) {
						MemberRep r = plaidpath.lookupMember(path);
						if (r instanceof StateRep) {
							StateRep depState = (StateRep) r;
							s.addMembers(depState.getMembers());
							newNeeds.addAll(depState.getNeeds());
						} else throw new RuntimeException("Something went wrong with dependencies.");
					} else {
						throw new RuntimeException("Required Dependency " + path + " not found.");
					}
				}
				s.setNeeds(newNeeds);  //replace old needs with the new needs
				if (s.hasNeeds()) dependants.add(s);
			}

			// create header output files during a first pass
//			try {
//				if (cc.isVerbose()) System.out.println("GENERATING HEADERS");
//				List<File> allFiles = new ArrayList<File>();
//				for(CompilationUnit cu : cus) {
//					List<File> fileList = cu.generateHeaders(cc);
//					if ( cc.isVerbose() ) {
//						for(File f : fileList) {
//							FileReader fr = new FileReader(f);
//							int charRead;
//							while((charRead = fr.read()) != -1) {
//								System.out.print((char)charRead);
//							}
//						}
//					}
//					allFiles.addAll(fileList);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
			// create the output file
			try {
				if (cc.isVerbose()) System.out.println("GENERATING CODE");
				List<File> allFiles = new ArrayList<File>();
				for(CompilationUnit cu : cus) {
					try {
						System.out.println("generateing code for:\n" + cu);
						List<File> fileList = cu.codegen(cc, plaidpath);
						
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
					} catch (PlaidException p) {
						System.out.println("Error while compiling " + cu.getSourceFile().toString() + ":");
						System.out.println("");
						p.printStackTrace();
					}
				}
				
				if ( !cc.isKeepTemporaryFiles() ) {
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
		System.out.println("PlaidCompiler Version 0.1 [ALPHA]");
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
		System.out.println(" -p|--plaidpath       ';' separated list of locations to search for other plaid resources.");
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
				} else if ( value.equals("-k") || value.equals("--keepTempFiles")) {
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
				} else if ( value.equals("-p") || value.equals("--plaidpath")) {
					if (it.hasNext()) {
						for (String dir : it.next().split(";")) {
							cc.addToPlaidPath(dir);
						}
					} else {
						System.out.println("ERROR: you must specify the plaidpath.");
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
		
		if (cc.getPlaidpath().size() == 0) cc.addToPlaidPath(System.getProperty("user.dir")); //default plaidpath to user directory
		
		return cc;
	}

	private void fileSystemChecks(CompilationUnit cu, String filepath) {
		//Error checking - enforce file conventions
		// all files must be in the directory corresponding to the package
		// file package.plaid can have multiple declarations
		// all other files can have only one declaration which is the same as the filename
		
		// Bug discovered by Aparup:
		// Windows systems, unlike Unix systems, use backslashes as separators.  As this character
		// is used inside a regular expression by String.split(), we have to escape the backslash,
		// otherwise we get a syntax error in our regular expression.  If we are on a Unix system,
		// escaping the slash (\/) does not change the behavior.
		String sep = "\\" + System.getProperty("file.separator");
		String filename = null;
		String directoryPackage = "";
		for (String dir : filepath.split(sep)) {
			if (dir.endsWith(".plaid")) //make sure we're at the end of the list
				filename = dir;
			else
				directoryPackage += dir + ".";
		}
		directoryPackage = directoryPackage.substring(0, directoryPackage.length()-1);
		if (filename == null) throw new RuntimeException("No Plaid file found");
		
		String declaredPackage = cu.getPackageString();
		
		//make sure the packages match
		if (!directoryPackage.endsWith(declaredPackage))
			throw new RuntimeException("File '" + filename + "' in package '" + declaredPackage + 
					"' resides in mismatched directory '" + directoryPackage + "'.");
		
		if (!filename.equals("package.plaid")) { //check that the declaration matches the filename
			String declname = filename.substring(0,filename.length()-6); //*.plaid
			List<Decl> declList = cu.getDecls();
			if (declList.size() > 1) 
				throw new RuntimeException("File '" + filename + "' can only contain the declaration for '" + declname + "'.");
			else if (!declList.get(0).getName().equals(declname))
				throw new RuntimeException("File '" + filename + "' contains mismatched declaration for '" + declList.get(0).getName() + "'." );
		}
		
		
		
		
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
