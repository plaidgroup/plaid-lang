package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public class MethodCall extends Expr {

	private final Expr argument;
	private final List<Expr> staticArgs;
	private final Expr receiver;
	private final Identifier methodId;
	
	public MethodCall(Token token, List<Expr> staticArgs, Expr receiver, Identifier methodId, Expr argument) {
		super(token);
		this.receiver   = receiver;
		this.staticArgs = staticArgs;
		this.methodId   = methodId;
		this.argument   = argument;
	}

	public List<Expr> getStaticArgs() {
		return this.staticArgs;		
	}
	
	public Expr getArgument() {
		return argument;
	}
	
	public Expr getReceiver() {
		return receiver;
	}
	
	public Identifier getMethodId() {
		return methodId;
	}

	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		
		if ( receiver != null ) sb.append(receiver.toString() + ".");
		sb.append(methodId.toString());
		sb.append("(" + argument + ")");
		
		return sb.toString();
	}
}
