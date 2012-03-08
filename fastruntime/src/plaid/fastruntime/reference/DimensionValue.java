package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.Ord;
import fj.data.List;
import fj.data.Set;

/**
 * Parent and innerValue can both be null.
 * @author jssunshi
 *
 */
public final class DimensionValue extends SingleValue {
	private final String tag;
	private final AbstractObjectValue innerValue;
	private final DimensionValue parent;
	
	public DimensionValue(String tag, AbstractObjectValue innerValue,
			DimensionValue parent) {
		super();
		this.tag = tag;
		this.parent = parent;
		this.innerValue = innerValue;
	}

	public String getTag() {
		return tag;
	}

	public DimensionValue getParent() {
		return parent;
	}

	public AbstractObjectValue getInnerValue() {
		return innerValue;
	}
	
	@Override
	public Set<String> getTags() {
		Set<String> currentTags = Set.single(Ord.stringOrd, tag);
		if(innerValue != null) {
			currentTags = currentTags.union(innerValue.getTags());
		}
		if(parent == null) {
			return currentTags;
		} else {
			return currentTags.union(parent.getTags());
		}
	}
	
	public DimensionValue withoutParent() {
		return new DimensionValue(this.tag, this.innerValue, null);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DimensionValue) {
			DimensionValue dv = (DimensionValue)obj;
			return (tag.equals(dv.tag) && 
					((innerValue == null && dv.innerValue == null) || 
							(innerValue != null && innerValue.equals(dv.innerValue))) &&
					((parent == null && dv.parent == null) || 
							(parent != null && parent.equals(dv.parent))));
		} else {
			return false;
		}
	}

	@Override
	public Set<String> getOuterTags() {
		Set<String> tagSet = Set.single(Ord.stringOrd, tag);
		if(parent != null) {
			return parent.getOuterTags().union(tagSet);
		} else {
			return tagSet;
		}
	}
	
	@Override
	public Set<String> getInnerTags() {
		Set<String> tagSet = Set.empty(Ord.stringOrd);
		if(parent != null) {
			tagSet = parent.getInnerTags().union(tagSet);
		} 
		if (innerValue != null) {
			tagSet = innerValue.getTags();
		}
		return tagSet;
	}
	
	@Override
	public boolean uniqueTags() {
		//TODO: Fix this, this is wrong!
		return true;
	}
	
	@Override
	public String toString() {
		String toReturn = tag;
		if(innerValue != null) {
			toReturn = toReturn + "{" + innerValue.toString() + "}";
		}
		if (parent != null) {
			toReturn = toReturn + "<:" + parent.toString();
		}
		return toReturn;
	}

	@Override
	public List<MethodInfo> getMethods() {
		List<MethodInfo> mi = List.nil();
		if(innerValue!=null) {
			mi = mi.append(innerValue.getMethods());
		}
		if(parent!=null) {
			mi = mi.append(parent.getMethods());
		}
		return mi;
	}
	

	@Override
	public List<FieldInfo> getFields() {
		List<FieldInfo> fi = List.nil();
		if(innerValue!=null) {
			fi = fi.append(innerValue.getFields());
		}
		if(parent!=null) {
			fi = fi.append(parent.getFields());
		}
		return fi;
	}

	@Override
	public ObjectValue remove(String member) {
		DimensionValue newParent = (DimensionValue)this.parent.remove(member);
		AbstractObjectValue newInnerValue = (AbstractObjectValue)this.innerValue.remove(member);
		return new DimensionValue(this.tag, newInnerValue, newParent);
	}

	@Override
	public ObjectValue rename(String currentName, String newName) {
		DimensionValue newParent = (DimensionValue)this.parent.rename(currentName, newName);
		AbstractObjectValue newInnerValue = (AbstractObjectValue)this.innerValue.rename(currentName,newName);
		return new DimensionValue(this.tag, newInnerValue, newParent);
	}
}
