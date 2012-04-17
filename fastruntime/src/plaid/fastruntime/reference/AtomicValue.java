package plaid.fastruntime.reference;

import fj.data.Set;

public abstract class AtomicValue extends SingleValue {
	
	@Override
	protected final Set<String> constructTags() {
		return EMPTY_TAGS;
	}
	
	@Override
	protected final Set<String> constructOuterTags() {
		return EMPTY_TAGS;
	}
	
	@Override
	protected final Set<String> constructInnerTags() {
		return EMPTY_TAGS;
	}

}
