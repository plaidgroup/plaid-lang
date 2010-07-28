package plaid.compilerjava.util;

public class MethodRep extends MemberRep {
	
	public MethodRep(String name) {
		super(name);
	}
	
	public String toString() {
		return "method " + getName();
	}
	
	public boolean equals(MemberRep m) {
		if (m instanceof MethodRep) {
			return this.getName().equals(m.getName());
		} else return false;
	}

	public String serialize() {
		return "m(" + getName() + ")";
	}
	
}
