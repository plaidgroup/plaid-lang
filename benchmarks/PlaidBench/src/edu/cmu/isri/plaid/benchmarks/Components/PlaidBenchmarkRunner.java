package edu.cmu.isri.plaid.benchmarks.Components;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Component;
import edu.cmu.isri.plaid.benchmarks.BenchmarkResult;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.Util;
import plaid.lang.S_tring;
import plaid.lang.T_rue;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * This class runs Plaid benchmarks
 * @author Benjamin Chung
 */
public class PlaidBenchmarkRunner extends Runner implements BenchmarkComponent {
    
    private Method declaredMethod;
    private Component componentDesc;

    public PlaidBenchmarkRunner(Method declaredMethod, HashMap<String, Object> properties, Component componentDesc) {
        super(properties);
        this.declaredMethod = declaredMethod;
        this.componentDesc = componentDesc;
    }

    public PlaidObject getProperty(PlaidObject key) {       
        if (!(key instanceof S_tring))
            return Util.javaToPlaid(0);
        String keyStr = key.toString();
        return Util.javaToPlaid(super.getProperty(keyStr));
    }

    @Override
    public BenchmarkResult run() throws Exception {
        PlaidObject timerPlaid = Util.javaToPlaid(this);
        try {
        declaredMethod.invoke(null, timerPlaid);
        declaredMethod.invoke(null, timerPlaid);
        } catch (RuntimeException e) {
            throw new TestFailedException(e);
        }
        return new BenchmarkResult(componentDesc, getTime());
    }
}
