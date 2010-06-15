package plaid.compilerjava.AST;

import java.util.*;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;

public class MethodTypeDecl implements TypeDecl {
	private final PermType retPermType;
	private final List<PermType> argTypes;
	private final PermType recvTypeBefore;
	private final PermType recvTypeAfter;
	
	public MethodTypeDecl(PermType retType, List<PermType> argTypes, PermType beforeType, PermType afterType) {
		this.retPermType = retType;
		this.argTypes = new ArrayList<PermType>();
		this.argTypes.addAll(argTypes);
		this.recvTypeBefore = beforeType;
		this.recvTypeAfter = afterType;
	}

	public PermType getRetPermType() {
		return retPermType;
	}

	public List<PermType> getArgTypes() {
		return Collections.unmodifiableList(argTypes);
	}

	public PermType getRecvTypeBefore() {
		return recvTypeBefore;
	}

	public PermType getRecvTypeAfter() {
		return recvTypeAfter;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
