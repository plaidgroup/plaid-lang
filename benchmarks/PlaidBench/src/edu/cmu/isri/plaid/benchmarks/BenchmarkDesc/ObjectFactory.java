
package edu.cmu.isri.plaid.benchmarks.BenchmarkDesc;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.cmu.isri.plaid.benchmarks.BenchmarkDesc package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.cmu.isri.plaid.benchmarks.BenchmarkDesc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Component }
     * 
     */
    public Component createComponent() {
        return new Component();
    }

    /**
     * Create an instance of {@link BenchmarkSuite }
     * 
     */
    public BenchmarkSuite createBenchmarkSuite() {
        return new BenchmarkSuite();
    }

    /**
     * Create an instance of {@link Benchmark }
     * 
     */
    public Benchmark createBenchmark() {
        return new Benchmark();
    }

}
