package plaid.runtime.models.map;

import java.util.Map;

import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidScope;

/**
 * Abstract class for the implementation of some common functionality of both 
 * local and global scopes.
 * 
 * @author mhahnenberg
 *
 */
public abstract class AbstractPlaidScopeMap implements PlaidScope {
	protected volatile Map<String, PlaidObject> immutableScopeMap;
	protected volatile Map<String, PlaidObject> mutableScopeMap;	
	
	public AbstractPlaidScopeMap() {
	}
	
	protected abstract <S,T> Map<S, T> createMap();
	
	protected final Map<String, PlaidObject> immutableScopeMap() {
		if ( immutableScopeMap == null ) {
			synchronized (this) {
				if ( immutableScopeMap == null ) {
					Map<String, PlaidObject> map = createMap();
					immutableScopeMap =  map;
				}
			}
		}
		return immutableScopeMap;
	}
	
	protected final Map<String, PlaidObject> mutableScopeMap() {
		if ( mutableScopeMap == null ) {
			synchronized (this) {
				if ( mutableScopeMap == null ) {
					Map<String, PlaidObject> map = createMap();
					mutableScopeMap =  map;
				}
			}
		}
		return mutableScopeMap;
	}
	
	protected final boolean immutableScopeMapContainsKey(String name) {
		if ( immutableScopeMap == null ) {
			return false;
		} else {
			return immutableScopeMap().containsKey(name);
		}
	}
	
	protected final boolean mutableScopeMapContainsKey(String name) {
		if ( mutableScopeMap == null ) {
			return false;
		} else {
			return mutableScopeMap().containsKey(name);
		}
	}

	@Override
	public void insert(String name, PlaidObject plaidObj) {
		insert(name, plaidObj, true);
	}
}
