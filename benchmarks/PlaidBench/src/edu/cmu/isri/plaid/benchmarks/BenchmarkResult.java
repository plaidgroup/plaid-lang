package edu.cmu.isri.plaid.benchmarks;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Component;

/**
 * This class contains the results of running a single benchmark component.
 * @author Benjamin Chung
 */
public class BenchmarkResult {
    
    public BenchmarkResult() {}

    public BenchmarkResult(Component component, long nsElapsed) {
        this.nsElapsed = nsElapsed;
        this.benchmarkComponent = component;
    }
    public Component benchmarkComponent;
    public long nsElapsed;
}
