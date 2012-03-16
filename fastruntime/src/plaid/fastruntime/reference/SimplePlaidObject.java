package plaid.fastruntime.reference;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.generated.ItoString$0$plaid;

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

	@Override
	public String toString() {
		if ( this.getDispatch() instanceof ItoString$0$plaid ) {
			PlaidObject toStringValue = ((ItoString$0$plaid)this.getDispatch()).toString(this);
			if ( toStringValue instanceof PlaidJavaObject ) {
				return ((PlaidJavaObject)toStringValue).getJavaObject().toString();
			} else {
				return getDispatch().getObjectValue().toString().replace("/", ".");
			}
		} else {
			return getDispatch().getObjectValue().toString().replace("/", ".");
		}
	}
}
