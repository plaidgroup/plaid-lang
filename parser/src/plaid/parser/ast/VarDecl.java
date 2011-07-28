package plaid.parser.ast;

import plaid.parser.Token;

public class VarDecl extends Stmt {
	private final Specifier speccifier;
	private final Type type;
	private final Identifier id;
	private final Expression expr;
	
	
	public VarDecl(Token token, Specifier speccifier, Type type,
			Identifier id, Expression expr) {
		super(token);
		this.speccifier = speccifier;
		this.type = type;
		this.id = id;
		this.expr = expr;
	}


	public Specifier getSpeccifier() {
		return speccifier;
	}


	public Type getType() {
		return type;
	}


	public Identifier getId() {
		return id;
	}


	public Expression getExpr() {
		return expr;
	}
	
	
}
