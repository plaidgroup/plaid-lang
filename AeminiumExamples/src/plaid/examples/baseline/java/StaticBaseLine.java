package plaid.examples.baseline.java;

public class StaticBaseLine {
		
	public static void forkJoin(int level) {
		boolean isZero = level == 0;
		if ( isZero == false ) {
			int nextLevel = level - 1;
			forkJoin(nextLevel);
			forkJoin(nextLevel);
		} else {}
	}

	public static void main(String[] args) {
		long begin = System.nanoTime();
		forkJoin(28);
		long end = System.nanoTime();
		long delta = end - begin;
		double divider = 1000*1000*1000.0;
		double result = delta / divider;
		System.out.printf("Runtime : %.3fs \n", result);
	}

}
