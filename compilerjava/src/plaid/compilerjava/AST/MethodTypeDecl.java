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
		if (retType == null || argTypes == null || beforeType == null || afterType == null) {
			throw new RuntimeException("types cannot be null");
		}
		for (PermType pt : argTypes) {
			if (pt == null) {
				throw new RuntimeException("arg types cannot be null");
			}
		}
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
		visitor.visitNode(this);
	}

	@Override
	public Token getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitChildren(ASTVisitor visitor) {
		for (PermType pt : this.argTypes) {
			pt.accept(visitor);
		}
		this.retPermType.accept(visitor);
		this.recvTypeBefore.accept(visitor);
		this.recvTypeAfter.accept(visitor);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Method: {");
		for (PermType argType : this.argTypes) {
			sb.append(argType.toString() + " -> ");
		}
		sb.append(this.retPermType.toString());
		sb.append(", ");
		sb.append("[" + this.recvTypeBefore + " >> " + this.recvTypeAfter + "]}");
		return sb.toString();
	}
}
