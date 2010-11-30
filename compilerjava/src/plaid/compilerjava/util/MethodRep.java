package plaid.compilerjava.util;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import plaid.compilerjava.types.MethodType;

// TODO: merge this with MethodTypeDecl?
public class MethodRep extends MemberRep {
	private final MethodType type;
	
	public MethodRep(String name) {
		this(name, null);
	}
	
	public MethodRep(String name, MethodType type) {
		super(name);
		this.type = type;
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
//		if (this.type == null) {
//			obj.put("ret_type", null);
//			obj.put("", null);
//		}
//		else {
//			obj.put("ret_type", this.type.getRetPermType());
//		}
		return JSONValue.toJSONString(obj);
	}
	
	public MethodType getType() {
		return this.type;
	}
	
	public boolean hasType() {
		return this.type != null;
	}

	public static MethodRep parseJSONObject(JSONObject obj) {
		if (!obj.get("member_type").equals("method")) {
			throw new RuntimeException("Trying to parse MethodRep from non-method!");
		}
		
//		PermType retType = (PermType)obj.get("ret_type");
		
		MethodRep rep = new MethodRep((String)obj.get("name"));
		
		return rep;
	}
}
