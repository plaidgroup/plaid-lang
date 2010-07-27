package plaid.compilerjava.util;

public class FieldRep extends MemberRep {
	
	public FieldRep(String name) {
		super(name);
	}
	
	public String toString() {
		return "field " + getName();
	}
}
