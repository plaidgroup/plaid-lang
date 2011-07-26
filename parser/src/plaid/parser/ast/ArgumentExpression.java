package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class ArgumentExpression extends Expression {
	protected final List<Expression> args;

	public ArgumentExpression(Token token, List<Expression> args) {
		super(token);
		this.args = args;
	}

	public List<Expression> getArgs() {
		return args;
	}
}
