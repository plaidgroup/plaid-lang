package plaid.runtime.models.map;

import java.util.*;

import plaid.runtime.PlaidClassLoader;
import plaid.runtime.PlaidClassNotFoundException;
import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidRuntime;
import plaid.runtime.PlaidRuntimeException;
import plaid.runtime.PlaidUnboundVariableException;
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
public final class PlaidGlobalScopeMap extends AbstractPlaidScopeMap {
	private final Set<Import> imports;
	private final Map<String, PlaidObject> mutableScopeMap;
	private final Map<String, PlaidObject> immutableScopeMap;
	private final QualifiedIdentifier globalPackage;
	
	private static final Map<QualifiedIdentifier, PlaidGlobalScopeMap> 
		globalScopes = new HashMap<QualifiedIdentifier, PlaidGlobalScopeMap>();

	private PlaidGlobalScopeMap(String qi, List<Import> imports) {
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
	}
	
	public void update(String name, PlaidObject plaidObj) {
		if (this.immutableScopeMap.containsKey(name)) {
			throw new PlaidRuntimeException("Cannot assign to variables " +
											"declared with \"val\".");
		}
		else if (this.mutableScopeMap.containsKey(name)) {		
			this.mutableScopeMap.put(name, plaidObj);
		}
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof PlaidGlobalScopeMap))
			return false;
		return ((PlaidGlobalScopeMap)o).globalPackage.equals(this.globalPackage);
	}
	
	public int hashCode() {
		return this.globalPackage.hashCode();
	}
	
	public static PlaidGlobalScopeMap create(String qi, List<Import> imports) {
		QualifiedIdentifier qualID = new QualifiedIdentifier(qi);
		PlaidGlobalScopeMap newGlobalScope;
		if (globalScopes.containsKey(qualID)) {
			newGlobalScope = globalScopes.get(qualID);
			newGlobalScope.imports.addAll(imports);
		}
		else {
			newGlobalScope = new PlaidGlobalScopeMap(qi, imports);
			globalScopes.put(qualID, newGlobalScope);
		}
		return newGlobalScope;
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
