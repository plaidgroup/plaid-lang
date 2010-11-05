package plaid.compilerjava.AST;

import java.util.List;
import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;

public class MethodCall implements Expression {

	private final Token callSite;
	private final List<Expression> arguments;
	private final Expression receiver;
	private final ID method;
	
	public MethodCall(Token callSite, Expression receiver, ID method, List<Expression> arguments) {
		this.callSite = callSite;
		this.arguments = arguments;
		this.receiver = receiver;
		this.method = method;
	}

	public List<Expression> getArguments() {
		return arguments;
	}

	public Expression getReceiver() {
		return receiver;
	}
	
	public ID getMethod() {
		return method;
	}

	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars,
			Set<ID> stateVars) {
		// TODO Auto-generated method stub

	}

	@Override
	public Token getToken() {
		return callSite;
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

	@Override
	public boolean hasToken() {
		return true;
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		receiver.accept(visitor);
		method.accept(visitor);
		for (Expression e : arguments) e.accept(visitor);

	}

}
