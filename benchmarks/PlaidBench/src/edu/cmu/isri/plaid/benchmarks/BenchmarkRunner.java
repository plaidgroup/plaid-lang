package edu.cmu.isri.plaid.benchmarks;

import edu.cmu.isri.plaid.benchmarks.Benchmark;
import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.BenchmarkSuite;
import edu.cmu.isri.plaid.benchmarks.Components.BenchmarkComponentFactory;
import edu.cmu.isri.plaid.benchmarks.Components.JavaBenchmarkFactory;
import edu.cmu.isri.plaid.benchmarks.Components.JavascriptBenchmarkFactory;
import edu.cmu.isri.plaid.benchmarks.Components.PlaidBenchmarkFactory;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Benchmark runner loads XML files into Benchmarks, then runs and outputs them.
 * @author Benjamin Chung
 */
public class BenchmarkRunner {
    long seed = System.currentTimeMillis();
    List<Benchmark> benchmarks = new ArrayList<Benchmark>();
    HashMap<String, BenchmarkComponentFactory> factoryMap = new HashMap<String, BenchmarkComponentFactory>();
    {
        factoryMap.put("Java", new JavaBenchmarkFactory());
        factoryMap.put("Javascript", new JavascriptBenchmarkFactory());
        factoryMap.put("Plaid", new PlaidBenchmarkFactory());
    }

    
    public void loadFromXML(String file) {
        try {
            javax.xml.bind.JAXBContext jaxbContext = javax.xml.bind.JAXBContext.newInstance("edu.cmu.isri.plaid.benchmarks.BenchmarkDesc");
            javax.xml.bind.Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            BenchmarkSuite benchmarkSuite = (BenchmarkSuite) unmarshaller.unmarshal(new File(file));
            for (edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Benchmark benchmark : benchmarkSuite.getBenchmark())
                benchmarks.add(new Benchmark(benchmark, factoryMap));
        } catch (javax.xml.bind.JAXBException je) {
            je.printStackTrace();
        }
    }

    public void run() throws Exception {
        for (Benchmark benchmark : benchmarks) {
            BenchmarkResult[] results = benchmark.run();

            System.out.println(benchmark.getBenchmarkDesc().getName()+" benchmark results");
            for (BenchmarkResult result : results) {
                System.out.println(result.benchmarkComponent.getType()+
                        ((result.benchmarkComponent.getName() != null)?" "+result.benchmarkComponent.getName():"")+
                        " benchmark took "+ result.nsElapsed / 1000000000d+
                        " seconds");
            }
        }
    }

    public static void main(String[] args) {
        BenchmarkRunner runner = new BenchmarkRunner();
        runner.loadFromXML(args[0]);

        try {
            runner.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
