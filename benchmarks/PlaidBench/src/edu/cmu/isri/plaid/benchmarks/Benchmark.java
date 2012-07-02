package edu.cmu.isri.plaid.benchmarks;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Component;
import edu.cmu.isri.plaid.benchmarks.Components.BenchmarkComponent;
import edu.cmu.isri.plaid.benchmarks.Components.BenchmarkComponentFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Benchmark wraps several Components, each of which is a benchmark in a particular language.
 * @author Benjamin Chung
 */
public class Benchmark {
    List<BenchmarkComponent> components = new ArrayList<BenchmarkComponent>();
    private edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Benchmark benchmarkDesc;

    public Benchmark(edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Benchmark benchmarkDesc, HashMap<String, BenchmarkComponentFactory> loaders) {
        this.benchmarkDesc = benchmarkDesc;
        for (Component component : benchmarkDesc.getComponent()) {
            if (!loaders.containsKey(component.getType()))
                throw new RuntimeException("Invalid benchmarkComponent type");
            
            components.add(loaders.get(component.getType()).getBenchmarkComponent(component));
        }
    }
    
    public BenchmarkResult[] run() throws Exception {
        BenchmarkResult[] output = new BenchmarkResult[components.size()];
        int i = 0;
        for (BenchmarkComponent component : components) {
            output[i++] = component.run();
        }
        return output;
    }

    public edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Benchmark getBenchmarkDesc() {
        return benchmarkDesc;
    }
}
