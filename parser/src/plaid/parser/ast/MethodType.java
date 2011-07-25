package plaid.parser.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MethodType implements TypeDecl {
	//private final Token token;
	private final Identifier name;
	private final PermType returnType;
	private final List<ChangeType> argTypes; //Order matters
	private final ChangeType receiverType;
	private final Map<Identifier,ChangeType> environmentTypes;
	private final List<MetaType> metaTypes;
	
	//TODO: do we need this, or is there another way to signal this?
	// This boolean is necessary to prevent nulls in the Plaid AST
	// If we get a method that does not indicate that any state change 
	// takes place then we will get a null varTypeTrans map.  Since we don't 
	// want to allow nulls to leak into the Plaid AST but we still need to 
	// indicate that there was no state change annotation, we set this boolean 
	// to true if we need to infer any typestate changes in the method.
	//private final boolean needTypeTransInference;
	
	public MethodType(Identifier name, 
				      PermType returnType, 
				      List<MetaType> metaTypes,
				      List<ChangeType> argTypes, 
				      ChangeType receiverType, 
				      Map<Identifier,ChangeType> environmentTypes) {
		this.name = name;
		this.returnType = returnType;
		this.metaTypes = metaTypes;
		this.argTypes = argTypes;
		this.receiverType = receiverType;
		this.environmentTypes = environmentTypes;	
	}
	
	public MethodType(Identifier name, 
		      PermType returnType, 
		      List<ChangeType> argTypes, 
		      ChangeType receiverType, 
		      Map<Identifier,ChangeType> environmentTypes) {
		this(name, returnType, new ArrayList<MetaType>(), argTypes, receiverType, environmentTypes);
	}

//	@Override
//	public <T> T accept(ASTVisitor<T> visitor) {
//		return visitor.visitNode(this);
//	}
//
//	@Override
//	public <T> void visitChildren(ASTVisitor<T> visitor) {
//		for (PermType pt : this.argTypes) {
//			pt.accept(visitor);
//		}
//		this.retPermType.accept(visitor);
//		this.recvTypeBefore.accept(visitor);
//		this.recvTypeAfter.accept(visitor);
//		for (Entry<ID, List<PermType>> typeTrans : this.varTypeTrans.entrySet()) {
//			typeTrans.getKey().accept(visitor);
//			typeTrans.getValue().get(0).accept(visitor);
//			typeTrans.getValue().get(1).accept(visitor);
//		}
//	}

	public Identifier getName() {
		return name;
	}
	
	public PermType getReturnType() {
		return returnType;
	}

	public List<ChangeType> getArgTypes() {
		return Collections.unmodifiableList(argTypes);
	}

	public ChangeType getReceiverType() {
		return receiverType;
	}

	public List<MetaType> getMetaTypes () {
		return metaTypes;
	}
	
	public Map<Identifier, ChangeType> getEnvironmentTypes() {
		return Collections.unmodifiableMap(environmentTypes);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("method " + returnType.toString() + " " + name.getName() + "(");
		for (ChangeType argType : this.argTypes) {
			sb.append(argType.toString() + ", "); //TODO : remove trailing comma
		}
		sb.append(")[" + receiverType.toString());
		for (Identifier eVar : environmentTypes.keySet()) {
			sb.append(", " + environmentTypes.get(eVar).toString() + " " + eVar.getName());
		}
		sb.append("]");
		
		return sb.toString();
	}
}
