/**
 * Copyright (c) 2010 The Plaid Group (see AUTHORS file)
 * 
 * This file is part of Plaid Programming Language.
 *
 * Plaid Programming Language is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *  Plaid Programming Language is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>.
 */
package plaid.runtime;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

import plaid.runtime.BigRational;
import plaid.runtime.models.map.PlaidLookupMap;
import plaid.runtime.models.map.PlaidStateMap;
import plaid.runtime.utils.Delegate;

public class Util {
	public static final String thisVar = "this";
	private static PlaidRuntime rt = PlaidRuntime.getRuntime();
    private static PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
	protected static AtomicReferenceArray<PlaidObject> integerCache = new AtomicReferenceArray<PlaidObject>(256);
    protected static volatile PlaidObject TRUE; 
    protected static volatile PlaidObject FALSE;
    protected static AtomicReference<PlaidObject> NONE = new AtomicReference<PlaidObject>();
	public static final PlaidTag javaObjectTag = tag("Object", "java.util", null);

	private static final Map<Class<?>,String> java2PlaidTypeMap;
	private static final Map<String,Class<?>> plaid2JavaTypeMap;

	private static void addTypeMapping(Map<Class<?>,String> j2p, Map<String,Class<?>> p2j, Class<?> jType, String pType) {
		j2p.put(jType, pType);
		p2j.put(pType, jType);
	}

	static {
		HashMap<Class<?>,String> j2p = new HashMap<Class<?>,String>();
		HashMap<String,Class<?>> p2j = new HashMap<String,Class<?>>();
		addTypeMapping(j2p, p2j, Double.class,     "plaid.lang.Float64");
		addTypeMapping(j2p, p2j, Float.class,      "plaid.lang.Float32");
		addTypeMapping(j2p, p2j, Long.class,       "plaid.lang.Int64");
		addTypeMapping(j2p, p2j, Integer.class,    "plaid.lang.Int32");
		addTypeMapping(j2p, p2j, Short.class,      "plaid.lang.Int16");
		addTypeMapping(j2p, p2j, Byte.class,       "plaid.lang.Int8");
		addTypeMapping(j2p, p2j, BigInteger.class, "plaid.lang.Integer");
		addTypeMapping(j2p, p2j, BigDecimal.class, "plaid.lang.Decimal");
		addTypeMapping(j2p, p2j, BigRational.class, "plaid.lang.Rational");
		addTypeMapping(j2p, p2j, Character.class,  "plaid.lang.Character");

		java2PlaidTypeMap = Collections.unmodifiableMap(j2p);
		plaid2JavaTypeMap = Collections.unmodifiableMap(p2j);
	}
    
	public static PlaidObject newObject() throws PlaidException {
		PlaidState object = (PlaidState)cl.lookup("plaid.lang.Object", unit());
		PlaidObject value = (PlaidObject)object.instantiate();
		return value;
	}

	public static PlaidState newState() throws PlaidException {
		return cl.state();
	}
	
	public static PlaidObject none() throws PlaidException {
		if ( NONE.get() == null ) {
			PlaidObject none = (PlaidObject)cl.lookup("plaid.lang.globals.NONE", unit());
			NONE.compareAndSet(null, none);
		}
		return NONE.get();
	}

	public static PlaidObject some(final PlaidObject value) throws PlaidException {
		PlaidState someState = (PlaidState)cl.lookup("plaid.lang.Some", unit());
		PlaidState init = newState();
		PlaidMemberDef mdef = memberDef("value", null, false, true);
		init.addMember(mdef, value);
		PlaidState initState = (PlaidState)someState.initState(init);
		return initState.instantiate();		
	}
	
	
	public static PlaidObject string(String s) throws PlaidException {
		PlaidState intState = (PlaidState)cl.lookup("plaid.lang.String", unit());
		PlaidJavaObject value = (PlaidJavaObject)intState.instantiate();
		value.setJavaObject(s);
		return value;
	}

