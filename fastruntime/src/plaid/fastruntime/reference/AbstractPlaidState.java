package plaid.fastruntime.reference;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;

public abstract class AbstractPlaidState implements PlaidState {

	private final ObjectValue metadata;
	
	public AbstractPlaidState(ObjectValue metadata) {
		this.metadata = metadata;
	}
	
	@Override
	public PlaidState with(PlaidState other) {
		if(this.metadata instanceof ListValue) {
			if(other.getObjectValue() instanceof ListValue) {
				//merge lists
				ObjectValue newMetadata = ((ListValue)this.metadata).addListValue((ListValue)other.getObjectValue());
				return new AbstractPlaidState(newMetadata) { };
			} else {
				// other is a single value
				ObjectValue newMetadata = ((ListValue)this.metadata).addValue((SingleValue)other.getObjectValue());
				return new AbstractPlaidState(newMetadata) { };
			}
		} else {
			// this is a single value
			if(other.getObjectValue() instanceof ListValue) {
				//add this value to other list
				ObjectValue newMetadata = ((ListValue)other.getObjectValue()).addValue((SingleValue)this.metadata);
				return new AbstractPlaidState(newMetadata) { };
			} else {
				// other is a single value
				ObjectValue newMetadata = new ListValue((SingleValue) this.metadata, (SingleValue) other.getObjectValue());
				return new AbstractPlaidState(newMetadata) { };
			}
		}
	}

	@Override
	public PlaidObject instantiate() {
		return new SimplePlaidObject(this, this.getStorage());
	}

	@Override
	public PlaidState change(PlaidState s) {
		ObjectValue changedValue = metadata.changeState(s.getObjectValue());
		return Util.DISPATCH_GEN.createStateInstance(changedValue);
	}

	@Override
	public PlaidObject[] getStorage() {
		return this.metadata.getDefaultStorage();
	}

	@Override
	public ObjectValue getObjectValue() {
		return this.metadata;
	}
	
	@Override
	public PlaidState remove(String member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaidState rename(String from, String to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaidState specialize(PlaidState other) {
		// TODO Auto-generated method stub
		return null;
	}

}
