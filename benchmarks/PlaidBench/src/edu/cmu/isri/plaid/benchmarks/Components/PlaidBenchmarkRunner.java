package edu.cmu.isri.plaid.benchmarks.Components;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Component;
import edu.cmu.isri.plaid.benchmarks.BenchmarkResult;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;

import java.lang.reflect.Method;

/**
 * This class runs Plaid benchmarks
 * @author Benjamin Chung
 */
public class PlaidBenchmarkRunner extends Runner implements BenchmarkComponent {
    
    private Method declaredMethod;
    private Component componentDesc;

    public PlaidBenchmarkRunner(Method declaredMethod, Component componentDesc) {

        this.declaredMethod = declaredMethod;
        this.componentDesc = componentDesc;
    }

    @Override
    public BenchmarkResult run() throws Exception {
        PlaidObject timerPlaid = Util.javaToPlaid(this);
        declaredMethod.invoke(null, timerPlaid);
        declaredMethod.invoke(null, timerPlaid);
        return new BenchmarkResult(componentDesc, getTime());
    }
}
