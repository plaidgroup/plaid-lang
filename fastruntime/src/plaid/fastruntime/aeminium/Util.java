package plaid.fastruntime.aeminium;

import plaid.fastruntime.aeminium.runtime.ForkJoinPool;
import plaid.fastruntime.aeminium.runtime.ForkJoinWorkerThread;

public final class Util {
	private Util() {}
	
	public static final ForkJoinPool POOL = new ForkJoinPool();
	
	public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
	
	public static final boolean parallelize() {
		ForkJoinWorkerThread wthread = (ForkJoinWorkerThread)Thread.currentThread();
		return     wthread.getLocalQueueSize() < 1 
				&& POOL.getActiveThreadCount() < CPU_COUNT;
	}
	
}
