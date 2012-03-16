package plaid.fastruntime.reference;

import fj.Ord;
import fj.data.Set;

public abstract class AtomicValue extends SingleValue {

	@Override
	protected final Set<String> constructTags() {
		return Set.empty(Ord.stringOrd);
	}
	
	@Override
	protected final Set<String> constructOuterTags() {
		return Set.empty(Ord.stringOrd);
	}
	
	@Override
	protected final Set<String> constructInnerTags() {
		return Set.empty(Ord.stringOrd);
	}

}
