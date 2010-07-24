package plaid.compilerjava.AST;

import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class Throws implements Expression {
	private final Token token;
	private final ID throwableID;
	
	public Throws(Token token, ID throwableID) {
		this.token = token;
		this.throwableID = throwableID;
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(ASTVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Token getToken() {
		return this.token;
	}
	
	public ID getThrowableID() {
		return this.throwableID;
	}

	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars,
			Set<ID> stateVars) {
		ID freshID = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, freshID.getName());
		
		// This code expects the object that is returned to it to have a String field named "message".
		// If it doesn't, everything will explode in a giant fireball, as you can see from the code below.
		Expression newDerefNode = new Dereference(this.throwableID, new ID("message"));
		newDerefNode.codegenExpr(out, freshID, localVars, stateVars);
		
		// WARNING: THIS IS AN ENORMOUS HACK (We should find a better way to do this in the future)
		// we declare a tautological if statement (i.e. if (true)) in order to avoid Java being angry about dead code
		// Right now, we generate code that casts a PlaidObject up to Object, then back down to PlaidJavaObject, 
		// gets the representative Java object from that, and then casts it to a String.
		out.ifCondition("true");
		out.openBlock();
		out.append("throw new RuntimeException((String)((" + CodeGen.plaidJavaObjectType + ")((Object)(" + freshID.getName() + "))).getJavaObject());");
		out.closeBlock();
	}

}
