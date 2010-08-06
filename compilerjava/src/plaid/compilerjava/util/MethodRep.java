package plaid.compilerjava.util;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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

	@Override
	public String toJSONString() {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("member_type", "method");
		obj.put("name", this.getName());
		return JSONValue.toJSONString(obj);
	}

	public static MethodRep parseJSONObject(JSONObject obj) {
		if (!obj.get("member_type").equals("method")) {
			throw new RuntimeException("Trying to parse MethodRep from non-method!");
		}
		
		MethodRep rep = new MethodRep((String)obj.get("name"));
		
		return rep;
	}
	
}
