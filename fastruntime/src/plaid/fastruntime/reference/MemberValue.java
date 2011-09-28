package plaid.fastruntime.reference;

import fj.Ord;
import fj.data.Set;

public abstract class MemberValue extends SingleValue {
	private final String name;
	
	public MemberValue(String name) {
		this.name = name;
	}
	
	@Override
	public Set<String> getTags() {
		return Set.empty(Ord.stringOrd);
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean uniqueTags() {
		return true;
	}
	
	@Override
	public Set<String> getOuterTags() {
		return Set.empty(Ord.stringOrd);
	}
	
	@Override
	public Set<String> getInnerTags() {
		return Set.empty(Ord.stringOrd);
	}
}
