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

import plaid.runtime.PlaidRuntimeControl;
import plaid.runtime.PlaidRuntimeState.RUNTIME_STATE;

public final class PlaidRuntimeStateChangeEvent extends PlaidRuntimeEvent {
	private final RUNTIME_STATE oldState;
	private final RUNTIME_STATE newState;
	
	public PlaidRuntimeStateChangeEvent(PlaidRuntimeControl rtcrtl, RUNTIME_STATE oldState, RUNTIME_STATE newState) {
		super(rtcrtl);
		this.oldState = oldState;
		this.newState = newState;
	}

	public RUNTIME_STATE getOldState() {
		return oldState;
	}

	public RUNTIME_STATE getNewState() {
		return newState;
	}
	
}
