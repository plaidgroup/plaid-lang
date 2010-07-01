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
import java.util.Map;

import plaid.runtime.PlaidException;
import plaid.runtime.PlaidIllegalAccessException;
import plaid.runtime.PlaidMemberDef;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidTag;
import plaid.runtime.Util;

public class PlaidObjectMap implements PlaidObject {
	protected Collection<PlaidObject> states;
	protected Map<PlaidMemberDef, PlaidObject> members;
	//protected Map<PlaidMemberDef, PlaidObject> immutableMembers;
	//protected Map<PlaidMemberDef,PlaidObject> mutableMembers;
	protected Collection<PlaidTag> tags;
	// map from scopes to sets of bound variable names for this object
	protected boolean readonly = false;
	
	public PlaidObjectMap() {
		states = new ArrayList<PlaidObject>();
		members = new HashMap<PlaidMemberDef, PlaidObject>();
		//immutableMembers = new HashMap<PlaidMemberDef, PlaidObject>();
		//mutableMembers = new HashMap<PlaidMemberDef, PlaidObject>();
		tags = new ArrayList<PlaidTag>();
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
		existingDef = findExisting(name);

		//update the existing member if compatible
		if (existingDef != null) {
			//check that old/new match in terms of mutability
			boolean existingIsMutable = existingDef.isMutable();
			if (existingIsMutable && !addedIsMutable)
				throw new PlaidRuntimeException("Member \"" + name + "\" already exists as a mutable member.");
			else if (!existingIsMutable && addedIsMutable)
				throw new PlaidRuntimeException("Member \"" + name + "\" already exists as an immutable member.");

			//check that the member is not already defined
			if (!members.get(existingDef).equals(PlaidRuntime.getRuntime().getClassLoader().unit()))  //undefined means having a unit value right now
				throw new PlaidRuntimeException("Member \"" + name + "\" already defined.");
			
			//find the correct tag to keep around
			if (!existingDef.isAnonymous()) {
				//cannot both be defined in a state
				//TODO: overriding - take more specific tag if specified to override
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
		} else { // new member - just add it
			members.put(memberDef, obj);
			
		}
	}
	
	@Override
	public void updateMember(String name, PlaidObject obj) {
		if (this.isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		PlaidMemberDef existingDef = findExisting(name);
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
		PlaidMemberDef existingDef = findExisting(name);
		if (existingDef != null) members.remove(existingDef);
		else throw new PlaidRuntimeException("Cannot remove undefined member \"" + name + "\".");
	}

	@Override
	public Map<PlaidMemberDef, PlaidObject> getMembers() {
		// TODO: Some sort of caching?
		Map<PlaidMemberDef, PlaidObject> mapCopy = new HashMap<PlaidMemberDef, PlaidObject>();
		mapCopy.putAll(members);
		return mapCopy;
	}
	
//	@Override
//	public Map<String, PlaidObject> getImmutableMembers() {
//		return Collections.unmodifiableMap(this.immutableMembers);
//	}
//	
//	@Override
//	public Map<String, PlaidObject> getMutableMembers() {
//		return Collections.unmodifiableMap(this.mutableMembers);
//	}

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
		
//		// convert other mutable members 
//		for (Map.Entry<String, PlaidObject> e : update.getMutableMembers().entrySet()) {
//			if (e.getValue() instanceof PlaidMethodMap) {
//				PlaidMethodMap pmm = (PlaidMethodMap)e.getValue();
//				addMember(e.getKey(), new PlaidMethodMap(pmm.getFullyQualifiedName(), this, pmm.delegate), false);
//			}
//			else {
//				addMember(e.getKey(), e.getValue(), false);
//			}
//		}
//		
		// add other states
		states.addAll(update.getStates());
		tags.addAll(update.getTags());
		
		return Util.unit();
	}
	
	@Override
	public PlaidObject copy() {
		PlaidObjectMap newObj = new PlaidObjectMap();
		// add members
		newObj.members.putAll(this.members);
//		// add immutable members
//		newObj.immutableMembers.putAll(this.immutableMembers);
//		// add mutable members
//		newObj.mutableMembers.putAll(this.mutableMembers);
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
			sb.append(f + ", ");
		}
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
//		// mutable members
//		sb.append("\nPlaidObject(\n\tmutableMembers={");
//		for (String f : mutableMembers.keySet()) {
//			sb.append(f + ", ");
//		}
//		if (sb.charAt(sb.length()-1) == ',')
//			sb.deleteCharAt(sb.length()-1);
//		
//		// immutable members
//		sb.append("}, \n\timmutableMembers={");
//		for (String f : immutableMembers.keySet()) {
//			sb.append(f + ", ");
//		}
//		if (sb.charAt(sb.length()-1) == ',')
//			sb.deleteCharAt(sb.length()-1);
//		
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

	private PlaidMemberDef findExisting(String name) {
		for (PlaidMemberDef m : members.keySet()) {
			if (name.equals(m.getMemberName())) {
				return m;
			}		
		}
		return null;
	}

}
