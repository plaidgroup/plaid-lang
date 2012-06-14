package SortingBenchmarkReference.BinarySearchTree.Java;

import java.util.Random;
import edu.cmu.isri.plaid.benchmarks.Components.Runner;


public class Benchmark {
	public static void benchmark(Runner benchmarkRunner) {
		int n = 5000;
		Random randGen = new Random();
		BST tree = new BST();
		int[] elems = new int[n];
		for (int i = 0; i < n; i++) {
			int elem = randGen.nextInt();
			elems[i] = elem;
		}
		benchmarkRunner.startTimer();
		for (int i = 0; i < n; i++) {
			tree.add(elems[i]);
		}
		for (int i = 0; i < n; i++) {
			tree.delete(elems[i]);
		}
		benchmarkRunner.stopTimer();
	}
}