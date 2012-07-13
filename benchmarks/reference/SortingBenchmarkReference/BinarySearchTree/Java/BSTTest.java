package SortingBenchmarkReference.BinarySearchTree.Java;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import java.util.Random;

/**
* Created by IntelliJ IDEA.
* User: Ben Chung
* Date: 6/5/12
* Time: 12:54 PM
* To change this template use File | Settings | File Templates.
*/
public class BSTTest {
    public boolean CheckInvariants(BST tree) {
        return CheckNodeInvariant(tree.root);
    }
    
    private boolean CheckNodeInvariant(BST.BSTNode node) {
        return (node == null) || (CheckNodeInvariant(node.left) && CheckNodeInvariant(node.right) && (node.left == null ||node.left.value <= node.value) && (node.right == null || node.right.value > node.value));
    }
    
    
    @Test
    public void testAdd() throws Exception {
        BST test = new BST();
        test.add(5);
        test.add(4);
        test.add(6);
        test.add(3);
        test.add(7);
        assertTrue(test.root.value == 5);
        assertTrue(test.root.right.value == 6);
        assertTrue(test.root.left.value == 4);
        assertTrue(test.root.left.left.value == 3);
        assertTrue(test.root.right.right.value == 7);
        assertTrue(CheckInvariants(test));
    }

    @Test
    public void testRemove() throws Exception {
        BST test = new BST();
        test.add(5);
        test.add(4);
        test.add(6);
        test.add(3);
        test.add(7);
        assertTrue(test.root.value == 5);
        assertTrue(test.root.right.value == 6);
        assertTrue(test.root.left.value == 4);
        assertTrue(test.root.left.left.value == 3);
        assertTrue(test.root.right.right.value == 7);
        assertTrue(CheckInvariants(test));
        test.delete(5);
        assertTrue(test.root.value == 6);
        assertTrue(test.root.right.value == 7);
        assertTrue(test.root.left.value == 4);
        assertTrue(test.root.left.left.value == 3);
        assertTrue(CheckInvariants(test));
        test.delete(6);
        assertTrue(test.root.value == 7);
        assertTrue(test.root.left.value == 4);
        assertTrue(test.root.left.left.value == 3);
        assertTrue(CheckInvariants(test));
        test.delete(7);
        assertTrue(test.root.value == 4);
        assertTrue(test.root.left.value == 3);
        assertTrue(CheckInvariants(test));
        test.delete(4);
        assertTrue(test.root.value == 3);
        assertTrue(CheckInvariants(test));
        test.delete(3);
        assertTrue(test.root == null);
        assertTrue(CheckInvariants(test));
    }

    @Test
    public void testFind() throws Exception {
        BST test = new BST();
        test.add(5);
        test.add(4);
        test.add(6);
        test.add(3);
        test.add(7);
        assertTrue(CheckInvariants(test));
        assertTrue(test.find(5));
        assertTrue(test.find(4));
        assertTrue(test.find(6));
        assertTrue(test.find(3));
        assertTrue(test.find(7));
    }
	
	@Test
	public void testLots() {
        BST test = new BST();
		Random random = new Random();
		int n = 10000;
		int[] inserted = new int[n];
		for (int i = 0; i < n; i++) {
			inserted[i] = random.nextInt();
			test.add(inserted[i]);
			assertTrue(CheckInvariants(test));
		}
		for (int i = 0; i < n; i++) {
			test.delete(inserted[i]);
			assertTrue(CheckInvariants(test));
		}
	}
}
