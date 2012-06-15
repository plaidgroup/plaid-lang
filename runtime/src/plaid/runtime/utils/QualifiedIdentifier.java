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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class QualifiedIdentifier {
	private final List<String> fragments;
	private final String qi;
	
	protected static final ConcurrentHashMap<String, QualifiedIdentifier> qiCache = new ConcurrentHashMap<String, QualifiedIdentifier>();
	
	protected QualifiedIdentifier(String name, List<String> fragments) {
		this.qi = name;
		this.fragments = fragments;
	}
	
	public static QualifiedIdentifier getQI(String name) {
		QualifiedIdentifier qi = qiCache.get(name);
		if ( qi == null ) {
			List<String> fragments = new ArrayList<String>();
			for ( String f : name.split("\\.") ) {
				fragments.add(f);
			}
			qi = new QualifiedIdentifier(name, Collections.unmodifiableList(fragments));
			qiCache.putIfAbsent(name, qi);
		}
		return qi;
	}
	
	public static QualifiedIdentifier getQI(List<String> fragments) {
		String name = ""; 
		for(String s : fragments) {
			name += s + ".";
		}
		return getQI(name.substring(0, name.length()-1));
	}
	
	public boolean isEmpty() {
		return fragments.isEmpty();
	}
	
	public String getQI() {
		return qi;
	}
	
	public String getSuffix() {
		if ( !isEmpty() ) {
			return fragments.get(fragments.size()-1);
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public QualifiedIdentifier getPrefix() {
		if (fragments.size() > 1)
			return getQI(fragments.subList(0, fragments.size()-1));
		else
			return getQI(fragments.get(0));
	}

	public List<String> split() {
		return fragments;
	}
	
	public QualifiedIdentifier append(String name) {
		return getQI(qi + "." + name);
	}

	public QualifiedIdentifier append(QualifiedIdentifier name) {
		return getQI(qi+"." + name);
	}
	
	@Override
	public int hashCode() {
		return qi.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof QualifiedIdentifier ) {
			QualifiedIdentifier other = (QualifiedIdentifier)obj;
			return qi.equals(other.qi);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return qi;
	}
}
