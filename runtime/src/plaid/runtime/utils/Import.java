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
 
package plaid.runtime.utils;

public class Import {
	private QualifiedIdentifier qid;
	
	public Import(String im) {
		qid = new QualifiedIdentifier(im);
	}
	
	public boolean isStar() {
		if ( qid.getSuffix().equals("*")) {
			return true;
		} else {
			return false;
		}
	}
	
	public QualifiedIdentifier getIdent() {
		if ( isStar() ) {
			return qid.getPrefix();
		} else {
			return qid;
		}
 	}

	@Override
	public String toString() {
		return "Import(" + qid.toString() + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Import))
			return false;
		return this.qid.equals(((Import)o).qid);
	}
	
	@Override
	public int hashCode() {
		return this.qid.hashCode();
	}
}

