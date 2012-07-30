package SortingBenchmarkReference.SplayTree.Java;

import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
* Created by IntelliJ IDEA.
* User: Ben Chung
* Date: 6/11/12
* Time: 12:27 PM
* To change this template use File | Settings | File Templates.
*/
public class SplayTreeTest {
    static SplayTree tree = new SplayTree();
    static Random rand = new Random();
    static final int n = 1000000;
    static Integer[] inserted = new Integer[n];
    @Test
    public void testAdd() throws Exception {
        for (int i = 0; i < n; i++) {
            inserted[i] = rand.nextInt();
            tree.add(inserted[i], null);
            assertTrue(tree.find(inserted[i]));
        }
    }

    @Test
    public void testFind() throws Exception {
        for (int n = 0; n < 10; n++) {
            for (int i = 0; i < inserted.length; i++) {
                assertTrue(tree.find(inserted[i]));
            }
        }
        for (int i = 0; i < 1000; i++) {
        	assertFalse(tree.find(rand.nextInt(inserted.length) + inserted.length + 1));
        }
    }

    @Test
    public void testDelete() throws Exception {
        rand.nextInt(inserted.length);
        List<Integer> ints = Arrays.asList(inserted);
        Collections.shuffle(ints);
        for (Integer i : ints) {
            tree.delete(i);
        }
    }
}
