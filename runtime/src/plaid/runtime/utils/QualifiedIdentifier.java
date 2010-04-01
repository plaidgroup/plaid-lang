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

import java.util.ArrayList;
import java.util.List;

public final class QualifiedIdentifier {
	private List<String> fragments;
	
	public QualifiedIdentifier(String qi) {
		fragments = new ArrayList<String>();
		for ( String f : qi.split("\\.") ) {
			fragments.add(f);
		}
	}
	
	protected QualifiedIdentifier(List<String> fragments) {
		this.fragments = fragments;
	}
	
	public boolean isEmpty() {
		return (fragments.isEmpty())?true:false;
	}
	
	public String getQI() {
		StringBuilder sb = new StringBuilder();
		for ( String f : fragments ) {
			sb.append(f + ".");
		}
		return sb.substring(0, sb.length()-1);
	}
	
	public String getSuffix() {
		if ( !isEmpty() ) {
			return fragments.get(fragments.size()-1);
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public QualifiedIdentifier getPrefix() {
		return new QualifiedIdentifier(fragments.subList(0, fragments.size()-1));
	}

	public List<String> split() {
		return new ArrayList<String>(new ArrayList<String>(fragments));
	}
	
	public QualifiedIdentifier append(String name) {
		List<String> frags = new ArrayList<String>();
		frags.addAll(fragments);
		frags.add(name);
		return new QualifiedIdentifier(frags);
	}

	public QualifiedIdentifier append(QualifiedIdentifier name) {
		List<String> frags = new ArrayList<String>();
		frags.addAll(fragments);
		frags.addAll(name.split());
		return new QualifiedIdentifier(frags);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fragments == null) ? 0 : fragments.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QualifiedIdentifier other = (QualifiedIdentifier) obj;
		if (fragments == null) {
			if (other.fragments != null)
				return false;
		} else if (!fragments.equals(other.fragments))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getQI();
	}
}
