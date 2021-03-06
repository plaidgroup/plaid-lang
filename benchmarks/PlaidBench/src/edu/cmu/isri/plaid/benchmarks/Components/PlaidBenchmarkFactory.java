package edu.cmu.isri.plaid.benchmarks.Components;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Component;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Ben Chung
 * Date: 6/7/12
 * Time: 7:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaidBenchmarkFactory implements BenchmarkComponentFactory {
    public static final String PlaidPath = "C:/Users/Ben Chung/Downloads/plaid3/";
    @Override
    public BenchmarkComponent getBenchmarkComponent(Component componentDesc, HashMap<String, Object> properties) {
        try {
            String toEval = componentDesc.getMethod();
            return new PlaidBenchmarkRunner(Class.forName(componentDesc.getMainClass()).getDeclaredMethod(toEval, plaid.fastruntime.PlaidObject.class), properties, componentDesc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
