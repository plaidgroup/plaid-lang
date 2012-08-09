package edu.cmu.isri.plaid.benchmarks.Components;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Component;
import edu.cmu.isri.plaid.benchmarks.BenchmarkResult;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
* Created by IntelliJ IDEA.
* User: Ben Chung
* Date: 6/7/12
* Time: 12:46 PM
* To change this template use File | Settings | File Templates.
*/
class JavaBenchmarkRunner extends Runner implements BenchmarkComponent {
    private Method invoke;
    private Component benchmarkComponent;

    public JavaBenchmarkRunner(Method invoke, HashMap<String, Object> properties, Component benchmarkComponent) {
        super(properties);
        this.invoke = invoke;
        this.benchmarkComponent = benchmarkComponent;
    }

    @Override
    public BenchmarkResult run() throws Exception {
        YourkitRunner.run(this, invoke, properties);
        return new BenchmarkResult(benchmarkComponent, getTime());
    }
}
