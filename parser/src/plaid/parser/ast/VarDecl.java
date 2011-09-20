package plaid.parser.ast;

import plaid.parser.Token;

public class VarDecl extends Stmt {
	private final Specifier specifier;
	private final TypeDecl varType;
	private final Identifier id;
	private final Expr expr;
	
	
	public VarDecl(Token token, Specifier speccifier, TypeDecl varType,
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

	public TypeDecl getVarType() {
		return varType;
	}

	public Identifier getId() {
		return id;
	}

	public Expr getExpr() {
		return expr;
	}
	
	
}
