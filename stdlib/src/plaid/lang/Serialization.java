package plaid.lang;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidState;
import plaid.runtime.Util;

@SuppressWarnings("unchecked")
public class Serialization {
	protected static final String TYPE      = "type$";
	protected static final String DATA      = "data$";
	protected static final String MAP_KEY   = "key$";
	protected static final String MAP_VALUE = "value$";
	
	protected Serialization() {
	}

	public static PlaidObject toJSON(PlaidObject plaidObj) {
		try {
			String jsonString = convertPlaidObjectToJSONObject(plaidObj).toJSONString();
			return Util.some(Util.string(jsonString));
		} catch (Throwable e ) {			
			return Util.none();
		}
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

			for (Entry<String, PlaidMemberDef> entry : plaidObj.getMembers().entrySet()) {
				if (!(entry.getValue().getValue() instanceof PlaidMethod)) {
					jsonObject.put(entry.getValue().getMemberName(), convertPlaidObjectToJSONObject(entry.getValue().getValue()));
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
		for (Entry<String, PlaidMemberDef> entry : plaidObj.getMembers().entrySet()) {
			if (entry.getValue().getMemberName().equals(name)) {
				return entry.getValue().getValue();
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
		Object obj = JSONValue.parse(json);
		if ( obj instanceof JSONObject ) {
			try {
				PlaidObject plaidObject = convertJSONObjectToPlaidObject((JSONObject)obj);
				return Util.some(plaidObject);
			} catch (Throwable ex){
				return Util.none();
			}
		} else {
			return Util.none();
		}
	}
	
	protected static PlaidObject convertJSONObjectToPlaidObject(JSONObject jsonObj) {
		final Object type = jsonObj.get(TYPE);
		if ( type == null ) {
			throw new PlaidRuntimeException("JSONObject has no type.");
		}
		if ( type instanceof String ) {
			if ( ((String) type).startsWith("class") ) {
				if ( ((String)type).equals("class java.lang.String")) {
					return plaid.runtime.Util.string((String)jsonObj.get(DATA));
				} else if ( ((String)type).equals("class java.lang.Integer") || 
						    ((String)type).equals("class java.lang.Long") ) {
					if ( jsonObj.get(DATA) instanceof Integer ) {
						return plaid.runtime.Util.integer((java.lang.Integer)jsonObj.get(DATA));
					} else if ( jsonObj.get(DATA) instanceof Long ) {
						return plaid.runtime.Util.integer(((Long)jsonObj.get(DATA)).intValue());
					} else {
						throw new PlaidRuntimeException("Invalid numerical value : " + jsonObj.get(DATA).getClass().toString());
					}
				} else if (  ((String)type).equals("class java.util.HashSet") ) {
					final HashSet newHashSet = new HashSet();
					JSONArray data = (JSONArray)jsonObj.get(DATA);
					for ( Object obj : data ) {
						newHashSet.add(convertJSONObjectToPlaidObject((JSONObject)obj));
					}
					return PlaidRuntime.getRuntime().getClassLoader().packJavaObject(newHashSet);
				} else if (  ((String)type).equals("class java.util.HashMap") ) {
					final HashMap<Object, Object> newHashMap = new HashMap<Object, Object>();
					JSONArray data = (JSONArray)jsonObj.get(DATA);
					for ( Object obj : data ) {
						newHashMap.put(convertJSONObjectToPlaidObject((JSONObject)((JSONObject)obj).get(MAP_KEY)), 
								       convertJSONObjectToPlaidObject((JSONObject)((JSONObject)obj).get(MAP_VALUE)));
					}
					return PlaidRuntime.getRuntime().getClassLoader().packJavaObject(newHashMap);
				} else {
					throw new PlaidRuntimeException("Cannot convert : " + type.getClass());
				}
			} else if (  ((String)type).startsWith("state") ) {
				if ( ((String)type).equals("state plaid.lang.List")) {
					final PlaidState listState = getPlaidState("plaid.lang.List");
					final PlaidState consState = getPlaidState("plaid.lang.Cons");
					final PlaidObject nil      = getPlaidState("plaid.lang.Nil").instantiate();
					PlaidObject current = nil;
					//for ( Object value : ((JSONArray)jsonObj.get(DATA)). ) {
					JSONArray data = (JSONArray)jsonObj.get(DATA);
					for ( int i = data.size(); 0 < i ; i-- ) {
						final Object value = data.get(i-1);
						final PlaidState values = plaid.runtime.Util.newState();
						final PlaidObject elem = convertJSONObjectToPlaidObject((JSONObject)value);
						values.addMember(Util.anonymousMemberDef("value", true, false), elem);
						values.addMember(Util.anonymousMemberDef("next", true, false), current);
						current = consState.initState(values).instantiate();
					}
					final PlaidState listValue = Util.newState();
					listValue.addMember(Util.anonymousMemberDef("head", true, false), current);
					return listState.initState(listValue).instantiate();
				} else {
					throw new PlaidRuntimeException("Cannot convert plaid state : " + type);
				}
			} else {
				throw new PlaidRuntimeException("Unsupported type : " + type);
			}
		} else if ( type instanceof JSONArray ) {
			final JSONArray typeArray = (JSONArray)type;
			final String stateName = ((String)typeArray.get(typeArray.size()-1)).substring("state ".length());
			final PlaidState typeState = getPlaidState(stateName);
			
			// populate fields
			final PlaidState values = plaid.runtime.Util.newState();
			for ( Entry<String, PlaidMemberDef> member : (Set<Entry<String, PlaidMemberDef>>)typeState.getMembers().entrySet() ) {
				if ( !(member.getValue().getValue() instanceof PlaidMethod ) ) {
					final PlaidMemberDef memDef = member.getValue();
					//System.out.println("Look for field " + memDef.getMemberName() );
					final JSONObject fieldValue = (JSONObject)jsonObj.get(memDef.getMemberName());
					if ( fieldValue == null ) {
						throw new PlaidRuntimeException("Cannot find field '"+memDef.getMemberName()+"' for " + stateName);
					}
					final PlaidMemberDef fieldMemDef = plaid.runtime.Util.anonymousMemberDef(memDef.getMemberName(), memDef.isMutable(), false);
					PlaidObject result = convertJSONObjectToPlaidObject(fieldValue);
					values.addMember(fieldMemDef, result);
				}
			}
			
			final PlaidObject instance = typeState.initState(values).instantiate();
			return instance;
		} else {
			throw new PlaidRuntimeException("Unsupported type : " + type);
		}
	}
	
	protected static PlaidState getPlaidState(String stateName) {
		//System.out.println("Instantiate: " + stateName );
		final PlaidObject stateObj = plaid.runtime.Util.lookup(stateName, plaid.runtime.Util.unit());
		return plaid.runtime.Util.toPlaidState(stateObj);
	}
}
