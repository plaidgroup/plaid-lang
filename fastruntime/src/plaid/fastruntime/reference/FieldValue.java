package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.PlaidStorage;
import fj.data.List;

public final class FieldValue extends MemberValue implements FieldInfo {

	public FieldValue(String name, String classInternalName) {
		super(name, classInternalName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FieldValue) {
			return this.getName().equals(((FieldValue)obj).getName());
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "val " + getName();
	}

	@Override
	public List<MethodInfo> getMethods() {
		return List.nil();
	}

	@Override
	public PlaidStorage getDefaultStorage() {
		// TODO Auto-generated method stub
		return null;
	}
}
