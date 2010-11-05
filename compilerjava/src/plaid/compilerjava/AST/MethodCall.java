package plaid.compilerjava.AST;

import java.util.List;
import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;

public class MethodCall implements Expression {

	private final Token callSite;
	private List<Expression> arguments;
	private Expression method;
	
	public MethodCall(Token callSite, Expression method, List<Expression> arguments) {
		this.callSite = callSite;
		this.arguments = arguments;
		this.method = method;
	}

	public List<Expression> getArguments() {
		return arguments;
	}

	public Expression getMethod() {
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
		method.accept(visitor);
		for (Expression e : arguments) e.accept(visitor);

	}

}
