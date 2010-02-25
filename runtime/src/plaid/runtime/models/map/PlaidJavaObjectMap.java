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
import java.util.Collections;
import java.util.Map;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidObject;

public final class PlaidJavaObjectMap extends PlaidObjectMap implements PlaidJavaObject {
	private Object value;
	private Class<Object> valueClass;
	private boolean reflected = false;
	
	public PlaidJavaObjectMap(Object value) {
		super();
		setJavaObject(value);
	}
	
	public PlaidJavaObjectMap() {
		super();
		this.value = null;
	}

	@Override
	public Map<String, PlaidObject> getMembers() {
		if ( reflected == false  && valueClass != null ) {
			for ( Field f : valueClass.getFields() ) {
				Object obj;
				try {
					obj = f.get(value);
					members.put(f.getName(), new PlaidJavaObjectMap(obj));
				} catch (IllegalArgumentException e) {
					throw new PlaidInvalidArgumentException("Wrong argument.");
				} catch (IllegalAccessException e) {
					throw new PlaidIllegalAccessException("Cannot get field value");
				}				
			}
			
			for ( Method m : valueClass.getMethods() ) {
				members.put(m.getName(), new PlaidJavaMethodMap(m.getName(), value, valueClass));
			}
		}
		return Collections.unmodifiableMap(members);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void setJavaObject(Object value) {
		this.value = value;
		this.valueClass = (Class<Object>) value.getClass();
	}
	
	@Override
	public Object getJavaObject() throws PlaidException {
		return value;
	}
	
	@Override
	public String toString() {
		return "PlaidJavaObject(" + value + ")";
	}
}
