package plaid.compilerjava.AST;

import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class MethodCall implements Expression {

	private final Token callSite;
	private final Expression argument;
	private final Expression receiver;
	private final ID method;
	
	public MethodCall(Token callSite, Expression receiver, ID method, Expression argument) {
		this.callSite = callSite;
		this.argument = argument;
		this.receiver = receiver;
		this.method = method;
	}
	
//	public MethodCall(Token callSite, Expression receiver, ID method, Expression argument) {
//		this.callSite = callSite;
//		this.arguments = new ArrayList<Expression>();
//		this.arguments.add(argument);
//		this.receiver = receiver;
//		this.method = method;
//	}

	public Expression getArgument() {
		return argument;
	}

	public Expression getReceiver() {
		return receiver;
	}
	
	public ID getMethod() {
		return method;
	}

	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {
		out.setLocation(callSite);
		
		// Evaluate the receiver.
		ID o = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, o.getName());
		receiver.codegenExpr(out, o, localVars, stateVars);
		
		// Lookup the method.
		ID m = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, m.getName());
		out.assignToLookup(m.getName(), CodeGen.convertOpNames(method.getName()), o.getName());
		
		// Evaluate the argument.
		ID a = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, a.getName());
		argument.codegenExpr(out, a, localVars, stateVars);
		
		// Call the method.
		ID p = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, p.getName());
		out.assignToCall(y.getName(), m.getName(), a.getName());
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
		return callSite != null;
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		receiver.accept(visitor);
		method.accept(visitor);
		argument.accept(visitor);
	}
}
