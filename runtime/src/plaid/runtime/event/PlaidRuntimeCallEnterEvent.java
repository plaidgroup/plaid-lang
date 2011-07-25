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
 
package plaid.runtime.event;

import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntimeControl;

public final class PlaidRuntimeCallEnterEvent extends PlaidRuntimeEvent {
	private final String methodName;
	private final PlaidObject receiver;
	private final String callerFilename;
	private final int callerLine;
	private final int callerColumn;
	
	public PlaidRuntimeCallEnterEvent(PlaidRuntimeControl rtcrtl, String methodName, PlaidObject receiver, String callerFilename, int callerLine, int callerColumn) {
		super(rtcrtl);
		this.methodName = methodName;
		this.receiver = receiver;
		this.callerFilename = callerFilename;
		this.callerLine = callerLine;
		this.callerColumn = callerColumn;
	}
	public String getMethodName() {
		return methodName;
	}
	
	public PlaidObject getReceiver() {
		return receiver;
	}

	public String getCallerFilename() {
		return callerFilename;
	}

	public int getCallerLine() {
		return callerLine;
	}

	public int getCallerColumn() {
		return callerColumn;
	}
	
	
}
