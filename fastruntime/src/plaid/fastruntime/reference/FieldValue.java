package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import fj.data.List;

public final class FieldValue extends MemberValue implements FieldInfo {

	private final boolean settable;
	
	public FieldValue(boolean settable, String name, String classInternalName) {
		super(name, classInternalName);
		this.settable = settable;
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
	public List<FieldInfo> getFields() {
		return List.single((FieldInfo) this);
	}

	@Override
	public int compareTo(FieldInfo o) {
		return  this.getName().compareTo(o.getName());
	}

	public boolean isSettable() {
		return settable;
	}
}
