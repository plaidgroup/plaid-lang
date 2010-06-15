package plaid.compilerjava.AST;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;

public class Permission implements ASTnode {
	public static final Permission FULL = new Permission(null, "full");
	public static final Permission IMMUTABLE = new Permission(null, "immutable");
	public static final Permission PURE = new Permission(null, "pure");

	private final Token token;
	private final String image;
	
	public Permission(Token token, String image) {
		this.token = token;
		this.image = image;
	}
	
	public String getImage() {
		return this.image;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visitNode(this);
	}

	@Override
	public Token getToken() {
		return this.token;
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		// no children
	}

}
