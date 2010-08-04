package plaid.compilerjava.util;

public class FieldRep extends MemberRep {
	
	public FieldRep(String name) {
		super(name);
	}
	
	public String toString() {
		return "field " + getName();
	}
	
	public boolean equals(MemberRep m) {
		if (m instanceof FieldRep) {
			return this.getName().equals(m.getName());
		} else return false;
	}
	
	public String serialize() {
		return "f(" + getName() + ")";
	}
}
