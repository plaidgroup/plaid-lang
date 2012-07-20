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

import java.io.File;

import org.junit.Test;

import plaid.compilerjava.CompilerConfiguration;
import plaid.compilerjava.CompilerCore;


public class CoreParserTests {
	
	private CompilerCore compileFile(String fileLoc, String outputDir) {
		CompilerConfiguration cc = new CompilerConfiguration();
		cc.setOutputDir(outputDir);
		cc.addInputFile(new File(fileLoc));
		cc.addToPlaidPath("bin/");
		cc.setVerbose(true);
		CompilerCore compiler = new CompilerCore(cc);
		return compiler;
	}
	
//	@Test
//	public void helloTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/hello.plaid","coreOutput/");
//		compiler.compile();
//	}
//	
//	@Test
//	public void printIntTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/printInt.plaid","coreOutput/");
//		compiler.compile();
//	}
//
//	@Test
//	public void addFiveTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/addFive.plaid","coreOutput/");
//		compiler.compile();
//	}
//	
//	@Test
//	public void addFiveStateTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/addFiveState.plaid","coreOutput/");
//		compiler.compile();
//	}
//
//	@Test
//	public void pairTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/pair.plaid","coreOutput/");
//		compiler.compile();
//	}
//	
//	@Test
//	public void addXTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/addX.plaid","coreOutput/");
//		compiler.compile();
//	}	
//
//	@Test
//	public void matchTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/simpleMatch.plaid","coreOutput/");
//		compiler.compile();
//	}	
//	
//	@Test
//	public void matcherTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/matcher.plaid","coreOutput/");
//		compiler.compile();
//	}	
//	
//	
//	@Test
//	public void addIntsTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/addInts.plaid","coreOutput/");
//		compiler.compile();
//	}	
//
//	@Test
//	public void localVarTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/localVarTest.plaid","coreOutput/");
//		compiler.compile();
//	}	
//	
//	@Test
//	public void functionTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/helloFunction.plaid","coreOutput/");
//		compiler.compile();
//	}
//
//	@Test
//	public void simpleListTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/simpleList.plaid","coreOutput/");
//		compiler.compile();
//	}
//	
//	@Test
//	public void ifTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/ifTest.plaid","coreOutput/");
//		compiler.compile();
//	}
//	
//	@Test
//	public void importsTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/imports.plaid","coreOutput/");
//		compiler.compile();
//	}
//	
//	@Test
//	public void switchTest() throws Throwable {
//		CompilerCore compiler = compileFile("coreExamples/switchTest.plaid","coreOutput/");
//		compiler.compile();
//	}
	
	@Test
	public void typeTest() throws Throwable {
		CompilerCore compiler = compileFile("coreExamples/typeTests/package.plaid","coreOutput/");
		compiler.compile();
	}
//NEED TO WRITE A REALISTIC LIST EXAMPLE USING PLAID CORE SYNTAX
	
//	@Test
//	public void compilerTest() throws Throwable {
//		CompilerCore compiler = new CompilerCore();
//		String[] strings = new String[2];
//		strings[0] = "coreExamples/list.plaid";
//		strings[1] = "coreOutput/";
//		System.setArgs(strings, 0);
//		compiler.compile();
//	}
	
}