import java.lang.*;
import java.util.*;

public class Solution {
	private static int sum = 0;
    public static int treeSolution(Node head) {
    	if (head == null) return 0;
        List<Integer> combination = new ArrayList<>();
    	solve(head, combination);
    	return sum;
    }

	public static void solve(Node node, List<Integer> combination) {
    	combination.add(node.value);
    	if (node.left != null) {
        	solve(node.left, combination);
    	}
    	if (node.right != null) {
    		solve(node.right, combination);
        }
    
    	if (node.left == null && node.right == null) {
        	StringBuilder sb = new StringBuilder();
        	for (int num : combination) {
        		sb.append(Integer.toString(num));
            }
        	sum += Integer.parseInt(sb.toString());
        }
    	combination.removeLast();
    }

    // <template>
    private static class Node {
        int value;  
        Node left;  
        Node right;  
    
        Node(int value) {  
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {  
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    // <template>
    
    private static void test() {
        Node node1 = new Node(2, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(1, node4, node3);
        assert treeSolution(node5) == 275;
    }
}
