package SortingBenchmarkReference.ShellSort.Java;

import org.junit.Test;

import java.util.Random;

import static junit.framework.Assert.assertTrue;

/**
* Created by IntelliJ IDEA.
* User: Ben Chung
* Date: 6/4/12
* Time: 11:19 AM
* To change this template use File | Settings | File Templates.
*/
public class ShellSortTest {
    private int[] getGaps() {
        return new int[] {701,301,132,57,23,10,4,1};
    }
    
    private int[] getArray(int n) {
        int[] toSort = new int[n];
        Random randGen = new Random();
        for (int i = 0; i < n; i++) {
            toSort[i] = randGen.nextInt();
        }
        return toSort;
    }
    
    private boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i-1] > array[i])
                return false;
        }
        return true;
    }
    
    @Test
    public void testShellSortBig() throws Exception {
        int n = 100000;
        int[] toSort = getArray(n);
        ShellSort.ShellSort(toSort, getGaps());
        assertTrue(isSorted(toSort));
    }

    @Test
    public void testShellSortDuplicates() throws Exception {
        int[] toSort = new int[] {1,2,2,2,3,4,5,6,5,5,1,7,2,5};
        ShellSort.ShellSort(toSort, getGaps());
        assertTrue(isSorted(toSort));
    }


    @Test
    public void testShellSortNegative() throws Exception {
        int[] toSort = new int[] {1,2,-2,2,3,4,-5,6,5,5,-1,7,2,5};
        ShellSort.ShellSort(toSort, getGaps());
        assertTrue(isSorted(toSort));
    }


    @Test
    public void testShellSortZero() throws Exception {
        int[] toSort = new int[] {};
        ShellSort.ShellSort(toSort, getGaps());
        assertTrue(isSorted(toSort));
    }

    @Test
    public void testShellSortOneElam() throws Exception {
        int[] toSort = new int[] {3};
        ShellSort.ShellSort(toSort, getGaps());
        assertTrue(isSorted(toSort));
    }
}
