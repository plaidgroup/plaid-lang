package plaid.fastruntime.reference;

import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;

public class SimplePlaidObject implements PlaidObject {

	private PlaidState dispatch;
	private PlaidObject[] storage;
	
	public SimplePlaidObject(PlaidState dispatch, PlaidObject[] storage){
		this.dispatch = dispatch;
		this.storage = storage;
	}
	
	@Override
	public PlaidState getDispatch() {
		return dispatch;
	}

	@Override
	public PlaidObject[] getStorage() {
		return storage;
	}

	@Override
	public void changeState(PlaidState s) {
		dispatch = dispatch.change(s);
		storage = dispatch.getStorage();
	}

}
