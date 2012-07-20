package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.data.List;

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
		this.init();
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
	protected TagSet constructTags() {
		TagSet currentTags = TagSet.makeSingle(tag);
		if(innerValue != null) {
			currentTags = currentTags.union(innerValue.getTags());
		}
		if(parent != null) {
			currentTags = currentTags.union(parent.getTags());
		}
		return currentTags;
	}
	
	public DimensionValue withoutParent() {
		return new DimensionValue(this.tag, this.innerValue, null);
	}
	
	@Override
	public TagSet constructOuterTags() {
		TagSet tagSet = TagSet.makeSingle(tag);
		if(parent != null) {
			return parent.getOuterTags().union(tagSet);
		} else {
			return tagSet;
		}
	}
	
	@Override
	public TagSet constructInnerTags() {
		TagSet tagSet = TagSet.makeEmpty();
		if(parent != null) {
			tagSet = parent.getInnerTags().union(tagSet);
		} 
		if (innerValue != null) {
			tagSet = innerValue.getTags().union(tagSet);
		}
		return tagSet;
	}

	@Override
	public List<MethodInfo> constructMethods() {
		List<MethodInfo> mi = NIL_METHOD_INFO;
		if(innerValue!=null) {
			mi = mi.append(innerValue.getMethods());
		}
		if(parent!=null) {
			mi = mi.append(parent.getMethods());
		}
		return mi;
	}
	

	@Override
	public List<FieldInfo> constructFields() {
		List<FieldInfo> fi = NIL_FIELD_INFO;
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
		DimensionValue newParent = null;
		if (this.parent!=null) {
			newParent = (DimensionValue)this.parent.remove(member);
		}
		AbstractObjectValue newInnerValue = null;
		if (this.innerValue!=null) {
			newInnerValue = (AbstractObjectValue)this.innerValue.remove(member);
		}
		return new DimensionValue(this.tag, newInnerValue, newParent);
	}

	@Override
	public ObjectValue rename(String currentName, String newName) {
		DimensionValue newParent = null;
		if (this.parent!=null) {
			newParent = (DimensionValue)this.parent.rename(currentName, newName);
		}
		AbstractObjectValue newInnerValue = null;
		if (this.innerValue!=null) {
			newInnerValue = (AbstractObjectValue)this.innerValue.rename(currentName,newName);
		}
		return new DimensionValue(this.tag, newInnerValue, newParent);
	}

	@Override
	public AbstractObjectValue add(MemberValue mv) {
		AbstractObjectValue newInnerValue;
		if (innerValue == null) {
			return new DimensionValue(this.tag, mv, parent);
		} else if (innerValue instanceof DimensionValue) {
			newInnerValue = new ListValue((DimensionValue)innerValue, mv);
		} else {
			newInnerValue = this.innerValue.add(mv);
		}
		return new DimensionValue(this.tag, newInnerValue, parent);
	}

	@Override
	protected String constructCanonicalRep() {
		final String innerValueRep;
		if (this.innerValue != null) {
			innerValueRep = innerValue.getCanonicalRep();
		} else {
			innerValueRep = "";
		}
		final String parentRep;
		if (this.parent != null) {
			parentRep = "<:" + parent.getCanonicalRep();
		} else {
			parentRep = "";
		}
		String result = "TAG[" + this.tag + "]{" + innerValueRep + "}"+parentRep;
		return result.intern();
	}
	
	/*
	 * Transitive && not reflective && not symmetric.
	 */
	protected final boolean subTagOf(DimensionValue other) {
		if(this.parent == null) {
			return false;
		} else if (this.parent == other) {
			return true;
		} else {
			return this.parent.subTagOf(other);
		}
	}
}
