package plaid.runtime;

import java.util.HashMap;
import java.util.Map;

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
	public void insert(String name, PlaidObject plaidObj) {
		this.insert(name, plaidObj, true);
	}
}
