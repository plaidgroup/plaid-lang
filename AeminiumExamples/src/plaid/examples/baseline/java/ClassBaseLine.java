package plaid.examples.baseline.java;

public class ClassBaseLine {

	public void forkJoin(int level){
		boolean isZero = level == 0;
		if ( isZero == false ) {
			int nextLevel = level - 1;
			this.forkJoin(nextLevel);
			this.forkJoin(nextLevel);
		} else {}
	}
	
	public static void main(String[] args) {
		ClassBaseLine cbl = new ClassBaseLine();
		long begin = System.nanoTime();
		cbl.forkJoin(28);
		long end = System.nanoTime();
		long delta = end - begin;
		double divider = 1000*1000*1000.0;
		double result = delta / divider;
		System.out.printf("%.3f\n", result);
	}

}
