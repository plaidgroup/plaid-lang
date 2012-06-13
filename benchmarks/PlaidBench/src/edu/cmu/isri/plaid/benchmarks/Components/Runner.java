package edu.cmu.isri.plaid.benchmarks.Components;

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

    public void startTimer() {
        start = System.nanoTime();
    }
    public void stopTimer() {
        end = System.nanoTime();
    }
    
    protected long getTime() {
        return end-start;
    }
}
