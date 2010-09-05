package plaid.runtime;

public interface PlaidScope {
	
	/**
	 * Looks up the given name in the current scope, recursively searching 
	 * parent scopes if necessary.
	 * 
	 * @param name
	 * @return The PlaidObject bound to this name in the current scope.
	 */
	public PlaidObject lookup(String name);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public PlaidObject shallowLookup(String name);
	
	/**
	 * Inserts a new binding into the current scope.  If one already exists in 
	 * the current scope (not the parent scopes) then this is an error.
	 * 
	 * @param name
	 * @param plaidObj
	 */
	public void insert(String name, PlaidObject plaidObj);
	
	/**
	 * TODO: Document
	 * @param name
	 * @param plaidObj
	 * @param immutable
	 */
	public void insert(String name, PlaidObject plaidObj, boolean immutable);
	
	/**
	 * Updates a pre-existing variable name binding with a new PlaidObject.  
	 * Recursively searches parent scopes until it encounters the specified 
	 * name.  If the name does not already exist in this scope or one of its 
	 * parent scopes then this is an error.
	 * 
	 * @param name
	 * @param plaidObj
	 */
	public void update(String name, PlaidObject plaidObj);
	
	/**
	 * 
	 * @param name
	 */
	public void remove(String name);
}
