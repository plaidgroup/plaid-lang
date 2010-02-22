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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import plaid.runtime.PlaidRuntime;
import plaid.runtime.event.PlaidRuntimeCallEnterEvent;
import plaid.runtime.event.PlaidRuntimeCallLeaveEvent;
import plaid.runtime.event.PlaidRuntimeExceptionEvent;
import plaid.runtime.event.PlaidRuntimeInitEvent;
import plaid.runtime.event.PlaidRuntimeLocationUpdateEvent;
import plaid.runtime.event.PlaidRuntimeShutdownEvent;
import plaid.runtime.event.PlaidRuntimeVariableUpdateEvent;

public class MainWindow extends JFrame  {
	private static final long serialVersionUID = -8845190410509452062L;
	private SourceViewer sourceViewer;
	private CallStackViewer callStackViewer;
	private JFileChooser fc = new JFileChooser();
	private CallStack callStack = new CallStack();
	private VariableViewer varViewer;
	private int currentLine = -1;
	private String currentFile = "";
	private DEBUG_MODE debugMode = DEBUG_MODE.STEP;
	
	enum DEBUG_MODE {
		STEP,
		CONT,
		FINISHED
	}
	
	public MainWindow() {
		super();
		setMinimumSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Plaid Simple Debugger");

		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));

		sourceViewer = new SourceViewer();
		callStackViewer = new CallStackViewer();
		callStack.addCallStackChangeListener(callStackViewer);
		
		varViewer = new VariableViewer();
		callStack.addCallStackChangeListener(varViewer);
		
		JSplitPane split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(callStackViewer), new JScrollPane(varViewer));
		split1.setResizeWeight(0.5);
		JScrollPane sp = new JScrollPane(sourceViewer);
		sp.setAutoscrolls(true);
		JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, sp, split1);
		split2.setResizeWeight(1);
		split2.setDividerLocation(0.7);
		getContentPane().add(split2, BorderLayout.CENTER);

		Action stepAction = new AbstractAction() {
			private static final long serialVersionUID = -3847493290175796288L;
			@Override
			public void actionPerformed(ActionEvent e) {
				actionStep();
			}
		};

		Action contAction = new AbstractAction() {
			private static final long serialVersionUID = -3847493290175796288L;
			@Override
			public void actionPerformed(ActionEvent e) {
				actionCont();
			}
		};

		// create menubar
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem fileOpen = new JMenuItem("Open");
		file.add(fileOpen);
		fileOpen.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int result = fc.showOpenDialog((JComponent)e.getSource());
				if  ( result == JFileChooser.APPROVE_OPTION ) {
					sourceViewer.setFile(fc.getSelectedFile());
				}
			}
		});
		file.addSeparator();
		JMenuItem fileExit = new JMenuItem("Exit");
		fileExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});
		file.add(fileExit);
		mb.add(file);

	//		JMenu dbg = new JMenu("Debugger");
	//		JMenuItem dbgStep = new JMenuItem("Step");
	//		dbgStep.setAction(stepAction);
	//		dbg.add(dbgStep);
	//		JMenuItem dbgCont = new JMenuItem("Cont");
	//		dbgCont.setAction(stepAction);
	//		dbg.add(dbgCont);
	//		mb.add(dbg);
		setJMenuBar(mb);

		
		
		// create toolbar
		JToolBar tb = new JToolBar();
		JButton stepBtn = new JButton(stepAction);
		stepBtn.setText("Step");
		tb.add(stepBtn);
		JButton contBtn = new JButton(contAction);
		contBtn.setText("Cont");
		tb.add(contBtn);
		getContentPane().add(tb, BorderLayout.NORTH);
		
	}

	public void handleCallEnter(PlaidRuntimeCallEnterEvent ev) {
		callStack.enter(ev);
	}

	public void handleCallLeave(PlaidRuntimeCallLeaveEvent ev) {
		callStack.leave(ev);
	}

	public void handleVariableUpdate(PlaidRuntimeVariableUpdateEvent ev) {
		callStack.updateVar(ev);
	}

	public void updateRuntimeLocation(PlaidRuntimeLocationUpdateEvent ev) {
		if ( currentFile.equals(ev.getFilename()) == false ) {
			// try loading it 
			File f = new File(ev.getFilename());
			if ( f.exists() == false ) {
				JOptionPane.showMessageDialog(this, "Cannot find file : " + ev.getFilename());
				int result = fc.showOpenDialog(this);
				if  ( result == JFileChooser.APPROVE_OPTION ) {
					sourceViewer.setFile(fc.getSelectedFile());
				} else {
					sourceViewer.reset();				
				}
			} else {
				sourceViewer.setFile(f);
			}
			currentFile = ev.getFilename();
		}
		
		switch (debugMode) {
		case STEP:
			if ( ev.getLine() != currentLine ) {
				currentLine = ev.getLine();
				sourceViewer.highlightLine(currentLine);
				PlaidRuntime.getRuntime().halt();
			}
			break;
		case CONT:
			if ( sourceViewer.isBreakpoint(ev.getLine()) ) {
				currentLine = ev.getLine();
				sourceViewer.highlightLine(currentLine);
				PlaidRuntime.getRuntime().halt();
				debugMode = DEBUG_MODE.STEP;
			}			
			break;
		}
	}

	public void handleInit(PlaidRuntimeInitEvent ev) {
		ev.getRuntimeControl().halt();
	}
	
	public void handleShutdown(PlaidRuntimeShutdownEvent ev) {
		sourceViewer.highlightLine(-1);
		varViewer.reset();
		setTitle(getTitle()+" [FINISHED]");
		debugMode = DEBUG_MODE.FINISHED;
		JOptionPane.showMessageDialog(this, "Program execution finished!");
	}
	
	public void handleException(final PlaidRuntimeExceptionEvent ev) {
		sourceViewer.highlightErrorLine(ev.getException().getLine());
		JOptionPane.showMessageDialog(this, ev.getException().getStackTrace(), ev.getException().getMessage(), JOptionPane.ERROR_MESSAGE);
		setTitle(getTitle()+" [ERROR : "+ ev.getException().getMessage()+ "]");
	}
	
	private void actionStep() {
		if ( debugMode != DEBUG_MODE.FINISHED ) {
			debugMode = DEBUG_MODE.STEP;
			PlaidRuntime.getRuntime().run();
		} else {
			JOptionPane.showMessageDialog(this, "Program execution finished!");
		}
	}
	
	private void actionCont() {
		if ( debugMode != DEBUG_MODE.FINISHED ) {
			debugMode = DEBUG_MODE.CONT;
			PlaidRuntime.getRuntime().run();
		} else {
			JOptionPane.showMessageDialog(this, "Program execution finished!");
		}
	}
}
