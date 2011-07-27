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

public class StateDecl extends Decl {
	
	private final State stateDef;
	private final QualifiedIdentifier caseOf;
	private final boolean isCaseOf;
	private final boolean typedef; // typedefs do not have tags associated with them
	private final Permission defaultPerm;
	private final List<MetaParameter> metaParams;
	private final List<MetaArgument> metaCaseOfArgs;
	
	public StateDecl(Token t, List<Modifier> modifiers, Identifier name, List<MetaParameter> metaParams, List<MetaArgument> metaCaseOfArgs, State stateDef, Permission defaultPerm, boolean typedef) {
		this(t, modifiers, name, metaParams, metaCaseOfArgs, stateDef, null, defaultPerm, typedef);
	}
	
	public StateDecl(Token t, List<Modifier> modifiers, Identifier name, List<MetaParameter> metaParams, List<MetaArgument> metaCaseOfArgs, State stateDef, QualifiedIdentifier caseOf, Permission defaultPerm, boolean typedef) {
		super(t, modifiers, name);
		this.metaParams = metaParams;
		this.stateDef = stateDef;
		if (caseOf == null) {
			this.caseOf = null;
			this.metaCaseOfArgs = new ArrayList<MetaArgument>();
			isCaseOf = false;
		} else {
			this.caseOf = caseOf;
			this.metaCaseOfArgs = metaCaseOfArgs;
			isCaseOf = true;
		}
		this.defaultPerm = defaultPerm;
		this.typedef = typedef;
	}

	public Identifier getID() {
		return name;
	}

	public State getStateDef() {
		return stateDef;
	}
	
	public QualifiedIdentifier getCaseOf() {
		return caseOf;
	}

	public boolean getIsCaseOf() {
		return isCaseOf;
	}
	
	public boolean isTypedef() {
		return this.typedef;
	}

	public List<MetaParameter> getMetaParameters() {
		return metaParams;
	}
	
	public List<MetaArgument> getMetaCaseOfArguments() {
		return metaCaseOfArgs;
	}

	public Permission getDefaultPerm() {
		return defaultPerm;
	}
}
