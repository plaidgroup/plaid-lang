package edu.cmu.isri.plaid.benchmarks.Components;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Component;
import edu.cmu.isri.plaid.benchmarks.BenchmarkResult;

import java.lang.reflect.Method;

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

    public JavaBenchmarkRunner(Method invoke, Component benchmarkComponent) {

        this.invoke = invoke;
        this.benchmarkComponent = benchmarkComponent;
    }

    @Override
    public BenchmarkResult run() throws Exception {
        invoke.invoke(null, this);
        invoke.invoke(null, this);
        return new BenchmarkResult(benchmarkComponent, getTime());
    }
}
