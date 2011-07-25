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
 
package plaid.parser.ast;

import java.util.ArrayList;
import java.util.List;

import plaid.parser.Token;

public class QI extends State {

	private List<String> qid;
	private boolean hasInit = false;
	private DeclList initState = null;
	private List<MetaArgument> metaArgs = new ArrayList<MetaArgument>();
	
	public QI(Token t, List<String> qid, DeclList initState) {
		super(t);
		this.qid = qid;
		if (initState != null) {
			hasInit = true;
			this.initState = initState;
		}
	}
	
	public QI(Token t, List<String> qid, List<MetaArgument> metaArgs, DeclList initState) {
		super(t);
		this.qid = qid;
		if (initState != null) {
			hasInit = true;
			this.initState = initState;
		}
		this.metaArgs = metaArgs;
	}
	
	public QI(Token t, String qi, DeclList initState) {
		super(t);
		qid = new ArrayList<String>();
		for ( String f : qi.split("\\.") ) {
			qid.add(f);
		}
		if (initState != null) {
			hasInit = true;
			this.initState = initState;
		}
	}
	
	public QI(Token t, String qi, List<MetaArgument> metaArgs, DeclList initState) {
		super(t);
		qid = new ArrayList<String>();
		for ( String f : qi.split("\\.") ) {
			qid.add(f);
		}
		if (initState != null) {
			hasInit = true;
			this.initState = initState;
		}
		this.metaArgs = metaArgs;
	}
	
	public QI(List<String> qid) {
		this(null, qid, null);
	}
	
	public QI(List<String> qid, List<MetaArgument> metaArgs) {
		this(null, qid, metaArgs, null);
	}
	
	public QI(List<String> qid, DeclList init) {
		this(null, qid, init);
	}

	public QI(List<String> qid, List<MetaArgument> metaArgs, DeclList init) {
		this(null, qid, metaArgs, init);
	}
	
	
	public QI(String qi) {
		this(null, qi, null);
	}
	
	public QI(String qi, DeclList init) {
		this(null, qi, init);
	}
	

	public QI(Token t, List<String> qid) {
		this(t, qid, null);
	}
	
	public QI(Token t, String qi) {
		this(t, qi, null);
	}
	
	public QI(Token t) {
		super(t);
	}

	public List<String> getQid() {
		return qid;
	}

	public void setQid(List<String> qid) {
		this.qid = qid;
	}
	
	@Override
	public Token getToken() {
		return token;
	}

	public boolean hasInit() {
		return this.hasInit;
	}

	public DeclList getInitState() {
		return initState;
	}

	public List<MetaArgument> getMetaArguments() {
		return metaArgs;
	}
}
