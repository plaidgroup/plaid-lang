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
import plaid.runtime.Util;

public class PlaidObjectMap implements PlaidObject {
	protected Collection<PlaidObject> states;
	protected Map<String,PlaidObject> members;
	protected boolean readonly = false;
	
	public PlaidObjectMap() {
		states = new ArrayList<PlaidObject>();
		members = new HashMap<String, PlaidObject>();
	}

	public void setReadOnly(boolean ro) {
		readonly = ro;
	}
	
	public boolean isReadOnly() {
		return readonly;
	}
	
	@Override
	public void addMember(String name, PlaidObject obj) {
		if ( isReadOnly() ) throw new PlaidIllegalAccessException("Cannot change readonly object.");	
		members.put(name, obj);
	}

	@Override
	public void removeMember(String name) {
		if ( isReadOnly() ) throw new PlaidIllegalAccessException("Cannot change readonly object.");
		if ( members.containsKey(name) ) {
			members.remove(name);
		}		
	}

	@Override
	public Map<String, PlaidObject> getMembers() {
		return Collections.unmodifiableMap(members);
	}

	@Override
	public void addState(PlaidObject state) throws PlaidException {
		if ( isReadOnly() ) throw new PlaidIllegalAccessException("Cannot change readonly object.");		
		if ( !states.contains(state) ) {
			states.add(state);
		};
	}

	@Override
	public void removeState(PlaidObject state) {
		if ( isReadOnly() ) throw new PlaidIllegalAccessException("Cannot change readonly object.");
		if ( states.contains(state)) {
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
	public PlaidObject changeState(PlaidObject update) throws PlaidException {
		if ( isReadOnly() ) throw new PlaidIllegalAccessException("Cannot change readonly object.");
		// cleanup current information
		members.clear();
		states.clear();
		
		// convert other members 
		for ( Map.Entry<String, PlaidObject> e : update.getMembers().entrySet() ) {
			if ( e.getValue() instanceof PlaidMethodMap ) {
				PlaidMethodMap pmm = (PlaidMethodMap)e.getValue();
				addMember(e.getKey(), new PlaidMethodMap(this, pmm.delegate));
			} else {
				addMember(e.getKey(), e.getValue());
			}
		}
		
		// add other states
		states.addAll(update.getStates());
		return Util.unit();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PlaidObject(members={");
		for( String f : members.keySet()) {
			sb.append(f + ",");
		}		
		if ( sb.charAt(sb.length()-1) == ',') sb.deleteCharAt(sb.length()-1); 
		sb.append("}, states={");
		for( Object s : states.toArray() ) {
			PlaidStateMap sm = (PlaidStateMap)s;
			sb.append(sm + ",");
		}		
		if ( sb.charAt(sb.length()-1) == ',') sb.deleteCharAt(sb.length()-1);
		sb.append("})");
		return sb.toString();
	}
}
