package plaid.ForkJoinBenchmark.java;

public class RunSequential {
	public static int START = 32;
	
	public static void forkJoin(int level) {
		int curLevel = level - 1;
		if ( curLevel > 0 ) {
			forkJoin(curLevel);
			forkJoin(curLevel);
		} 
	}
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println("start level    : " + START);
		forkJoin(START);
		long stop = System.nanoTime();
		System.out.println("execution time : " + (stop-start)/(1000*1000*1000.0));
		
	}
}