	public static PlaidObject integer(int i) throws PlaidException {
		return integer(BigInteger.valueOf(i));
	}
	
	public static PlaidObject integer(java.math.BigInteger i) throws PlaidException {
		BigInteger cacheSize = BigInteger.valueOf(integerCache.length());
		if ( i.compareTo(BigInteger.ZERO) >= 0 && i.compareTo(cacheSize) < 0 ) {
			int intValue = i.intValue();
			PlaidObject value = integerCache.get(intValue);
			if ( value == null ) {
				value = buildInteger(i);
				if ( !integerCache.weakCompareAndSet(intValue, null, value) ) {
					value = integerCache.get(intValue);
				}
			} 
			return value;
		} else {
			return buildInteger(i);
		}
	}
	
	private static PlaidObject buildInteger(BigInteger i) {
		return buildBasic(i);
	}

	public static boolean isOfSupportedBasicType(Object o) {
		return java2PlaidTypeMap.containsKey(o.getClass());
	}

	/**
	 * Used to construct Plaid types which are simple wrappers around the Java equivalent.
	 */
	public static PlaidObject buildBasic(Object nativeVal) {
		Class<?> javaType = nativeVal.getClass();
		String plaidType = java2PlaidTypeMap.get(javaType);
		PlaidState intState = toPlaidState(cl.lookup(plaidType, unit()));
		PlaidState init = newState();
		PlaidMemberDef mdef = memberDef("nativeVal", null, false, true);
		init.addMember(mdef, cl.packJavaObject(nativeVal));
		PlaidState initState = (PlaidState)intState.initState(init);
		return initState.instantiate();
	}
	
	public static PlaidObject floatingDouble(Double d) throws PlaidException {
		return buildBasic(d);
	}

	public static PlaidObject character(Character c) throws PlaidException {
		return buildBasic(c);
	}
	
	public static PlaidObject boolObject(boolean b) throws PlaidException {
		if (b) { return trueObject(); }
		else { return falseObject(); }
	}
	
	public static PlaidObject trueObject() throws PlaidException {
        if ( TRUE == null ) {
            synchronized(Util.class) {
                if ( TRUE == null ) {
                    PlaidState trueState = toPlaidState(cl.lookup("plaid.lang.True", unit()));
                    TRUE =(PlaidObject)trueState.instantiate();
                }
            }
        }
        return TRUE;
	}
	
	public static PlaidObject falseObject() throws PlaidException {
        if ( FALSE == null ) {
            synchronized(Util.class) {
                if ( FALSE == null ) {
                    PlaidState trueState = toPlaidState(cl.lookup("plaid.lang.False", unit()));
                    FALSE =(PlaidObject)trueState.instantiate();
                }
            }
        }
        return FALSE;
	}
	
	public static PlaidTag getTag(String tagPath) throws PlaidException {
		PlaidState tagState = toPlaidState(cl.lookup(tagPath, unit()));
		return tagState.getTag();
	}

	public static PlaidObject unit() throws PlaidException {
		return cl.unit();
	}
	
	public static PlaidObject call(PlaidObject func, PlaidObject args) {
		return rt.call(func, args);
	}
	
	public static PlaidTailCall tailCall(PlaidObject method, PlaidObject args) {
		return cl.tailCall(method, args);
	}
	
	public static PlaidMethod protoField(Delegate dlg) {
		return cl.protoField(dlg);
	}
	
	public static PlaidMethod protoMethod(String fullyQualName, Delegate dlg) {
		return cl.protoMethod(fullyQualName, dlg);
	}
	
	public static PlaidMemberDef memberDef(String memberName, PlaidTag definedIn, boolean mutable, boolean overrides) {
		return cl.memberDef(memberName, false, definedIn, mutable, overrides);
	}
	
	public static PlaidMemberDef memberDef(PlaidMemberDef other) {
		return cl.memberDef(other.getMemberName(), false, other.definedIn(), other.isMutable(), other.overrides());
	}
	
