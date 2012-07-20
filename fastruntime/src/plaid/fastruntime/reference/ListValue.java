package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;

/**
 * A ListValue contains a minimum of two SingleValues.
 * @author jssunshi
 *
 */
public final class ListValue extends AbstractObjectValue {

	private UnmodifiableList<SingleValue> singleValues;
	
	public ListValue(SingleValue... singleValues) {
		this(UnmodifiableList.makeList(singleValues));
	}
	
	private ListValue(UnmodifiableList<SingleValue> singleValues) {
		this.singleValues = singleValues;
		this.init();
	}
	
	public UnmodifiableList<SingleValue> getAll() {
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
			return singleValues.get(1);
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
	public TagSet constructTags() {
		return TagSet.makeAllFromSingleValues(singleValues);
	}

	@Override
	protected TagSet constructOuterTags() {
		return TagSet.makeOuterFromSingleValues(singleValues);
	}
	
	@Override
	protected TagSet constructInnerTags() {
		return TagSet.makeInnerFromSingleValues(singleValues);
	}

	@Override
	protected UnmodifiableList<MethodInfo> constructMethods() {
		UnmodifiableList<MethodInfo> currentList = NIL_METHOD_INFO;
		for(SingleValue sv : singleValues) {
			currentList = currentList.append((sv.getMethods()));
		}
		return currentList;
	}
	

	@Override
	protected UnmodifiableList<FieldInfo> constructFields() {
		UnmodifiableList<FieldInfo> currentList = NIL_FIELD_INFO;
		for(SingleValue sv : singleValues) {
			currentList = currentList.append((sv.getFields()));
		}
		return currentList;
	}
	
	@Override
	public ObjectValue remove(String member) {
		UnmodifiableList<SingleValue> toReturn = NIL_SINGLE_VALUE;
		for(SingleValue sv : singleValues) {
			if(sv instanceof MemberValue && !((MemberValue) sv).getName().equals(member)) {
				toReturn = toReturn.cons(sv);
			}
		}
		if (toReturn.length() > 1) {
			return new ListValue(toReturn);
		} else if (toReturn.length() == 1) {
			return toReturn.head();
		} else {
			return new EmptySingleValue();//throw new plaid.fastruntime.errors.PlaidInternalException("Trying to revove an object from ListValue with only one object.");
		}
	}

	@Override
	public ObjectValue rename(final String currentName, final String newName) {
		UnmodifiableList<SingleValue> toReturn = NIL_SINGLE_VALUE;
		for(SingleValue sv : singleValues) {
			toReturn = toReturn.cons((SingleValue)sv.rename(currentName, newName));
		}
		return new ListValue(toReturn);
	}
	@Override
	protected String constructCanonicalRep() {
		UnmodifiableList<SingleValue> sortedSingleValues = this.singleValues.sort();
		StringBuilder sb = new StringBuilder();
		for (SingleValue sv : sortedSingleValues) {
			sb.append(";");
			sb.append(sv.getCanonicalRep()); 
		}
		return sb.toString();
	}
}
