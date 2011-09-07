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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JList;

import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidState;
import plaid.runtime.tools.debugger.CallStack.CallStackChangeListener;

public class VariableViewer extends JList implements CallStackChangeListener {
	private static final long serialVersionUID = -8629860822273437560L;
	private Object[] listData = new Object[0];
	private List<VariabeChangeListener> updateListeners = new ArrayList<VariabeChangeListener>();
	
	public VariableViewer() {
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		    	handleMouseClick(e);
		    }
		};
		this.addMouseListener(mouseListener);
	}

	@Override
	public void handleCallStackChange(CallStack cs) {
		List<String> data = new ArrayList<String>();
		List<Object> dataObjects = new ArrayList<Object>();
		StackFrame sf = cs.getCurrentStackFrame();
		if (cs != null && cs.getCallStackHeight() > 0 ) {
			for( Map.Entry<String, Object> entry : sf.getVars() ) {
				if (  ( entry.getValue() instanceof PlaidState) == false ) {
					data.add(entry.getKey() + " = " + entry.getValue());
					dataObjects.add(entry.getValue());
				}
			}
			setListData(data.toArray());
			listData  = dataObjects.toArray();
		}
		triggerVariableChangeListener();
	}
	
	public void reset() {
		setListData(new Object[0]);
	}

	public void handleMouseClick(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int index = locationToIndex(e.getPoint());
            if ( 0 <= index && index < listData.length && listData[index] instanceof PlaidObject ) {
            	PlaidObjectExplorer poe = new PlaidObjectExplorer(this, (PlaidObject)listData[index]);
            	poe.setVisible(true);
            }
         }
	}
	
	public void addVariableChangeListener(VariabeChangeListener vcl) {
		updateListeners.add(vcl);
	}
	
	private void triggerVariableChangeListener() {
		if ( updateListeners.size() == 0 ) return;
		
		for (VariabeChangeListener evl : updateListeners) {
			evl.handleVariablesUpdate();
		}
	}
	
	public interface VariabeChangeListener {
		public void handleVariablesUpdate();
	}
}
