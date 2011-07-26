package plaid.parser.ast;

import plaid.parser.Token;

public class VarDecl extends Stmt {
	private final Specifier speccifier;
	private final Type type;
	private final Identifier var;
	private final Expression expr;
	
	
	public VarDecl(Token token, Specifier speccifier, Type type,
			Identifier var, Expression expr) {
		super(token);
		this.speccifier = speccifier;
		this.type = type;
		this.var = var;
		this.expr = expr;
	}


	public Specifier getSpeccifier() {
		return speccifier;
	}


	public Type getType() {
		return type;
	}


	public Identifier getVar() {
		return var;
	}


	public Expression getExpr() {
		return expr;
	}
	
	
}
