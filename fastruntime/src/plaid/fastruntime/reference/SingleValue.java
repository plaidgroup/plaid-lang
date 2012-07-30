package plaid.fastruntime.reference;

import plaid.fastruntime.NameInfo;

public abstract class SingleValue extends AbstractObjectValue implements NameInfo, Comparable<NameInfo> {
	@Override
	public ListValue addValue(SingleValue other) {
		return new ListValue(this, other);
	}
	
	@Override
	public int compareTo(NameInfo other) {
		return this.getCanonicalRep().compareTo(other.getCanonicalRep());
	}
}
