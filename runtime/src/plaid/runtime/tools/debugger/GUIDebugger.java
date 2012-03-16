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

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimePlugin;
import plaid.runtime.event.PlaidRuntimeEventListenerAdapter;
import plaid.runtime.event.PlaidRuntimeCallEnterEvent;
import plaid.runtime.event.PlaidRuntimeCallLeaveEvent;
import plaid.runtime.event.PlaidRuntimeExceptionEvent;
import plaid.runtime.event.PlaidRuntimeInitEvent;
import plaid.runtime.event.PlaidRuntimeLocationUpdateEvent;
import plaid.runtime.event.PlaidRuntimeShutdownEvent;
import plaid.runtime.event.PlaidRuntimeVariableUpdateEvent;
import plaid.runtime.models.map.PlaidRuntimeMap;

public class GUIDebugger extends PlaidRuntimeEventListenerAdapter implements PlaidRuntimePlugin {
	private MainWindow mainWindow;
		
	public GUIDebugger() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					GUIDebugger.this.mainWindow = new MainWindow();
					GUIDebugger.this.mainWindow.setVisible(true);
				}
			});
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void handleCallEnter(final PlaidRuntimeCallEnterEvent ev) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					mainWindow.handleCallEnter(ev);
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void handleCallLeave(final PlaidRuntimeCallLeaveEvent ev) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					mainWindow.handleCallLeave(ev);
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void handleVariableUpdate(final PlaidRuntimeVariableUpdateEvent ev) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					mainWindow.handleVariableUpdate(ev);
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateRuntimeLocation(final PlaidRuntimeLocationUpdateEvent ev) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					mainWindow.updateRuntimeLocation(ev);
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void handleInit(final PlaidRuntimeInitEvent ev) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					mainWindow.handleInit(ev);
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void handleShutdown(final PlaidRuntimeShutdownEvent ev) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					mainWindow.handleShutdown(ev);
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Override
	public void handleException(final PlaidRuntimeExceptionEvent ev) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					mainWindow.handleException(ev);
				}
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean activate(PlaidRuntime rt) {
		if ( rt instanceof PlaidRuntimeMap ) {
			rt.addRuntimeEventListener(this);
			return true;
		}
		return false;
	}

	@Override
	public void shutdown() {
	}	
}
