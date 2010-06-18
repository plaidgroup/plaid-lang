package plaid.runtime;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Abstract class for the implementation of some common functionality of both 
 * local and global scopes.
 * 
 * @author mhahnenberg
 *
 */
public abstract class AbstractPlaidScope implements PlaidScope {
	protected final Map<String, PlaidObject> immutableScopeMap;
	protected final Map<String, PlaidObject> mutableScopeMap;
	
	public AbstractPlaidScope() {
		this.immutableScopeMap = new HashMap<String, PlaidObject>();
		this.mutableScopeMap = new HashMap<String, PlaidObject>();
	}

	@Override
	public void clearOrUpdateOldMembers(PlaidObject oldObj, PlaidObject newObj) {
		Map<String, PlaidObject> oldMembers = oldObj.getMembers();
		
		// with the way that state change works, newObj is actually the same memory reference 
		// as it used to be, so all of the scopes in which it was "this" are actually looking for newObj
		// instead of oldObj.  this is because the equals and hashcode method for PlaidObject has not been
		// overridden from Java's default Object implementation, so we have to use newObj here.
		for (PlaidScope scope : newObj.getBoundScopes()) {
			PlaidObject obj = scope.shallowLookup("this$plaid");
			// from scopes where newObj is "this"
			if (obj != null && obj.equals(newObj)) {
				// remove all old members 
				for (Entry<String, PlaidObject> oldMember : oldMembers.entrySet()) {
					scope.remove(oldMember.getKey());
				}
				
				// insert all the new members
				scope.insertAllMembers(newObj);
			}
		}
	}

	@Override
	public void insert(String name, PlaidObject plaidObj) {
		this.insert(name, plaidObj, true);
	}
}
