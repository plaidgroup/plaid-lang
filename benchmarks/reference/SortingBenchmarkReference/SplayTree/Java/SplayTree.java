package SortingBenchmarkReference.SplayTree.Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ben Chung
 * Date: 6/11/12
 * Time: 9:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class SplayTree {

    class SplayNode {
        int key;
		Object value;
        SplayNode parent, left, right;
        public SplayNode(int key, Object value, SplayNode parent) {
            this.key = key;
            this.parent = parent;
			this.value = value;
        }
        
        public void add(int key, Object elem) {
            if (key > this.key) {
                if (this.right == null) {
                    this.right = new SplayNode(key, elem, this);
                    this.right.splay();
                }
                else
                    this.right.add(key, elem);
            } else {
                if (this.left == null) {
                    this.left = new SplayNode(key, elem, this);
                    this.left.splay();
                }
                else
                    this.left.add(key, elem);

            }
        }

        public boolean find(int elem) {
            if (this.key == elem)
                return true;
            else if (elem > this.key && this.right != null)
                return this.right.find(elem);
            else if (this.left != null)
                return this.left.find(elem);

            return false;
        }

        public void delete(int elem) {
            if (elem != key) {
                if (elem > this.key && this.right != null)
                    this.right.delete(elem);
                else if (this.left != null)
                    this.left.delete(elem);
                return;
            }
            if (this.left != null && this.right != null) {
                this.key = this.right.findDeleteMin();
                return;
            }

            if (this.left != null && this.right==null) {
                if (parent != null)
                    parent.deleteReplaceChild(this, this.left);
                else {
                    root = this.left;
                    this.left.parent = null;
                }
            }
            else if (this.left == null && this.right != null)
                if (parent != null)
                    parent.deleteReplaceChild(this, this.right);
                else {
                    root = this.right;
                    this.right.parent = null;
                }
            else if (this.left == null && this.right == null)
                if (parent != null)
                    parent.deleteChild(this);
                else {
                    root = null;
                }

            if (parent != null)
                parent.splay();
        }

        private void splay() {
			SplayNode dummy, left, right;
			dummy = left = right = new SplayNode(0, null, null);
			SplayNode current = root;
			while (true) {
				if (key < current.key) {
					if (current.left == null) {
						break;
					}
					if (key < current.left.key) {
						// Rotate right.
						SplayNode tmp = current.left;
						current.left = tmp.right;
						tmp.right = current;
						current = tmp;
						if (current.left == null) {
							break;
						}
					}
					// Link right.
					right.left = current;
					right = current;
					current = current.left;
				} else if (key > current.key) {
					if (current.right == null) {
						break;
					}
					if (key > current.right.key) {
						// Rotate left.
						SplayNode tmp = current.right;
						current.right = tmp.left;
						tmp.left = current;
						current = tmp;
						if (current.right == null) {
							break;
						}
					}
					// Link left.
					left.right = current;
					left = current;
					current = current.right;
				} else {
					break;
				}
			}	
        }
        
        private void rotateRight() {
            SplayNode q = parent, p = this;
            if (parent.parent != null) {
                parent.parent.deleteReplaceChild(parent, this);
            } else {
                root = this;
                p.parent = null;
            }
            q.left = p.right;
            if (q.left != null)
                q.left.parent = q;
            p.right = q;
            if (p.right != null)
                p.right.parent = p;
        }
        
        private void rotateLeft() {
            SplayNode p = parent, q = this;
            if (parent.parent != null) {
                parent.parent.deleteReplaceChild(parent, q);
            } else {
                q.parent = null;
                root = q;
            }
            p.right = q.left;
            if (p.right != null)
                p.right.parent = p;
            q.left = p;
            if (q.left != null)
                q.left.parent = q;
        }
        
        private void deleteChild(SplayNode child) {
            if (child.key > key && this.right == child) {
                this.right = null;
            } else if (this.left == child) {
                this.left = null;
            }
        }

        private void deleteReplaceChild(SplayNode child, SplayNode replacement) {
            if (child.key > key && this.right == child) {
                this.right = replacement;
            } else if (this.left == child) {
                this.left = replacement;
            }

            if (replacement != null)
                replacement.parent = this;
        }
        
        private int findDeleteMin() {
            if (this.left == null) {
                parent.deleteChild(this);
                this.parent.splay();
                return this.key;
            }
            return this.left.findDeleteMin();
        }
    
        
    }

    SplayNode root;
    public void add(int key, Object elem) {
        if (root == null)
            root = new SplayNode(key, elem, null);
        else
            root.add(key, elem);
    }
    public boolean find(int key) {
        if (root == null)
            return false;
        else
            return root.find(key);
    }
    public void delete(int elem) {
        if (root != null)
            root.delete(elem);
    }
}
