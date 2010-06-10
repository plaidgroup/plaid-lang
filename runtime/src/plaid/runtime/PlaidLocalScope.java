package plaid.runtime;

import java.util.*;
import java.util.Map.Entry;

/**
 * Class to handle local scoping.  Maps variable names to the objects they are 
 * bound to in the current local scope.
 * 
 * @author mhahnenberg
 *
 */
public class PlaidLocalScope implements PlaidScope {
	private final PlaidScope parentScope;
	private final Map<String, PlaidObject> immutableScopeMap;
	private final Map<String, PlaidObject> mutableScopeMap;
	private final Set<String> stateMembers;
	
	public PlaidLocalScope(PlaidScope parentScope) {
		this.parentScope = parentScope;
		this.immutableScopeMap = new HashMap<String, PlaidObject>();
		this.mutableScopeMap = new HashMap<String, PlaidObject>();
		this.stateMembers = new HashSet<String>();
	}
	
	public PlaidObject lookup(String name) {
		if (this.immutableScopeMap.containsKey(name))
			return this.immutableScopeMap.get(name);
		else if (this.mutableScopeMap.containsKey(name))
			return this.mutableScopeMap.get(name);
		return this.parentScope.lookup(name);
	}
	
	@Override
	public void insert(String name, PlaidObject plaidObj) {
		this.insert(name, plaidObj, true);
	}
	
	@Override
	public void insert(String name, PlaidObject plaidObj, boolean immutable) {
		// if there is an existing state member, we want to shadow it
		if (this.stateMembers.contains(name)) {
			// need to make sure we can't shadow the same name multiple times though
			this.stateMembers.remove(name);
		}
		else if (this.immutableScopeMap.containsKey(name) || this.mutableScopeMap.containsKey(name)) {
			throw new PlaidRuntimeException("Cannot insert '" + name + "': already defined in current scope.");
		}
		
		if (immutable) {
			this.immutableScopeMap.put(name, plaidObj);
		}
		else {
			this.mutableScopeMap.put(name, plaidObj);
		}
	}
	
	public void update(String name, PlaidObject plaidObj) {
		if (this.immutableScopeMap.containsKey(name)) {
			throw new PlaidRuntimeException("Cannot assign to variables declared with \"val\".");
		}
		else if (this.mutableScopeMap.containsKey(name)) {
			// if this is a state member, we have to update the actual state ("this") 
			// as well as the binding in the current scope
			if (this.stateMembers.contains(name)) {
				// get the object bound to "this" in the current scope
				PlaidObject thisObj = this.lookup("this$plaid");
				// update it's member bound to "name" in it
				thisObj.updateMember(name, plaidObj);
			}
			this.mutableScopeMap.put(name, plaidObj);
		}
		else {
			this.parentScope.update(name, plaidObj);
		}
	}
	
	@Override
	public void insertAllMembers(PlaidObject obj) {
		for (Entry<String, PlaidObject> member : obj.getImmutableMembers().entrySet()) {
			this.insert(member.getKey(), member.getValue(), true);
			// we need to make sure that these members can be shadowed correctly
			this.stateMembers.add(member.getKey());
		}
		
		for (Entry<String, PlaidObject> member : obj.getMutableMembers().entrySet()) {
			this.insert(member.getKey(), member.getValue(), false);
			// we need to make sure that these members can be shadowed correctly
			this.stateMembers.add(member.getKey());
		}
	}

	@Override
	public void clearOldMembers(PlaidObject obj) {
		// make sure this is the right scope to change things
		if (this.immutableScopeMap.containsValue(obj) || this.mutableScopeMap.containsValue(obj)) {
			for (Entry<String, PlaidObject> member : obj.getMembers().entrySet()) {
				if (this.immutableScopeMap.containsKey(member.getKey())) {
					this.immutableScopeMap.remove(member.getKey());
				}
				else if (this.mutableScopeMap.containsKey(member.getKey())) {
					this.mutableScopeMap.remove(member.getKey());
				}
				else {
					throw new PlaidRuntimeException("Error: member of object not found in corresponding scope.");
				}
				this.stateMembers.remove(member.getKey());
			}
		}
		else {
			this.parentScope.clearOldMembers(obj);
		}
	}
}
