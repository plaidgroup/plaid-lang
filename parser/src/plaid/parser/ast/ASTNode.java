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

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import plaid.parser.Token;

public abstract class ASTNode {
	
	public static final Token DEFAULT_TOKEN = new Token();
	
	public ASTNode(Token token) {
		this.token = token;
	}
	
	protected final Token token;

	public Token getToken() {
		return this.token;
	}
	
	@SuppressWarnings("unchecked")
	/***
	 * This method assumes that all fields of the receiver object are of type ASTNode, List<ASTNode>, Map<?, ASTNode>. 
	 * Equivalence checking will fail for all other types of fields.
	 * @author jssunshi
	 */
	public boolean equivalent(ASTNode other) {
		Field[] fields = this.getClass().getFields();
		try { 
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (!field.getName().equals("token")) { //ignore tokens
					Object myField = field.get(this);
					Object otherField = field.get(other);
					if (myField instanceof ASTNode) {
						if (!(((ASTNode) myField)).equivalent((ASTNode)otherField)) {
							return false;
						}
					} else if (myField instanceof List) {
						//TODO: This will fail if the field is a list of something other than ASTNodes
						List<ASTNode> myList = (List<ASTNode>) myField;
						List<ASTNode> otherList = (List<ASTNode>) otherField;
						for (int j = 0; j < myList.size(); j++) {
							if(myList.get(i).equivalent(otherList.get(i))) 
								return false;
						}
					} else if (myField instanceof Map) {
						Map<Object,ASTNode> myMap = (Map<Object,ASTNode>)myField;
						Map<Object,ASTNode> otherMap = (Map<Object,ASTNode>)otherField;
						for(Object key : myMap.keySet()) {
							if(!myMap.get(key).equivalent(otherMap.get(key))) {
								return false;
							}
						}
						return true;
					} else {
						throw new IllegalArgumentException("Default ASTNode.equivalent method only supports maps, lists, or ASTNode fields.");
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}