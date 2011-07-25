package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class SplitBlock extends Expression {

	private final List<Expression> body;

	public SplitBlock(Token token, List<Expression> body) {
		super(token);
		this.body = body;
		if ( this.body.isEmpty() ) {
			this.body.add(new UnitLiteral(DEFAULT_TOKEN));
		}
	}


	@Override
	public Token getToken() {
		return token;
	}

	public List<Expression> getBody() {
		return body;
	}
}


