package plaid.parser.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import plaid.parser.Token;

public class MethodCall extends Expression {

	private final List<Expression> arguments = new ArrayList<Expression>();
	private final boolean hasArgs;
	private final Expression receiver;
	private final ID method;
	private final List<MetaArgument> metaArgs;
	
	public MethodCall(Token token, Expression receiver, ID method, Expression argument) {
		super(token);
		if (argument instanceof UnitLiteral) {
			hasArgs = false;
		} else {
			this.arguments.add(argument);
			hasArgs = true;
		}
		this.receiver = receiver;
		this.method = method;
		this.metaArgs = new ArrayList<MetaArgument>();
	}
	
	public MethodCall(Token token, Expression receiver, ID method, List<MetaArgument> metaArgs, Expression argument) {
		super(token);
		if (argument instanceof UnitLiteral) {
			hasArgs = false;
		} else {
			this.arguments.add(argument);
			hasArgs = true;
		}
		this.receiver = receiver;
		this.method = method;
		this.metaArgs = metaArgs;
	}
	
	public MethodCall(Token token, Expression receiver, ID method, List<Expression> arguments) {
		super(token);
		this.arguments.addAll(arguments);
		if (arguments.size() == 1 && arguments.get(0) instanceof UnitLiteral) {
			this.hasArgs = false;
			this.arguments.clear();
		} else {
			this.hasArgs = true;
		}
		this.receiver = receiver;
		this.method = method;
		this.metaArgs = null;
	}
	
	public MethodCall(Token token, Expression receiver, ID method, List<MetaArgument> metaArgs, List<Expression> arguments) {
		super(token);
		this.arguments.addAll(arguments);
		if (arguments.size() == 1 && arguments.get(0) instanceof UnitLiteral) {
			this.hasArgs = false;
			this.arguments.clear();
		} else {
			this.hasArgs = true;
		}
		this.receiver = receiver;
		this.method = method;
		this.metaArgs = metaArgs;
	}

	public List<Expression> getArguments() {
		return Collections.unmodifiableList(arguments);
	}

	public List<MetaArgument> getMetaArgument() {
		return metaArgs;
	}
	
	public Expression getReceiver() {
		return receiver;
	}
	
	public ID getMethod() {
		return method;
	}

	public boolean hasArgs() {
		return hasArgs;
	}
}
