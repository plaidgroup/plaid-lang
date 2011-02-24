package plaid.runtime.models.map;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import plaid.runtime.PlaidMemberDef;
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
	private volatile Set<String> stateMembers;

	public PlaidLocalScopeMap(PlaidScope parentScope) {
		super();
		this.parentScope = parentScope;
	}
	
	protected final Set<String> stateMembers() {
		if ( stateMembers == null ) {
			synchronized (this) {
				if ( stateMembers == null ) {
					//Map<String, Boolean> backend = new IdentityHashMap<String, Boolean>();
					//stateMembers = Collections.synchronizedSet(Collections.newSetFromMap(backend));
					stateMembers = Collections.synchronizedSet(new HashSet<String>());
				}
			}
		}
		return stateMembers;
	}
	
	protected final boolean stateMembersContains(String name) {
		if ( stateMembers == null  ) {
			return false;
		} else {
			return stateMembers().contains(name);
		}
	}
	
	@Override
	protected  <S,T> Map<S, T> createMap() {
		return Collections.synchronizedMap(new HashMap<S, T>());
	}
	
	private PlaidObject lookupMember(String name) {
		 if (immutableScopeMapContainsKey("this$plaid")) {
			 PlaidObject obj = this.immutableScopeMap.get("this$plaid");
			 PlaidMemberDef def = obj.getMember(name);
			 if (def != null)
				 return def.getValue();			
		 }
		return null;
	}
	
	@Override
	public PlaidObject lookup(String name) {
		// Search in mutable scope map first so that (mutable) method parameters
		// properly shadow (immutable) methods.
		if (mutableScopeMapContainsKey(name))
			return mutableScopeMap().get(name);
		else if (immutableScopeMapContainsKey(name))
			return immutableScopeMap().get(name);
		PlaidObject member = lookupMember(name);
		if (member != null)
			return member;
		
		return parentScope.lookup(name);
	}
	
	@Override
	public void insert(String name, PlaidObject plaidObj, boolean immutable) {
		// if there is an existing state member, we want to shadow it
		if (stateMembersContains(name)) {
			// need to make sure we can't shadow the same name multiple times 
			stateMembers().remove(name);
		}
		else if (immutableScopeMapContainsKey(name) || 
				mutableScopeMapContainsKey(name)) {
			throw new PlaidRuntimeException("Cannot insert '" + name + 
					"': already defined in current scope.");
		}
		
		if (immutable) {
			immutableScopeMap().put(name, plaidObj);
		}
		else {
			mutableScopeMap().put(name, plaidObj);
		}
	}
	
	private boolean updateMember(String name, PlaidObject plaidObj) {
		if (immutableScopeMapContainsKey("this$plaid")) {
			PlaidObject obj = this.immutableScopeMap.get("this$plaid");
			PlaidMemberDef def = obj.getMember(name);
			if (def != null && def.isMutable()) {
				obj.updateMember(name, plaidObj);
				return true;
			}	
		 }
		return false;
	}
	
	@Override
	public void update(String name, PlaidObject plaidObj) {
		if (immutableScopeMapContainsKey(name)) {
			throw new PlaidRuntimeException("Cannot assign to variables " +
					"declared with \"val\".");
		}
		else if (mutableScopeMapContainsKey(name)) {
			// if this is a state member, we have to update the actual state 
			// ("this") as well as the binding in the current scope
			if (stateMembersContains(name)) {
				// get the object bound to "this" in the current scope
				PlaidObject thisObj = lookup("this$plaid");
				// update it's member bound to "name" in it
				thisObj.updateMember(name, plaidObj);
			}
			mutableScopeMap().put(name, plaidObj);
		}
		else {
			if (!updateMember(name, plaidObj))
				parentScope.update(name, plaidObj);
		}
	}
	
	@Override
	public PlaidObject shallowLookup(String name) {
		if (mutableScopeMapContainsKey(name)) {
			return mutableScopeMap().get(name);
		}
		else if (immutableScopeMapContainsKey(name)) {
			return immutableScopeMap().get(name);
		}
		else {
			return null;
		}
	}
	
	@Override
	public void remove(String name) {
		if (immutableScopeMapContainsKey(name)) {
			immutableScopeMap().remove(name);
		}
		else if (mutableScopeMapContainsKey(name)) {
			mutableScopeMap().remove(name);
		}
		else {
			throw new PlaidUnboundVariableException("Variable does not exist in " +
					"current scope.");
		}
		stateMembers().remove(name);
	}
}
