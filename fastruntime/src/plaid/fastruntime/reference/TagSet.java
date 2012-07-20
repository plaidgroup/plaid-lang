package plaid.fastruntime.reference;

import java.util.HashMap;


public final class TagSet {
	
	private final HashMap<String, Object> tagMap;
	
	public static final TagSet EMPTY = new TagSet();
	
	private TagSet(String tag) {
		this.tagMap = new HashMap<String, Object>();
		this.tagMap.put(tag, null);
	}
	
	private TagSet(HashMap<String, Object> tagMap) {
		this.tagMap = tagMap;
	}
	
	private TagSet() {
		this.tagMap = new HashMap<String, Object>();
	}
	
	public static TagSet makeSingle(String tag) {
		return new TagSet(tag);
	}
	
	public static TagSet makeEmpty() {
		return EMPTY;
	}

	public boolean emptyIntersection(TagSet otherSet) {
		if(this == EMPTY || otherSet == EMPTY) {
			return true;
		} else {
			for(String tag : this.tagMap.keySet()) {
				if (otherSet.tagMap.containsKey(tag)) {
					return false;
				}
			}
			return true;
		}
	}
	
	public TagSet union(TagSet otherSet) {
		if (this == EMPTY) {
			return otherSet;
		} else if (otherSet == EMPTY) {
			return this;
		} else {
			
			@SuppressWarnings("unchecked")
			HashMap<String, Object> newTagMap = (HashMap<String, Object>)otherSet.tagMap.clone();
			newTagMap.putAll(tagMap);
			return new TagSet(newTagMap);
		}
	}
	
	public static TagSet makeAllFromSingleValues(Iterable<SingleValue> singleValues) {
		HashMap<String,Object> newTagMap = new HashMap<String,Object>();
		for(SingleValue singleValue : singleValues) {
			newTagMap.putAll(singleValue.getTags().tagMap);
		}
		return tagMapOrEmpty(newTagMap);
	}
	
	public static TagSet makeInnerFromSingleValues(Iterable<SingleValue> singleValues) {
		HashMap<String,Object> newTagMap = new HashMap<String,Object>();
		for(SingleValue singleValue : singleValues) {
			newTagMap.putAll(singleValue.getInnerTags().tagMap);
		}
		return tagMapOrEmpty(newTagMap);
	}
	
	public static TagSet makeOuterFromSingleValues(Iterable<SingleValue> singleValues) {
		HashMap<String,Object> newTagMap = new HashMap<String,Object>();
		for(SingleValue singleValue : singleValues) {
			newTagMap.putAll(singleValue.getOuterTags().tagMap);
		}
		return tagMapOrEmpty(newTagMap);
	}
	
	private static TagSet tagMapOrEmpty(HashMap<String, Object> tagMap) {
		if (tagMap.isEmpty()) {
			return EMPTY;
		} else {
			return new TagSet(tagMap);
		}
	}
	
	public boolean member(String tag) {
		return this.tagMap.containsKey(tag);
	}
}
