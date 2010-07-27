package plaid.runtime.models.map;

import java.util.*;

import plaid.runtime.PlaidClassLoader;
import plaid.runtime.PlaidClassNotFoundException;
import plaid.runtime.PlaidConstants;
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
	//private static final Map<Import, Set<Import>> expandedImports = new HashMap<Import, Set<Import>>();
	private static final Set<Import> javaImports = new HashSet<Import>();
	
	private final Map<String, Import> importMap;
	private final Map<String, PlaidObject> mutableScopeMap;
	private final Map<String, PlaidObject> immutableScopeMap;
	private final QualifiedIdentifier globalPackage;
	
	private static final Map<QualifiedIdentifier, PlaidGlobalScopeMap> globalScopes = new HashMap<QualifiedIdentifier, PlaidGlobalScopeMap>();

	private PlaidGlobalScopeMap(String qi, List<Import> imports) {
		this.importMap = new HashMap<String, Import>();
		this.addImports(imports);
		//this.addImport(new Import(qi + ".*"));  //this will now be done in the compiler
		this.mutableScopeMap = new HashMap<String, PlaidObject>();
		this.immutableScopeMap = new HashMap<String, PlaidObject>();
		this.globalPackage = new QualifiedIdentifier(qi);
	}
	
	private void addImport(Import imp) {
		if (imp.isStar()) //java imports are the only starred imports, leave these for slow lookup
			javaImports.add(imp);
		else {
			Import current = this.importMap.get(imp.getIdent().getSuffix());
			if (current == null)
				this.importMap.put(imp.getIdent().getSuffix(), imp);
			else if (!current.equals(imp))
				throw new PlaidRuntimeException("Import conflict: Symbol `" + current.getIdent().getSuffix() 
					+ "' imported from packages `" + current.getIdent().getPrefix()
					+ "' and `" + imp.getIdent().getPrefix() + "'.");
		}
	}
	
	private void addImports(Collection<Import> imports) {
		for (Import imp : imports)
			this.addImport(imp);
	}
	
	public PlaidObject lookup(String name) {
		// check scope map
		if (this.mutableScopeMap.containsKey(name))
			return this.mutableScopeMap.get(name);
		else if (this.immutableScopeMap.containsKey(name))
			return this.immutableScopeMap.get(name);
		
		// TODO: Check if that's correct.
		Import imp = importMap.get(name + PlaidConstants.ID_SUFFIX);
		if (imp == null)
			imp = importMap.get(name);
		
		if (imp != null) {
			PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
			PlaidObject po = cl.loadClass(imp.getIdent().toString());
			if (po != null) {
				return po;
			}
			else {
				throw new PlaidClassNotFoundException(
						"Cannot find class : " + imp.getIdent().toString());
			}
		}
		
		
		// We currently can't expand Java imports like import java.util.*;
		// So to keep everything working we fall back to our old (slower) system in that case.
		for (Import im : javaImports) {
			PlaidClassLoader cl = PlaidRuntime.getRuntime().getClassLoader();
			QualifiedIdentifier nameQI = new QualifiedIdentifier(name);
			QualifiedIdentifier fullQualName;
			// If we specified a matching prefix too, use the full thing to lookup.
			if (nameQI.getPrefix().equals(im.getIdent().getPrefix())) {
				fullQualName = nameQI;
			}
			else {
				fullQualName = im.getIdent().append(nameQI.getSuffix());
			}
			PlaidObject po = cl.loadClass(fullQualName.toString());
			if (po != null) {
				return po;
			}
		}
		
		// couldn't find anything, so return a lookup map so they can try again
		return new PlaidLookupMap(this.globalPackage, new QualifiedIdentifier(name));
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
			newGlobalScope.addImports(imports);
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
