package rbtree;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTreePrinterTest {

//    private static RedBlackNode test1() {
//        RedBlackNode root = new RedBlackNode(Color.BLACK, RedBlackNode.nilNode, 2);
//        RedBlackNode n11 = new RedBlackNode(Color.BLACK, root, 7);
//        RedBlackNode n12 = new RedBlackNode(Color.BLACK, root, 5);
//        RedBlackNode n21 = new RedBlackNode(Color.BLACK, n11, 2);
//        RedBlackNode n22 = new RedBlackNode(Color.BLACK, n11, 6);
//        RedBlackNode n23 = new RedBlackNode(Color.BLACK, n12, 3);
//        RedBlackNode n24 = new RedBlackNode(Color.BLACK, n12, 6);
//        RedBlackNode n31 = new RedBlackNode(Color.BLACK, n21, 5);
//        RedBlackNode n32 = new RedBlackNode(Color.BLACK, n21, 8);
//        RedBlackNode n33 = new RedBlackNode(Color.BLACK, n22, 4);
//        RedBlackNode n34 = new RedBlackNode(Color.BLACK, n22, 5);
//        RedBlackNode n35 = new RedBlackNode(Color.BLACK, n23, 8);
//        RedBlackNode n36 = new RedBlackNode(Color.BLACK, n23, 4);
//        RedBlackNode n37 = new RedBlackNode(Color.BLACK, n24, 5);
//        RedBlackNode n38 = new RedBlackNode(Color.BLACK, n24, 8);
//
//        root.l = n11;
//        root.r = n12;
//
//        n11.l = n21;
//        n11.r = n22;
//        n12.l = n23;
//        n12.r = n24;
//
//        n21.l = n31;
//        n21.r = n32;
//        n22.l = n33;
//        n22.r = n34;
//        n23.l = n35;
//        n23.r = n36;
//        n24.l = n37;
//        n24.r = n38;
//
//        return root;
//    }

//    private static RedBlackNode test2() {
//        RedBlackNode root = new RedBlackNode(Color.BLACK, , 2);
//        RedBlackNode n11 = new RedBlackNode(Color.BLACK, root, 7);
//        RedBlackNode n12 = new RedBlackNode(Color.BLACK, root, 5);
//        RedBlackNode n21 = new RedBlackNode(Color.BLACK, , 2);
//        RedBlackNode n22 = new RedBlackNode(Color.BLACK, , 6);
//        RedBlackNode n23 = new RedBlackNode(Color.BLACK, , 9);
//        RedBlackNode n31 = new RedBlackNode(Color.BLACK, , 5);
//        RedBlackNode n32 = new RedBlackNode(Color.BLACK, , 8);
//        RedBlackNode n33 = new RedBlackNode(Color.BLACK, , 4);
//
//        root.l = n11;
//        root.r = n12;
//
//        n11.l = n21;
//        n11.r = n22;
//
//        n12.r = n23;
//        n22.l = n31;
//        n22.r = n32;
//
//        n23.l = n33;
//
//        return root;
//    }

    public static void main(String[] args) {
    	ArrayList<Integer> one = new ArrayList<>();
    	one.add(8); one.add(4); one.add(1); one.add(5); one.add(6); 
    	one.add(9); one.add(3); one.add(0); one.add(2); one.add(7);
    	
    	ArrayList<Integer> two = new ArrayList<>();
    	two.add(3); two.add(4); two.add(6); two.add(8); two.add(7); 
    	two.add(1); two.add(5); two.add(9); two.add(0); two.add(2);
    	
    	RedBlackTree tree = new RedBlackTree();
    	for (Integer i : one) {
    		System.out.println("Inserting: " + i + "\n");
    		tree.insert(i);
			BTreePrinter.printRedBlackNode(tree.getRoot());
			System.out.println("\n\n");
    	}
    	
    	for (Integer i : two) {
    		System.out.println("Deleting: " + i + "\n");
    		tree.delete(i);
			BTreePrinter.printRedBlackNode(tree.getRoot());
			System.out.println("\n\n");
    	}
    }
}

//class RedBlackNode<T extends Comparable<?>> {
//    RedBlackNode left, right;
//    T data;
//
//    public RedBlackNode(T data) {
//        this.data = data;
//    }
//}

class BTreePrinter {

    public static <T extends Comparable<?>> void printRedBlackNode(RedBlackNode root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printRedBlackNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printRedBlackNodeInternal(List<RedBlackNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<RedBlackNode> newRedBlackNodes = new ArrayList<RedBlackNode>();
        for (RedBlackNode node : nodes) {
            if (node != null) {
            	if (node.data == -1) {
            		System.out.print("n");
            	}
            	else if (node.color == Color.RED) {
        			try
        			{
        				Thread.sleep((long)(50));
        			} catch (InterruptedException e)
        			{
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
            		System.err.print(node.data);
            	}
            		
            	else {
        			try
        			{
        				Thread.sleep((long)(50));
        			} catch (InterruptedException e)
        			{
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
            		System.out.print(node.data);
            	}
            		
                newRedBlackNodes.add(node.l);
                newRedBlackNodes.add(node.r);
            } else {
                newRedBlackNodes.add(null);
                newRedBlackNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).l != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).r != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printRedBlackNodeInternal(newRedBlackNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(RedBlackNode node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.l), BTreePrinter.maxLevel(node.r)) + 1;
    }

    private static  boolean isAllElementsNull(List list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}