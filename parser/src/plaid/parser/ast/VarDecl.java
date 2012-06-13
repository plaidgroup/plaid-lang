package plaid.parser.ast;

import plaid.parser.Token;

public class VarDecl extends Stmt {
	private final Specifier specifier;
	private final Type varType;
	private final Identifier id;
	private final Expr expr;
	
	
	public VarDecl(Token token, Specifier speccifier, Type varType,
			Identifier id, Expr expr) {
		super(token);
		this.specifier = speccifier;
		this.varType = varType;
		this.id = id;
		this.expr = expr;
	}

	public Specifier getSpecifier() {
		return specifier;
	}

	public Type getVarType() {
		return varType;
	}

	public Identifier getId() {
		return id;
	}

	public Expr getExpr() {
		return expr;
	}
	
	
}
