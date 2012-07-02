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

import plaid.runtime.event.PlaidRuntimeCallEnterEvent;
import plaid.runtime.event.PlaidRuntimeCallLeaveEvent;
import plaid.runtime.event.PlaidRuntimeVariableUpdateEvent;

public class CallStack {
	private List<StackFrame> callStack = new ArrayList<StackFrame>();
	private List<CallStackChangeListener> callStackListeners = new ArrayList<CallStackChangeListener>();
	
	public CallStack() {
	}
	
	public final StackFrame enter(PlaidRuntimeCallEnterEvent ev) {
		StackFrame sf = new StackFrame(ev);
		callStack.add(sf);
		triggerCallStackChangeListeners();
		return sf;	
	}
	
	public final StackFrame leave(PlaidRuntimeCallLeaveEvent ev) {
		StackFrame sf = callStack.remove(callStack.size()-1);
		triggerCallStackChangeListeners();
		return sf;
	}

	public final void updateVar(PlaidRuntimeVariableUpdateEvent ev) {
		StackFrame sf = getCurrentStackFrame();
		if ( sf != null ) {
			sf.updateVar(ev.getName(), ev.getValue());
		}
		triggerCallStackChangeListeners();
	}
	
	public final int getCallStackHeight() {
		return callStack.size();
	}
	
	public final StackFrame getStackFrame(int index) {
		if ( 0 <= index  && index < callStack.size() ) {
			return callStack.get(index);
		} else {
			return null;
		}
	}
	
	public StackFrame getCurrentStackFrame() {
		return getStackFrame(getCallStackHeight()-1);
	}
	
	public void addCallStackChangeListener(CallStackChangeListener evl) {
		callStackListeners.add(evl);
	}

	public void removeCallStackChangeListener(CallStackChangeListener evl) {
		callStackListeners.remove(evl);
	}
	
	private void triggerCallStackChangeListeners() {
		if ( callStackListeners.size() == 0 )  return;
		
		for ( CallStackChangeListener evl : callStackListeners ) {
			evl.handleCallStackChange(this);
		}
	}
	
	public interface CallStackChangeListener {
		public void handleCallStackChange(final CallStack cs);
	}
}
