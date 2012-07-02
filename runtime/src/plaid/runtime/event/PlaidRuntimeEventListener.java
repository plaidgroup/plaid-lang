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


public interface  PlaidRuntimeEventListener {
	public void updateRuntimeStateChange(final PlaidRuntimeStateChangeEvent ev);
	
	public void updateRuntimeLocation(final PlaidRuntimeLocationUpdateEvent ev);
	
	public void handleCallEnter(final PlaidRuntimeCallEnterEvent ev);
	
	public void handleCallLeave(final PlaidRuntimeCallLeaveEvent ev);

	public void handleVariableUpdate(final PlaidRuntimeVariableUpdateEvent ev);

	public void handleShutdown(final PlaidRuntimeShutdownEvent ev);

	public void handleInit(final PlaidRuntimeInitEvent ev);
	
	public void handleException(final PlaidRuntimeExceptionEvent ev);
}
