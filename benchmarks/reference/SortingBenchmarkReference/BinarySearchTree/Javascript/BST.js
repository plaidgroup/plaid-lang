function BSTNode(val, parent, tree) {
	this.value = val;
	this.parent = parent;
	this.tree = tree;
	this.left = null;
	this.right = null;
	
	this.add = function(elem) {
		if (elem > this.value) {
			if (this.right == null)
				this.right = new BSTNode(elem, this, tree);
			else
				this.right.add(elem);
		}
		else {
			if (this.left == null)
				this.left = new BSTNode(elem, this, tree);
			else
				this.left.add(elem);
		}
	};
	this.find = function(elem) {
		if (this.value == elem) return true;
		if (elem > this.value && this.right != null) {
			return this.right.search(elem);
		} else if (elem < this.value && this.left != null) {
			return this.left.search(elem);
		} else {
			return false;
		}
	};
	
	this.replaceParentChild = function(node) {
		if (this.parent != null) {
			if (this.value > this.parent.value) {
				this.parent.right = node;
			} else {
				this.parent.left = node;
			}
			if (node != null)
				node.parent = this.parent;
		}
		else {
			if (node != null)
				node.parent = null;
			this.tree.root = node;
		}
	}
	
	this.findDeleteMinNode = function() {
		if (this.left != null)
			return left.findDeleteMinNode();
		this.replaceParentChild(this.right);
		return this;
	}
	
	this.del = function(elem) {
		if (this.value != elem) {
			if (elem > this.value && this.right != null)
				this.right.del(elem);
			else if (elem < this.value && this.left != null)
				this.left.del(elem);
			return;
		}
		
		if (this.left != null && this.right != null) {
			this.value = this.right.findDeleteMinNode().value;
		}
		else if (this.left == null && this.right != null) {
			this.replaceParentChild(this.right);
		}
		else if (this.left != null && this.right == null) {
			this.replaceParentChild(this.left);
		}
		else if (this.left == null && this.right == null) {
			this.replaceParentChild(null);
		}
	};
}

function BST() {
	this.root = null;
	this.add = function(elem) {
		if (this.root != null)
			this.root.add(elem);
		else
			this.root = new BSTNode(elem, null, this);
	};
	this.find = function (elem) {
		if (this.root != null)
			return this.root.find(elem);
		return false;
	};
	this.del = function (elem) {
		if (this.root != null)
			this.root.del(elem);
	};
}

function benchmark(runner) {
	var bst = new BST();
	var n = 5000;
	var elems = new Array();
	for (var i = 0; i < n; i++) {
		var elem = Math.floor(Math.random() * (10000000 + 1));
		elems.push(elem);
	}
	runner.startTimer();
	for (var k = 0; k < n; k++) {
		bst.add(elems[i]);
	}
	for (var l = 0; l < n; l++) {
		bst.del(elems[i]);
	}
	runner.stopTimer();
}