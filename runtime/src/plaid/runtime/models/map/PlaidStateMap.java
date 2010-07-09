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
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidState;
import plaid.runtime.PlaidTag;
import plaid.runtime.Util;
import plaid.runtime.annotations.RepresentsState;
import plaid.runtime.annotations.RepresentsTag;
import plaid.runtime.utils.QualifiedIdentifier;

public class PlaidStateMap extends PlaidObjectMap implements PlaidState {
	protected PlaidPackageMap pkg;
	protected String name;
	protected RepresentsState psa;
	protected PlaidObject prototype = new PlaidObjectMap();
	protected Class<Object> templateClass;
	protected PlaidTag tag;
	protected boolean hasTag;
	
	public static PlaidStateMap loadPlaidState(Class<Object> templateClass) {
		PlaidStateMap theState = null;
		RepresentsState psa = null;
		
		for (Field f : templateClass.getFields()) {
			psa = f.getAnnotation(RepresentsState.class);
			if (psa != null) {
				try {
					theState = (PlaidStateMap)f.get(templateClass);
				} catch (IllegalArgumentException e) {
					throw new PlaidInvalidArgumentException("Cannot get value from field : " + f.getName());
				} catch (IllegalAccessException e) {
					throw new PlaidIllegalAccessException("Cannot access field of class : " + templateClass.getName());
				}
				break;
			}
		}
		
		if (theState != null) {
			theState.psa = psa;
			theState.templateClass = templateClass;	
		}
		
		return theState;
	}
	
	public PlaidStateMap(PlaidPackageMap pkg, String name, Class<Object> templateClass) {
		this.pkg = pkg;
		this.name = name;
		this.psa = templateClass.getAnnotation(RepresentsState.class);
		this.templateClass = templateClass;
		for (Field f : templateClass.getFields()) {
			RepresentsState psa = f.getAnnotation(RepresentsState.class);
			if (psa != null) {
				try {
					prototype = (PlaidObject)f.get(templateClass);
				} catch (IllegalArgumentException e) {
					throw new PlaidInvalidArgumentException("Cannot get value from field : " + f.getName());
				} catch (IllegalAccessException e) {
					throw new PlaidIllegalAccessException("Cannot access field of class : " + templateClass.getName());
				}
				continue;
			}
			RepresentsTag ta = f.getAnnotation(RepresentsTag.class);
			if (ta != null) {
				try {
					tag  = (PlaidTag)f.get(templateClass);
				} catch (IllegalArgumentException e) {
					throw new PlaidInvalidArgumentException("Cannot get value from field : " + f.getName());
				} catch (IllegalAccessException e) {
					throw new PlaidIllegalAccessException("Cannot access field of class : " + templateClass.getName());
				}
			}
		}
		prototype.addState(this);
		if (tag != null) {
			hasTag = true;
			prototype.addTag(tag);
		} else {
			hasTag = false;
		}
	}
	
	public PlaidStateMap() {
		this.pkg = new PlaidPackageMap(new QualifiedIdentifier("<ANONYMOUS>"));
		this.name = "<ANONYMOUS>";
		this.psa = null;
		this.templateClass = null;
		this.hasTag = false;
		this.tag = null;
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
		result.addAll(prototype.getStates());
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
		addToPlaidObject(result.prototype, this.prototype);
		// merge with parameters
		for (PlaidState p : args) {
			addToPlaidObject(result.prototype, p.getPrototype());
		}
		//result.setReadOnly(true);
		return result;
	}

	@Override
 	public PlaidObject instantiate(PlaidObject ...args) throws PlaidException {
		if (args.length != 0) {
			throw new PlaidInvalidArgumentException("Cannot instantiate PlaidState with arguments");
		}
		if (psa != null && psa.javaobject()) {
			return initialize(new PlaidJavaObjectMap());
		} 
		if (psa != null && psa.stateobject()) {
			PlaidStateMap psm = (PlaidStateMap) initialize(new PlaidStateMap());
			if (prototype instanceof PlaidStateMap) {
				psm.setName(((PlaidStateMap)prototype).getName());
				psm.setPackage(((PlaidStateMap)prototype).getPackage());
			}
			return psm;
		} 
		else {
			return initialize(new PlaidObjectMap());
		}
	}

	protected PlaidObject initialize(PlaidObjectMap pom) {
		// add members from prototype initializing any that are proto-
		for ( Map.Entry<PlaidMemberDef, PlaidObject> member : prototype.getMembers().entrySet() ) {
			if ( member.getValue() instanceof PlaidProtoMethodMap ) {
				PlaidProtoMethodMap ppmm = (PlaidProtoMethodMap)member.getValue();
				PlaidMethodMap method = new PlaidMethodMap(ppmm.getFullyQualifiedName(), pom, ppmm.getDelegate());
				//method.addTag(ppmm.getTags().iterator().next());
				pom.addMember(member.getKey(), method);
			} 
			else if ( member.getValue() instanceof PlaidProtoFieldMap ) {
				PlaidProtoFieldMap ppfm =(PlaidProtoFieldMap)member.getValue();
				PlaidMethod initializer = new PlaidMethodMap(member.getKey().getMemberName(), pom, ppfm.getInitalizer());
				pom.addMember(member.getKey(), initializer.invoke(Util.unit()));
			}
		}
		
		// add states from prototype
		for (PlaidObject ps : this.prototype.getStates()) {
			pom.addState(ps);
		}
		
		// add tags from the prototype
		for (PlaidTag t : this.prototype.getTags()) {
			pom.addTag(t);
		}
		
		return pom;
	}

	protected void addToPlaidObject(PlaidObject target, PlaidObject source) {
		// copy  members 
		for ( Map.Entry<PlaidMemberDef, PlaidObject> e : source.getMembers().entrySet()) {
			target.addMember(e.getKey(), e.getValue());
		}
		
//		// copy mutable members 
//		for ( Map.Entry<String, PlaidObject> e : source.getMutableMembers().entrySet()) {
//			target.addMember(e.getKey(), e.getValue(), false);
//		}
		
		// copy states
		for ( PlaidObject s : source.getStates() ) {
			target.addState(s);
		}
		
		// copy tags
		for (PlaidTag t : source.getTags()) {
			target.addTag(t); //TODO : need to throw errors when tags cannot coexist
		}
	}
	
	public String toString() {
		return "State<" + getQI() + ">";				
	}

	@Override
	public void addMember(PlaidMemberDef name, PlaidObject obj) {
		prototype.addMember(name, obj);
	}

	@Override
	public Map<PlaidMemberDef, PlaidObject> getMembers() {
		return Collections.unmodifiableMap(prototype.getMembers());
	}
	
	@Override
	public void removeTag(PlaidTag tag) {
		prototype.removeTag(tag);
	}
	
//	@Override
//	public Map<String, PlaidObject> getImmutableMembers() {
//		return this.prototype.getImmutableMembers();
//	}
//	
//	@Override
//	public Map<String, PlaidObject> getMutableMembers() {
//		return this.prototype.getMutableMembers();
//	}
	
	@Override
	public PlaidObject getPrototype() {
		return prototype;
	}

	@Override
	public PlaidTag getTag() throws PlaidException {
		return tag;
	}

	@Override
	public boolean hasTag() throws PlaidException {
		return hasTag;
	}

	@Override
	public String getPath() {
		return pkg.getQI().toString() + "." + name;
	}
}
