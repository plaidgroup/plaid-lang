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
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidTag;
import plaid.runtime.Util;

public class PlaidObjectMap implements PlaidObject {
	protected Collection<PlaidObject> states;
	protected Map<String, PlaidObject> immutableMembers;
	protected Map<String,PlaidObject> mutableMembers;
	protected Collection<PlaidTag> tags;
	protected boolean readonly = false;
	
	public PlaidObjectMap() {
		states = new ArrayList<PlaidObject>();
		immutableMembers = new HashMap<String, PlaidObject>();
		mutableMembers = new HashMap<String, PlaidObject>();
		tags = new ArrayList<PlaidTag>();
	}

	public void setReadOnly(boolean ro) {
		readonly = ro;
	}
	
	public boolean isReadOnly() {
		return readonly;
	}
	
	@Override
	public void addMember(String name, PlaidObject obj) {
		this.addMember(name, obj, true);
	}
	
	@Override
	public void addMember(String name, PlaidObject obj, boolean immutable) {
		if (this.isReadOnly())
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
				
		if (this.mutableMembers.containsKey(name) && immutable ||
			this.immutableMembers.containsKey(name) && !immutable) {
			throw new PlaidRuntimeException("Cannot have a member that is declared as both \"var\" and \"val\".");
		}
		
		if (immutable) {
			this.immutableMembers.put(name, obj);
		}
		else {
			this.mutableMembers.put(name, obj);
		}
	}
	
	@Override
	public void updateMember(String name, PlaidObject obj) {
		if (this.immutableMembers.containsKey(name)) {
			throw new PlaidRuntimeException("Cannot assign to variables declared with \"val\".");
		}
		else if (!this.mutableMembers.containsKey(name)) {
			throw new PlaidRuntimeException("Name \"" + name + "\" is undefined.");
		}
		this.mutableMembers.put(name, obj);
	}

	@Override
	public void removeMember(String name) {
		if (this.isReadOnly()) {
			throw new PlaidIllegalAccessException("Cannot change readonly object.");
		}
		
		if (this.mutableMembers.containsKey(name)) {
			this.mutableMembers.remove(name);
		}
		else if (this.immutableMembers.containsKey(name)) {
			this.immutableMembers.remove(name);
		}
	}

	@Override
	public Map<String, PlaidObject> getMembers() {
		// TODO: Some sort of caching?
		Map<String, PlaidObject> mapCopy = new HashMap<String, PlaidObject>();
		mapCopy.putAll(mutableMembers);
		mapCopy.putAll(immutableMembers);
		return mapCopy;
	}
	
	@Override
	public Map<String, PlaidObject> getImmutableMembers() {
		return Collections.unmodifiableMap(this.immutableMembers);
	}
	
	@Override
	public Map<String, PlaidObject> getMutableMembers() {
		return Collections.unmodifiableMap(this.mutableMembers);
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
		if ( isReadOnly() ) throw new PlaidIllegalAccessException("Cannot change readonly object.");
		// cleanup current information
		mutableMembers.clear();
		immutableMembers.clear();
		states.clear();
		tags.clear();
		
		// convert other immutable members 
		for (Map.Entry<String, PlaidObject> e : update.getImmutableMembers().entrySet()) {
			if (e.getValue() instanceof PlaidMethodMap) {
				PlaidMethodMap pmm = (PlaidMethodMap)e.getValue();
				addMember(e.getKey(), new PlaidMethodMap(this, pmm.delegate), true);
			}
			else {
				addMember(e.getKey(), e.getValue(), true);
			}
		}
		
		// convert other mutable members 
		for (Map.Entry<String, PlaidObject> e : update.getMutableMembers().entrySet()) {
			if (e.getValue() instanceof PlaidMethodMap) {
				PlaidMethodMap pmm = (PlaidMethodMap)e.getValue();
				addMember(e.getKey(), new PlaidMethodMap(this, pmm.delegate), false);
			}
			else {
				addMember(e.getKey(), e.getValue(), false);
			}
		}
		
		// add other states
		states.addAll(update.getStates());
		tags.addAll(update.getTags());
		return Util.unit();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// mutable members
		sb.append("PlaidObject(mutableMembers={");
		for (String f : mutableMembers.keySet()) {
			sb.append(f + ", ");
		}
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		// immutable members
		sb.append("}, \nimmutableMembers={");
		for (String f : immutableMembers.keySet()) {
			sb.append(f + ", ");
		}
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		// states
		sb.append("}, \nstates={");
		for( Object s : states.toArray() ) {
			PlaidStateMap sm = (PlaidStateMap)s;
			sb.append(sm + ",");
		}
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		// tags
		sb.append("}, \ntags={");
		for (Object t : tags.toArray()) {
			PlaidTag tm = (PlaidTagMap)t;
			sb.append(tm + ",");
		}	
		if (sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		sb.append("})");
		return sb.toString();
	}
}
