package plaid.compilerjava.AST;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;

public class Type implements ASTnode {
	public static final Type UNIT = new Type(null, "unit");
	public static final Type DYN = new Type(null, "dyn");
	
	private final Token token;
	private final String image;
	
	public Type(Token token, String image) {
		this.token = token;
		this.image = image;
	}

	public Token getToken() {
		return token;
	}

	public String getImage() {
		return image;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		// no children
	}
	
	public String toString() {
		if (this.image.equals(""))
			return "dyn";
		return this.image;
	}
}
