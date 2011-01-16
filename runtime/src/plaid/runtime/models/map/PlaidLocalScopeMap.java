package plaid.runtime.models.map;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidScope;
import plaid.runtime.PlaidUnboundVariableException;

/**
 * Class to handle local scoping.  Maps variable names to the objects they are 
 * bound to in the current local scope.
 * 
 * @author mhahnenberg
 *
 */
public final class PlaidLocalScopeMap extends AbstractPlaidScopeMap {
	private final PlaidScope parentScope;
	private Set<String> stateMembers;
	
	public PlaidLocalScopeMap(PlaidScope parentScope) {
		super();
		this.parentScope = parentScope;

	}
	
	protected final Set<String> stateMembers() {
		if ( stateMembers == null ) {
			synchronized (this) {
				if ( stateMembers == null ) {
					//Map<String, Boolean> backend = new IdentityHashMap<String, Boolean>();
					//this.stateMembers = Collections.synchronizedSet(Collections.newSetFromMap(backend));
					this.stateMembers = Collections.synchronizedSet(new HashSet<String>());
				}
			}
		}
		return stateMembers;
	}
	
	@Override
	protected  <S,T> Map<S, T> createMap() {
		return Collections.synchronizedMap(new HashMap<S, T>());
	}
	
	@Override
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
		if (this.stateMembers().contains(name)) {
			// need to make sure we can't shadow the same name multiple times 
			this.stateMembers().remove(name);
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
	}
	
	@Override
	public void update(String name, PlaidObject plaidObj) {
		if (this.immutableScopeMap.containsKey(name)) {
			throw new PlaidRuntimeException("Cannot assign to variables " +
					"declared with \"val\".");
		}
		else if (this.mutableScopeMap.containsKey(name)) {
			// if this is a state member, we have to update the actual state 
			// ("this") as well as the binding in the current scope
			if (this.stateMembers().contains(name)) {
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
			throw new PlaidUnboundVariableException("Variable does not exist in " +
					"current scope.");
		}
		this.stateMembers().remove(name);
	}
}
