package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.NamingConventions;
import fj.data.List;

public final class MethodValue extends MemberValue implements MethodInfo {

	private int numArgs;
	
	
	public MethodValue(String name, int numArgs, String classInternalName) {
		super(name, classInternalName);
		this.numArgs = numArgs;
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
	public List<FieldInfo> getFields() {
		return List.nil();
	}

	@Override
	public String getMethodDescriptor() {
		return NamingConventions.getMethodDescriptor(numArgs + 1);
	}

	@Override
	public int numArgs() {
		return this.numArgs;
	}


}
