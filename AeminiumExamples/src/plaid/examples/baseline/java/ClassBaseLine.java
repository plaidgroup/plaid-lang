package plaid.examples.baseline.java;

public class ClassBaseLine {

	public void forkJoin(int level){
		boolean isZero = level == 0;
		if ( isZero == false ) {
			int nextLevel = level - 1;
			this.forkJoin(nextLevel);
			this.forkJoin(nextLevel);
		}
	}
	
	public static void main(String[] args) {
		ClassBaseLine cbl = new ClassBaseLine();
		cbl.forkJoin(28);
	}

}
