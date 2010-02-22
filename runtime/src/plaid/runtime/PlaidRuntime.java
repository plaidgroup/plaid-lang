/**
 * Copyright (c) 2010 The PlaidGroup (see AUTHORS file)
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
 
 package plaid.runtime;

import static plaid.runtime.PlaidRuntimeState.RUNTIME_STATE.STOPPED;

import java.util.ArrayList;
import java.util.List;

import plaid.runtime.event.PlaidRuntimeCallEnterEvent;
import plaid.runtime.event.PlaidRuntimeCallLeaveEvent;
import plaid.runtime.event.PlaidRuntimeEventListener;
import plaid.runtime.event.PlaidRuntimeExceptionEvent;
import plaid.runtime.event.PlaidRuntimeInitEvent;
import plaid.runtime.event.PlaidRuntimeLocationUpdateEvent;
import plaid.runtime.event.PlaidRuntimeShutdownEvent;
import plaid.runtime.event.PlaidRuntimeStateChangeEvent;
import plaid.runtime.event.PlaidRuntimeVariableUpdateEvent;
import plaid.runtime.models.map.PlaidRuntimeMap;

public abstract class PlaidRuntime implements PlaidRuntimeState, PlaidRuntimeControl {

	// static fields
	private static PlaidRuntime runtime;

	// instance fields
	@SuppressWarnings("all")
	private PlaidClassLoader classLoader; 
	private String currentFilename = "<UNKNOWN>";
	private int currentLine = -1;
	private int currentColumn = -1;
	private RUNTIME_STATE currentState = RUNTIME_STATE.UNINITIALIZED;
	private Object currentStateLock = new Object();
	private List<PlaidRuntimeEventListener> eventListeners = new ArrayList<PlaidRuntimeEventListener>();
	private List<PlaidRuntimePlugin> plugins = new ArrayList<PlaidRuntimePlugin>();
	
	public PlaidRuntime() {
		// load debugger
		String dbgEnv = System.getenv("PLAID_RT_DEBUGGER");
		if ( dbgEnv != null ) {
			if ( dbgEnv.equalsIgnoreCase("gui") ) {
				dbgEnv = "plaid.runtime.tools.debugger.GUIDebugger";
			}
			PlaidRuntimePlugin plugin = loadPlugin(dbgEnv);
			if ( plugin != null ) {
				if ( plugin.activate(this) ) {
					plugins.add(plugin);
				}
			}
		}
		
		String compEnv = System.getenv("PLAID_RT_COMPONENTS");
		if ( compEnv != null ) {
			for (String entry : compEnv.split(System.getProperty("path.separator"))) {
				PlaidRuntimePlugin plugin = loadPlugin(entry);
				if ( plugin != null ) {
					if ( plugin.activate(this) ) {
						plugins.add(plugin);
					}
				}
			}
		}
		
		Thread currentThread = Thread.currentThread();
		currentThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				if ( e instanceof PlaidException ) {
					triggerExceptionEvent(new PlaidRuntimeExceptionEvent(PlaidRuntime.this, (PlaidException)e));
				}
				e.printStackTrace();
				PlaidRuntime.this.setRuntimeState(RUNTIME_STATE.UNINITIALIZED);
			}
		});

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				for (PlaidRuntimePlugin plugin : plugins ) {
					plugin.shutdown();
				}
			}
		});
	}

	public PlaidRuntimePlugin loadPlugin(String name) {
		PlaidRuntimePlugin plugin = null;
		
		try {
			Class<?> klass = getClass().getClassLoader().loadClass(name);
			for ( Class<?> ic : klass.getInterfaces() ) {
				if ( ic.isAssignableFrom(PlaidRuntimePlugin.class) ) {
					plugin = (PlaidRuntimePlugin)klass.newInstance();
					break;
				}
			}
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		
		return plugin;
	}
	
	public void addRuntimeEventListener(PlaidRuntimeEventListener evl) {
		if ( eventListeners.contains(evl) == false ) {
			eventListeners.add(evl);
		}
	}

	public void removeRuntimeEventListener(PlaidRuntimeEventListener evl) {
		if ( eventListeners.contains(evl) == false ) {
			eventListeners.remove(evl);
		}
	}

	private void triggerExceptionEvent(PlaidRuntimeExceptionEvent ev) {
		if ( eventListeners.size() == 0 ) return;

		for ( PlaidRuntimeEventListener evl : eventListeners ) {
			evl.handleException(ev);
		}
	}
	
	private void triggerInitEvent(PlaidRuntimeInitEvent ev) {
		if ( eventListeners.size() == 0 ) return;

		for ( PlaidRuntimeEventListener evl : eventListeners ) {
			evl.handleInit(ev);
		}
	}
	
	private void triggerShutdownEvent(PlaidRuntimeShutdownEvent ev) {
		if ( eventListeners.size() == 0 ) return;

		for ( PlaidRuntimeEventListener evl : eventListeners ) {
			evl.handleShutdown(ev);
		}
	}
	
	private void triggerStateChangeEvent(PlaidRuntimeStateChangeEvent ev) {
		if ( eventListeners.size() == 0 ) return;

		for ( PlaidRuntimeEventListener evl : eventListeners ) {
			evl.updateRuntimeStateChange(ev);
		}
	}

	private void triggerLocationUpdateEvent(PlaidRuntimeLocationUpdateEvent ev) {
		if ( eventListeners.size() == 0 ) return;

		for ( PlaidRuntimeEventListener evl : eventListeners ) {
			evl.updateRuntimeLocation(ev);
		}
	}

	private void triggerCallEnterEvent(PlaidRuntimeCallEnterEvent ev) {
		if ( eventListeners.size() == 0 ) return;

		for ( PlaidRuntimeEventListener evl : eventListeners ) {
			evl.handleCallEnter(ev);
		}
	}
	
	private void triggerCallLeaveEvent(PlaidRuntimeCallLeaveEvent ev) {
		if ( eventListeners.size() == 0 ) return;

		for ( PlaidRuntimeEventListener evl : eventListeners ) {
			evl.handleCallLeave(ev);
		}
	}

	private void triggerVariableUpdate(PlaidRuntimeVariableUpdateEvent ev) {
		if ( eventListeners.size() == 0 ) return;

		for ( PlaidRuntimeEventListener evl : eventListeners ) {
			evl.handleVariableUpdate(ev);
		}
	}

	private void checkRuntimeState() {
		synchronized (currentStateLock) {
			while ( currentState != RUNTIME_STATE.RUNNING ) {
				try {
					currentStateLock.wait();
				} catch (InterruptedException e) {}
			}
		}
	}

	@Override
	public void enterCall(PlaidObject this$plaid, String name) {
		if ( currentState == RUNTIME_STATE.UNINITIALIZED ) return;
		triggerCallEnterEvent(new PlaidRuntimeCallEnterEvent(this, name, this$plaid, currentFilename, currentLine, currentColumn));
		checkRuntimeState();
	}

	@Override
	public int getCurrentColumn() {
		return currentColumn;
	}
	
	@Override
	public String getCurrentFilename() {
		return currentFilename;
	}

	@Override
	public int getCurrentLine() {
		return currentLine;
	}

	@Override
	public RUNTIME_STATE getRuntimeState() {
		return currentState;
	}

	@Override
	public void leaveCall(PlaidObject this$plaid, String name) {
		if ( currentState == RUNTIME_STATE.UNINITIALIZED ) return;
		triggerCallLeaveEvent(new PlaidRuntimeCallLeaveEvent(this, name, this$plaid, currentFilename, currentLine, currentColumn));
	}

	@Override
	public void setRuntimeState(RUNTIME_STATE state) {
		RUNTIME_STATE oldState = currentState;
		synchronized (currentStateLock) {
			currentState = state;
			currentStateLock.notifyAll();
		}
		triggerStateChangeEvent(new PlaidRuntimeStateChangeEvent(this, oldState, currentState));
	}

	public void halt() {
		//currentState = RUNTIME_STATE.STOPPED;
		setRuntimeState(STOPPED);
	}
	
	public void run() {
		setRuntimeState(RUNTIME_STATE.RUNNING);
	}
	
	public void init() {
		if ( currentState != PlaidRuntimeState.RUNTIME_STATE.UNINITIALIZED ) {
			throw new PlaidRuntimeException("Runtime already initialized");
		}
		setRuntimeState(RUNTIME_STATE.RUNNING);
		triggerInitEvent(new PlaidRuntimeInitEvent(this));
		checkRuntimeState();
	}
	
	public void shutdown() {
		if ( currentState == PlaidRuntimeState.RUNTIME_STATE.UNINITIALIZED ) {
			//throw new PlaidRuntimeException("Runtime has never been initialized");
		}
		triggerShutdownEvent(new PlaidRuntimeShutdownEvent(this));
		setRuntimeState(RUNTIME_STATE.UNINITIALIZED);
	}
	
	@Override
	public void updateLocation(String filename, int line, int column) {
		currentFilename = filename;
		currentLine = line;
		currentColumn = column;
		triggerLocationUpdateEvent(new PlaidRuntimeLocationUpdateEvent(this, filename, line, column));
		checkRuntimeState();
	}

	@Override
	public void updateVar(String name, Object o) {
		if ( currentState == RUNTIME_STATE.UNINITIALIZED ) return;
		if ( name.endsWith(PlaidConstants.ID_SUFFIX)) return;
		triggerVariableUpdate(new PlaidRuntimeVariableUpdateEvent(this, name, o));
		checkRuntimeState();
	}

	public static PlaidRuntime getRuntime() {
		if ( runtime == null ) {
			runtime = new PlaidRuntimeMap();
		}
		return runtime;
	}

	public abstract PlaidClassLoader getClassLoader();

	public abstract PlaidObject call(PlaidObject func, PlaidObject args);
}
