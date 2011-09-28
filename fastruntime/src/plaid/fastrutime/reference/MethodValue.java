package plaid.fastrutime.reference;

import static plaid.fastruntime.dcg.NamingConventions.PLAID_OBJECT_DESCRIPTOR;
import plaid.fastruntime.MethodInfo;
import fj.data.List;

public final class MethodValue extends MemberValue implements MethodInfo {

	private int numArgs;
	private String classInternalName;
	
	public MethodValue(String name, int numArgs, String classInternalName) {
		super(name);
		this.numArgs = numArgs;
		this.classInternalName = classInternalName;
	}

	@Override
	public boolean uniqueTags() {
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MethodValue) {
			return this.getName().equals(((MethodValue)obj).getName());
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "method " + getName();
	}

	@Override
	public List<MethodInfo> getMethods() {
		//the fact that Java requires the MethodInfo cast is annoying
		return List.single((MethodInfo)this);
	}

	@Override
	public String getStaticClassInternalName() {
		return this.classInternalName;
	}

	@Override
	public String getMethodDescriptor() {
		StringBuilder sb = new StringBuilder("(");
		sb.append(PLAID_OBJECT_DESCRIPTOR); // receiver
		for(int i=0; i<this.numArgs; i++) {
			sb.append(PLAID_OBJECT_DESCRIPTOR); //args
		}
		sb.append(")");
		sb.append(PLAID_OBJECT_DESCRIPTOR); // return
		return sb.toString();
	}

	@Override
	public int numArgs() {
		return this.numArgs;
	}
}