	public static PlaidMemberDef anonymousMemberDef(String memberName, boolean mutable, boolean overrides) {
		return cl.memberDef(memberName, true, null, mutable, overrides);
	}
	
	public static PlaidObject lookup(String name, PlaidObject thisVar) {
		return cl.lookup(name, thisVar);
	}
	
	public static PlaidObject lookup(String name, PlaidScope scope) {
		return cl.lookup(name, scope);
	}
	
	public static PlaidTag tag(String tag, String pkg, PlaidTag superTag) {
		return cl.tag(tag, pkg, superTag);
	}
	
	public static Object[] convertParamsToArray(PlaidObject params) throws PlaidException {
		List<Object> objs = new ArrayList<Object>();
		if (params.getStates().contains(lookup("plaid.lang.Pair", unit()))) {
			PlaidObject elem = params;
			while (elem != unit()) {
				if (elem.getStates().contains(lookup("plaid.lang.Pair", unit()))) {
					PlaidObject fst = null;
					PlaidObject snd = null;
					for (PlaidMemberDef m: elem.getMembers().values()) {
						if (m.getMemberName().equals("fst")) {
							fst = elem.getMembers().get(m.getMemberName()).getValue();
						} else if (m.getMemberName().equals("snd")) {
							snd = elem.getMembers().get(m.getMemberName()).getValue();
						}
					}		
					if (fst instanceof PlaidJavaObject) {
						objs.add(((PlaidJavaObject)fst).getJavaObject());
						elem = snd;
					}
					else {
						boolean added = false;
						if ( fst instanceof PlaidObject ) {
							for ( PlaidObject po : fst.getStates() ) {
								PlaidState ps = (PlaidState)po;
								if ( ps.getPath().equals("plaid.lang.True")) {
									objs.add(Boolean.TRUE);
									added = true;
									break;
								} else if (ps.getPath().equals("plaid.lang.False")) {
									objs.add(Boolean.FALSE);
									added = true;
									break;
								}
							}
							if (((PlaidObject)fst).getTags() != null ) {
								for (Object o : ((PlaidObject)fst).getTags().keySet() ) {
									PlaidTag tag = (PlaidTag)o;
									if(plaid2JavaTypeMap.containsKey(tag.getPath())) {
										objs.add((((PlaidJavaObject)(fst.getMember("nativeVal").getValue())).getJavaObject()));
										added = true;
										break;
									} 
								}
							}
						}
						if ( !added ) {
							objs.add(fst);
						}
						elem = snd;						
					}
				}
			}
		} else if (params instanceof PlaidJavaObject) {
			objs.add(((PlaidJavaObject)params).getJavaObject());
		} else if ( params instanceof PlaidObject ) {
			// check if the plaid object has a Java representation
			for ( PlaidObject po : params.getStates() ) {
				PlaidState ps = (PlaidState)po;
				if ( ps.getPath().equals("plaid.lang.True")) {
					objs.add(Boolean.TRUE);
				} else if (ps.getPath().equals("plaid.lang.False")) {
					objs.add(Boolean.FALSE);
				}				
			}

			if (((PlaidObject)params).getTags() != null ) {
				for (Object o : ((PlaidObject)params).getTags().keySet() ) {
					PlaidTag tag = (PlaidTag)o;
					if(plaid2JavaTypeMap.containsKey(tag.getPath())) {
						objs.add((((PlaidJavaObject)(params.getMember("nativeVal").getValue())).getJavaObject()));
						break;
					} 
				}
			}
			if ( objs.isEmpty() ) {
				objs.add(params);
			}
		} else {
			objs.add(params);
		}

		return objs.toArray();
	}
	
	public static PlaidObject packPlaidObjectsIntoArray(PlaidObject... objs) {
		if (objs.length == 0) {
			return unit();
		}
		PlaidState ps = toPlaidState(lookup("plaid.lang.Pair", unit()));
		PlaidObject result = ps.instantiate();
		result.addMember(Util.anonymousMemberDef("fst", false, false), objs[0]);
		result.addMember(Util.anonymousMemberDef("snd", false, false), convertArrayToParams(Arrays.copyOfRange(objs, 1, objs.length)));
		
		return result;
	}
	
