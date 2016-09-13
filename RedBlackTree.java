package rbtree;

import java.awt.Color;

public class RedBlackTree
{
	private RedBlackNode root = RedBlackNode.nilNode;
	
	public RedBlackNode getRoot() {
		return this.root;
	}
	
	public void insert(int data) {
		RedBlackNode node = new RedBlackNode(Color.RED, null, data);
		RedBlackNode parent = RedBlackNode.nilNode;
		RedBlackNode current = root;
		
		while (current != RedBlackNode.nilNode) {
			parent = current;
			if (node.compareTo(current) < 0) {
				current = current.l;
			} else {
				current = current.r;
			}
		}
		
		node.p = parent;
		if (parent == RedBlackNode.nilNode) {
			root = node;
		} else if (node.compareTo(parent) < 0) {
			parent.l = node;
		} else {
			parent.r = node;
		}
		
		insertFixup(node);
	}
	
	private void insertFixup(RedBlackNode node) {
		while (node.p.color == Color.RED) {
			if (node.p == node.p.p.l) {
				RedBlackNode uncle = node.p.p.r;
				if (uncle.color == Color.RED) {
					node.p.color = Color.BLACK;
					uncle.color = Color.BLACK;
					node.p.p.color = Color.RED;
					node = node.p.p;
				} else {
					if (node == node.p.r) {
						node = node.p;
						rotateLeft(node);
					}
					node.p.color = Color.BLACK;
					node.p.p.color = Color.RED;
					rotateRight(node.p.p);
				}
			} else {
				RedBlackNode uncle = node.p.p.l;
				if (uncle.color == Color.RED) {
					node.p.color = Color.BLACK;
					uncle.color = Color.BLACK;
					node.p.p.color = Color.RED;
					node = node.p.p;
				} else {
					if (node == node.p.l) {
						node = node.p;
						rotateRight(node);
					}
					node.p.color = Color.BLACK;
					node.p.p.color = Color.RED;
					rotateLeft(node.p.p);
				}
			}
		}
		
		this.root.color = Color.BLACK;
	}
	
	private void rotateLeft(RedBlackNode node) {
		RedBlackNode oldRightSubTree = node.r;
		
		node.r = oldRightSubTree.l;
		
		if (oldRightSubTree != RedBlackNode.nilNode) {
			oldRightSubTree.l.p = node;
		}
		
		oldRightSubTree.p = node.p;
		
		if (node.p == RedBlackNode.nilNode) {
			root = oldRightSubTree;
		} else if (node == node.p.l) {
			node.p.l = oldRightSubTree;
		} else {
			node.p.r = oldRightSubTree;
		}
		
		oldRightSubTree.l = node;
		node.p = oldRightSubTree;
	}
	
	private void rotateRight(RedBlackNode node) {
		RedBlackNode oldLeftSubTree = node.l;
		
		node.l = oldLeftSubTree.r;
		
		if (oldLeftSubTree != RedBlackNode.nilNode) {
			oldLeftSubTree.r.p = node;
		}
		
		oldLeftSubTree.p = node.p;
		
		if (node.p == RedBlackNode.nilNode) {
			root = oldLeftSubTree;
		} else if (node == node.p.r) {
			node.p.r = oldLeftSubTree;
		} else {
			node.p.l = oldLeftSubTree;
		}
		
		oldLeftSubTree.r = node;
		node.p = oldLeftSubTree;
	}
	
	private void transplant(RedBlackNode u, RedBlackNode v) {
		if (u.p == RedBlackNode.nilNode) {
			this.root = v;
		} else if (u == u.p.l) {
			u.p.l = v;
		} else {
			u.p.r = v;
		}
		
		v.p = u.p;
	}
	
	public void delete(int data) {
		RedBlackNode z = treeSearch(this.root, data);
		RedBlackNode y = z;
		RedBlackNode x = RedBlackNode.nilNode;
		Color yOriginalColor = y.color;
		
		if (z.l == RedBlackNode.nilNode) {
			x = z.r;
			transplant(z, z.r);
		} else if (z.r == RedBlackNode.nilNode) {
			x = z.l;
			transplant(z, z.l);
		} else {
			y = treeMinimum(z.r);
			yOriginalColor = y.color;
			x = y.r;
			if (y.p == z) {
				x.p = y;
			} else {
				transplant(y, y.r);
				y.r = z.r;
				y.r.p = y;
			}
			transplant(z, y);
			y.l = z.l;
			y.l.p = y;
			y.color = z.color;
		}
		
		if (yOriginalColor == Color.BLACK) {
			deleteFixUp(x);
		}
	}
	
	private void deleteFixUp(RedBlackNode x) {
		while (x != this.root && x.color == Color.BLACK) {
			if (x == x.p.l) {
				RedBlackNode w = x.p.r;
				if (w.color == Color.RED) {
					w.color = Color.BLACK;
					x.p.color = Color.RED;
					rotateLeft(x.p);
					w = x.p.r;
				}
				if (w.l.color == Color.BLACK && w.r.color == Color.BLACK) {
					w.color = Color.RED;
					x = x.p;
				} else {
					if (w.r.color == Color.BLACK) {
						w.l.color = Color.BLACK;
						w.color = Color.RED;
						rotateRight(w);
						w = x.p.r;
					}
					w.color = x.p.color;
					x.p.color = Color.BLACK;
					w.r.color = Color.BLACK;
					rotateLeft(x.p);
					x = this.root;
				}
			} else {
				RedBlackNode w = x.p.l;
				if (w.color == Color.RED) {
					w.color = Color.BLACK;
					x.p.color = Color.RED;
					rotateRight(x.p);
					w = x.p.l;
				}
				if (w.r.color == Color.BLACK && w.l.color == Color.BLACK) {
					w.color = Color.RED;
					x = x.p;
				} else {
					if (w.l.color == Color.BLACK) {
						w.r.color = Color.BLACK;
						w.color = Color.RED;
						rotateLeft(w);
						w = x.p.l;
					}
					w.color = x.p.color;
					x.p.color = Color.BLACK;
					w.l.color = Color.BLACK;
					rotateRight(x.p);
					x = this.root;
				}
			}
		}
		x.color = Color.BLACK;
	}
	
	private RedBlackNode treeMinimum(RedBlackNode x) {
		while (x.l != RedBlackNode.nilNode) {
			x = x.l;
		}
		
		return x;
	}
	
	private RedBlackNode treeSearch(RedBlackNode node, int data) {
		if (node == RedBlackNode.nilNode || node.data == data) {
			return node;
		}
		
		if (data < node.data) {
			return treeSearch(node.l, data);
		} else {
			return treeSearch(node.r, data);
		}
	}
}
