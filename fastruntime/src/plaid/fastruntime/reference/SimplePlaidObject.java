package plaid.fastruntime.reference;

import java.util.HashMap;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidLambda;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidState;
import plaid.generated.ItoS_tring$0$plaid;

public class SimplePlaidObject implements PlaidObject {

	private PlaidDispatch dispatch;
	private PlaidObject[] storage;
	
	public SimplePlaidObject(PlaidDispatch dispatch, PlaidObject[] storage){
		this.dispatch = dispatch;
		this.storage = storage;
	}
	
	@Override
	public PlaidDispatch getDispatch() {
		return dispatch;
	}

	@Override
	public PlaidObject[] getStorage() {
		return storage;
	}

	@Override
	public void changeState(PlaidState s) {
		PlaidState newState = dispatch.change(s);
		ObjectValue oldObjectValue = this.dispatch.getObjectValue();
		PlaidObject[] oldStorage = this.storage;
		this.dispatch = newState.getDispatch();
		storage = newState.getObjectValue().getPostChangeStorage(oldObjectValue, oldStorage);
	}

	@Override
	public String toString() {
		if ( this.getDispatch() instanceof ItoS_tring$0$plaid ) {
			PlaidObject toStringValue = ((ItoS_tring$0$plaid)this.getDispatch()).toS_tring(this);
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
