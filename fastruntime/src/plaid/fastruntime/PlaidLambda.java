package plaid.fastruntime;

import plaid.fastruntime.errors.PlaidIllegalOperationException;

public abstract class PlaidLambda implements PlaidObject {

	@Override
	public PlaidDispatch getDispatch() {
		throw new PlaidIllegalOperationException("Lambda objects do not have a dispatch.");
	}

	@Override
	public PlaidObject[] getStorage() {
		throw new PlaidIllegalOperationException("Lamdba objects do not have storage.");
	}

	@Override
	public void changeState(PlaidState s) {
		throw new PlaidIllegalOperationException("Cannot change state on a Lambda object");
	}
	
}
