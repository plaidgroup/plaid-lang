package plaid.compilerjava.util;

import java.util.ArrayList;
import java.util.List;

public class StateRep implements MemberRep {
	private String name;
	private List<String> declaredMembers = new ArrayList<String>();
	
	public StateRep(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addMember(String memberName) {
		declaredMembers.add(memberName);
	}
	
	
}
