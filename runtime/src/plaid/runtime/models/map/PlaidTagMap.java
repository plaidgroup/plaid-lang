package plaid.runtime.models.map;

import plaid.runtime.PlaidTag;

public class PlaidTagMap implements PlaidTag {

	private String tagName;
	private PlaidTag caseOf;
	private boolean hasSuperTag;
	
	public PlaidTagMap(String tagName) {
		this.tagName = tagName;
		hasSuperTag = false;
		
	}
	
	public PlaidTagMap(String tagName, PlaidTag caseOf) {
		this.tagName = tagName;
		this.caseOf = caseOf;
		hasSuperTag = (caseOf != null);
	}

	@Override
	public boolean hasSuperTag() {
		return hasSuperTag;
	}

	@Override
	public PlaidTag superTag() {
		return caseOf;
	}

	public String toString() {
		String s = "Tag<" + tagName;
		if (hasSuperTag)
			return  s + " of " + caseOf.getName() + ">";
		else
			return s + ">";
	}
	
	public String getName() {
			return tagName;
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
