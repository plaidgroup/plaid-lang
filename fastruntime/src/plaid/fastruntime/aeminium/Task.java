package plaid.fastruntime.aeminium;

import plaid.fastruntime.aeminium.runtime.RecursiveAction;

public abstract class Task extends RecursiveAction {
	private static final long serialVersionUID = 3692838792880453578L;
	
	private volatile int depsCount;
	
	public Task(int depsCount) {
		this.depsCount = depsCount;
	}

	public int getDepCount() { 
		return depsCount;
	}
	
	public int decDepCount() {
		if ( depsCount == 1 ) {
			depsCount = 0;
			return 0;
		} else {
			synchronized (this) {
				this.depsCount--;
				return depsCount;
			}
		}
	}
}
