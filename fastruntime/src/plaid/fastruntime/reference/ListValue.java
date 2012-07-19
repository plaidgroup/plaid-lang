package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.F;
import fj.F2;
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
		this(List.list(singleValues));
	}
	
	private ListValue(List<SingleValue> singleValues) {
		this.singleValues = singleValues;
		this.init();
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
	public Set<String> constructTags() {
		Set<String> tags = EMPTY_TAGS;
		for(SingleValue sv : singleValues) {
			tags = tags.union(sv.getTags());
		}
		return tags;
	}

	@Override
	protected Set<String> constructOuterTags() {
		Set<String> currentSet = EMPTY_TAGS;
		for(SingleValue sv : singleValues) {
			currentSet = currentSet.union(sv.getOuterTags());
		}
		return currentSet;
	}
	
	@Override
	protected Set<String> constructInnerTags() {
		Set<String> currentSet = EMPTY_TAGS;
		for(SingleValue sv : singleValues) {
			currentSet = currentSet.union(sv.getInnerTags());
		}
		return currentSet;
	}

	@Override
	protected List<MethodInfo> constructMethods() {
		List<MethodInfo> currentList = NIL_METHOD_INFO;
		for(SingleValue sv : singleValues) {
			currentList = currentList.append((sv.getMethods()));
		}
		return currentList;
	}
	

	@Override
	protected List<FieldInfo> constructFields() {
		List<FieldInfo> currentList = NIL_FIELD_INFO;
		for(SingleValue sv : singleValues) {
			currentList = currentList.append((sv.getFields()));
		}
		return currentList;
	}
	
	@Override
	public ObjectValue remove(String member) {
		List<SingleValue> toReturn = NIL_SINGLE_VALUE;
		for(SingleValue sv : singleValues) {
			if(sv instanceof MemberValue && !((MemberValue) sv).getName().equals(member)) {
				toReturn = toReturn.cons(sv);
			}
		}
		if (toReturn.length() > 1) {
			return new ListValue(toReturn);
		} else if (toReturn.length() == 1) {
			return toReturn.index(0);
		} else {
			return new EmptySingleValue();//throw new plaid.fastruntime.errors.PlaidInternalException("Trying to revove an object from ListValue with only one object.");
		}
	}

	@Override
	public ObjectValue rename(final String currentName, final String newName) {
		F<SingleValue,SingleValue> callRename = new F<SingleValue,SingleValue>() {
			public SingleValue f(SingleValue a) {
				return (SingleValue)a.rename(currentName, newName);
			}
		};
		List<SingleValue> newSingleValues = singleValues.map(callRename);
		return new ListValue(newSingleValues);
	}
	
	private final static F2<String, SingleValue, String> COMBINE_CANONICAL_STRINGS = new F2<String, SingleValue, String>() {
		public String f(String a, SingleValue b) {
			return a + ";" + b.getCanonicalRep();
		}
	};
	
	@Override
	protected String constructCanonicalRep() {
		List<SingleValue> sortedSingleValues = this.singleValues.sort(SINGLE_VALUE_ORD);
		String result = sortedSingleValues.foldLeft(COMBINE_CANONICAL_STRINGS, "");
		return result;//.intern();
	}
}
