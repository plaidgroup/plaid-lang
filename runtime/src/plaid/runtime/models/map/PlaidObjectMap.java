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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidState;
import plaid.runtime.PlaidTag;
import plaid.runtime.Util;
import plaid.runtime.types.PlaidPermType;
import plaid.runtime.types.PlaidPermission;
import plaid.runtime.types.PlaidPermissionTable;
import plaid.runtime.types.PlaidPermissionTableMap;
import plaid.runtime.types.PlaidUniquePermission;
import plaid.runtime.utils.Delegate;

public class PlaidObjectMap implements PlaidObject {
	@SuppressWarnings("unchecked")
	public static Collection<PlaidObject> EMPTY_STATES = Collections.unmodifiableCollection(Collections.EMPTY_LIST);
	@SuppressWarnings("unchecked")
	public static Collection<PlaidTag> EMPTY_TAGS      = Collections.unmodifiableCollection(Collections.EMPTY_LIST);
	protected PlaidPermissionTable permTable;
	protected Collection<PlaidObject> states;
	protected Map<String, PlaidMemberDef> members;
	protected Map<String, PlaidMemberDef> membersFixed;
	protected boolean membersChanged = true;
	protected Collection<PlaidTag> tags;
	// map from scopes to sets of bound variable names for this object
	protected boolean readonly = false;
	
	public PlaidObjectMap() {
		this(new PlaidPermissionTableMap(),
			 PlaidUniquePermission.unique());
	}
	
	public PlaidObjectMap(PlaidPermission initPerm) {
		this(new PlaidPermissionTableMap(),
			 initPerm);
	}
	
	private PlaidObjectMap(PlaidPermissionTable permTable, 
			               PlaidPermission initPerm) {
		this.permTable = permTable;
		initPerm.addPermission(this.permTable);
	}

	protected final Map<String, PlaidMemberDef> membersFixed() {
		if ( membersChanged ) {
			membersFixed = Collections.unmodifiableMap(members());
		}
		return membersFixed;
	}
	
	protected final Collection<PlaidObject> states() {
		if ( states == null ) {
			synchronized (this) {
				if ( states == null ) {
					states = new ArrayList<PlaidObject>();
				}
			}
		}
		return states;
	}
	
	protected final Collection<PlaidTag> tags() {
		if ( tags == null ) {
			synchronized (this) {
				if ( tags == null ) {
					tags = new ArrayList<PlaidTag>();
				}
			}
		}
		return tags;
	}
	
	protected final Map<String, PlaidMemberDef> members() {
		if ( members == null ) {
			synchronized (this) {
				if ( members == null ) {
					members = new HashMap<String, PlaidMemberDef>();
				}
			}
		}
		return members;
	}
	
	public void setReadOnly(boolean ro) {
		readonly = ro;
	}
	
	public boolean isReadOnly() {
		return readonly;
	}
	
	@Override
	public void addMember(PlaidMemberDef memberDef, PlaidObject obj) {
		
		if (this.isReadOnly())
			throw new PlaidIllegalAccessException("Cannot change readonly object.");

		boolean addedIsMutable = memberDef.isMutable();
		String name = memberDef.getMemberName();
		PlaidMemberDef existingDef = null;
		
		
		memberDef.setValue(obj);
		//Look for existing member definition
		existingDef = members().get(name);//findExisting(name, members().keySet());

		//if existing - update the existing member if allowed
		if (existingDef != null ) {
				
			//if existing is abstract, remove it and add the new one (do not update defined in)
			if (existingDef.getValue() instanceof PlaidAbstractValueMap) {
				//members().remove(name);
				members().put(name, memberDef);
			} else {
				
				//check that old/new match in terms of mutability
				boolean existingIsMutable = existingDef.isMutable();
				if (existingIsMutable && !addedIsMutable)
					throw new PlaidRuntimeException("Member \"" + name + "\" already exists as a mutable member.");
				else if (!existingIsMutable && addedIsMutable)
					throw new PlaidRuntimeException("Member \"" + name + "\" already exists as an immutable member.");
	
				//check that the member is not already defined or that it is being overriden
				if (!(members().get(existingDef.getMemberName()).getValue() instanceof PlaidAbstractValueMap) && !memberDef.overrides())  //undefined means having a unit value right now
					throw new PlaidRuntimeException("Member \"" + name + "\" already defined.");
			
				//find the correct tag to keep around
				if (memberDef.overrides()) {
					if (!memberDef.overrideIsBound()) {  //TODO:will we ever want to re-bind an override?
						if (existingDef.isAnonymous()) {
							memberDef.bindOverride("<Anonymous>"); // TODO: don't use a string like this
						} else 
							memberDef.bindOverride(existingDef.definedIn());
					}
					//members().remove(existingDef.getMemberName());
					members().put(existingDef.getMemberName(), memberDef);
				} else if (!existingDef.isAnonymous()) {
					//cannot both be defined in a state
					if (!memberDef.isAnonymous()) {
						throw new PlaidRuntimeException("Member \"" + name + "\" defined in states \"" + existingDef.definedIn() + 
							"\" and \"" + memberDef.definedIn() + "\".");
					} else { //take existing tag: just update the member
						existingDef.setValue(obj);
						//members().put(existingDef,obj);
					}
				} else { //remove existing def and use the added def
					//members().remove(existingDef.getMemberName());
					members().put(existingDef.getMemberName(), memberDef);
				}
			}
		} else { // new member - just add it
			members().put(name, memberDef);
		}
		membersChanged = true;
	}
	
