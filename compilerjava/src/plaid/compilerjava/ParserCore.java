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
import java.io.InputStream;

import plaid.compilerjava.AST.CompilationUnit;
import plaid.compilerjava.coreparser.ParseException;
import plaid.compilerjava.coreparser.PlaidCoreParser;
import plaid.runtime.PlaidException;

public class ParserCore {
	public static CompilationUnit parse(InputStream toParse) throws PlaidException, ParseException {
		return parse(toParse,null);
	}
	
	public static CompilationUnit parse(InputStream toParse, File sourceFile) throws PlaidException, ParseException {
		//System.out.println("parsing...");
		try {
			PlaidCoreParser pp = new PlaidCoreParser(toParse);
			pp.init(sourceFile);	
			return pp.DeclsStart();		
		} catch (PlaidException e) {
			e.printStackTrace();
			throw e;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}
}