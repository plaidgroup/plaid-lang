package plaid.fastrutime.reference;

public final class MethodValue extends MemberValue {

	public MethodValue(String name) {
		super(name);
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
}
