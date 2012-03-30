package plaid.fastruntime.reference;

import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidState;
import plaid.generated.ItoString$0$plaid;

public class SimplePlaidObject implements PlaidObject {

	private PlaidDispatch dispatch;
	private PlaidObject[] storage;
	private Object[] memberDefs;
	
	public SimplePlaidObject(PlaidDispatch dispatch, PlaidObject[] storage){
		this(dispatch,storage,null);
	}
	
	public SimplePlaidObject(PlaidDispatch dispatch, PlaidObject[] storage, Object[] memberDefs){
		this.dispatch = dispatch;
		this.storage = storage;
		this.memberDefs = memberDefs;
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
		this.dispatch = newState.getDispatch();
		storage = newState.getStorage();
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

	@Override
	public Object[] getMemberDefs() {
		return this.memberDefs;
	}
}
