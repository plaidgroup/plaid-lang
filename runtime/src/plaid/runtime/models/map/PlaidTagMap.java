package plaid.runtime.models.map;

import plaid.runtime.PlaidState;
import plaid.runtime.PlaidTag;

public class PlaidTagMap implements PlaidTag {

	private String theCase;
	private PlaidState caseOf;
	private boolean hasSuperTag;
	
	public PlaidTagMap(String theCase, PlaidState caseOf) {
		this.theCase = theCase;
		this.caseOf = caseOf;
		hasSuperTag = caseOf.hasTag();
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
}
