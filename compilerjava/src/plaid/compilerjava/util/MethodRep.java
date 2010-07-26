package plaid.compilerjava.util;

public class MethodRep extends MemberRep {
	
	public MethodRep(String name) {
		super(name);
	}
	
	public String toString() {
		return "method " + getName();
	}

}
