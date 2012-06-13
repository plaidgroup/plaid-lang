package edu.cmu.isri.plaid.benchmarks.Components;

import edu.cmu.isri.plaid.benchmarks.BenchmarkResult;

/**
 * A BenchmarkComponent is a function or a set of functions in one language that execute a benchmark in that language.
 * @author Benjamin Chung
 */
public interface BenchmarkComponent {
    BenchmarkResult run() throws Exception;
}
