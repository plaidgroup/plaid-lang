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
		forkJoin(28);
	}

}
