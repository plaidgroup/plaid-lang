package plaid.fastruntime.aeminium;

import java.util.concurrent.locks.ReentrantLock;

public final class Datagroup {
	private ReentrantLock lock;
	
	public final void enterAtomic() {
		this.lock.lock();
	}
	
	public final void leaveAtomic() {
		this.lock.unlock();
	}
}
