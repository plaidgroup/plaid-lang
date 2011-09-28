package plaid.fastrutime.reference;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.PlaidStorage;

public abstract class AbstractPlaidState implements PlaidState {

	private final ObjectValue metadata;
	
	public AbstractPlaidState(ObjectValue metadata) {
		this.metadata = metadata;
	}
	
	@Override
	public PlaidState with(PlaidState other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaidObject instantiate() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public PlaidState change(PlaidState s) {
		ObjectValue changedValue = metadata.changeState(s.getObjectValue());
		//TODO: Complete method
		return null;
	}

	@Override
	public PlaidStorage getStorage() {
		//return metadata.getStorage();
		return null;
	}

	@Override
	public ObjectValue getObjectValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
