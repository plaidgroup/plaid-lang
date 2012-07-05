package edu.cmu.isri.plaid.benchmarks;

import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.BenchmarkSuite;
import edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Property;
import edu.cmu.isri.plaid.benchmarks.Components.BenchmarkComponentFactory;
import edu.cmu.isri.plaid.benchmarks.Components.JavaBenchmarkFactory;
import edu.cmu.isri.plaid.benchmarks.Components.JavascriptBenchmarkFactory;
import edu.cmu.isri.plaid.benchmarks.Components.PlaidBenchmarkFactory;

import java.io.*;
import java.util.*;

/**
 * Benchmark runner loads XML files into Benchmarks, then runs and outputs them.
 * @author Benjamin Chung
 */
public class BenchmarkRunner {
    long seed = System.currentTimeMillis();
    List<Benchmark> benchmarks = new ArrayList<Benchmark>();
    HashMap<String, BenchmarkComponentFactory> factoryMap = new HashMap<String, BenchmarkComponentFactory>();
    private HashMap<String, Object> properties = new HashMap<String, Object>();

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


            //Default properties
            properties.put("Random",new Random(2012));


            for (Property p : benchmarkSuite.getProperty()) {
                try {
                    properties.put(p.getName(), Integer.parseInt(p.getValue()));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid property value: "+p.getValue());
                }
            }

            for (edu.cmu.isri.plaid.benchmarks.BenchmarkDesc.Benchmark benchmark : benchmarkSuite.getBenchmark())
                benchmarks.add(new Benchmark(benchmark, properties, factoryMap));

        } catch (javax.xml.bind.JAXBException je) {
            je.printStackTrace();
        }
    }

    public void run(boolean verbose, boolean genXml) throws Exception {
        BenchmarkResult[][] allResults = new BenchmarkResult[benchmarks.size()][];
        int n = 0;
        for (Benchmark benchmark : benchmarks) {
            BenchmarkResult[] results = benchmark.run();
            allResults[n++] = results;
            if (verbose) {
                System.out.println(benchmark.getBenchmarkDesc().getName()+" benchmark results");
                for (BenchmarkResult result : results) {
                    System.out.println(result.benchmarkComponent.getType()+
                            ((result.benchmarkComponent.getName() != null)?" "+result.benchmarkComponent.getName():"")+
                            " benchmark took "+ result.nsElapsed / 1000000000d+
                            " seconds");
                }
            }
        }
        if (genXml) {
            StringWriter writer = new StringWriter();
            javax.xml.bind.JAXB.marshal(allResults, writer);
            String xml = writer.toString();
            File out = new File("output.xml");
            FileOutputStream output = new FileOutputStream(out);
            output.write(xml.getBytes());
            output.close();
        }
    }

    public static void main(String[] args) {
        BenchmarkRunner runner = new BenchmarkRunner();
        if (args.length > 0)
            runner.loadFromXML(args[0]);

        ArrayList<String> argsList = new ArrayList<String>(Arrays.asList(args));

        try {
            boolean flag = argsList.contains("-x");
            runner.run(!flag, flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
