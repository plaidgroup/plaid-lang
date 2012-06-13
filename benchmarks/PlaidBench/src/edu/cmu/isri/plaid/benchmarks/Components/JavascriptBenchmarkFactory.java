package edu.cmu.isri.plaid.benchmarks.Components;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Component;
import edu.cmu.isri.plaid.benchmarks.BenchmarkResult;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * This class creates Javascript BenchmarkComponents
 * @author Benjamin Chung
 */
public class JavascriptBenchmarkFactory implements BenchmarkComponentFactory {
    @Override
    public BenchmarkComponent getBenchmarkComponent(Component componentDesc) {
        try {
            return new JavascriptBenchmarkRunner(new FileReader(componentDesc.getPath()), componentDesc);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This class runs Javascript benchmarks with Jav8.
     * @author Benjamin Chung
     */
    private static class JavascriptBenchmarkRunner extends Runner implements BenchmarkComponent {
        private Reader input;
        private Component benchmarkComponent;

        public JavascriptBenchmarkRunner(Reader input, Component benchmarkComponent) {
            this.input = input;
            this.benchmarkComponent = benchmarkComponent;
        }

        @Override
        public BenchmarkResult run() throws Exception {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("jav8");
            engine.eval(input);
            String toEval = benchmarkComponent.getMethod();
            ((Invocable) engine).invokeFunction(toEval, this);
            input.close();
            return new BenchmarkResult(benchmarkComponent, getTime());
        }
    }
}
