package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class SplitBlock extends Expression {

	private final List<Expression> datagroups;
	private final List<Expression> body;
	
	public SplitBlock(Token token, List<Expression> datagroups, List<Expression> body) {
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

	public List<Expression> getBody() {
		return body;
	}
}


