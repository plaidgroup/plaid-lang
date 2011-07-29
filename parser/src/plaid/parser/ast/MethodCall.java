package plaid.parser.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public class MethodCall extends Expression {

	private final List<Expression> arguments = new ArrayList<Expression>();
	private final boolean hasArgs;
	private final Expression receiver;
	private final Identifier methodId;
	
	public MethodCall(Token token, Expression receiver, Identifier methodId, Expression argument) {
		super(token);
		if (argument instanceof UnitLiteral) {
			hasArgs = false;
		} else {
			this.arguments.add(argument);
			hasArgs = true;
		}
		this.receiver = receiver;
		this.methodId = methodId;
	}

	public List<Expression> getArguments() {
		return Collections.unmodifiableList(arguments);
	}
	
	public Expression getReceiver() {
		return receiver;
	}
	
	public Identifier getMethod() {
		return methodId;
	}

	public boolean hasArgs() {
		return hasArgs;
	}

	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		
		if ( receiver != null ) sb.append(receiver.toString() + ".");
		sb.append(methodId.toString());
		sb.append("(" + exprListToString(arguments) + ")");
		
		return sb.toString();
	}
}
