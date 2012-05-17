package plaid.fastruntime.aeminium;

import java.util.concurrent.locks.ReentrantLock;

import plaid.fastruntime.PlaidDispatch;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.PlaidState;
import plaid.fastruntime.errors.PlaidIllegalOperationException;

public final class Datagroup implements PlaidObject {
	private ReentrantLock lock;
	
	public final void enterAtomic() {
		this.lock.lock();
	}
	
	public final void leaveAtomic() {
		this.lock.unlock();
	}

	@Override
	public PlaidDispatch getDispatch() {
		throw new PlaidIllegalOperationException("Cannot get dispatch object from datagroup");
	}

	@Override
	public PlaidObject[] getStorage() {
		throw new PlaidIllegalOperationException("Cannot get storage object from datagroup");
	}

	@Override
	public void changeState(PlaidState s) {
		throw new PlaidIllegalOperationException("Cannot state change datagroup");
	}
}