	public static PlaidObject convertArrayToParams(Object[] objs) {
		if ( objs.length == 0 ) {
			return unit();
		}
		PlaidState ps = toPlaidState(lookup("plaid.lang.Pair", unit()));
		PlaidObject result = ps.instantiate();
		result.addMember(Util.anonymousMemberDef("fst", false, false), cl.packJavaObject(objs[0]));
		result.addMember(Util.anonymousMemberDef("snd", false, false), convertArrayToParams(Arrays.copyOfRange(objs, 1, objs.length)));
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static Class convertPrimitiveTypes(Class type) {
		if ( type.isPrimitive() ) {
			if ( type.getName().equals("int") ) {
				return Integer.class;
			} else if ( type.getName().equals("boolean") ) {
				return Boolean.class;
			} else if ( type.getName().equals("byte") ) {
				return Byte.class;
			} else if ( type.getName().equals("float")) {
				return Float.class;
			} else if ( type.getName().equals("char")) {
				return Character.class;
			} else if ( type.getName().endsWith("double")) {
				return Double.class;
			} else if ( type.getName().endsWith("short")) {
				return Short.class;
			} else if ( type.getName().endsWith("void")) {
				return Void.class;
			} else if ( type.getName().endsWith("long")) {
				return Long.class;
			}
		}
		return type;
	}

	public static boolean isKeyword(String w) {
		for ( String kw : PlaidConstants.JAVA_KEYWORDS ) {
			if ( kw.equals(w) ) {
				return true;
			} 
		}
		return false;
	}

	public static PlaidJavaObject toPlaidJavaObject(PlaidObject obj) throws PlaidCastException {
		if ( obj instanceof PlaidJavaObject ) {
			return (PlaidJavaObject)obj;
		} else {
			throw new PlaidCastException("Failed to cast '"+obj+"'to PlaidJavaObject.");
		}
	}
	
	public static PlaidMethod toPlaidMethod(PlaidObject obj) throws PlaidCastException {
		if ( obj instanceof PlaidMethod ) {
			return (PlaidMethod)obj;
		} else {
			if ( obj instanceof PlaidScope )  {
				throw new PlaidMethodNotFoundException("Method '" + obj +"' not found.");
			} else if ( obj instanceof PlaidLookupMap ) {
				PlaidLookupMap obj_m = (PlaidLookupMap) obj;
				throw new PlaidMethodNotFoundException("Method '" + obj_m.getToLookup() + 
					"' not found in package '" + obj_m.getThePackage() + "'.");
			} else {
				throw new PlaidCastException("Failed to cast '"+obj+"'to PlaidMethod.");
			}
		}
	}
	
	public static PlaidState toPlaidState(PlaidObject obj) throws PlaidCastException {
		if ( obj instanceof PlaidState ) {
			return (PlaidState)obj;
		} else if ( obj instanceof PlaidLookupMap ) {
			PlaidLookupMap obj_m = (PlaidLookupMap) obj;
			throw new PlaidClassNotFoundException("State '" + obj_m.getToLookup() + 
				"' not found in package '" + obj_m.getThePackage() + "'.");
		} else {
			throw new PlaidCastException("Failed to cast '"+obj+"'to PlaidState.");
		}
	}
	
	public static String getQualifiedIdString(PlaidState state) {
		//TODO: update PlaidState interface to include getQI
		return ((PlaidStateMap)state).getQI().toString();
	}

	/**
	 * The following are the widening primitive conversion rules as detailed in 
	 * the Java Language Specification 3rd Edition, section 5.1.2
	 * 
	 * byte to short, int, long, float, or double
	 * short to int, long, float, or double
	 * char to int, long, float, or double
	 * int to long, float, or double
	 * long to float or double
 	 * float to double 
 	 * 
	 * @param fromClass
	 * @param toClass
	 * @return
	 */
	public static Class<?> widenPrimitiveType(Class<?> fromClass, Class<?> toClass) {
		if (fromClass.isPrimitive() && toClass.isPrimitive()) {
			if (fromClass.equals(byte.class)) {
				return conversionHelper(toClass, short.class, int.class, long.class, float.class, double.class);
			}
			else if (fromClass.equals(short.class)) {
				return conversionHelper(toClass, int.class, long.class, float.class, double.class);
			}
			else if (fromClass.equals(char.class)) {
				return conversionHelper(toClass, int.class, long.class, float.class, double.class);
			}
			else if (fromClass.equals(int.class)) {
				return conversionHelper(toClass, long.class, float.class, double.class);
			}
			else if (fromClass.equals(long.class)) {
				return conversionHelper(toClass, float.class, double.class);
			}
			else if (fromClass.equals(float.class)) {
				return conversionHelper(toClass, double.class);
			}
		}
		return null;
	}
	
	private static Class<?> conversionHelper(Class<?> toClass, Class<?> ...validClasses) {
		if(validClasses == null) {
			return null;
		}

		for (Class<?> c : validClasses) {
			if (toClass.equals(c)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Checks to see whether fromClass can be legally "narrowed" to the toClass. In Plaid, this
	 * is done to ease interop between Plaid and Java for the standard integer and floating point
	 * types which are arbitrary precision. These can be narrowed to one of the primitive types
	 * with the caveat that the value must fit within the target type, or a runtime exception
	 * will be thrown.
	 */
	public static Class<?> narrowPrimitiveType(Class<?> fromClass, Class<?> toClass) {
		if (fromClass.equals(plaid2JavaTypeMap.get("plaid.lang.Integer"))) {
			return conversionHelper(toClass, byte.class, short.class, int.class, long.class);
		}
		else if (fromClass.equals(plaid2JavaTypeMap.get("plaid.lang.Rational"))) {
			return conversionHelper(toClass, float.class, double.class);
		}
		
		return null;
	}
	
	/**
	 * Converts the reference classes to their corresponding primitive types:
	 * byte, short, int, long, float, or double
	 * 
	 * If the class does not have a primitive type then the class is simply returned.
	 * 
	 * @param c
	 * @return
	 */
	public static Class<?> convertToPrimitive(Class<?> c) {
		if (c.equals(Byte.class)) {
			return byte.class;
		}
		else if (c.equals(Short.class)){
			return short.class;
		}
		else if (c.equals(Integer.class)) {
			return int.class;
		}
		else if (c.equals(Float.class)) {
			return float.class;
		}
		else if (c.equals(Double.class)) {
			return double.class;
		}
		else if (c.equals(Boolean.class)) {
			return boolean.class;
		}
		else if (c.equals(Long.class)) {
			return long.class;
		}
		return c;
	}
	
	public static boolean requiresPrimitiveWidening(Method m, Class<?>[] argTypes) {
		Class<?>[] methodArgTypes = m.getParameterTypes();
		if (methodArgTypes.length != argTypes.length)
			throw new RuntimeException("Incorrect number of args.");
				
		for (int i = 0; i < methodArgTypes.length; i++) {
			Class<?> methodArgType = convertToPrimitive(methodArgTypes[i]);
			Class<?> argType = convertToPrimitive(argTypes[i]);
			if (methodArgType.isAssignableFrom(argType) || methodArgType.isAssignableFrom(argTypes[i])) {
				continue;
			}
			else if (widenPrimitiveType(argType, methodArgType) != null) {
				return true;
			}
			else {
				throw new RuntimeException("Argument types do not match.");
			}
		}
		return false;
	}

	public static PlaidObject convertJavaToPlaid(Object obj) {
		if ( obj != null && isOfSupportedBasicType(obj) ) {
			return buildBasic(obj);
		} else {
			return cl.packJavaObject(obj);			
		}
	}
}
