package plaid.compilerjava.tools;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import plaid.compilerjava.ParserCore;
import plaid.compilerjava.AST.*;
import plaid.compilerjava.coreparser.ParseException;
import plaid.runtime.PlaidException;

public class ASTInspector {
	public static void main(String[] args) throws PlaidException, FileNotFoundException, ParseException {
		File plaidFile = new File("../compilerTests/coreExamples/freezeTests/package.plaid");
		CompilationUnit root = ParserCore.parse(new FileInputStream(plaidFile));
		System.out.println("drawing AST...");
		drawAST(root);
	}
	
	public static <T extends ASTnode> DefaultMutableTreeNode genTreeViewAST(T root) {
		ASTInspectorVisitor treeGenVisitor = new ASTInspectorVisitor();
		root.accept(treeGenVisitor);
		return treeGenVisitor.getRoot();
	}
	
	public static <T extends ASTnode> void printAST(T root) {
		//root.accept(new ASTPrintVisitor());
	}
	
	public static <T extends ASTnode> void drawAST(T root) {
		JFrame mainFrame = new JFrame("Plaid AST Inspector");
		mainFrame.add(new ASTInspectorPanel(genTreeViewAST(root)));
		mainFrame.setMinimumSize(new Dimension(500, 500));
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	private static class ASTInspectorPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private JTree inspectorHierarchy;
		private JScrollPane inspectorPane;
		
		private ASTInspectorPanel(DefaultMutableTreeNode root) {
			inspectorHierarchy = new JTree(root);
			inspectorHierarchy.setMinimumSize(new Dimension(500, 500));
			inspectorHierarchy.setRootVisible(true);
			
			inspectorPane = new JScrollPane(inspectorHierarchy);
			inspectorPane.setMinimumSize(new Dimension(500, 500));
			inspectorPane.setPreferredSize(new Dimension(500, 500));
			
			this.add(inspectorPane);
			this.setMinimumSize(new Dimension(500, 500));
			this.setVisible(true);
		}
	}
}
