package plaid.compilerjava.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StateRep extends MemberRep {
	private List<MemberRep> declaredMembers = new ArrayList<MemberRep>();
	private List<String> needs = new ArrayList<String>();
//	private Map<String,MemberRep> decalaredFields = new HashMap<String,MemberRep>();
//	private Map<String,MemberRep> declareMethods = new HashMap<String,MemberRep>();
//	private Map<String,MemberRep> decalaredStates = new HashMap<String,MemberRep>();
	
	
	public StateRep(String name) {
		super(name);
	}
	
	public void addMember(MemberRep rep) {
		declaredMembers.add(rep);
	}
	
	public void addMembers(List<MemberRep> members) {
		declaredMembers.addAll(members);
	}
	public List<MemberRep> getMembers() {
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
		for (MemberRep m : declaredMembers) {
			String memberKind = "";
			if (m instanceof FieldRep) memberKind = "f";
			else if (m instanceof MethodRep) memberKind = "m";
			else if (m instanceof StateRep) memberKind = "s";
			toRet.append(memberKind + "(" + m.getName() + "),");
		}
		if (toRet.lastIndexOf(",") > -1)
			toRet.setCharAt(toRet.lastIndexOf(","), ')');
		else
			toRet.append(")");
		if (!needs.isEmpty()) {
			toRet.append(" Needs(");
			for (String n : needs) toRet.append(n + ",");
			toRet.setCharAt(toRet.lastIndexOf(","), ')');
		}
		return toRet.toString();

	}
	
	public boolean equals(MemberRep m) {
		if (m instanceof StateRep) {
			return this.getName().equals(m.getName());
		} else return false;
	}
	
	public String serialize() {
		return "s(" + getName() + ")";
	}
	
}
