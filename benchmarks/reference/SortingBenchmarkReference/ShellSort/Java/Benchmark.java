package SortingBenchmarkReference.ShellSort.Java;

import java.util.Random;
import edu.cmu.isri.plaid.benchmarks.Components.Runner;


public class Benchmark {
	public static void benchmark(Runner benchmarkRunner) {
		int n = (Integer)benchmarkRunner.getProperty("nElems");
		Random randGen = (Random)benchmarkRunner.getProperty("Random");
		
		int[] elems = new int[n];
		for (int i = 0; i < n; i++)
			elems[i] = randGen.nextInt();
		
		benchmarkRunner.startTimer();
		ShellSort.ShellSort(elems, new int[] {701, 301, 132, 57, 23, 10, 4, 1});
		benchmarkRunner.stopTimer();
	}
}