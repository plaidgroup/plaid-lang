package plaid.fastruntime.reference;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.PlaidStorage;

public class SimplePlaidObject implements PlaidObject {

	private PlaidState dispatch;
	private PlaidStorage storage;
	
	public SimplePlaidObject(PlaidState dispatch, PlaidStorage storage){
		this.dispatch = dispatch;
		this.storage = storage;
	}
	
	@Override
	public PlaidState getDispatch() {
		return dispatch;
	}

	@Override
	public PlaidStorage getStorage() {
		return storage;
	}

	@Override
	public void changeState(PlaidState s) {
		dispatch = dispatch.change(s);
		storage = dispatch.getStorage();
	}

}
