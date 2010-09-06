package plaid.lang;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidState;

public class Serialization {
	protected static final String TYPE      = "type$";
	protected static final String DATA      = "data$";
	protected static final String MAP_KEY   = "key$";
	protected static final String MAP_VALUE = "value$";
	
	protected Serialization() {
	}

	public static String toJSON(PlaidObject plaidObj) {
		return convertPlaidObjectToJSONObject(plaidObj).toJSONString();
	}

	protected static JSONAware convertPlaidObjectToJSONObject(PlaidObject plaidObj) throws PlaidRuntimeException {
		if (plaidObj instanceof PlaidJavaObject) {
			Object jobj = ((PlaidJavaObject) plaidObj).getJavaObject();
			return convertJavaObjectToJSONObject(jobj);
		} else if (isPlaidList(plaidObj)) {
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
			for (PlaidObject pso : plaidObj.getStates()) {
				PlaidState ps = (PlaidState) pso;
				states.add("state " + ps.getPath());
			}
			jsonObject.put(TYPE, states);

			// TODO: add tags

			for (Entry<PlaidMemberDef, PlaidObject> entry : plaidObj
					.getMembers().entrySet()) {
				if (!(entry.getValue() instanceof PlaidMethod)) {
					jsonObject.put(entry.getKey().getMemberName(), convertPlaidObjectToJSONObject(entry.getValue()));
				}
			}
			return jsonObject;
		}
	}

	protected static JSONAware convertJavaObjectToJSONObject(Object javaObj)
			throws PlaidRuntimeException {
		if ((javaObj instanceof java.lang.Boolean)
				|| (javaObj instanceof java.lang.Integer)
				|| (javaObj instanceof java.lang.Long)
				|| (javaObj instanceof java.lang.String)) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put(TYPE, javaObj.getClass().toString());
			jsonObj.put(DATA, javaObj);
			return jsonObj;
		} else if (javaObj instanceof Map) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put(TYPE, javaObj.getClass().toString());
			JSONArray data = new JSONArray();
			for (Map.Entry e : (Set<Map.Entry>)((Map)javaObj).entrySet() ) {
				JSONObject elem = new JSONObject();
				elem.put(TYPE, Map.Entry.class.getName());
				JSONAware key;
				if ( e.getKey() instanceof PlaidObject ) {
					key = convertPlaidObjectToJSONObject(((PlaidObject)e.getKey()));
				} else {
					key = convertJavaObjectToJSONObject(e.getKey());
				}
				
				JSONAware value;
				if ( e.getValue() instanceof PlaidObject ) {
					value = convertPlaidObjectToJSONObject(((PlaidObject)e.getValue()));
				} else {
					value = convertJavaObjectToJSONObject(e.getValue());
				}
				elem.put(MAP_KEY, key);
				elem.put(MAP_VALUE, value);
				data.add(elem);
			}
			jsonObj.put(DATA, data);
			return jsonObj;
		} else if (javaObj instanceof Set) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put(TYPE, javaObj.getClass().toString());
			JSONArray data = new JSONArray();
			for (Object o : ((Set) javaObj)) {
				if (o instanceof PlaidObject) {
					data.add(convertPlaidObjectToJSONObject((PlaidObject) o));
				} else {
					data.add(convertJavaObjectToJSONObject(o));
				}
			}
			jsonObj.put(DATA, data);
			return jsonObj;
		} else {
			throw new PlaidRuntimeException("Cannot serialize Java object of type: " + javaObj.getClass().getName());
		}
	}

	protected static PlaidObject getField(PlaidObject plaidObj, String name) {
		for (Entry<PlaidMemberDef, PlaidObject> entry : plaidObj.getMembers()
				.entrySet()) {
			if (entry.getKey().getMemberName().equals(name)) {
				return entry.getValue();
			}
		}
		throw new PlaidRuntimeException("Cannot find '" + name + "' field in: "
				+ plaidObj);
	}

	protected static boolean hasState(PlaidObject plaidObject, String state) {
		if (plaidObject != PlaidRuntime.getRuntime().getClassLoader().unit()) {
			for (PlaidObject po : plaidObject.getStates()) {
				PlaidState ps = (PlaidState) po;
				if (ps.getPath().equals(state)) {
					return true;
				}
			}
		}
		return false;
	}

	protected static boolean isPlaidList(PlaidObject plaidObject) {
		if (plaidObject != PlaidRuntime.getRuntime().getClassLoader().unit()) {
			if (plaidObject.getStates().size() == 1) {
				for (PlaidObject po : plaidObject.getStates()) {
					PlaidState ps = (PlaidState) po;
					if (ps.getPath().equals("plaid.lang.List")) {
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
