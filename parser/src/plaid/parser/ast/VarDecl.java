package plaid.parser.ast;

import plaid.parser.Token;

public class VarDecl extends Stmt {
	private final Specifier specifier;
	private final Type type;
	private final Identifier id;
	private final Expr expr;
	
	
	public VarDecl(Token token, Specifier speccifier, Type type,
			Identifier id, Expr expr) {
		super(token);
		this.specifier = speccifier;
		this.type = type;
		this.id = id;
		this.expr = expr;
	}

	public Specifier getSpecifier() {
		return specifier;
	}

	public Type getType() {
		return type;
	}

	public Identifier getId() {
		return id;
	}

	public Expr getExpr() {
		return expr;
	}
	
	
}
