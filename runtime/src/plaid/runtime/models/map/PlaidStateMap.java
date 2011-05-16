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
import java.util.Map;

import plaid.runtime.PlaidCastException;
import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidInvalidArgumentException;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidState;
import plaid.runtime.PlaidTag;
import plaid.runtime.Util;
import plaid.runtime.annotations.RepresentsState;
import plaid.runtime.annotations.RepresentsTag;
import plaid.runtime.types.PlaidPermission;
import plaid.runtime.types.PlaidUniquePermission;
import plaid.runtime.utils.Delegate;
import plaid.runtime.utils.QualifiedIdentifier;

public class PlaidStateMap extends PlaidObjectMap implements PlaidState {
	protected static final PlaidPackageMap anonymousPackage = new PlaidPackageMap(QualifiedIdentifier.getQI("<ANONYMOUS>"));
	protected PlaidPackageMap pkg;
	protected String name;
	protected RepresentsState psa;
	protected PlaidObject prototype = new PlaidObjectMap();
	protected Class<Object> templateClass;
	protected PlaidTag tag;
	
	public static PlaidStateMap loadPlaidState(Class<Object> templateClass) {
		PlaidStateMap theState = null;
		RepresentsState psa = null;
		
		for (Field f : templateClass.getFields()) {
			psa = f.getAnnotation(RepresentsState.class);
			if (psa != null) {
				try {
					f.setAccessible(true);
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
	}
	
	public PlaidStateMap() {
		this.pkg = anonymousPackage;
		this.name = "<ANONYMOUS>";
		this.psa = null;
		this.templateClass = null;
		this.tag = null;
		//this.qi = anonymousQI;
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
		if ( states == null && prototype.getStates() == PlaidObjectMap.EMPTY_STATES ) {
			return PlaidObjectMap.EMPTY_STATES;
		}
		Collection<PlaidObject> result = new ArrayList<PlaidObject>();
		result.addAll(states());
		result.addAll(prototype.getStates());
		return result;
	}

	public void setPrototype(PlaidObject plaidObj) {
		this.prototype = plaidObj;
	}
	
	public PlaidPackageMap getPackage() {
		return pkg;
	}

	public QualifiedIdentifier getQI() {
		return pkg.getQI().append(name);
		//return qi;
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
	public PlaidState initState(PlaidState init) throws PlaidException {
		PlaidStateMap result = new PlaidStateMap();
		addToPlaidObject(result.prototype, this.prototype);
		Map<String, PlaidMemberDef> initMembers = init.getMembers();
		Map<String, PlaidMemberDef> stateMembers = this.getMembers();
	
		for (PlaidMemberDef member : initMembers.values()) {
			
			String memberName = member.getMemberName();
			PlaidMemberDef existing = stateMembers.get(memberName); //findExisting(memberName, stateMembers.keySet());
			
			// override means defined in the current state from above in the state hierarchy
			// 1) find existing member to override from this state
			// 2) add to prototype with new MemberDef
			
			if (existing == null) {
				if (member.overrides())
					throw new PlaidRuntimeException("No member " + memberName + " to override");
				else {
					//keep as anonymous //TODO : are these the right semantics
					result.addMember(Util.memberDef(member), initMembers.get(memberName).getValue());
				}
			} else {
				if (member.overrides()) { //when overriding, don't care about whether the existing member is abstract
					
					//check that mutability lines up // TODO: other checks?
					boolean mutable = existing.isMutable();
					//String originalDef = existing.definedIn();
					if (mutable != member.isMutable())
						throw new PlaidRuntimeException("Overriding member " + memberName + " must be " +
								(mutable ? "mutable" : "immutable")  + " like the original definition in.");
			
					// else add as an override member and bind override to the found member
					//TODO: do we need to distinguish that this was done in an init?
					//otherwise, sometimes overriding something defined in the same place as the overrid
					PlaidMemberDef overrideMemberDef = Util.memberDef(memberName, this.getTag(), mutable, true); 
					//overrideMemberDef.bindOverride(originalDef); //This is done in addMember()
					result.addMember(overrideMemberDef, initMembers.get(memberName).getValue());
				} else { 
					
					//check that the existing member is abstract
					PlaidObject existingValue = stateMembers.get(existing.getMemberName()).getValue();
					if (existingValue != PlaidRuntime.getRuntime().getClassLoader().unit() && 
							!(existingValue instanceof PlaidAbstractValueMap))
						throw new PlaidRuntimeException("Cannot re-initialize member " + memberName + " without overriding.");
					
					//if not, treat initialized member as definedIn the place where declared abstract
					//check that mutability lines up // TODO: other checks?
					boolean mutable = existing.isMutable();
					PlaidTag originalDef = existing.definedIn();
					if (mutable != member.isMutable())
						throw new PlaidRuntimeException("Initialized member " + memberName + " must be " +
								(mutable ? "mutable" : "immutable")  + " like the abstract definition from " +
								originalDef + ".");
					
					result.removeMember(memberName);
					result.addMember(Util.memberDef(memberName, originalDef, mutable, false), initMembers.get(memberName).getValue());	
				}
			} 
			
			
		}
		
		return result;
	}

	@Override
 	public PlaidObject instantiate(PlaidPermission initPerm, PlaidObject ...args) throws PlaidException {
		if (args.length != 0) {
			throw new PlaidInvalidArgumentException("Cannot instantiate PlaidState with arguments");
		}
		if (psa != null && psa.javaobject()) {
			return initialize(new PlaidJavaObjectMap(initPerm, null));
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
			return initialize(new PlaidObjectMap(initPerm));
		}
	}
	
	@Override
 	public PlaidObject instantiate(PlaidObject ...args) throws PlaidException {
		return this.instantiate(PlaidUniquePermission.unique(), args);
	}

	public static PlaidObject initializeMember(PlaidObject memberValue, PlaidObjectMap thisPointer) {
		if (memberValue instanceof PlaidProtoMethodMap) {
			PlaidProtoMethodMap ppmm = (PlaidProtoMethodMap) memberValue;
			return new PlaidMethodMap(ppmm.getFullyQualifiedName(), thisPointer, ppmm.getDelegate());
		} else if (memberValue  instanceof PlaidProtoFieldMap) {
			PlaidProtoFieldMap ppfm =(PlaidProtoFieldMap) memberValue;
			PlaidMethod initializer = new PlaidMethodMap("init", thisPointer, ppfm.getInitalizer());	
			return initializer.invoke(Util.unit());
		} else {
			return memberValue;
		}
	}
	
	protected PlaidObject initialize(PlaidObjectMap pom) {
		// add members from prototype initializing any that are proto-
		for ( Map.Entry<String, PlaidMemberDef> member : prototype.getMembers().entrySet() ) {
			PlaidObject memberValue = member.getValue().getValue();
			PlaidMemberDef memberKey = member.getValue();
			
			pom.addMember(Util.memberDef(memberKey), initializeMember(memberValue,pom));	
		}
		
		// add states from prototype
		if ( prototype.getStates() != PlaidObjectMap.EMPTY_STATES ) {
			for (PlaidObject ps : prototype.getStates() ) {
				pom.addState(ps);
			}
		}
		
		// add tags from the prototype
		if ( prototype.getTags() != PlaidObjectMap.EMPTY_TAGS ) {
			Map<PlaidTag,PlaidTag> tagMap = prototype.getTags();
			for (PlaidTag t : tagMap.keySet()) {
				PlaidTag enclosingTag = tagMap.get(t);
				if (enclosingTag == null)
					pom.addTopTag(t);
				else
					pom.addTag(t,enclosingTag);
			}
		}
		
		// add build-in operators
		PlaidMemberDef mdef = PlaidRuntime.getRuntime().getClassLoader().memberDef("asInstanceOf", true, PlaidRuntime.getRuntime().getClassLoader().tag("buildin", "internal", null), false, true);
		pom.addMember(mdef, new PlaidMethodMap("asInstanceOf", pom, new Delegate() {
			@Override
			public PlaidObject invoke(PlaidObject thisVar, PlaidObject args)
			throws PlaidException {
				if ( args instanceof PlaidJavaStateMap ) {
					Class<?> iface = ((PlaidJavaStateMap)args).valueClass;
					if ( iface.isInterface() ) {
						return PlaidRuntime.getRuntime().getClassLoader().javaProxy(thisVar, iface);
					} else {
						throw new PlaidCastException("'asInstanceOf' need a Java interface as parameter and not" + iface.getCanonicalName());
					}	
				} else {	
					throw new PlaidCastException("'asInstanceOf' need a Java interface as parameter and not : " + args.toString());
				}	
			}	
		}));

		
		return pom;
	}

	protected void addToPlaidObject(PlaidObject target, PlaidObject source) {
		// copy  members 
		for ( Map.Entry<String, PlaidMemberDef> e : source.getMembers().entrySet()) {
			target.addMember(Util.memberDef(e.getValue()), e.getValue().getValue());
		}
		
		// copy states
		for ( PlaidObject s : source.getStates() ) {
			target.addState(s);
		}
		
		// copy tags
		Map<PlaidTag,PlaidTag> sourceTags = source.getTags();
		for (PlaidTag t : sourceTags.keySet()) {
			PlaidTag enclosingTag = sourceTags.get(t);
			if (enclosingTag != null)
				target.addTag(t,enclosingTag); //TODO : need to throw errors when tags cannot coexist
			else
				target.addTopTag(t);
		}
	}
	
	public String toString() {
		return "State<" + getQI() + ">";				
	}

	@Override
	public void addMember(PlaidMemberDef name, PlaidObject obj) {
		//abstract values represented by special PlaidObject subclass instance
		if (obj == PlaidRuntime.getRuntime().getClassLoader().unit()) obj = new PlaidAbstractValueMap();
		prototype.addMember(name, obj);
	}
	
	@Override
	public void removeMember(String name) {
		prototype.removeMember(name);
	}

	@Override
	public Map<String, PlaidMemberDef> getMembers() {
		return prototype.getMembers();
	}
	
	@Override
	public void removeTag(PlaidTag tag) {
		prototype.removeTag(tag);
	}
	
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
		return tag != null;
	}

	@Override
	public String getPath() {
		 return pkg.getQI().toString() + "." + name;
	}


}
