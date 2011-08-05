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
 
package plaid.runtime.models.map;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import plaid.runtime.PlaidCastException;
import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidState;
import plaid.runtime.Util;
import plaid.runtime.types.PlaidPermission;
import plaid.runtime.utils.Delegate;

public class PlaidJavaObjectMap extends PlaidObjectMap implements PlaidJavaObject {
	@SuppressWarnings("unchecked")
	private final static Map<Class, Field[]>  fieldMap  = new ConcurrentHashMap<Class, Field[]>();
	@SuppressWarnings("unchecked")
	private final static Map<Class, Method[]> methodMap = new ConcurrentHashMap<Class, Method[]>();
	private Object value;
	private Class<Object> valueClass;
	private boolean reflected  = false;

	public PlaidJavaObjectMap(Object value) {
		super();
		setJavaObject(value);
		String name = valueClass.getCanonicalName();
		if (name != null) {
			PlaidObject tagObject = PlaidRuntime.getRuntime().getClassLoader().lookup(name, PlaidRuntime.getRuntime().getClassLoader().unit());
			if (tagObject != null) {
				PlaidState tagState = (PlaidState) tagObject;
				this.addTopTag(tagState.getTag());
			}
		}
	}
	
	public PlaidJavaObjectMap(PlaidPermission initPerm, Object value) {
		super(initPerm);
		// We have to pass null if we want to be able to tell the difference between 
		// passing an initPerm and using the initPerm as the object we want to wrap.
		// This is done, for example, when constructing integer literals.
		if (value == null) {
			this.value = null;
		}
		else {
			setJavaObject(value);
		}
	}
	
	public PlaidJavaObjectMap() {
		super();
		this.value = null;
	}
	
	public final static Field[] getFields(Class<?> klazz) {
		Field[] fields = fieldMap.get(klazz);
		if ( fields == null ) {
			fields = klazz.getFields();
			fieldMap.put(klazz, fields);
			for ( Field f : fields ) {
				f.setAccessible(true); // bypass runtime check and rely on compiler
			}
		}
		return fields;
	}

	public final static Method[] getMethods(Class<?> klazz) {
		Method[] methods = methodMap.get(klazz);
		if ( methods == null ) {
			methods = klazz.getMethods();
			methodMap.put(klazz, methods);
			for ( Method m : methods ) {
				m.setAccessible(true); // bypass runtime check and rely on compiler
			}
		}
		return methods;
	}


	@Override
	public final Map<String, PlaidMemberDef> getMembers() {
		// TODO: Not sure if the use of mutability for all fields and immutability for all methods here is correct
		if (valueClass != null) {
			for (Field f : valueClass.getFields() ) {
				if ( reflected && Modifier.isFinal(f.getModifiers())) continue; // read final fields only once
				Object obj;
				try {
					obj = f.get(value);
					// find existing entry
					PlaidMemberDef entry = members().get(f.getName());
					if ( entry == null ) {
						entry = Util.memberDef(f.getName(), null, true, false);
						members().put(f.getName(), entry);
					}
					if ( obj != null ) {
						entry.setValue(new PlaidJavaObjectMap(obj));
					} else {
						entry.setValue( PlaidRuntime.getRuntime().getClassLoader().unit());
					}
				} catch (IllegalArgumentException e) {
					throw new PlaidInvalidArgumentException("Wrong argument.");
				} catch (IllegalAccessException e) {
					throw new PlaidIllegalAccessException("Cannot get field value");
				}				
			}
			if ( reflected == false) {
				for (Method m : valueClass.getMethods()) {
					PlaidMemberDef mdef = Util.memberDef(m.getName(), null, false, false);
					mdef.setValue(new PlaidJavaMethodMap(m.getName(), value, valueClass));
					this.members().put(m.getName(), mdef);
				}
				reflected = true;
			}
		}
		return membersFixed();
	}
		
	@Override
	public final PlaidMemberDef getMember(String name) {
		PlaidMemberDef result = members().get(name);

		// check fields
		for (Field f : getFields(valueClass)) {
			if ( f.getName().equals(name) ) {
				if ( result != null  && Modifier.isFinal(f.getModifiers())) break; // read final fields only once
				Object obj;
				try {
					obj = f.get(value);
					result = Util.memberDef(f.getName(), null, true, false);
					if ( obj != null ) {
						result.setValue(Util.convertJavaToPlaid(obj));
					} else {
						result.setValue( PlaidRuntime.getRuntime().getClassLoader().unit());
					}
					members().put(f.getName(), result);
					return result;
				} catch (IllegalArgumentException e) {
					throw new PlaidInvalidArgumentException("Wrong argument.");
				} catch (IllegalAccessException e) {
					throw new PlaidIllegalAccessException("Cannot get field value");
				}
			}
		}

		if ( !reflected && result == null ) {
			// try to find method
			for (Method m : getMethods(valueClass)) {
				if ( m.getName().equals(name)) {
					result = Util.memberDef(m.getName(), null, false, false);
					result.setValue(new PlaidJavaMethodMap(m.getName(), value, valueClass));
					this.members().put(m.getName(), result);
					return result;
				}
			}
		}
		
		// check for buildin operators
		if ( result == null ) {
			if ( name.equals("asInstanceOf") ) {
				result = PlaidRuntime.getRuntime().getClassLoader().memberDef("asInstanceOf", true, null, false, true);
				result.setValue(new PlaidMethodMap("asInstanceOf", this, new Delegate() {					
					@Override
					public PlaidObject invoke(PlaidObject thisVar, PlaidObject args) throws PlaidException {
						PlaidJavaObjectMap pjom = (PlaidJavaObjectMap)thisVar;
						if ( args instanceof PlaidJavaStateMap ) {
							Class<?> iface = ((PlaidJavaStateMap)args).valueClass;
							if ( iface.isInterface() ) {
								boolean matched = false;
								for ( Class<?> cls : pjom.getJavaObject().getClass().getInterfaces() ) {
									if ( cls == iface ) {
										matched = true;
									}
								}
								if (matched) {
									return thisVar;
								} else {
									throw new PlaidCastException("'" + pjom.getJavaObject().getClass().getCanonicalName() + "' does not implement '" + iface.getCanonicalName()+"'");
								}
							} else {
								throw new PlaidCastException("'asInstanceOf' need a Java interface as parameter and not" + iface.getCanonicalName());
							}	
						} else {	
							throw new PlaidCastException("'asInstanceOf' need a Java interface as parameter and not : " + args.toString());
						}
					}
				}));
			}
		}
		return result;
	}
	
	@Override
	public final void updateMember(String name, PlaidObject obj) {
		// if we update a field we also have to update the corresponding java object
		if ( !reflected ) getMembers(); // take care that fieldMembers is initialized
		if ( members().containsKey(name) ) {
			for ( Field f : getFields(valueClass)) {
				if (f.getName().equals(name)) {
					if ( obj instanceof PlaidJavaObject ) {
						try {
							f.set(value, ((PlaidJavaObject) obj).getJavaObject());
						} catch (Exception e) {
							throw new PlaidRuntimeException("Cannot update Java object field.");
						}
					} else {
						throw new PlaidRuntimeException("Cannot store PlaidObject in Java Field.");
					}
				}
			}
		}
		super.updateMember(name, obj);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final void setJavaObject(Object value) {
		this.value = value;
		if ( value == null ) {
			System.out.println("debug me");
		}
		this.valueClass = (Class<Object>) value.getClass();
	}
	
	@Override
	public final Object getJavaObject() throws PlaidException {
		return value;
	}
	
	@Override
	public final String toString() {
		return "PlaidJavaObject(" + value + ")";
	}
}
