package plaid.ForkJoinBenchmark.java;

public class RunThreads {
	public static int START = 32;
	
	public static void forkJoin(int level) {
		int curLevel = level - 1;
		if ( curLevel > 0 ) {
			forkJoin(curLevel);
			forkJoin(curLevel);
		} 
	}
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[Runtime.getRuntime().availableProcessors()];
		final int perThreadStartLevel = (int) (START - (Math.log(threads.length)/Math.log(2)));
		
		System.out.println("number of threads : " + threads.length );
		System.out.println("start level       : " + START);
		System.out.println("per thread start  : " + perThreadStartLevel);
		
		long start = System.nanoTime();

		for (int i = 0 ; i < threads.length ; i++ ) {
			threads[i] = new Thread() {
				public void run() { forkJoin(perThreadStartLevel); };
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
		long stop = System.nanoTime();
		System.out.println("Time : " + (stop-start)/(1000*1000*1000.0));
		
	}
}
