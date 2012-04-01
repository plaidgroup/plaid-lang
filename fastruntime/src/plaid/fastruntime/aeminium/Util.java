package plaid.fastruntime.aeminium;

import plaid.fastruntime.aeminium.runtime.ForkJoinPool;

public final class Util {
	private Util() {}
	
	public static final ForkJoinPool POOL = new ForkJoinPool();
	
	public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
	
	public static int parallelize = CPU_COUNT;

	public static final void triggerParallelism() {
		parallelize = (CPU_COUNT - POOL.getActiveThreadCount()) >> 1; 
	}

	public static final Datagroup GLOBAL_DATAGROUP = new Datagroup();
}
