package plaid.fastruntime.reference;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.errors.PlaidIllegalOperationException;

public abstract class AbstractPlaidJavaDispatch implements PlaidDispatch {
	
	public AbstractPlaidJavaDispatch()  {
		super();
	}

	@Override
	public PlaidState change(PlaidState s) {
		throw new PlaidIllegalOperationException("State change not supported on Java object.");
	}

	@Override
	public boolean matches(String tag) {
		throw new PlaidIllegalOperationException("Matching not supported on Java object.");
	}

	@Override
	public ObjectValue getObjectValue() {
		throw new PlaidIllegalOperationException("Object values not supported on Java object.");
	}

}
