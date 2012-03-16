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
 
package plaid.runtime.tools.debugger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import plaid.runtime.PlaidObject;
import plaid.runtime.event.PlaidRuntimeCallEnterEvent;

public class StackFrame {
	private PlaidRuntimeCallEnterEvent callEvent;
	private Map<String, Object> vars = new HashMap<String, Object>();
	
	public StackFrame(PlaidRuntimeCallEnterEvent ev) {
		this.callEvent = ev;
	}
	
	public void updateVar(String name, Object value) {
		vars.put(name, value);
	}
	
	public Collection<Map.Entry<String, Object>> getVars() {
		return vars.entrySet();
	}
	
	public int getCallerColumn() {
		return callEvent.getCallerColumn();
	}

	public String getCallerFilename() {
		return callEvent.getCallerFilename();
	}

	public int getCallerLine() {
		return callEvent.getCallerLine();
	}

	public String getMethodName() {
		return callEvent.getMethodName();
	}

	public PlaidObject getReceiver() {
		return callEvent.getReceiver();
	}

	@Override
	public String toString() {
		return "StackFrame("+callEvent.getMethodName()+ "@"+ callEvent.getReceiver()+")";
	}
}
