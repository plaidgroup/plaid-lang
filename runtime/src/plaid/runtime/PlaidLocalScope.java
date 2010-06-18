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
public final class PlaidLocalScope extends AbstractPlaidScope {
	private final PlaidScope parentScope;
	private final Set<String> stateMembers;
	
	public PlaidLocalScope(PlaidScope parentScope) {
		super();
		this.parentScope = parentScope;
		this.stateMembers = new HashSet<String>();
	}
	
	public PlaidObject lookup(String name) {
		// Search in mutable scope map first so that (mutable) method parameters
		// properly shadow (immutable) methods.
		if (this.mutableScopeMap.containsKey(name))
			return this.mutableScopeMap.get(name);
		else if (this.immutableScopeMap.containsKey(name))
			return this.immutableScopeMap.get(name);
		return this.parentScope.lookup(name);
	}
	
	@Override
	public void insert(String name, PlaidObject plaidObj, boolean immutable) {
		// if there is an existing state member, we want to shadow it
		if (this.stateMembers.contains(name)) {
			// we need to remove this scope from the set of scopes in which the
			// old object was bound
			this.shallowLookup(name).removeNameBinding(name, this);
			
			// need to make sure we can't shadow the same name multiple times 
			this.stateMembers.remove(name);
		}
		else if (this.immutableScopeMap.containsKey(name) || 
				 this.mutableScopeMap.containsKey(name)) {
			throw new PlaidRuntimeException("Cannot insert '" + name + 
					"': already defined in current scope.");
		}
		
		if (immutable) {
			this.immutableScopeMap.put(name, plaidObj);
		}
		else {
			this.mutableScopeMap.put(name, plaidObj);
		}
		
		// add the new object-scope mapping
		plaidObj.addNameBinding(name, this);
	}
	
	public void update(String name, PlaidObject plaidObj) {
		if (this.immutableScopeMap.containsKey(name)) {
			throw new PlaidRuntimeException("Cannot assign to variables " +
					"declared with \"val\".");
		}
		else if (this.mutableScopeMap.containsKey(name)) {
			// if this is a state member, we have to update the actual state 
			// ("this") as well as the binding in the current scope
			if (this.stateMembers.contains(name)) {
				// get the object bound to "this" in the current scope
				PlaidObject thisObj = this.lookup("this$plaid");
				// update it's member bound to "name" in it
				thisObj.updateMember(name, plaidObj);
			}
			this.mutableScopeMap.put(name, plaidObj);
			
			// since we're binding a new object to the old variable name, we 
			// need to remove the binding to the old object and add a new 
			// binding of the same name to the new object
			this.shallowLookup(name).removeNameBinding(name, this);
			plaidObj.addNameBinding(name, this);
		}
		else {
			this.parentScope.update(name, plaidObj);
		}
	}
	
	@Override
	public void insertAllMembers(PlaidObject obj) {
		Map<String, PlaidObject> immutableMembers = obj.getImmutableMembers();
		Map<String, PlaidObject> mutableMembers = obj.getMutableMembers();
		for (Entry<String, PlaidObject> member : immutableMembers.entrySet()) {
			this.insert(member.getKey(), member.getValue(), true);
			// need to make sure that these members can be shadowed correctly
			this.stateMembers.add(member.getKey());
		}
		
		for (Entry<String, PlaidObject> member : mutableMembers.entrySet()) {
			this.insert(member.getKey(), member.getValue(), false);
			// need to make sure that these members can be shadowed correctly
			this.stateMembers.add(member.getKey());
		}
	}
	
	@Override
	public PlaidObject shallowLookup(String name) {
		if (this.mutableScopeMap.containsKey(name)) {
			return this.mutableScopeMap.get(name);
		}
		else if (this.immutableScopeMap.containsKey(name)) {
			return this.immutableScopeMap.get(name);
		}
		else {
			return null;
		}
	}
	
	@Override
	public void remove(String name) {
		if (this.immutableScopeMap.containsKey(name)) {
			this.immutableScopeMap.remove(name);
		}
		else if (this.mutableScopeMap.containsKey(name)) {
			this.mutableScopeMap.remove(name);
		}
		else {
			throw new PlaidRuntimeException("Variable does not exist in " +
					"current scope.");
		}
		this.stateMembers.remove(name);
	}
}
