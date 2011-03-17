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

import java.util.ArrayList;
import java.util.List;

public class QualifiedID {

	private List<String> qidList;
	
	public QualifiedID(List<String> qidList) {
		super();
		this.qidList = qidList;
	}
	
	public QualifiedID(String qid, String sep) {
		qidList = new ArrayList<String>();
		int indexOfSep = qid.indexOf(sep);
		while(indexOfSep > -1) {
			qidList.add(qid.substring(0,indexOfSep));
			qid = qid.substring(indexOfSep+1);
			indexOfSep = qid.indexOf(".");
		}
		qidList.add(qid);
	}

	public List<String> getQidList() {
		return qidList;
	}

	public void setQidList(List<String> qidList) {
		this.qidList = qidList;
	}

	public String toString() {
		String qid = "";
		for (String id : qidList) {
			qid = qid + id + ".";
		}
		return qid.substring(0,qid.length() - 1);
	}
	
	public String toDirectoryName() {
		String qid = "";
		String separator = System.getProperty("file.separator");
		for (String id : qidList) {
			qid = qid + id + separator;
		}
		return qid;
	}
	
	public String toShortName() {
		return qidList.get(qidList.size() - 1);
	}
}
