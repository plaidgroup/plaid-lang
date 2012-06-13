package edu.cmu.isri.plaid.benchmarks.Components;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Component;

/**
 * Classes which implement this interface convert Components from XML into runnable BenchmarkComponents via whatever is required for their language.
 * @author Benjamin Chung
 */
public interface BenchmarkComponentFactory {
    public BenchmarkComponent getBenchmarkComponent(Component componentDesc);
}
