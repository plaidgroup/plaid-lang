package plaid.runtime.models.map;

import java.util.ArrayList;
import java.util.List;

import plaid.runtime.PlaidObject;
import plaid.runtime.PlaidState;
import plaid.runtime.PlaidTag;
import plaid.runtime.utils.QualifiedIdentifier;

public class PlaidTagMap implements PlaidTag {

	private String tagName;
	private PlaidTag superTag;
	//private PlaidState caseOf;
	private boolean hasSuperTag;
	private PlaidTag root;
	private QualifiedIdentifier pkg;
	private List<PlaidTag> hierarchy = new ArrayList<PlaidTag>();
	
	public PlaidTagMap(String tagName, String pkg, PlaidTag superTag) {
		this.tagName = tagName;
		this.pkg = QualifiedIdentifier.getQI(pkg);
		//this.caseOf = caseOf;
		if (superTag != null) {
			this.hasSuperTag = true;
			this.superTag = superTag;
		} else {
			this.hasSuperTag = false;
			this.superTag = null;
		}
		hierarchy.add(this);
		PlaidTag findRoot = this;
		while (findRoot.hasSuperTag()) {
			findRoot = findRoot.superTag();
			hierarchy.add(findRoot);
		}
		root = findRoot;
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
			toRet += " <: " + hierarchy.get(i).getName();
		}
		return toRet + ">";
	}
	
	public String getName() {
			return tagName;
	}
	
	public PlaidTag rootTag() {
		return root;
	}
	
	//@Override
	public List<PlaidTag> getHierarchy() {
		return hierarchy;
	}
	
	//@Override
	public boolean matches(PlaidTag tag) {
		if (this.root != tag.rootTag()) return false;
		return tag.getHierarchy().contains(this);  //TODO: probably a more efficient way to do this - the above line should help
	}
	
	@Override
	public String getPath() {
		 return pkg.toString() + "." + this.tagName;
	}
	
	@Override
	public void nest(PlaidState s) {
		PlaidObject prototype = s.getPrototype();   
		List<PlaidTag> oldTopTags = new ArrayList<PlaidTag>();
		oldTopTags.addAll(prototype.getTopTags());
		for (PlaidTag oldTopTag : oldTopTags ) {
			prototype.removeTag(oldTopTag);
			
			//if matches, is the parent tag which is added later implicitly with inTag as topTag
			if (!oldTopTag.matches(this))  
				prototype.addTag(oldTopTag, this);
		}
		prototype.addTopTag(this);
		
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (o instanceof PlaidTag) {
			return this == o;
		}
		else return false;
	}
}
