package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.NamingConventions;
import plaid.fastruntime.ObjectValue;
import fj.data.List;

public final class MethodValue extends MemberValue implements MethodInfo {

	private final int numArgs;
	private final String canonicalRep;
	
	
	public MethodValue(String name, int numArgs, String classInternalName) {
		super(name, classInternalName);
		this.numArgs = numArgs;
		canonicalRep = this.constructCanonicalRep();
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
	
	@Override
	public ObjectValue rename(String currentName, String newName) {
		if(this.getName().equals(currentName)) {
			return new MethodValue(newName, this.numArgs, this.getStaticClassInternalName());
		} else {
			return this;
		}
	}

	@Override
	protected String constructCanonicalRep() {
		String result =  "method:" + this.getName() + this.numArgs + this.getStaticClassInternalName();
		return result.intern();
	}

	@Override
	public String getCanonicalRep() {
		return canonicalRep;
	}

}
