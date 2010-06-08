package plaid.runtime;

import java.util.*;

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
	
	public PlaidLocalScope(PlaidScope parentScope) {
		this.parentScope = parentScope;
		this.immutableScopeMap = new HashMap<String, PlaidObject>();
		this.mutableScopeMap = new HashMap<String, PlaidObject>();
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
		if (this.immutableScopeMap.containsKey(name) || this.mutableScopeMap.containsKey(name)) {
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
			this.mutableScopeMap.put(name, plaidObj);
		}
		else {
			this.parentScope.update(name, plaidObj);
		}
	}
}
