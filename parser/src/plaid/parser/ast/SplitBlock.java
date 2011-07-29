package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class SplitBlock extends Expr {

	private final List<Expr> datagroups;
	private final Expr body;
	
	public SplitBlock(Token token, List<Expr> datagroups, Expr body) {
		super(token);
		this.datagroups = datagroups;
		this.body = body;
	}
	
	public List<Expr> getDatagroups() {
		return datagroups;
	}

	public Expr getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "split<"+exprListToString(datagroups)+">" + body.toString();			
	}
}


