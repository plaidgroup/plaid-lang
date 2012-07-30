package edu.cmu.isri.plaid.benchmarks.Components;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Ben Chung
 * Date: 6/7/12
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Runner implements BenchmarkComponent {
    private long start;
    private long end;
    protected HashMap<String, Object> properties;

    protected Runner(HashMap<String, Object> properties) {

        this.properties = properties;
    }

    public void startTimer() {
        start = System.nanoTime();
    }
    public void stopTimer() {
        end = System.nanoTime();
    }
    
    public Object getProperty(String name) {
        return properties.get(name);
    }
    
    protected long getTime() {
        return end-start;
    }
}
