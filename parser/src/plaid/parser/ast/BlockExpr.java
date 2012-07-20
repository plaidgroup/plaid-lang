package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class BlockExpr extends Expr {
	private final List<Stmt> statements;

	public BlockExpr(Token token, List<Stmt> statements) {
		super(token);
		this.statements = statements;
	}

	public List<Stmt> getStatements() {
		return statements;
	}

	@Override
	public String toString() {
		return "{" + stmtListToString(statements) + "}";
	}
}
