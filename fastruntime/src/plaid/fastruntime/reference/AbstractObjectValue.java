package plaid.fastruntime.reference;

import plaid.fastruntime.ObjectValue;
import fj.data.Set;

public abstract class AbstractObjectValue implements ObjectValue {

	@Override
	public final AbstractObjectValue changeState(ObjectValue other) {
		if(other instanceof ListValue) { 
			// SU-List
			ListValue list = (ListValue)other;
			AbstractObjectValue result = this.changeState(list.getFirst());
			return result.changeState(list.getRest());
		} else if (other instanceof MemberValue) {
			// SU-Mv
			return this.addValue((MemberValue)other);
		} else if (other instanceof DimensionValue) {
			DimensionValue otherDV = (DimensionValue) other;
			// SU-AddH
			if(otherDV.uniqueTags() && 
					this.getTags().intersect(otherDV.getTags()).size() == 0) {
				return this.addValue(otherDV);
			}
			//SU-MatchDim
			else if(this instanceof ListValue) {
				ListValue thisLV = (ListValue) this;
				AbstractObjectValue firstOV = thisLV.getFirst();
				if (firstOV instanceof DimensionValue) {
					DimensionValue thisDV = (DimensionValue)firstOV;
					if (!thisDV.getTags().intersect(otherDV.getOuterTags()).isEmpty() &&
							otherDV.getTags().intersect(thisLV.getRest().getTags()).isEmpty())
					// JSS: This clause is different than the last premise in the SU-MatchDim
						// This operates only on inputs, while the premise operates on the results, which seems unnecessary {
						return thisDV.changeState(otherDV);
				}	
				return thisLV.getRest().changeState(otherDV);
			} else if (this instanceof DimensionValue) {
				DimensionValue thisDV = (DimensionValue) this;	
				// SU-MatchInner
				if(thisDV.getInnerValue()!= null &&
						!thisDV.getInnerValue().getTags().intersect(otherDV.getOuterTags()).isEmpty() &&
					!otherDV.getTags().member(thisDV.getTag()) &&
					(thisDV.getParent() == null ||
							thisDV.getParent().getTags().intersect(otherDV.getTags()).isEmpty())) {
					AbstractObjectValue newInnerValue = thisDV.getInnerValue().changeState(otherDV);
					return new DimensionValue(thisDV.getTag(), newInnerValue, thisDV.getParent());
				}
				//SU-MatchSuperInner
				else if(thisDV.getParent() != null &&
						!otherDV.getOuterTags().intersect(thisDV.getParent().getInnerTags()).isEmpty() &&
						thisDV.withoutParent().getTags().intersect(otherDV.getTags()).isEmpty()) {
					DimensionValue newParent = (DimensionValue)thisDV.getParent().changeState(otherDV);
					return new DimensionValue(thisDV.getTag(), thisDV.getInnerValue(), newParent);
				}
				//SU-MatchSuperInner
				else if(thisDV.getParent() != null &&
						!otherDV.getOuterTags().intersect(thisDV.getParent().getInnerTags()).isEmpty() &&
						thisDV.withoutParent().getTags().intersect(otherDV.getTags()).isEmpty()) {
					DimensionValue newParent = (DimensionValue)thisDV.getParent().changeState(otherDV);
					return new DimensionValue(thisDV.getTag(), thisDV.getInnerValue(), newParent);
				}
				//SU-MatchSuper
				else if(thisDV.getParent() != null &&
						!otherDV.getOuterTags().member(thisDV.getTag()) &&
						!otherDV.getOuterTags().intersect(thisDV.getParent().getOuterTags()).isEmpty()) 
				{
					return thisDV.getParent().changeState(otherDV);
				}
				//SU-Match
				else if(otherDV.getOuterTags().member(thisDV.getTag())) {
					DimensionValue dvsub = childrenOfTag(thisDV.getTag(), otherDV, null);
					if (dvsub == null) {
						return thisDV;
					} else {
						if(dvsub.getTags().intersect(thisDV.getTags()).isEmpty() &&
								dvsub.uniqueTags()) {
							return childrenOfTag(thisDV.getTag(), otherDV, thisDV);
						}
					}
				}
			}
		}
		throw new RuntimeException("Ooops ... State change failed because because of an unknown case.");
	}

	/*
	 * Returns DimensionValue of all children of the argument tag. Used by SU-Match. 
	 * Returns null if the top-level tag of this DimensionValue is equal to the argument. 
	 * Throws an exception if the argument does not appear in the outer-tags of the DimensionValue.
	 * @param tag the tag whose children to return
	 * @param currentValue the dimension value to search
	 * @param newParent new parent to replace this tag, if null will return children without parent
	 */
	private static DimensionValue childrenOfTag(String tag, 
			DimensionValue currentValue, DimensionValue newParent) {
		if(tag.equals(currentValue.getTag())) {
			return newParent;
		} else if (currentValue.getParent() == null) {
			throw new RuntimeException("The argument does not appear in the outer-tags of the DimensionValue");
		} else {
			return new DimensionValue(currentValue.getTag(), 
					currentValue.getInnerValue(), childrenOfTag(tag, currentValue.getParent(), newParent));
		}
	}

	public boolean uniqueTags() {
		//TODO: Implement unique tags correctly
		return true;
	}
	
	public abstract Set<String> getTags();
	
	public abstract Set<String> getOuterTags();
	
	public abstract Set<String> getInnerTags();
	
	public abstract ListValue addValue(SingleValue other);

}
