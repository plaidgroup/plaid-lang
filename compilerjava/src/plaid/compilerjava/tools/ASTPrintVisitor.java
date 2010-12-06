package plaid.compilerjava.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import plaid.compilerjava.ParserCore;
import plaid.compilerjava.AST.*;
import plaid.compilerjava.coreparser.ParseException;
import plaid.compilerjava.types.FieldType;
import plaid.runtime.PlaidException;

public class ASTPrintVisitor extends AbstractASTVisitor {
	public static void main(String[] args) throws PlaidException, FileNotFoundException, ParseException {
		File plaidFile = new File("coreExamples/addFive.plaid");
		CompilationUnit root = ParserCore.parse(new FileInputStream(plaidFile));
		printAST(root);
	}
	
	public static void printAST(CompilationUnit root) {
		ASTPrintVisitor visitor = new ASTPrintVisitor();
		root.accept(visitor);
	}
	
	int tablevel;
	public ASTPrintVisitor() {
		tablevel = -1;
	}
	
	private static void printlnWithTabs(String str, int num) {
		for (int i = 0; i < num; i++)
			System.out.print("\t");
		System.out.println(str);
	}
	
	public ASTVisitor enter(ASTnode node) {
		tablevel++;
		return this;
	}
	
	public ASTnode leave(ASTnode node, ASTnode oldNode, ASTVisitor visitor) {
		tablevel--;
		return node;
	}
	
	public ASTnode visitNode(ASTnode node) {
		this.enter(node);
		printlnWithTabs("ASTnode", tablevel);
		node.visitChildren(this);
		return this.leave(node, node, this);
	}
	
	public ASTnode visitNode(Application node) {
		this.enter(node);
		printlnWithTabs("Application", tablevel);
		node.visitChildren(this);
		return this.leave(node, node, this);
	}
}
