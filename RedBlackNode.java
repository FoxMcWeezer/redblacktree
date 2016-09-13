package rbtree;

import java.awt.Color;

// Make this class generic
public class RedBlackNode implements Comparable<RedBlackNode>
{
	public static RedBlackNode nilNode = new RedBlackNode(Color.BLACK, null, -1);
	
	public Color color;
	
	public int data;

	public RedBlackNode p;
	public RedBlackNode l;
	public RedBlackNode r;
	
	public RedBlackNode(Color color, RedBlackNode p, int data) {
		this.color = color;
		this.p = p;
		this.l = nilNode;
		this.r = nilNode;
		this.data = data;
	}
	
	@Override
	public int compareTo(RedBlackNode o) {
		return this.data - o.data;
	}
}

