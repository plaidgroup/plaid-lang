package plaid.compilerjava.util;

import java.util.ArrayList;
import java.util.List;

public abstract class MemberRep {
	
	private String name;
	private boolean recompiling = false;
	
	public MemberRep(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void startRecompilation() {
		recompiling = true;
	}
	
	public boolean isRecompiling() {
		return recompiling;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof MemberRep) {
			return equals((MemberRep) o);
		} else return false;
	}
	
	public abstract boolean equals(MemberRep m);
	
	//convert to String for annotation purposes
	public abstract String serialize();
	
	//format of the string expected:
	//comma separated list of one of the following forms:
	//	f(<name>)  represents a field member
	//	m(<name>)  represents a method member
	//	s(<name>)  represents a state member
	public static final List<MemberRep> deserializeStateMembers(String members) {
		List<MemberRep> toRet = new ArrayList<MemberRep>();
		
		for (String m : members.split(",")) {
			if (m.length() != 0) {
				if (m.startsWith("f")) toRet.add(new FieldRep(m.substring(2,m.length()-1)));
				else if (m.startsWith("m")) toRet.add(new MethodRep(m.substring(2,m.length()-1)));
				else if (m.startsWith("s")) toRet.add(new StateRep(m.substring(2,m.length()-1)));  //TODO : fill this out? or mark as needing to be filled out
				else throw new RuntimeException("Bad member String, delete old source and try recompiling everything");
			}
		}
		
		return toRet;
		
	}
	
}
