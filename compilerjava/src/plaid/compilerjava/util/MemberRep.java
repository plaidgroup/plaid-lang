package plaid.compilerjava.util;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

// TODO: Merge this with TypeDecl?
public abstract class MemberRep implements JSONAware {
	
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
//	public static final List<MemberRep> deserializeStateMembers(String members) {
//		List<MemberRep> toRet = new ArrayList<MemberRep>();
//		
//		for (String m : members.split(",")) {
//			if (m.length() != 0) {
//				if (m.startsWith("f")) toRet.add(new FieldRep(m.substring(2,m.length()-1)));
//				else if (m.startsWith("m")) toRet.add(new MethodRep(m.substring(2,m.length()-1)));
//				else if (m.startsWith("s")) toRet.add(new StateRep(m.substring(2,m.length()-1)));  //TODO : fill this out? or mark as needing to be filled out
//				else throw new RuntimeException("Bad member String, delete old source and try recompiling everything");
//			}
//		}
//		
//		return toRet;
//		
//	}
	
	/**
	 * Creates an escaped JSON representation of the member it is called on.
	 * The current format for states is:
	 * 
	 * {
	 * 	 "member_type": "state",
	 * 	 "name": "FooState",
	 *   "members": []
	 * }
	 * 
	 * The current format for methods is:
	 * {
	 *   "member_type": "method",
	 *   "name": "fooMethod",
	 * }
	 * 
	 * The current format for fields is:
	 * {
	 *   "member_type": "field",
	 *   "name", "barField"
	 * }
	 */
	public abstract String toJSONString();
	
	public static MemberRep parseJSONObject(JSONObject obj) {
		if (obj.get("member_type").equals("state")) {
			return StateRep.parseJSONObject(obj);
		}
		else if (obj.get("member_type").equals("method")) {
			return MethodRep.parseJSONObject(obj);
		}
		else if (obj.get("member_type").equals("field")) {
			return FieldRep.parseJSONObject(obj);
		}
		else {
			throw new RuntimeException("Unknown member type.");
		}
	}
	
	/**
	 * We have to write our own escape method because json-simple escapes the 
	 * '/' character, which is not a valid escape sequence in Java, for no 
	 * reason as far as I can tell.
	 * 
	 * @return
	 */
	public static final String escapeJSONString(String json) {
		return json.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'");
	}
}
