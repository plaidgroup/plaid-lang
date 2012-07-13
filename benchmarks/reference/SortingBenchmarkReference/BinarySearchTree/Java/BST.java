package SortingBenchmarkReference.BinarySearchTree.Java;

public class BST {
    class BSTNode {
        public BSTNode(int value, BSTNode parent) {
            this.value = value;
			this.parent = parent;
        }

        BSTNode left;
        BSTNode right;
		BSTNode parent;
        int value;
		
		
		private void replaceParentChild(BSTNode node) {
            if (parent != null) {
                if (value > parent.value) {
                    parent.right = node;
                } else {
                    parent.left = node;
                }
                if (node != null)
                    node.parent = parent;
            }
            else {
                if (node != null)
                    node.parent = null;
                root = node;
            }
		}
        
        void add(int elem) {
            if (elem > value) {
                if (right == null)
                    right = new BSTNode(elem, this);
                else
                    right.add(elem);
            }
            else {
                if (left == null)
                    left = new BSTNode(elem, this);
                else
                    left.add(elem);
            }
        }

        boolean search(int elem) {
			if (value == elem) return true;
			if (elem > value && right != null) {
				return right.search(elem);
			} else if (elem < value && left != null) {
				return left.search(elem);
			} else {
				return false;
			}
		}

		private BSTNode findDeleteMinNode() {
			if (left != null)
				return left.findDeleteMinNode();
			replaceParentChild(this.right);
			return this;
		}

		void delete(int elem) {
			if (value != elem) {
				if (elem > value && right != null)
					right.delete(elem);
				else if (elem < value && left != null)
					left.delete(elem);
				return;
			}
			
			if (left != null && right != null) {
				this.value = right.findDeleteMinNode().value;
			}
			else if (left == null && right != null) {
				replaceParentChild(right);
			}
			else if (left != null && right == null) {
				replaceParentChild(left);
			}
			else if (left == null && right == null) {
				replaceParentChild(null);
			}
			
		}
    }
    BSTNode root;
    public BST() {}

    public void add(int value) {
        if (root == null)
            root = new BSTNode(value, null);
        else {
            root.add(value);
        }

    }

    public void delete(int value) {
		if (root != null)
			root.delete(value);
    }

    public boolean find(int value) {
		if (root != null)
			return root.search(value);
		return false;
	}
}
