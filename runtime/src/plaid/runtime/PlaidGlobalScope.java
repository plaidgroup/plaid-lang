package plaid.runtime;

import java.util.*;
import java.util.Map.Entry;

import plaid.runtime.models.map.PlaidLookupMap;
import plaid.runtime.utils.Import;
import plaid.runtime.utils.QualifiedIdentifier;

/**
 * Class to handle global scoping.  Globally scoped items include imports, 
 * top-level fields, methods, and state declarations.  An instance of this 
 * class must be at the top of every scope hierarchy (i.e. it is the root 
 * scope).
 * 
 * @author mhahnenberg
 *
 */
public final class PlaidGlobalScope extends AbstractPlaidScope {
	private final Set<Import> imports;
	private final Map<String, PlaidObject> mutableScopeMap;
	private final Map<String, PlaidObject> immutableScopeMap;
	private final QualifiedIdentifier globalPackage;
	
	private static final Map<QualifiedIdentifier, PlaidGlobalScope> 
		globalScopes = new HashMap<QualifiedIdentifier, PlaidGlobalScope>();

	private PlaidGlobalScope(String qi, List<Import> imports) {
		this.imports = new HashSet<Import>(imports);
		this.imports.add(new Import(qi + ".*"));
		this.mutableScopeMap = new HashMap<String, PlaidObject>();
		this.immutableScopeMap = new HashMap<String, PlaidObject>();
		this.globalPackage = new QualifiedIdentifier(qi);
	}
	
	public void addImport(Import imp) {
		this.imports.add(imp);
	}
	
	public void addImports(Collection<Import> imports) {
		this.imports.addAll(imports);
	}
	
	public PlaidObject lookup(String name) {
		// check scope map
		if (this.mutableScopeMap.containsKey(name))
			return this.mutableScopeMap.get(name);
		else if (this.immutableScopeMap.containsKey(name))
			return this.immutableScopeMap.get(name);
		
		for (Import imp : this.imports) {
			PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
			if (imp.isStar()) {
				QualifiedIdentifier nameQI = new QualifiedIdentifier(name);
				QualifiedIdentifier fullQualName;
				// if we specified a matching prefix too, use the full thing 
				// to lookup
				if (nameQI.getPrefix().equals(imp.getIdent().getPrefix())) {
					fullQualName = nameQI;
				}
				else {
					fullQualName = imp.getIdent().append(nameQI.getSuffix());
				}
				PlaidObject po = cl.loadClass(fullQualName.toString());
				if (po != null) {
					return po;
				}
			}
			else {
				if (imp.getIdent().getSuffix().equals(name)) {
					PlaidObject po = cl.loadClass(imp.getIdent().toString());
					if (po != null) {
						return po;
					}
					else {
						throw new PlaidClassNotFoundException(
							"Cannot find class : "+imp.getIdent().toString());
					}
				} 
			}
		}

		// couldn't find anything, so return a lookup map so they can try again
		return new PlaidLookupMap(this.globalPackage, 
								  new QualifiedIdentifier(name));
	}
	
	@Override
	public PlaidObject shallowLookup(String name) {
		// check scope map
		if (this.mutableScopeMap.containsKey(name))
			return this.mutableScopeMap.get(name);
		else if (this.immutableScopeMap.containsKey(name))
			return this.immutableScopeMap.get(name);
		else
			return null;
	}
	
	@Override
	public void insert(String name, PlaidObject plaidObj, boolean immutable) {
		if (this.immutableScopeMap.containsKey(name) || 
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
		
		plaidObj.addNameBinding(name, this);
	}
	
	public void update(String name, PlaidObject plaidObj) {
		if (this.immutableScopeMap.containsKey(name)) {
			throw new PlaidRuntimeException("Cannot assign to variables " +
											"declared with \"val\".");
		}
		else if (this.mutableScopeMap.containsKey(name)) {
			// since we're binding a new object to the old variable name, we need 
			// to remove the binding to the old object and add a new binding of 
			// the same name to the new object
			this.mutableScopeMap.get(name).removeNameBinding(name, this);
			
			this.mutableScopeMap.put(name, plaidObj);
		}
		
		
		plaidObj.addNameBinding(name, this);
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof PlaidGlobalScope))
			return false;
		return ((PlaidGlobalScope)o).globalPackage.equals(this.globalPackage);
	}
	
	public int hashCode() {
		return this.globalPackage.hashCode();
	}
	
	public static PlaidGlobalScope create(String qi, List<Import> imports) {
		QualifiedIdentifier qualID = new QualifiedIdentifier(qi);
		PlaidGlobalScope newGlobalScope;
		if (globalScopes.containsKey(qualID)) {
			newGlobalScope = globalScopes.get(qualID);
			newGlobalScope.imports.addAll(imports);
		}
		else {
			newGlobalScope = new PlaidGlobalScope(qi, imports);
			globalScopes.put(qualID, newGlobalScope);
		}
		return newGlobalScope;
	}
	
	@Override
	public void insertAllMembers(PlaidObject plaidObj) {
		Map<String, PlaidObject> immMembers = plaidObj.getImmutableMembers();
		Map<String, PlaidObject> mutMembers = plaidObj.getMutableMembers();
		for (Entry<String, PlaidObject> member : immMembers.entrySet()) {
			// if the lookup succeeds, we don't want to overwrite the old binding
			if (this.shallowLookup(member.getKey()) == null) {
				this.insert(member.getKey(), member.getValue(), true);
			}
		}
		
		for (Entry<String, PlaidObject> member : mutMembers.entrySet()) {
			// if the lookup succeeds, we don't want to overwrite the old binding
			if (this.shallowLookup(member.getKey()) == null) {
				this.insert(member.getKey(), member.getValue(), true);
			}
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
			throw new PlaidUnboundVariableException("Variable does not " +
					"exist in global scope.");
		}
	}
}
