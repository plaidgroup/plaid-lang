package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class SplitBlock extends Expression {

	private final List<Expression> datagroups;
	private final Expression body;
	
	public SplitBlock(Token token, List<Expression> datagroups, Expression body) {
		super(token);
		this.datagroups = datagroups;
		this.body = body;
	}
	
	public List<Expression> getDatagroups() {
		return datagroups;
	}

	public Expression getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "split<"+exprListToString(datagroups)+">" + body.toString();			
	}
}


