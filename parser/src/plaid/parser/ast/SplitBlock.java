package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class SplitBlock extends Expression {

	private final List<Expression> datagroups;
	private final List<Stmt> body;
	
	public SplitBlock(Token token, List<Expression> datagroups, List<Stmt> body) {
		super(token);
		this.datagroups = datagroups;
		this.body = body;
		if ( this.body.isEmpty() ) {
			this.body.add(new UnitLiteral(DEFAULT_TOKEN));
		}
	}
	
	public List<Expression> getDatagroups() {
		return datagroups;
	}

	public List<Stmt> getBody() {
		return body;
	}
}


