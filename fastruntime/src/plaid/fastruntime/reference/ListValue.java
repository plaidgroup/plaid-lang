package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.Equal;
import fj.F;
import fj.F2;
import fj.Ord;
import fj.Ordering;
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
	private final String canonicalRep;
	
	public ListValue(SingleValue... singleValues) {
		this(List.list(singleValues));
	}
	
	private ListValue(List<SingleValue> singleValues) {
		this.singleValues = singleValues;
		this.canonicalRep = this.constructCanonicalRep();
	}
	
	public List<SingleValue> getAll() {
		return singleValues;
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
	}
	
	@Override
	public AbstractObjectValue add(MemberValue other) {
		return this.addValue(other);
	}
	
	public ListValue addListValue(ListValue other) {
		return new ListValue(this.singleValues.append(other.singleValues));
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
	

	@Override
	public List<FieldInfo> getFields() {
		List<FieldInfo> currentList = List.nil();
		for(SingleValue sv : singleValues) {
			currentList = currentList.append((sv.getFields()));
		}
		return currentList;
	}

	@Override
	public ObjectValue remove(String member) {
		List<SingleValue> toReturn = List.nil();
		for(SingleValue sv : singleValues) {
			if(sv instanceof MemberValue && ((MemberValue) sv).getName().equals(member)) {
				toReturn = toReturn.cons(sv);
			}
		}
		if (toReturn.length() > 1) {
			return new ListValue(toReturn);
		} else if (toReturn.length() == 1) {
			return new ListValue(toReturn.index(0));
		} else {
			throw new plaid.fastruntime.errors.PlaidInternalException("Trying to revove an object from ListValue with only one object.");
		}
	}

	@Override
	public ObjectValue rename(final String currentName, final String newName) {
		F<SingleValue,SingleValue> callRename = new F<SingleValue,SingleValue>() {
			public SingleValue f(SingleValue a) {
				return (SingleValue)a.rename(currentName,newName);
			}
		};
		List<SingleValue> newSingleValues = singleValues.map(callRename);
		return new ListValue(newSingleValues);
	}

	private final static Ord<SingleValue> svOrd;
	static {
		F<SingleValue, F<SingleValue, Ordering>> orderSingleValues = new F<SingleValue, F<SingleValue, Ordering>>() {
			@Override
			public F<SingleValue, Ordering> f(SingleValue a) {
				final String thisRep = a.getCanonicalRep();
				return new F<SingleValue, Ordering>() {
					public Ordering f(SingleValue other) {
						return Ord.stringOrd.compare(thisRep, other.getCanonicalRep());
					}
				};
			}
		};
		
		svOrd = Ord.ord(orderSingleValues);
	}
	
	private final static F2<String, SingleValue, String> combineCanonicalStrings = new F2<String, SingleValue, String>() {
		public String f(String a, SingleValue b) {
			return a + ";" + b;
		}
	};
	
	@Override
	protected String constructCanonicalRep() {
		List<SingleValue> sortedSingleValues = this.singleValues.sort(svOrd);
		String result = sortedSingleValues.foldLeft(combineCanonicalStrings, "");
		return result.intern();
	}

	@Override
	public String getCanonicalRep() {
		return this.canonicalRep;
	}

	
}
