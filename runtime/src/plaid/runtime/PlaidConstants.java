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
 
package plaid.runtime;

public final class PlaidConstants {
	private PlaidConstants() {}
	
	public static final String ID_SUFFIX =  "$plaid";
	
	public static final String[] JAVA_KEYWORDS = {
		"abstract", "continue", "for", "new", "switch", 
		"assert", "default", "goto", "package", "synchronized",
		"boolean", "do", "if", "private", "this",
		"break", "double", "implements", "protected", "throw",
		"byte", "else", "import", "public", "throws",
	    "case", "enum", "instanceof", "return", "transient",
	    "catch", "extends", "int", "short", "try",
	    "char", "final", "interface", "static", "void",
	    "class", "finally", "long", "strictfp", "volatile",
	    "const", "float", "native", "super", "while", "true",
	    "false"};
}
