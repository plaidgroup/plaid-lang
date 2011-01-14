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
import java.util.Set;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidJavaObject;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidTag;
import plaid.runtime.Util;
import plaid.runtime.types.PlaidPermissionTable;
import plaid.runtime.types.PlaidPermissionTableMap;
import plaid.runtime.types.PlaidPermission;
import plaid.runtime.types.PlaidPermType;
import plaid.runtime.types.PlaidUniquePermission;

public class PlaidObjectMap implements PlaidObject {
	protected PlaidPermissionTable permTable;
	protected Collection<PlaidObject> states;
	protected Map<PlaidMemberDef, PlaidObject> members;
	protected Collection<PlaidTag> tags;
	// map from scopes to sets of bound variable names for this object
	protected boolean readonly = false;
	
	public PlaidObjectMap() {
		this(new ArrayList<PlaidObject>(),
			 new HashMap<PlaidMemberDef, PlaidObject>(),
			 new ArrayList<PlaidTag>(),
			 new PlaidPermissionTableMap(),
			 PlaidUniquePermission.unique());
	}
	
	public PlaidObjectMap(PlaidPermission initPerm) {
		this(new ArrayList<PlaidObject>(),
				 new HashMap<PlaidMemberDef, PlaidObject>(),
				 new ArrayList<PlaidTag>(),
				 new PlaidPermissionTableMap(),
				 initPerm);
	}
	
	private PlaidObjectMap(ArrayList<PlaidObject> states, 
			HashMap<PlaidMemberDef, PlaidObject> members,
			ArrayList<PlaidTag> tags,
			PlaidPermissionTable permTable, 
			PlaidPermission initPerm) {
		this.states = states;
		this.members = members;
		this.tags = tags;
		this.permTable = permTable;
		initPerm.addPermission(this.permTable);
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
		
		//Look for existing member definition
		existingDef = findExisting(name, members.keySet());

		//if existing - update the existing member if allowed
		if (existingDef != null ) {
				
			//if existing is abstract, remove it and add the new one (do not update defined in)
			if (members.get(existingDef) instanceof PlaidAbstractValueMap) {
				members.remove(existingDef);
				members.put(memberDef, obj);
			} else {
				
				//check that old/new match in terms of mutability
				boolean existingIsMutable = existingDef.isMutable();
				if (existingIsMutable && !addedIsMutable)
					throw new PlaidRuntimeException("Member \"" + name + "\" already exists as a mutable member.");
				else if (!existingIsMutable && addedIsMutable)
					throw new PlaidRuntimeException("Member \"" + name + "\" already exists as an immutable member.");
	
				//check that the member is not already defined or that it is being overriden
				if (!(members.get(existingDef) instanceof PlaidAbstractValueMap) && !memberDef.overrides())  //undefined means having a unit value right now
					throw new PlaidRuntimeException("Member \"" + name + "\" already defined.");
			
				//find the correct tag to keep around
				if (memberDef.overrides()) {
					if (!memberDef.overrideIsBound()) {  //TODO:will we ever want to re-bind an override?
						if (existingDef.isAnonymous()) {
							memberDef.bindOverride("<Anonymous>"); // TODO: don't use a string like this
						} else 
							memberDef.bindOverride(existingDef.definedIn());
					}
					members.remove(existingDef);
					members.put(memberDef, obj);
				} else if (!existingDef.isAnonymous()) {
					//cannot both be defined in a state
					if (!memberDef.isAnonymous()) {
						throw new PlaidRuntimeException("Member \"" + name + "\" defined in states \"" + existingDef.definedIn() + 
							"\" and \"" + memberDef.definedIn() + "\".");
					} else { //take existing tag: just update the member
						members.put(existingDef,obj);
					}
				} else { //remove existing def and use the added def
					members.remove(existingDef);
					members.put(memberDef, obj);
				}
			}
		} else { // new member - just add it
			members.put(memberDef, obj);
			
		}
	}
	
