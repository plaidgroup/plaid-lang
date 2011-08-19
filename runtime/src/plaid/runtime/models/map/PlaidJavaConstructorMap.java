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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.sun.org.apache.xpath.internal.Arg;

import plaid.runtime.PlaidConstants;
import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.Util;

public class PlaidJavaConstructorMap extends PlaidObjectMap implements PlaidMethod{
	public static final String NAME = "new" + PlaidConstants.ID_SUFFIX;
	protected Class<?> cl;
	
	public PlaidJavaConstructorMap(Class<?> cl) {
		this.cl = cl;
	}
	
	class ConstructorMatch {
		Constructor<?> constr;
		ArgumentCompatibility compat;
		
		public ConstructorMatch(Constructor<?> constr, ArgumentCompatibility compat) {
			this.constr = constr;
			this.compat = compat;
		}
		
		public boolean isBetterThan(ConstructorMatch other) {
			if(other == null) {
				return true;
			}
			
			return this.compat.isBetterMatchThan(other.compat);
		}
	}
	
	@Override
	public PlaidObject invoke(PlaidObject args) throws PlaidException {
		try {
			Class<?>[] paramTypes = null;
			Object[] params = null;
			ConstructorMatch match = null;
			if ( args instanceof PlaidJavaObject ) {
				paramTypes = new Class[1];
				//paramTypes[0] = (Class<Object>) Object.class;
				paramTypes[0] = ((PlaidJavaObject)args).getJavaObject().getClass();
				params = new Object[1];
				params[0] = ((PlaidJavaObject)args).getJavaObject();
			} else if( args == Util.unit() )  {
				paramTypes = new Class[0];
				params = new Object[0];
			} else {
				// check for pair
				params = Util.convertParamsToArray(args);
				paramTypes = new Class[params.length];
				for (int i = 0; i < params.length; i++) {
					paramTypes[i] = params[i].getClass();
				}
			} 
			
			try {
				for ( Constructor<?> ctr : cl.getConstructors()) {
					Class<?>[] mpTypes = ctr.getParameterTypes();
					ArgumentCompatibility compat = ArgumentCompatibility.check(mpTypes, paramTypes);
					if(compat == null) {
						continue;
					}
					
					ConstructorMatch newMatch = new ConstructorMatch(ctr, compat);
					
					if(newMatch.isBetterThan(match)) {
						match = newMatch;
					}
					
					if(newMatch.compat.isExact()) {
						break;
					}
				}
			}
			catch (SecurityException e) {
				throw new PlaidIllegalAccessException("Cannot find matching constructor");
			}
			
			Object result;
			if (match == null) {
				throw new PlaidIllegalAccessException("Cannot find matching constructor for " + args + " in class name "+ cl.getCanonicalName());
			}
			
			Object[] convertedParams = match.compat.convertParams(params);
			result = match.constr.newInstance(convertedParams);
			
			if ( result == null ) {
				return  PlaidRuntime.getRuntime().getClassLoader().unit();
			} else {
				PlaidJavaObject plaidResult = new PlaidJavaObjectMap(result);
				// add the tag  //TODO: this is not correct since Java objects will not actually have tags...?
				//plaidResult.addTag(new PlaidTagMap(result.getClass().getName(), result.getClass().getPackage().getName(), Util.javaObjectTag),null);
				return plaidResult;
			}
		} catch (IllegalArgumentException e) {
			throw new PlaidInvalidArgumentException("Cannot call constructor.");
		} catch (IllegalAccessException e) {
			throw new PlaidIllegalAccessException("Cannot call constructor.");
		} catch (InvocationTargetException e) {
			throw new PlaidIllegalAccessException("Cannot call constructor. Original error message:" + e.getCause().toString());
		} catch (InstantiationException e) {
			throw new PlaidIllegalAccessException("Cannot call constructor.");
		}
	}

	@Override
	public String toString() {
		return "PlaidJavaMethod("+ NAME +")";
	}
}
