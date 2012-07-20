package plaid.fastruntime.reference;


public abstract class AtomicValue extends SingleValue {
	
	@Override
	protected final TagSet constructTags() {
		return TagSet.makeEmpty();
	}
	
	@Override
	protected final TagSet constructOuterTags() {
		return TagSet.makeEmpty();
	}
	
	@Override
	protected final TagSet constructInnerTags() {
		return TagSet.makeEmpty();
	}

}