	@Override
	public void updateMember(String name, PlaidObject obj) {
		if ( obj instanceof PlaidLookupMap ) {
			throw new PlaidRuntimeException("Cannot store PlaidLookupMap in PlaidObjects.");
		}
		
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		PlaidMemberDef existingDef = members().get(name);//findExisting(name, getMembers().keySet());
		if (existingDef == null) { //Cannot update a member that is not present
			throw new PlaidRuntimeException("Member \"" + name + "\" is undefined.");
		} else {
			if (!existingDef.isMutable()) { //Cannot update an immutable member
				throw new PlaidRuntimeException("Cannot update immutable member \"" + name + "\".");
			} else  { //update the definition
				//members().put(existingDef, obj);
				existingDef.setValue(obj);
			}
		}
	}

	@Override
	public void removeMember(String name) {
		if (this.isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		//remove the member if it exists else throw exception
		PlaidMemberDef existingDef = members().get(name);//findExisting(name, members().keySet());
		if (existingDef != null) members().remove(existingDef.getMemberName());
		else throw new PlaidRuntimeException("Cannot remove undefined member \"" + name + "\".");
		membersChanged = true;
	}

	@Override
	public Map<String, PlaidMemberDef> getMembers() {
		return membersFixed();
	}
	
	@Override
	public PlaidMemberDef getMember(String name) {
		return members().get(name);
	}
	
	@Override
	public void addState(PlaidObject state) throws PlaidException {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (!states().contains(state)) {
			states().add(state);
		}
	}

	@Override
	public void removeState(PlaidObject state) {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (states().contains(state)) {
			states().remove(state);
		}		
	}

	@Override
	public Collection<PlaidObject> getStates() {
		if ( states == null ) {
			return EMPTY_STATES;
		}
		return Collections.unmodifiableCollection(states());
	}
	
	@Override
	public void addTag(PlaidTag tag) throws PlaidException {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (!tags().contains(tag)) {
			tags().add(tag);
		}
	}

	@Override
	public void removeTag(PlaidTag tag) {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (tags().contains(tag)) {
			tags().remove(tag);
		}		
	}

	@Override
	public Collection<PlaidTag> getTags() {
		if ( tags == null ) {
			return EMPTY_TAGS;
		}
		return tags();
	}
	
	public boolean matchesTag(String tagString) {
		for (PlaidTag tag : getTags()) {
			if (tag.matches(tagString)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PlaidObject changeState(PlaidObject update) throws PlaidException {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (!changeByTransition(update)) changeByWipe(update);
		
		return Util.unit();
	}
	
	private boolean changeByTransition(PlaidObject update) {
		boolean isTransition = false;
		
		
		//Need to figure out which states from the old object to remove members from
		//and which states from the new object to add members from
		//1) first find tags from matching hierarchies and pair them	
		Map<PlaidTag,PlaidTag> outIn = new HashMap<PlaidTag,PlaidTag>();
		for (PlaidTag newTag : update.getTags()) {
			for (PlaidTag existingTag : this.getTags()) {
				if (newTag.rootState().equals(existingTag.rootState())) {
					if (outIn.keySet().contains(existingTag)) throw new PlaidRuntimeException(existingTag + " already a member of this object");
					if (outIn.values().contains(existingTag)) throw new PlaidRuntimeException(newTag + " already a member of the new object");
					outIn.put(existingTag, newTag);
					isTransition = true;
				}
			}
		}
		if (!isTransition) return false; //Do wipe away state change if no tags match
		
		//2) next find the roots of each pair and determine which states are do not add state
		//   and which are do not remove states
		List<String> noAdd = new ArrayList<String>();
		List<String> toRemove = new ArrayList<String>();
		List<String> removedOverrides = new ArrayList<String>();
		for (PlaidTag existingTag : outIn.keySet()) {
			List<String> existingHierarchy = existingTag.getHierarchy();
			int existingCount = existingHierarchy.size()-1;
			List<String> newHierarchy = outIn.get(existingTag).getHierarchy();
			int newCount = newHierarchy.size()-1;
			
			
			while ( existingCount >= 0 && newCount >= 0 &&
					existingHierarchy.get(existingCount).equals(newHierarchy.get(newCount))) {
				newCount--;
				existingCount--;
			}
			
			if ( existingCount < 0 ) { //we are transitioning down
				// do not add members from states at the existing state and above
				newCount++;
				while ( newCount < newHierarchy.size() ) noAdd.add(newHierarchy.get(newCount++));
			} else if ( newCount < 0 ) { //we are transitioning up
				// remove members here and below
				while ( existingCount >= 0 ) toRemove.add(existingHierarchy.get(existingCount--));
				// do not add members above
				newCount++;
				while ( newCount < newHierarchy.size() ) noAdd.add(newHierarchy.get(newCount++));
			} else { //we are transitioning across
				// remove members here and below
				while ( existingCount >= 0 ) toRemove.add(existingHierarchy.get(existingCount--));
				// do not add above here
				newCount++;
				while ( newCount < newHierarchy.size() ) noAdd.add(newHierarchy.get(newCount++));
			}
		}
		
		//3) loop over existing members removing those defined in states from toRemove List
		List<PlaidMemberDef> removeDefs = new ArrayList<PlaidMemberDef>();
		for (PlaidMemberDef member : members().values()) {
			if (toRemove.contains(member.definedIn())) {
				removeDefs.add(member);
				if (member.overrides()) removedOverrides.add(member.getMemberName());
			}
		}
		for (PlaidMemberDef rem : removeDefs) removeMember(rem.getMemberName());
		
		//4) remove outgoing tags
		for (PlaidTag outTag : outIn.keySet()) removeTag(outTag);
		
		//5) loop over updating members adding those defined in states not in the noAdd list
		//   except those which are abstract (missing) (or were previously overriden - to implement)
		Map<String, PlaidMemberDef> newMembers = update.getMembers();
		for (PlaidMemberDef incomingMember : newMembers.values()) {
			if (!noAdd.contains(incomingMember.definedIn()) || //in the noAdd list, or
					(members().get(members().get(incomingMember.getMemberName())) != null && members().get(members().get(incomingMember.getMemberName()).getValue()) instanceof PlaidAbstractValueMap) || //abstract
					removedOverrides.contains(incomingMember.getMemberName())) { //previously overriden
				PlaidObject incomingMemberValue = newMembers.get(incomingMember.getMemberName()).getValue();
				if (incomingMemberValue instanceof PlaidMethodMap) { //update this pointer if necessary
					PlaidMethodMap oldMethod = (PlaidMethodMap) incomingMemberValue;
					incomingMemberValue = new PlaidMethodMap(oldMethod.getFullyQualifiedName(), this, oldMethod.delegate);
				}
				addMember(incomingMember, incomingMemberValue);
			}
		}
		//6) add incoming tags
		for (PlaidTag inTag : outIn.values()) addTag(inTag);
			
		return true;
	
	}
	
	private void changeByWipe(PlaidObject update) {

		// cleanup current information
		members().clear(); //TODO: fix this
		states().clear();
		tags().clear();
		
		// add in the members from the updating object
		for (Map.Entry<String, PlaidMemberDef> e : update.getMembers().entrySet()) {
			if (e.getValue().getValue() instanceof PlaidMethodMap) {
				PlaidMethodMap pmm = (PlaidMethodMap)e.getValue().getValue();
				addMember(Util.memberDef(e.getValue()), new PlaidMethodMap(pmm.getFullyQualifiedName(), this, pmm.delegate));
			}
			else {
				addMember(Util.memberDef(e.getValue()), e.getValue().getValue());
			}
		}
		
		// add other states
		states().addAll(update.getStates());
		tags().addAll(update.getTags());
		
		membersChanged = true;
	}
	
	@Override
	public PlaidObject copy() {
		PlaidObjectMap newObj = new PlaidObjectMap();
		// add members
		newObj.members().putAll(this.members());
		// add states
		newObj.states().addAll(this.states());
		// add tags
		newObj.tags().addAll(this.tags());
		return newObj;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		//members
		sb.append("\nPlaidObject(\n\tmembers={");
		for (PlaidMemberDef f : members().values()) {
			if (members().get(f) instanceof PlaidAbstractValueMap) sb.append("Abstract ");
			sb.append(f + ", ");
		}
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
	
		// states
		sb.append("}, \n\tstates={");
		for( Object s : states().toArray() ) {
			PlaidStateMap sm = (PlaidStateMap)s;
			sb.append(sm + ",");
		}
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		// tags
		sb.append("}, \n\ttags={");
		for (Object t : tags().toArray()) {
			PlaidTag tm = (PlaidTagMap)t;
			sb.append(tm + ",");
		}	
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		sb.append("})");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		PlaidMemberDef hashmember = members().get("hashCode");
		if (hashmember == null)
			return super.hashCode();
		PlaidObject hashmethobj = hashmember.getValue();
		if (hashmethobj == null)
			return super.hashCode();
		if (!(hashmethobj instanceof PlaidMethodMap))
			return super.hashCode();
		
		PlaidMethodMap hashmeth = (PlaidMethodMap) hashmethobj;
		//PlaidObject retval = hashmeth.invoke(Util.unit());
		PlaidObject retval = plaid.runtime.Util.call(hashmeth, Util.unit()); //for tco

		// We need to find out if retval is a (Plaid) integer and return its value
		// TODO: This is a hack!
		if (!(retval instanceof PlaidJavaObject))
			return super.hashCode();
		
		Object jo = ((PlaidJavaObject) retval).getJavaObject();
		if (!(jo instanceof Integer))
			return super.hashCode();
		
		return ((Integer) jo).intValue();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this == other)
			return true;
		if (!(other instanceof PlaidObjectMap))
			return false;
		
		PlaidObjectMap po = (PlaidObjectMap) other;
		PlaidMemberDef eqmember = members().get("eqeq$plaid");
		if (eqmember == null)
			return false;
		PlaidObject eqmethobj = eqmember.getValue();
		if (eqmethobj == null)
			return false;
		if (!(eqmethobj instanceof PlaidMethodMap))
			return false;
		
		PlaidMethodMap eqmeth = (PlaidMethodMap) eqmethobj;
		//PlaidObject retval = eqmeth.invoke(po);
		PlaidObject retval = plaid.runtime.Util.call(eqmeth, po);	//for tco

		// We need to find out if retval is a (Plaid) true
		return retval.matchesTag("plaid.lang.True");
	}

	@Override
	public void cast(PlaidPermType oldType, PlaidPermType newType) {
		// TODO: lambda/method types
		// TODO: make sure the actual type part is compatible
		// make sure permission being cast to is compatible with all other existing aliases
		PlaidPermission oldPerm = oldType.getPermission();
		PlaidPermission newPerm = newType.getPermission();
		permTable.execPermCast(oldPerm, newPerm);
	}

	@Override
	public void split(PlaidPermType initialType, PlaidPermType neededType, PlaidPermType residueType) {
		PlaidPermission initialPerm = initialType.getPermission();
		PlaidPermission neededPerm = neededType.getPermission();
		PlaidPermission residuePerm = residueType.getPermission();
		permTable.execPermSplit(initialPerm, neededPerm, residuePerm);
	}

	@Override
	public void join(PlaidPermType toJoinType1, PlaidPermType toJoinType2, PlaidPermType resultType) {
		PlaidPermission toJoinPerm1 = toJoinType1.getPermission();
		PlaidPermission toJoinPerm2 = toJoinType2.getPermission();
		PlaidPermission resultPerm = resultType.getPermission();
		permTable.execPermSplit(toJoinPerm1, toJoinPerm2, resultPerm);
	}
	
	@Override
	public PlaidState freeze() {
		
		// add members from object to the state as proto members
		PlaidState frozenState = new PlaidStateMap();
		for ( Map.Entry<String, PlaidMemberDef> member : this.getMembers().entrySet() ) {
			final PlaidObject memberValue = member.getValue().getValue();
			PlaidMemberDef memberKey = member.getValue();
			if (memberValue instanceof PlaidMethodMap) {
				PlaidMethodMap pmm = (PlaidMethodMap) memberValue;
				PlaidProtoMethodMap protoMethod = new PlaidProtoMethodMap(pmm.getFullyQualifiedName(), pmm.getDelegate());
				frozenState.addMember(Util.memberDef(memberKey), protoMethod);
			} else if (memberValue instanceof PlaidAbstractValueMap) {
				frozenState.addMember(Util.memberDef(memberKey), memberValue);
			} else {	
				PlaidProtoFieldMap protoField = new PlaidProtoFieldMap(new Delegate() {
					public PlaidObject invoke(PlaidObject thisVar, PlaidObject args) throws PlaidException {	
						return memberValue;
					}
				});
				frozenState.addMember(memberKey, protoField);
			}
		}
		// add states from this object to frozen state
		if ( this.getStates() != PlaidObjectMap.EMPTY_STATES ) {
			for (PlaidObject ps : this.getStates() ) {
				frozenState.getPrototype().addState(ps);
			}
		}
		
		// add tags from this object to frozen state
		if ( this.getTags() != PlaidObjectMap.EMPTY_TAGS ) {
			for (PlaidTag t : this.getTags()) {
				frozenState.getPrototype().addTag(t);
			}
		}
		
		return frozenState;
	}
}
