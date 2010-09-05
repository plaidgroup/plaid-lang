package plaid.lang;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import plaid.compilerjava.coreparser.Token;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidState;

public class Serialization {
	protected static String TYPE = "type$";
	protected static String DATA = "data$";
	
	protected Serialization() {}
	
	public static String toJSON(PlaidObject plaidObj) {
		return convertPlaidObjectToJSONObject(plaidObj).toJSONString();
	}
	
	protected static JSONAware convertPlaidObjectToJSONObject(PlaidObject plaidObj) throws PlaidRuntimeException {
		if ( plaidObj instanceof PlaidJavaObject ) {
			Object jobj = ((PlaidJavaObject)plaidObj).getJavaObject();
			if ( (jobj instanceof java.lang.Boolean) || 
				 (jobj instanceof java.lang.Integer) || 
				 (jobj instanceof java.lang.Long) || 
				 (jobj instanceof java.lang.String) ) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(TYPE, jobj.getClass().getName());
				jsonObj.put(DATA, jobj);
				return jsonObj;
			} else if ( jobj instanceof Token || jobj instanceof HashMap || jobj instanceof Set ) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put(TYPE, jobj.getClass().getName());
				jsonObj.put(DATA, "FIXME");
				return jsonObj;
			}else {
				throw new PlaidRuntimeException("Cannot serialize Java object of type: " + jobj.getClass().getName());
			}
		} else if ( isPlaidList(plaidObj) ) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put(TYPE, "state plaid.lang.List");
			JSONArray values = new JSONArray();
			PlaidObject list = getField(plaidObj, "head");
			while (!hasState(list, "plaid.lang.Nil")) {
				values.add(convertPlaidObjectToJSONObject(getField(list, "value")));
				list = getField(list, "next");
			}
			jsonObj.put(DATA, values);
			return jsonObj;
		} else {
			JSONObject jsonObject = new JSONObject();
			JSONArray states = new JSONArray();
			for ( PlaidObject pso : plaidObj.getStates()) {
				PlaidState ps = (PlaidState)pso;
				states.add("state " + ps.getPath());
			}
			jsonObject.put(TYPE, states);
			
			// TODO: add tags 
			
			for ( Entry<PlaidMemberDef, PlaidObject> entry : plaidObj.getMembers().entrySet() ) {
				if ( !(entry.getValue() instanceof PlaidMethod) ) {
					jsonObject.put(entry.getKey().getMemberName(), convertPlaidObjectToJSONObject(entry.getValue()));
				}
			}
			return jsonObject;
		}
	}
	
	protected static PlaidObject getField(PlaidObject plaidObj, String name) {
		for ( Entry<PlaidMemberDef, PlaidObject> entry : plaidObj.getMembers().entrySet() ) {
			if (entry.getKey().getMemberName().equals(name)) {
				return entry.getValue();
			}
		}
		throw new PlaidRuntimeException("Cannot find '"+name+"' field in: " + plaidObj);
	}

	protected static boolean hasState(PlaidObject plaidObject, String state) {
		if ( plaidObject != PlaidRuntime.getRuntime().getClassLoader().unit() ) {
			for ( PlaidObject po : plaidObject.getStates() ) {
				PlaidState ps = (PlaidState)po;
				if ( ps.getPath().equals(state)) {
					return true;
				}
			}
		}
		return false;
	}

	protected static boolean isPlaidList(PlaidObject plaidObject) {
		if ( plaidObject != PlaidRuntime.getRuntime().getClassLoader().unit() ) {
			if ( plaidObject.getStates().size() == 1 ) {
				for ( PlaidObject po : plaidObject.getStates() ) {
					PlaidState ps = (PlaidState)po;
					if ( ps.getPath().equals("plaid.lang.List")) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static PlaidObject fromJSON(String json) {
		return null;
	}
}

