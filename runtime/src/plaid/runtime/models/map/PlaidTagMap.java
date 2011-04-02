package plaid.runtime.models.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import plaid.runtime.PlaidPackage;
import plaid.runtime.PlaidTag;

public class PlaidTagMap implements PlaidTag {

	private String tagName;
	private PlaidTag superTag;
	//private PlaidState caseOf;
	private boolean hasSuperTag;
	private String root;
	private PlaidPackage pkg;
	private List<String> hierarchy = new ArrayList<String>();
	
	public PlaidTagMap(String tagName, PlaidPackage pkg, PlaidTag superTag) {
		this.tagName = tagName;
		this.pkg = pkg;
		//this.caseOf = caseOf;
		if (superTag != null) {
			this.hasSuperTag = true;
			this.superTag = superTag;
		} else {
			this.hasSuperTag = false;
			this.superTag = null;
		}
		hierarchy.add(this.getPath());
		PlaidTag findRoot = this;
		while (findRoot.hasSuperTag()) {
			findRoot = findRoot.superTag();
			hierarchy.add(findRoot.getPath());
		}
		root = findRoot.getPath();
	}
	
//	@Override
//	public PlaidState caseOf() {
//		return caseOf;
//	}

	@Override
	public boolean hasSuperTag() {
		return hasSuperTag;
	}

	@Override
	public PlaidTag superTag() {
		return this.superTag;
	}

	public String toString() {
		String toRet = "Tag<" + this.tagName;
		for (int i = 1; i < this.hierarchy.size(); i++) {
			toRet += " <: " + hierarchy.get(i);
		}
		return toRet + ">";
	}
	
	public String getName() {
			return tagName;
	}
	
	public String rootTag() {
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
	
	@Override
	public String getPath() {
		 return pkg.getQI().toString() + "." + this.tagName;
	}
}
