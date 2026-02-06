import java.util.*;

public class Solution {
	private static Map<Node, Integer> dict = new HashMap<Node, Integer>();
    public static boolean treeSolution(Node head) {
    	if (head == null) {
    		return true;
        }
        // Your code
        // “ヽ(´▽｀)ノ”
    	return Math.abs(height(head.left) - height(head.right)) <= 1
        	&& treeSolution(head.left)
        	&& treeSolution(head.right);
    }

	public static int height(Node head) {
    	if (head == null) {
        	return 0;
    	}
    
    	if (dict.containsKey(head)) {
        	return dict.get(head);
    	}
    
    	int result = Math.max(1 + height(head.left), 1 + height(head.right));
    	dict.put(head, result);
    	return result;
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
    }
    // <template>
    
    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        assert treeSolution(node5);
    }
}
