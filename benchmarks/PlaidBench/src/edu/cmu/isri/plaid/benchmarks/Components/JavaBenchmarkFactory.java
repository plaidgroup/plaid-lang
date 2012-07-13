package edu.cmu.isri.plaid.benchmarks.Components;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Component;

import java.util.Arrays;
import java.util.HashMap;

/**
 * This class creates BenchmarkComponents for Java benchmarks.
 * @author Benjamin Chung
 */
public class JavaBenchmarkFactory implements BenchmarkComponentFactory {
    private static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    @Override
    public BenchmarkComponent getBenchmarkComponent(Component component, HashMap<String, Object> properties) {
        try {
            Class bench = Class.forName(component.getMainClass());
            String toEval = component.getMethod();
            return new JavaBenchmarkRunner(bench.getDeclaredMethod(toEval, Runner.class), properties, component);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
