package plaid.parser.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public class MethodCall extends Expr {

	private final List<Expr> arguments = new ArrayList<Expr>();
	private final boolean hasArgs;
	private final Expr receiver;
	private final Identifier methodId;
	
	public MethodCall(Token token, Expr receiver, Identifier methodId, Expr argument) {
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

	public List<Expr> getArguments() {
		return Collections.unmodifiableList(arguments);
	}
	
	public Expr getReceiver() {
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
