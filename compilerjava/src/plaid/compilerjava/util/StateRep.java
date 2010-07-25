package plaid.compilerjava.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StateRep extends MemberRep {
	private List<String> declaredMembers = new ArrayList<String>();
	private List<String> needs = new ArrayList<String>();
	
	public StateRep(String name) {
		super(name);
	}
	
	public void addMember(String memberName) {
		declaredMembers.add(memberName);
	}
	
	public void addMembers(List<String> members) {
		declaredMembers.addAll(members);
	}
	public List<String> getMembers() {
		return Collections.unmodifiableList(declaredMembers);
	}
	
	public void addNeed(String path) {
		needs.add(path);
	}
	
	public void addNeeds(List<String> newNeeds) {
		needs.addAll(newNeeds);
	}
	
	public void setNeeds(List<String> newNeeds) {
		needs = newNeeds;
	}
	
	public void removeNeed(String path) {
		needs.remove(path);
	}
	
	public List<String> getNeeds() {
		return Collections.unmodifiableList(needs);
	}
	
	public boolean hasNeeds() {
		return !needs.isEmpty();
	}
	
	public String toString() {
		StringBuilder toRet  = new StringBuilder("state " + getName() + "(");
		for (String s : declaredMembers) toRet.append(s + ",");
		if (toRet.lastIndexOf(",") > -1)
			toRet.setCharAt(toRet.lastIndexOf(","), ')');
		else
			toRet.append(")");
		return toRet.toString();

	}
	
}
