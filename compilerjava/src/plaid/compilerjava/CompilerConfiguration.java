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
import java.util.ArrayList;
import java.util.List;

import plaid.compilerjava.AST.CompilationUnit;

public class CompilerConfiguration {
	private String outputDir = System.getProperty("user.dir");
	private boolean debugMode = false;
	private List<File> inputFiles = new ArrayList<File>();
	private boolean invokeCompiler = true;
	private boolean keepTemporaryFiles = false;
	private boolean verbose = false;
	private boolean copyright = false;
	private boolean printCompilerStackTrace = true;
	private CompilationUnit cu = null;
	
	public CompilerConfiguration() {}
	
	public CompilerConfiguration(CompilerConfiguration other, CompilationUnit cu) {
		this.outputDir = other.outputDir;
		this.debugMode = other.debugMode;
		this.inputFiles = other.inputFiles;
		this.invokeCompiler = other.invokeCompiler;
		this.keepTemporaryFiles = other.keepTemporaryFiles;
		this.verbose = other.verbose;
		this.copyright = other.copyright;
		this.cu = cu;
	}

	public CompilationUnit getCurrentCompilationUnit() {
		return cu;
	}
	
	public String getOutputDir() {
		return outputDir;
	}
	
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	
	public boolean isDebugMode() {
		return debugMode;
	}
	
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	
	public void addInputFile(File file) {
		inputFiles.add(file);
	}
	
	public List<File> getInputFiles() {
		return inputFiles;
	}

	public boolean isInvokeCompiler() {
		return invokeCompiler;
	}

	public void setInvokeCompiler(boolean invokeCompiler) {
		this.invokeCompiler = invokeCompiler;
	}

	public boolean isKeepTemporaryFiles() {
		return keepTemporaryFiles;
	}

	public void setKeepTemporaryFiles(boolean keepTemporaryFiles) {
		this.keepTemporaryFiles = keepTemporaryFiles;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	
	public void setCopyrightHeader(boolean value) {
		copyright = value;
	}
	
	public boolean addCopyrightHeader() {
		return copyright;
	}
	
	public void enableCompilerStackTrace() {
		this.printCompilerStackTrace = true;
	}
	
	public void disableCompilerStackTrace() {
		this.printCompilerStackTrace = false;
	}
	
	public boolean compilerStackTraceEnabled() {
		return this.printCompilerStackTrace;
	}
	
}