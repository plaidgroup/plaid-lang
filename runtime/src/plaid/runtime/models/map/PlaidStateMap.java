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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidState;
import plaid.runtime.Util;
import plaid.runtime.annotations.RepresentsState;
import plaid.runtime.utils.QualifiedIdentifier;

public class PlaidStateMap extends PlaidObjectMap implements PlaidState {
	protected PlaidPackageMap pkg;
	protected String name;
	protected RepresentsState psa;
	protected PlaidObject templateState = new PlaidObjectMap();
	protected Class<Object> templateClass;
	
	public PlaidStateMap(PlaidPackageMap pkg, String name, Class<Object> templateClass) {
		this.pkg = pkg;
		this.name = name;
		this.psa = templateClass.getAnnotation(RepresentsState.class);
		this.templateClass = templateClass;
		for ( Field f : templateClass.getFields() ) {
			RepresentsState psa = f.getAnnotation(RepresentsState.class);
			if ( psa != null ) {
				try {
					templateState  = (PlaidObject)f.get(templateClass);
				} catch (IllegalArgumentException e) {
					throw new PlaidInvalidArgumentException("Cannot get value from field : " + f.getName());
				} catch (IllegalAccessException e) {
					throw new PlaidIllegalAccessException("Cannot access field of class : " + templateClass.getName());
				}
				break;
			}
		}
		templateState.addState(this);
	}
	
	public PlaidStateMap() {
		this.pkg = new PlaidPackageMap(new QualifiedIdentifier("<ANONYMOUS>"));
		this.name = "<ANONYMOUS>";
		this.psa = null;
		this.templateClass = null;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPackage(PlaidPackageMap pkg) {
		this.pkg = pkg;
	}
	
	@Override
	public Collection<PlaidObject> getStates() {
		Collection<PlaidObject> result = new ArrayList<PlaidObject>();
		result.addAll(states);
		result.addAll(templateState.getStates());
		return Collections.unmodifiableCollection(result);
	}

	public PlaidPackageMap getPackage() {
		return pkg;
	}

	public QualifiedIdentifier getQI() {
		return pkg.getQI().append(name);
	}

	@Override
	public PlaidState with(PlaidState... args) throws PlaidException {
		PlaidStateMap result = new PlaidStateMap();
		// merge with this
		addToPlaidObject(result.templateState, this.templateState);
		// merge with parameters
		for ( PlaidObject p : args) {
			PlaidStateMap ps = (PlaidStateMap)p;
			addToPlaidObject(result.templateState, ps.templateState);
		}
		//result.setReadOnly(true);
		return result;
	}

	@Override
 	public PlaidObject instantiate(PlaidObject ...args) throws PlaidException {
		if ( args.length != 0 ) {
			throw new PlaidInvalidArgumentException("Cannot instanticate PlaidState with arguments");
		}
		if ( psa != null && psa.javaobject() ) {
			return initialize(new PlaidJavaObjectMap());
		} if ( psa != null && psa.stateobject()) {
			PlaidStateMap psm = (PlaidStateMap) initialize(new PlaidStateMap());
			if ( templateState instanceof PlaidStateMap ) {
				psm.setName(((PlaidStateMap)templateState).getName());
				psm.setPackage(((PlaidStateMap)templateState).getPackage());
			}
			return psm;
		} else {
			return initialize(new PlaidObjectMap());
		}
	}

	protected PlaidObject initialize(PlaidObjectMap pom) {
		for ( Map.Entry<String, PlaidObject> member : templateState.getMembers().entrySet() ) {
			if ( member.getValue() instanceof PlaidProtoMethodMap ) {
				PlaidProtoMethodMap ppmm = (PlaidProtoMethodMap)member.getValue();
				pom.addMember(member.getKey(), new PlaidMethodMap(pom, ppmm.getDelegate()));
			} else if ( member.getValue() instanceof PlaidProtoFieldMap ) {
				PlaidProtoFieldMap ppfm =(PlaidProtoFieldMap)member.getValue();
				PlaidMethod initializer = new PlaidMethodMap(pom, ppfm.getInitalizer());
				pom.addMember(member.getKey(), initializer.invoke(Util.unit()));
			}
		}
		
		// add ourself as to the states
		for ( PlaidObject ps : this.templateState.getStates() ) {
			pom.addState(ps);
		}
		
		return pom;
	}

	protected void addToPlaidObject(PlaidObject target, PlaidObject source) {
		// copy members 
		for ( Map.Entry<String, PlaidObject> e : source.getMembers().entrySet()) {
			target.addMember(e.getKey(), e.getValue());
		}
		
		// copy states
		for ( PlaidObject s : source.getStates() ) {
			target.addState(s);
		}
	}
	
	public String toString() {
		return "State<" + getQI() + ">";				
	}

	@Override
	public void addMember(String name, PlaidObject obj) {
		templateState.addMember(name, obj);
	}

	@Override
	public Map<String, PlaidObject> getMembers() {
		return Collections.unmodifiableMap(templateState.getMembers());
	}
}
