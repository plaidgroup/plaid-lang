package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class BlockExpr extends Expression {
	private final List<Stmt> statements;

	public BlockExpr(Token token, List<Stmt> statements) {
		super(token);
		this.statements = statements;
	}

	public List<Stmt> getStatements() {
		return statements;
	}
	
	public boolean equivalent(ASTNode other) {
		if (other instanceof BlockExpr) {
			BlockExpr otherBlock = (BlockExpr) other;
			for (int i = 0; i < statements.size(); i++) {
				if(statements.get(i).equivalent(otherBlock.statements.get(i))) {
					return false;
				}
			}
			return true;
		} 
		else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "{" + stmtListToString(statements) + "}";
	}
}
