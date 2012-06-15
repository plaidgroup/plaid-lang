package SortingBenchmarkReference.SplayTree.Java;


import java.util.Random;
import edu.cmu.isri.plaid.benchmarks.Components.Runner;

public class Benchmark {
	public static void Benchmark(Runner runner) {
		int n = 5000;
		Random randGen = new Random();
		int[] elems = new int[n];
		SplayTree tree = new SplayTree();
		runner.startTimer();
		for (int i = 0; i < n; i++) {
			int elem = randGen.nextInt();
			elems[i] = elem;
			tree.add(elem, null);
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < n; j++) {
				
				tree.find(elems[j]);
			}
		}
		for (int i = 0; i < n; i++)
			tree.delete(elems[i]);
		runner.stopTimer();
	}
}