	@Override
	public void updateMember(String name, PlaidObject obj) {
		if (this.isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		PlaidMemberDef existingDef = findExisting(name, getMembers().keySet());
		if (existingDef == null) { //Cannot update a member that is not present
			throw new PlaidRuntimeException("Member \"" + name + "\" is undefined.");
		} else {
			if (!existingDef.isMutable()) //Cannot update an immutable member
				throw new PlaidRuntimeException("Cannot update immutable member \"" + name + "\".");
			else  //update the definition
				members.put(existingDef, obj);
		}
	}

	@Override
	public void removeMember(String name) {
		if (this.isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		//remove the member if it exists else throw exception
		PlaidMemberDef existingDef = findExisting(name, members.keySet());
		if (existingDef != null) members.remove(existingDef);
		else throw new PlaidRuntimeException("Cannot remove undefined member \"" + name + "\".");
	}

	@Override
	public Map<PlaidMemberDef, PlaidObject> getMembers() {
		return Collections.unmodifiableMap(this.members);
	}
	
	@Override
	public void addState(PlaidObject state) throws PlaidException {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (!states.contains(state)) {
			states.add(state);
		}
	}

	@Override
	public void removeState(PlaidObject state) {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (states.contains(state)) {
			states.remove(state);
		}		
	}

	@Override
	public Collection<PlaidObject> getStates() {
		Collection<PlaidObject> result = new ArrayList<PlaidObject>();
		result.addAll(states);
		return Collections.unmodifiableCollection(result);
	}
	
	@Override
	public void addTag(PlaidTag tag) throws PlaidException {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (!tags.contains(tag)) {
			tags.add(tag);
		}
	}

	@Override
	public void removeTag(PlaidTag tag) {
		if (isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (tags.contains(tag)) {
			tags.remove(tag);
		}		
	}

	@Override
	public Collection<PlaidTag> getTags() {
		Collection<PlaidTag> result = new ArrayList<PlaidTag>();
		result.addAll(tags);
		return Collections.unmodifiableCollection(result);
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
		for (PlaidMemberDef member : members.keySet()) {
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
		Map<PlaidMemberDef,PlaidObject> newMembers = update.getMembers();
		for (PlaidMemberDef incomingMember : newMembers.keySet()) {
			if (!noAdd.contains(incomingMember.definedIn()) || //in the noAdd list, or
					members.get(findExisting(incomingMember.getMemberName(), members.keySet())) instanceof PlaidAbstractValueMap || //abstract
					removedOverrides.contains(incomingMember.getMemberName())) { //previously overriden
				PlaidObject incomingMemberValue = newMembers.get(incomingMember);
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
		members.clear(); //TODO: fix this
		states.clear();
		tags.clear();
		
		// add in the members from the updating object
		for (Map.Entry<PlaidMemberDef, PlaidObject> e : update.getMembers().entrySet()) {
			if (e.getValue() instanceof PlaidMethodMap) {
				PlaidMethodMap pmm = (PlaidMethodMap)e.getValue();
				addMember(e.getKey(), new PlaidMethodMap(pmm.getFullyQualifiedName(), this, pmm.delegate));
			}
			else {
				addMember(e.getKey(), e.getValue());
			}
		}
		
		// add other states
		states.addAll(update.getStates());
		tags.addAll(update.getTags());
	}
	
	@Override
	public PlaidObject copy() {
		PlaidObjectMap newObj = new PlaidObjectMap();
		// add members
		newObj.members.putAll(this.members);
		// add states
		newObj.states.addAll(this.states);
		// add tags
		newObj.tags.addAll(this.tags);
		return newObj;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		//members
		sb.append("\nPlaidObject(\n\tmembers={");
		for (PlaidMemberDef f : members.keySet()) {
			if (members.get(f) instanceof PlaidAbstractValueMap) sb.append("Abstract ");
			sb.append(f + ", ");
		}
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
	
		// states
		sb.append("}, \n\tstates={");
		for( Object s : states.toArray() ) {
			PlaidStateMap sm = (PlaidStateMap)s;
			sb.append(sm + ",");
		}
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		// tags
		sb.append("}, \n\ttags={");
		for (Object t : tags.toArray()) {
			PlaidTag tm = (PlaidTagMap)t;
			sb.append(tm + ",");
		}	
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		sb.append("})");
		return sb.toString();
	}

	protected static final PlaidMemberDef findExisting(String name, Set<PlaidMemberDef> members) {
		for (PlaidMemberDef m : members) {
			if (name.equals(m.getMemberName())) {
				return m;
			}		
		}
		return null;
	}

	@Override
	public int hashCode() {
		PlaidMemberDef hashmember = PlaidObjectMap.findExisting("hashCode", this.members.keySet());
		if (hashmember == null)
			return super.hashCode();
		PlaidObject hashmethobj = this.members.get(hashmember);
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
		PlaidMemberDef eqmember = PlaidObjectMap.findExisting("eqeq$plaid", this.members.keySet());
		if (eqmember == null)
			return false;
		PlaidObject eqmethobj = this.members.get(eqmember);
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
}
