package edu.cmu.isri.plaid.benchmarks.Components;

/**
 * Created by IntelliJ IDEA.
 * User: Ben Chung
 * Date: 7/5/12
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestFailedException extends Exception {
    public TestFailedException(Exception e) {
        this.initCause(e);
    }
}
