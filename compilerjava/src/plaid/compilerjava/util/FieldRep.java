package plaid.compilerjava.util;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import plaid.compilerjava.types.FieldType;

//TODO: merge this with FieldTypeDecl?
public class FieldRep extends MemberRep {
	private final FieldType type;
	
	public FieldRep(String name) {
		this(name, null);
	}
	
	public FieldRep(String name, FieldType type) {
		super(name);
		this.type = type;
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
//		if (this.type == null) {
//			//obj.put("", value);
//		}
//		else {
//			obj.put("", this.type.getPermType());
//		}
		return JSONValue.toJSONString(obj);
	}
	
	public FieldType getType() {
		return this.type;
	}
	
	public boolean hasType() {
		return this.type != null;
	}

	public static FieldRep parseJSONObject(JSONObject obj) {
		if (!obj.get("member_type").equals("field")) {
			throw new RuntimeException("Trying to parse FieldRep from non-field!");
		}
		
		FieldRep rep = new FieldRep((String)obj.get("name"));
		
		return rep;
	}
}
