package plaid.fastruntime.reference;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.Util;
import plaid.fastruntime.errors.PlaidIllegalOperationException;

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
	public boolean matches(String tag) {
		return getObjectValue().matches(tag);
	}
	
	@Override
	public String getTopTag() {
		return getObjectValue().getTopTag();
	}
	
	@Override
	public PlaidState remove(String member) {
		ObjectValue newOV = this.getObjectValue().remove(member);
		return Util.DISPATCH_GEN.createStateInstance(newOV);
	}

	@Override
	public PlaidState rename(String from, String to) {
		ObjectValue newOV = this.getObjectValue().rename(from, to);
		return Util.DISPATCH_GEN.createStateInstance(newOV);
	}

	@Override
	public PlaidState specialize(PlaidState other) {
		ObjectValue newOV = this.getObjectValue().specialize(other.getObjectValue());
		return Util.DISPATCH_GEN.createStateInstance(newOV);
	}
	
	@Override
	public PlaidState getDispatch() {
		throw new PlaidIllegalOperationException("Tried to access dispatch object of Plaid State." +
				"Probably to call a method or access a field. States do not have methods or fields.");
	}
	
	@Override
	public void changeState(PlaidState s) {
		throw new PlaidIllegalOperationException("Tried to change state on PlaidState." +
				"States cannot change .");
	}

}
