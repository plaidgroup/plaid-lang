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

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import plaid.runtime.tools.debugger.CallStack.CallStackChangeListener;

public class CallStackViewer  extends JList implements CallStackChangeListener {
	private static final long serialVersionUID = -157079910198371980L;

	public CallStackViewer() {
	}

	@Override
	public void handleCallStackChange(CallStack cs) {
		removeAll();
		List<String> callStack = new ArrayList<String>();
		for ( int i = 0 ; i < cs.getCallStackHeight() ; i++ ) {
			StackFrame sf = cs.getStackFrame(i);
			String entry = sf.getMethodName();
			callStack.add(0, entry);
		}
		setListData(callStack.toArray());
	}
}
