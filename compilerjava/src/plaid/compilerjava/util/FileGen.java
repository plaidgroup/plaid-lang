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
 
package plaid.compilerjava.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileGen {

	public static File createOutputFile(String name, String outputDirectory, String code, String qid) {
		String directory, file;
		
		try {
			directory = outputDirectory + System.getProperty("file.separator")+ qid;
			file = name + ".java";
			File outputDir = new File(directory);
			outputDir.mkdirs();
			File output = new File(directory + file);
			output.createNewFile();
			PrintWriter printOut = new PrintWriter(output);
			printOut.print(code);
			printOut.flush();
			
			return output;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static File createOutputFile(String name, String outputDirectory, String code, QualifiedID qid) {
		return createOutputFile(name, outputDirectory, code, qid.toDirectoryName());
	}
}
