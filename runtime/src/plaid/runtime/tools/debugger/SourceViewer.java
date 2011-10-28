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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JEditorPane;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

public class SourceViewer extends JEditorPane {
	private static final long serialVersionUID = -3767840606514817785L;
	private StyledDocument document = null;
	private MutableAttributeSet defaultSyntax = new SimpleAttributeSet();
	private MutableAttributeSet highlightSyntax = new SimpleAttributeSet();
	private MutableAttributeSet breakpointSyntax = new SimpleAttributeSet();
	private MutableAttributeSet errorSyntax = new SimpleAttributeSet();
	private Integer[] lineLength = new Integer[0];
	private Map<String, List<Integer>> breakpoints = new HashMap<String, List<Integer>>();
	private File currentFile = new File("UNKNOWN");
	private int currentHighlight = -1;
	public SourceViewer() {
		StyledEditorKit editorKit = new StyledEditorKit();
		this.setEditorKit(editorKit);
		document = (StyledDocument) this.getDocument();

		// set colors for the different styles
		StyleConstants.setBackground(highlightSyntax, Color.GREEN);
		StyleConstants.setForeground(highlightSyntax, Color.WHITE);

		StyleConstants.setBackground(defaultSyntax, Color.WHITE);
		StyleConstants.setForeground(defaultSyntax, Color.BLACK);

		StyleConstants.setBackground(errorSyntax, Color.RED);
		StyleConstants.setForeground(errorSyntax, Color.WHITE);

		StyleConstants.setBackground(breakpointSyntax, Color.BLUE);
		StyleConstants.setForeground(breakpointSyntax, Color.YELLOW);

		setEditable(false);
		setMinimumSize(new Dimension(300, 300));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if ( e.getClickCount() == 2 ) {
					JEditorPane sourceViewer = (JEditorPane)e.getSource();
					int pos = sourceViewer.viewToModel(new Point(e.getX(), e.getY()));
					int offset = 0;
					int line = 1;
					for (int i : lineLength) {
						if ( pos < offset+i) {
							break;
						}
						offset+=i;
						line++;
					}
					sourceViewer.select(1, 0);
					toggleBreakpoint(line);
				}
			}			
		});
		
		setCaret(new javax.swing.text.Caret()
        {
            public void install(javax.swing.text.JTextComponent c){}
            public void deinstall(javax.swing.text.JTextComponent c){}
            public void paint(Graphics g){}
            public void addChangeListener(ChangeListener l){}
            public void removeChangeListener(ChangeListener l){}
            public boolean isVisible(){return false;}
            public void setVisible(boolean v){}
            public boolean isSelectionVisible(){return false;}
            public void setSelectionVisible(boolean v){}
            public void setMagicCaretPosition(Point p){}
            public Point getMagicCaretPosition(){return new Point(0,0);}
            public void setBlinkRate(int rate){}
            public int getBlinkRate(){return 10000;}
            public int getDot(){return 0;}
            public int getMark(){return 0;}
            public void setDot(int dot){}
            public void moveDot(int dot){}
        });

	}
	
	public void setFile(File file) {
		try {
			document.remove(0, document.getLength());
			BufferedReader br = new BufferedReader(new FileReader(file));
			List<Integer> lineLenghtCollector = new ArrayList<Integer>();	
			int offset = 0;
			while ( br.ready() ) {
				String line = String.format("%04d| ", (lineLenghtCollector.size()+1)) + br.readLine() + System.getProperty("line.separator");
				lineLenghtCollector.add(line.length());
				document.insertString(offset, line, defaultSyntax);
				offset += line.length();
			}
			lineLength = lineLenghtCollector.toArray(lineLength);
			if ( breakpoints.keySet().contains(file.getCanonicalPath()) == false ) {
				breakpoints.put(file.getCanonicalPath(), new ArrayList<Integer>());
			}
			currentHighlight = 0;
			currentFile = file;
			updateHighlighting();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public void highlightLine(int line) {
		document.setCharacterAttributes(0, document.getLength(), defaultSyntax, false);
		if ( 0 < line && line <= lineLength.length ) {
			line--;
			int offset = 0;
			for( int i = 0 ; i < line ; i++) {
				offset += lineLength[i];
			}
			currentHighlight = line;
			updateHighlighting();
			try {
				scrollRectToVisible(modelToView(offset));
			} catch (BadLocationException e) {}		}
	}
	
	public void highlightErrorLine(int line) {
		document.setCharacterAttributes(0, document.getLength(), defaultSyntax, false);
		if ( 0 < line && line <= lineLength.length ) {
			line--;
			int offset = 0;
			for( int i = 0 ; i < line ; i++) {
				offset += lineLength[i];
			}
			document.setCharacterAttributes(offset, lineLength[line], errorSyntax, false);
		}
	}

	private void highlightBreakpoints() {
		try {
			if ( breakpoints.keySet().contains(currentFile.getCanonicalPath()) ) {
				for ( Integer line : breakpoints.get(currentFile.getCanonicalPath())) {
					int offset = 0;
					for ( int i = 0; i < line -1; i++ ) { offset+=lineLength[i]; }
					document.setCharacterAttributes(offset, lineLength[line-1], breakpointSyntax, false);					
				}
			}
		} catch (IOException e) {}
	}

	private void toggleBreakpoint(int line) {
		try {
			if ( breakpoints.keySet().contains(currentFile.getCanonicalPath()) ) {
				if ( breakpoints.get(currentFile.getCanonicalPath()).contains(line) ) {
					List<Integer> bs = breakpoints.get(currentFile.getCanonicalPath());
					bs.remove(bs.indexOf(line));
				} else {
					breakpoints.get(currentFile.getCanonicalPath()).add(line);
				}
			}
		} catch (IOException e) {}
		updateHighlighting();
	}
	
	private void updateHighlighting() {
		document.setCharacterAttributes(0, document.getLength(), defaultSyntax, false);
		highlightBreakpoints();
		int offset = 0;
		for ( int i = 0; i < currentHighlight ; i++ ) { offset+=lineLength[i]; }
		document.setCharacterAttributes(offset + 5, lineLength[currentHighlight]-5, highlightSyntax, false);
	}
	
	public boolean isBreakpoint(int line) {
		try {
			if ( breakpoints.keySet().contains(currentFile.getCanonicalPath()) ) {
				if ( breakpoints.get(currentFile.getCanonicalPath()).contains(line) ) {
					return true;
				}
			}
		} catch (IOException e) {}
		return false;
	}
	
	public void reset() {
		try {
			document.remove(0, document.getLength());
			lineLength = new Integer[0];
 		} catch (BadLocationException e){}	
	}
}

