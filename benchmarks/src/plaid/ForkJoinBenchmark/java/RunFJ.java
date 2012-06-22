package plaid.ForkJoinBenchmark.java;

import plaid.fastruntime.aeminium.Task;
import plaid.fastruntime.aeminium.runtime.ForkJoinPool;
import plaid.fastruntime.aeminium.runtime.ForkJoinTask;
import plaid.fastruntime.aeminium.runtime.RecursiveAction;


public class RunFJ {
	public static int START = 32;
	public static ForkJoinPool pool = plaid.fastruntime.aeminium.Util.POOL;
	public static int CPU_COUNT = Runtime.getRuntime().availableProcessors();
	//public static AtomicInteger taskCounter = new AtomicInteger();
	
	public static void forkJoin(int level) {
		final int curLevel = level - 1;

		//System.out.println(oracle(curLevel));
		if ( plaid.fastruntime.aeminium.Util.parallelize() == false ) {
			if ( curLevel > 0 ) {
				forkJoin(curLevel);
				forkJoin(curLevel);
			} 
		} else {
			if ( curLevel > 0 ) {
				plaid.fastruntime.aeminium.Util.parallelize--;
				RecursiveAction t1 = new Task(0) {				
					@Override
					protected void compute() {
						//taskCounter.incrementAndGet();
						forkJoin(curLevel);
					}
				};
				RecursiveAction t2 = new Task(0) {				
					@Override
					protected void compute() {
						//taskCounter.incrementAndGet();
						forkJoin(curLevel);
					}
				};
				ForkJoinTask.invokeAll(t1, t2);
			}
		}
	}
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		
		System.out.println("start level : " + START);
		
		RecursiveAction t = new RecursiveAction() {				
			@Override
			protected void compute() {
				forkJoin(START);
			}
		};
	
		pool.submit(t);
		
		while ( pool.getActiveThreadCount() > 0 ) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		long stop = System.nanoTime();
		System.out.println("execution time : " + (stop-start)/(1000*1000*1000.0));
		//System.out.println("created Tasks " + taskCounter.get());
		
	}
}
