package plaid.runtime.models.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import plaid.runtime.PlaidState;
import plaid.runtime.PlaidTag;

public class PlaidTagMap implements PlaidTag {

	private String theCase;
	private PlaidState caseOf;
	private boolean hasSuperTag;
	private String root;
	private List<String> hierarchy = new ArrayList<String>();
	
	public PlaidTagMap(String theCase, PlaidState caseOf) {
		this.theCase = theCase;
		this.caseOf = caseOf;
		hasSuperTag = caseOf.hasTag();
		hierarchy.add(theCase);
		PlaidTag findRoot = this;
		while (findRoot.hasSuperTag()) {
			hierarchy.add(findRoot.caseOf().getPath());
			findRoot = findRoot.superTag();
		}
		root = findRoot.caseOf().getPath();
		hierarchy.add(root);
	}
	
	@Override
	public PlaidState caseOf() {
		return caseOf;
	}

	@Override
	public boolean hasSuperTag() {
		return hasSuperTag;
	}

	@Override
	public PlaidTag superTag() {
		return caseOf.getTag();
	}

	public String toString() {
		return "Tag<" + theCase + " of " + caseOf.toString().substring(5);	
	}
	
	public String getName() {
			return theCase;
	}
	
	public String rootState() {
		return root;
	}
	
	public List<String> getHierarchy() {
		return Collections.unmodifiableList(hierarchy);
	}
	
	
	public boolean matches(String tag) {
		if (tag.equals(getName())){
			return true;
		} else if (hasSuperTag()) {
			return superTag().matches(tag);
		} else {
			return false;
		}
	}
}
