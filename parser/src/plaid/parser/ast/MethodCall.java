package plaid.parser.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public class MethodCall extends Expression {

	private final List<Expression> arguments = new ArrayList<Expression>();
	private final boolean hasArgs;
	private final Expression receiver;
	private final Identifier method;
	
	public MethodCall(Token token, Expression receiver, Identifier method, Expression argument) {
		super(token);
		if (argument instanceof UnitLiteral) {
			hasArgs = false;
		} else {
			this.arguments.add(argument);
			hasArgs = true;
		}
		this.receiver = receiver;
		this.method = method;
	}

	public List<Expression> getArguments() {
		return Collections.unmodifiableList(arguments);
	}
	
	public Expression getReceiver() {
		return receiver;
	}
	
	public Identifier getMethod() {
		return method;
	}

	public boolean hasArgs() {
		return hasArgs;
	}
}
