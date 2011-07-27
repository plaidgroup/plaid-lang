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

import java.lang.reflect.Method;
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
	
	/***
	 * This method assumes that all fields of the receiver object are of type ASTNode, List<ASTNode>, Map<?, ASTNode>. 
	 * Equivalence checking will fail for all other types of fields.
	 * @author jssunshi
	 */
	public final boolean equivalent(ASTNode other) {
		Class<?> thisClass = this.getClass();
		Method[] methods = thisClass.getMethods();
		try { 
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (!method.getName().equals("getToken") && //ignore tokens
						!method.getName().equals("getClass") && //ignore getClass
						method.getName().startsWith("get")) {  //ignore all other non-getters
					Object myField = method.invoke(this);
					Object otherField = method.invoke(this);
					if (myField instanceof ASTNode) {
						if (!(((ASTNode) myField)).equivalent((ASTNode)otherField)) {
							return false;
						}
					} else if (myField instanceof List) {
						List<?> myList = (List<?>) myField;
						List<?> otherList = (List<?>) otherField;
						for (int j = 0; j < myList.size(); j++) {
							ASTNode myItem = (ASTNode)myList.get(j);
							ASTNode otherItem = (ASTNode)otherList.get(j);
							if(!myItem.equivalent(otherItem)) { 
								return false;
							}
						}
					} else if (myField instanceof Map) {
						
						Map<?,?> myMap = (Map<?,?>)myField;
						Map<?,?> otherMap = (Map<?,?>)otherField;
						for(Object key : myMap.keySet()) {
							ASTNode myItem = (ASTNode)myMap.get(key);
							ASTNode otherItem = (ASTNode)otherMap.get(key);
							if(myItem.equivalent(otherItem)) {
								return false;
							}
						}
						return true;
					} else {
						throw new IllegalArgumentException("Default ASTNode.equivalent method only " +
								"supports maps, lists, or ASTNode fields.");
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}