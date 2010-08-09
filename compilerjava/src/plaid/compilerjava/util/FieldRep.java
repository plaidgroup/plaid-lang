package plaid.compilerjava.util;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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

	@Override
	public String toJSONString() {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("member_type", "field");
		obj.put("name", this.getName());
		return JSONValue.toJSONString(obj);
	}

	public static FieldRep parseJSONObject(JSONObject obj) {
		if (!obj.get("member_type").equals("field")) {
			throw new RuntimeException("Trying to parse FieldRep from non-field!");
		}
		
		FieldRep rep = new FieldRep((String)obj.get("name"));
		
		return rep;
	}
}
