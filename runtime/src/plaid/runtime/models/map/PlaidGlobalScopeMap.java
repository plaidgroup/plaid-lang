package plaid.runtime.models.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
	private final Set<Import> javaImports = new HashSet<Import>();
	private final Map<String, Import> importMap;
	private final Object importsLock = new Object();

	private final QualifiedIdentifier globalPackage;
	
	private static final Map<QualifiedIdentifier, PlaidGlobalScopeMap> globalScopes = new HashMap<QualifiedIdentifier, PlaidGlobalScopeMap>();

	private PlaidGlobalScopeMap(String qi, List<Import> imports) {
		this.importMap = new HashMap<String, Import>();
		this.addImports(imports);
		this.globalPackage = QualifiedIdentifier.getQI(qi);
	}
	
	private void addImport(Import imp) {
		synchronized (importsLock) {
			if (imp.isStar()) //java imports are the only starred imports, leave these for slow lookup
				javaImports.add(imp);
			else {
				Import current = importMap.get(imp.getIdent().getSuffix());
				if (current == null)
					importMap.put(imp.getIdent().getSuffix(), imp);
				else if (!current.equals(imp))
					throw new PlaidRuntimeException("Import conflict: Symbol `" + current.getIdent().getSuffix() 
						+ "' imported from packages `" + current.getIdent().getPrefix()
						+ "' and `" + imp.getIdent().getPrefix() + "'.");
			}
		}
	}
	
	private void addImports(Collection<Import> imports) {
		synchronized (importsLock) {
			for (Import imp : imports) {
				addImport(imp);
			}
		}
	}
	
	protected <S,T> Map<S, T> createMap() {
		return new ConcurrentHashMap<S, T>(16, (float)0.75, Runtime.getRuntime().availableProcessors());
	}
	
	public PlaidObject lookup(String name) {
		// check scope map
		if (mutableScopeMapContainsKey(name))
			return mutableScopeMap().get(name);
		else if (immutableScopeMapContainsKey(name))
			return immutableScopeMap().get(name);
		
		synchronized (importsLock) {
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
				QualifiedIdentifier nameQI = QualifiedIdentifier.getQI(name);
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
			return new PlaidLookupMap(this.globalPackage, QualifiedIdentifier.getQI(name));
		}
	}
	
	@Override
	public PlaidObject shallowLookup(String name) {
		// check scope map
		if (mutableScopeMapContainsKey(name))
			return mutableScopeMap().get(name);
		else if (immutableScopeMapContainsKey(name))
			return immutableScopeMap().get(name);
		else
			return null;
	}
	
	@Override
	public void insert(String name, PlaidObject plaidObj, boolean immutable) {
		if (immutableScopeMapContainsKey(name) || 
			mutableScopeMap().containsKey(name)) {
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
	
	public void update(String name, PlaidObject plaidObj) {
		if (immutableScopeMapContainsKey(name)) {
			throw new PlaidRuntimeException("Cannot assign to variables " +
											"declared with \"val\".");
		}
		else if (mutableScopeMapContainsKey(name)) {		
			mutableScopeMap().put(name, plaidObj);
		}
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof PlaidGlobalScopeMap))
			return false;
		return ((PlaidGlobalScopeMap)o).globalPackage.equals(this.globalPackage);
	}
	
	public int hashCode() {
		return globalPackage.hashCode();
	}
	
	public static PlaidGlobalScopeMap create(String qi, List<Import> imports) {
		synchronized (globalScopes) {
			QualifiedIdentifier qualID = QualifiedIdentifier.getQI(qi);
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
			throw new PlaidUnboundVariableException("Variable does not " +
					"exist in global scope.");
		}
	}
}
