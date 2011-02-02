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
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.tools.debugger.VariableViewer.VariabeChangeListener;

public class PlaidObjectExplorer extends JDialog implements VariabeChangeListener {
	private static final long serialVersionUID = -3684470886045795965L;
	private PlaidObject dataObject;
	private VariableViewer varViewer;
	private JTree treeView;
	private DefaultMutableTreeNode topNode = new DefaultMutableTreeNode("");
	
	public PlaidObjectExplorer(VariableViewer varViewer, PlaidObject obj) {
		this.dataObject = obj;
		this.varViewer = varViewer;
		setSize(300,300);
		setModal(false);
		setTitle("Plaid Object Explorer");
		
		if ( dataObject instanceof PlaidJavaObject ) {
			topNode.setUserObject("PlaidJavaObject");
		} else {
			topNode.setUserObject("PlaidObject");
		}
		treeView = new JTree(topNode);
		setContentPane(new JScrollPane(treeView));
		
		updateView();
		
	    treeView.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	          handleMouseEvent(e);
	        }
	      });
	    
	    varViewer.addVariableChangeListener(this);
	}

	private void updateView() {
		if ( topNode.getChildCount() == 0 ) {
			//topNode.removeAllChildren();
			DefaultMutableTreeNode states= new DefaultMutableTreeNode("States");
			DefaultMutableTreeNode methods = new DefaultMutableTreeNode("Methods");
			DefaultMutableTreeNode fields = new DefaultMutableTreeNode("Fields");
			topNode.add(states);
			topNode.add(methods);
			topNode.add(fields);

			for(PlaidObject po : dataObject.getStates() ) {
				DefaultMutableTreeNode state= new DefaultMutableTreeNode(po);
				states.add(state);
			}

			for (Map.Entry<String, PlaidMemberDef> entry : dataObject.getMembers().entrySet() ) {
				DefaultMutableTreeNode item = new DefaultMutableTreeNode(new DataObject( entry.getValue().getValue(),entry.getValue().getMemberName()));
				if ( entry.getValue() instanceof PlaidMethod ) {
					methods.add(item);
				} else {
					fields.add(item);
				}
			}
			treeView.expandPath(new TreePath(fields.getPath()));
		} else {
			DefaultMutableTreeNode states = (DefaultMutableTreeNode)topNode.getChildAt(0);
			DefaultMutableTreeNode methods = (DefaultMutableTreeNode)topNode.getChildAt(1);
			DefaultMutableTreeNode fields = (DefaultMutableTreeNode)topNode.getChildAt(2);
			
			states.removeAllChildren();
			for(PlaidObject po : dataObject.getStates() ) {
				DefaultMutableTreeNode state= new DefaultMutableTreeNode(po);
				states.add(state);
			}
			for (Map.Entry<String, PlaidMemberDef> entry : dataObject.getMembers().entrySet() ) {
				DefaultMutableTreeNode item = new DefaultMutableTreeNode(new DataObject( entry.getValue().getValue(),entry.getValue().getMemberName()));
				if ( entry.getValue() instanceof PlaidMethod ) {
					methods.add(item);
				} else {
					fields.add(item);
				}
			}
			
			fields.removeAllChildren();
			methods.removeAllChildren();
			for (Map.Entry<String, PlaidMemberDef> entry : dataObject.getMembers().entrySet() ) {
				DefaultMutableTreeNode item = new DefaultMutableTreeNode(new DataObject( entry.getValue().getValue(),entry.getValue().getMemberName()));
				if ( entry.getValue() instanceof PlaidMethod ) {
					methods.add(item);
				} else {
					fields.add(item);
				}
			}
		}
		treeView.updateUI();
	}
	
	public void handleMouseEvent(MouseEvent e) {
		if ( e.getClickCount() == 2 ) {
			TreePath tp = treeView.getPathForLocation(e.getX(), e.getY());
			if ( tp != null ) {
				if ( tp.getLastPathComponent() instanceof DefaultMutableTreeNode ) {
					DefaultMutableTreeNode tn = (DefaultMutableTreeNode)tp.getLastPathComponent();
					if ( tn.getUserObject() instanceof DataObject && ((DataObject)tn.getUserObject()).getPlaidObject() instanceof PlaidObject ) {
						PlaidObject po = ((DataObject)tn.getUserObject()).getPlaidObject();
						PlaidObjectExplorer poe = new PlaidObjectExplorer(varViewer,po);
						poe.setVisible(true);
					}
				}
			}
		}
	}
	
	private class DataObject {
		private final PlaidObject po;
		private final String name;
		
		public PlaidObject getPlaidObject() {
			return po;
		}

		@SuppressWarnings("unused")
		public String getName() {
			return name;
		}

		public DataObject(PlaidObject po, String name) {
			super();
			this.po = po;
			this.name = name;
		}
		
		
		@Override
		public String toString() {
			return name + " : " + po;
		}

		@Override
		public int hashCode() {
			return (int)(Math.random()*10000);
		}

		@Override
		public boolean equals(Object obj) {
			return false;
		}
	}

	@Override
	public void handleVariablesUpdate() {
		updateView();
	}
}
