package plaid.fastruntime.reference;

import plaid.fastruntime.MethodInfo;
import fj.Equal;
import fj.F;
import fj.Ord;
import fj.Show;
import fj.data.List;
import fj.data.Set;

/**
 * A ListValue contains a minimum of two SingleValues.
 * @author jssunshi
 *
 */
public final class ListValue extends AbstractObjectValue {

	private List<SingleValue> singleValues;
	
	public ListValue(SingleValue... singleValues) {
		this.singleValues = List.list(singleValues);
	}
	
	private ListValue(List<SingleValue> singleValues) {
		this.singleValues = singleValues;
	}
	
	public SingleValue getFirst() {
		return singleValues.head();
	}

	
	/**
	 * Returns all of the values in the list except the first value. If there are only two elements 
	 * in the list, this will return the second element as a SingleValue. Otherwise it will return 
	 * a new ListValue.
	 * @return
	 */
	public AbstractObjectValue getRest() {
		if (singleValues.length() == 2) {
			return singleValues.tail().head();
		} else {
			return new ListValue(singleValues.tail());
		}
	}

	@Override
	public ListValue addValue(SingleValue other) {
		return new ListValue(singleValues.cons(other));
//		if (other instanceof SingleValue) {
//			return new ListValue(singleValues.cons((SingleValue) other));
//		} else {
//			List<SingleValue> otherValues = ((ListValue) other).singleValues;
//			return new ListValue(singleValues.append(otherValues));
//		}
	}

	@Override
	public boolean uniqueTags() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Set<String> getTags() {
		Set<String> tags = Set.empty(Ord.stringOrd);
		for(SingleValue sv : singleValues) {
			tags = tags.union(sv.getTags());
		}
		return tags;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ListValue) {
			ListValue otherLV = ((ListValue)obj);
			if(singleValues.length() == otherLV.singleValues.length()) {
				boolean isEqual = true;
				for(SingleValue sv : singleValues) {
					Equal<SingleValue> equal = Equal.anyEqual();
					F<SingleValue, Boolean> eqSV = equal.eq(sv);
					isEqual = otherLV.singleValues.exists(eqSV);
				}
				return isEqual;
			} else { //unequal length lists
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public Set<String> getOuterTags() {
		Set<String> currentSet = Set.empty(Ord.stringOrd);
		for(SingleValue sv : singleValues) {
			currentSet = currentSet.union(sv.getOuterTags());
		}
		return currentSet;
	}
	
	@Override
	public Set<String> getInnerTags() {
		Set<String> currentSet = Set.empty(Ord.stringOrd);
		for(SingleValue sv : singleValues) {
			currentSet = currentSet.union(sv.getInnerTags());
		}
		return currentSet;
	}
	
	@Override
	public String toString() {
		Show<SingleValue> singleValueShow = Show.anyShow();
		return Show.listShow(singleValueShow).showS(singleValues);
	}

	@Override
	public List<MethodInfo> getMethods() {
		List<MethodInfo> currentList = List.nil();
		for(SingleValue sv : singleValues) {
			currentList = currentList.append((sv.getMethods()));
		}
		return currentList;
	}
	
}
