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

public class PlaidException extends RuntimeException {
	private final String filename;
	private final int line;
	private final int column;

	public PlaidException(String string, Throwable cause) {
		super(PlaidRuntime.getRuntime().getCurrentFilename() + "@" + PlaidRuntime.getRuntime().getCurrentLine() + ":"+ PlaidRuntime.getRuntime().getCurrentColumn()+ " => " + string, cause);
		this.filename = PlaidRuntime.getRuntime().getCurrentFilename();
		this.line     = PlaidRuntime.getRuntime().getCurrentLine();
		this.column   = PlaidRuntime.getRuntime().getCurrentColumn();	
	}
	
	public PlaidException(String string) {
		super(PlaidRuntime.getRuntime().getCurrentFilename() + "@" + PlaidRuntime.getRuntime().getCurrentLine() + ":"+ PlaidRuntime.getRuntime().getCurrentColumn()+ " => " + string);
		this.filename = PlaidRuntime.getRuntime().getCurrentFilename();
		this.line     = PlaidRuntime.getRuntime().getCurrentLine();
		this.column   = PlaidRuntime.getRuntime().getCurrentColumn();
	}

	public PlaidException() {
		super();
		this.filename = PlaidRuntime.getRuntime().getCurrentFilename();
		this.line     = PlaidRuntime.getRuntime().getCurrentLine();
		this.column   = PlaidRuntime.getRuntime().getCurrentColumn();
	}

	/**
	 * Support serialization.
	 */
	private static final long serialVersionUID = 8057942615564974921L;
	
	
	public String getFilename() {
		return filename;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}
}
