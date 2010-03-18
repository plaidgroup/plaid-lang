package plaid.compilerjava.tools;

import java.awt.Dimension;
import java.io.*;

import javax.swing.*;
import javax.swing.tree.*;

import plaid.compilerjava.ParserCore;
import plaid.compilerjava.AST.*;
import plaid.runtime.PlaidException;

public class ASTInspector {
	public static void main(String[] args) throws PlaidException, FileNotFoundException {
		File plaidFile = new File("coreExamples/addFive.plaid");
		CompilationUnit root = ParserCore.parse(new FileInputStream(plaidFile));
		drawAST(root);
	}
	
	public static <T extends ASTnode> DefaultMutableTreeNode genTreeViewAST(T root) {
		ASTInspectorVisitor treeGenVisitor = new ASTInspectorVisitor();
		root.accept(treeGenVisitor);
		return treeGenVisitor.getRoot();
	}
	
	public static <T extends ASTnode> void printAST(T root) {
		root.accept(new ASTPrintVisitor());
	}
	
	public static <T extends ASTnode> void drawAST(T root) {
		JFrame mainFrame = new JFrame();
		mainFrame.add(new ASTInspectorPanel(genTreeViewAST(root)));
		mainFrame.setMinimumSize(new Dimension(500, 500));
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	private static class ASTInspectorPanel extends JPanel {
		private JTree inspectorHierarchy;
		private JScrollPane inspectorPane;
		
		private ASTInspectorPanel(DefaultMutableTreeNode root) {
			inspectorHierarchy = new JTree(root);
			inspectorHierarchy.setMinimumSize(new Dimension(500, 500));
			inspectorHierarchy.setPreferredSize(new Dimension(500, 500));
			inspectorHierarchy.setRootVisible(true);
			
			inspectorPane = new JScrollPane(inspectorHierarchy);
			inspectorPane.setMinimumSize(new Dimension(500, 500));
			
			this.add(inspectorPane);
			this.setMinimumSize(new Dimension(500, 500));
			this.setVisible(true);
		}
		
		public static ASTInspectorPanel createInspectorPanel(Expression root) {
			return null;
		}
		
		private static TreeNode createTreeView(Expression root) {
			return null;
		}
	}
}
