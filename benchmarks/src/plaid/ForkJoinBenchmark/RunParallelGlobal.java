package plaid.ForkJoinBenchmark;

import plaid.ForkJoinBenchmark.RunSequential;
import plaid.fastruntime.PlaidObject;


public class RunParallelGlobal {
	public static void main(String[] args) {
		int startLevel = RunSequential.START_LEVEL;
		if( args.length > 0 ) {
			startLevel = Integer.valueOf(args[0]);
		}
		
		Thread[] threads = new Thread[Runtime.getRuntime().availableProcessors()];

		final int perThreadStartLevel = (int) (startLevel - (Math.log(threads.length)/Math.log(2)));

		System.out.println("number of threads : " + threads.length );
		System.out.println("start level       : " + startLevel);
		System.out.println("per thread start  : " + perThreadStartLevel);

		long start = System.nanoTime();
		
		final PlaidObject value  = plaid.fastruntime.Util.integer(perThreadStartLevel);
		
		for (int i = 0 ; i < threads.length ; i++ ) {
			threads[i] = new Thread() {
				public void run() { 
					PlaidObject result = plaid.ForkJoinBenchmark.forkJoinGlobal.invoke$plaid(value);
				};
			};
			threads[i].start();
		}
		// wait for termination
		for ( Thread t : threads ) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		long end = System.nanoTime();
		System.out.println("execution time    : " + ((end - start)/(1000*1000*1000.0))+ "s" );

	}
}
