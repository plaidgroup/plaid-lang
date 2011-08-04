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

import plaid.parser.Token;

public abstract class ASTNode {
	
	public static final Token DEFAULT_TOKEN = new Token(0, "");
	
	/**
	 * All arguments to the constructor of any ASTNode must be non-null.
	 * @param token
	 */
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
	/**
	 * @param other
	 * @return
	 */
	public final boolean equivalent(ASTNode other) {
		Class<?> thisClass = this.getClass();
		Method[] methods = thisClass.getMethods();
		boolean matches = true; // assume true
		if (!(other instanceof ASTNode)) {
			return false;
		}
		try { 
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (!method.getName().equals("getToken") && //ignore tokens
						!method.getName().equals("getClass") && //ignore getClass
						method.getName().startsWith("get")) {  //ignore all other non-getters
					Object myField = method.invoke(this);
					Object otherField = method.invoke(other);
					if (myField instanceof ASTNode) {
						if (!(((ASTNode) myField)).equivalent((ASTNode)otherField)) {
							matches = false;
						}
					} else if (myField instanceof List) {
						List<?> myList = (List<?>) myField;
						List<?> otherList = (List<?>) otherField;
						if (myList.size() != otherList.size()) {
							matches = false;
						}
						for (int j = 0; j < myList.size(); j++) {
							ASTNode myItem = (ASTNode)myList.get(j);
							ASTNode otherItem = (ASTNode)otherList.get(j);
							if(!myItem.equivalent(otherItem)) { 
								matches = false;
							}
						}
					} 
					else if (myField instanceof Enum || myField instanceof String || 
							myField instanceof Double || myField instanceof Integer || myField instanceof Boolean) {
						matches = matches && (myField.equals(otherField));
					} else if (myField == null) {
						matches = matches && otherField == null;
					}
					else {
						throw new IllegalArgumentException("Default ASTNode.equivalent method only " +
								"supports maps, lists, or ASTNode fields.");
					}
				}
			}
			return matches;
		} catch (Exception e) {
			return false;
		}
	}
	
}