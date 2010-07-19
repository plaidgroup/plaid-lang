package plaid.compilerjava.AST;

import java.util.*;
import java.util.Map.Entry;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;

public class MethodTypeDecl implements TypeDecl {
	private final Token token;
	private final ID name;
	private final PermType retPermType;
	private final List<ID> argNames;
	private final List<PermType> argTypes;
	private final PermType recvTypeBefore;
	private final PermType recvTypeAfter;
	// map from variables that change typestate in the method to their before and after types
	// the list for each entry should be exactly 2 long
	private final Map<ID, List<PermType>> varTypeTrans;
	// This boolean is necessary to prevent nulls in the Plaid AST
	// If we get a method that does not indicate that any state change 
	// takes place then we will get a null varTypeTrans map.  Since we don't 
	// want to allow nulls to leak into the Plaid AST but we still need to 
	// indicate that there was no state change annotation, we set this boolean 
	// to true if we need to infer any typestate changes in the method.
	private final boolean needTypeTransInference;
	
	public MethodTypeDecl(Token token, ID name, PermType retType, List<ID> argNames, List<PermType> argTypes, PermType beforeType, PermType afterType, Map<ID, List<PermType>> varTypeTrans) {
		this.token = token;
		this.name = name;
		if (retType == null || argTypes == null || beforeType == null || afterType == null) {
			throw new RuntimeException("types cannot be null");
		}
		for (ID argName : argNames) {
			if (argName == null) {
				throw new RuntimeException("arg names cannot be null");
			}
		}
		this.argNames = argNames;
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
		
		// we use a null value for varTypeTrans in the constructor to signify that 
		// we don't know if variables change state in a method.  We don't want null
		// values to leak into the Plaid AST, so we instantiate the field and flag 
		// this issue with the needTypeTransInference boolean
		if (varTypeTrans == null) {
			this.varTypeTrans = new HashMap<ID, List<PermType>>();
			needTypeTransInference = true;
		}
		else {
			this.varTypeTrans = varTypeTrans;
			needTypeTransInference = false;
		}
	}

	@Override
	public Token getToken() {
		return this.token;
	}
	
	public ID getName() {
		return this.name;
	}
	
	public PermType getRetPermType() {
		return retPermType;
	}

	public List<ID> getArgNames() {
		return Collections.unmodifiableList(this.argNames);
	}
	
	public List<PermType> getArgTypes() {
		return Collections.unmodifiableList(this.argTypes);
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
	public void visitChildren(ASTVisitor visitor) {
		for (PermType pt : this.argTypes) {
			pt.accept(visitor);
		}
		this.retPermType.accept(visitor);
		this.recvTypeBefore.accept(visitor);
		this.recvTypeAfter.accept(visitor);
		for (Entry<ID, List<PermType>> typeTrans : this.varTypeTrans.entrySet()) {
			typeTrans.getKey().accept(visitor);
			typeTrans.getValue().get(0).accept(visitor);
			typeTrans.getValue().get(1).accept(visitor);
		}
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
