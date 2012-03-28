package plaid.fastruntime.aeminium;

import plaid.fastruntime.aeminium.runtime.ForkJoinPool;
import plaid.fastruntime.aeminium.runtime.ForkJoinWorkerThread;

public final class Util {
	private Util() {}
	
	public static final ForkJoinPool POOL = new ForkJoinPool();
	
	public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
	
	public static final boolean parallelize() {
		//return POOL.getActiveThreadCount() < CPU_COUNT;
		//return POOL.getQueuedTaskCount() < 1;
		//return POOL.getRunningThreadCount() < CPU_COUNT;
		//return POOL.getQueuedSubmissionCount() < 2;
		ForkJoinWorkerThread wthread = (ForkJoinWorkerThread)Thread.currentThread();
		return     wthread.getLocalQueueSize() < 1 
				&& POOL.getActiveThreadCount() < CPU_COUNT;
//		return     POOL.getActiveThreadCount() < CPU_COUNT
//				&& POOL.getQueuedTaskCount() < CPU_COUNT/4
//				&& POOL.getQueuedSubmissionCount() < CPU_COUNT/4;
	}
	
}
