public class Solution {
    public static boolean treeSolution(Node head) {
        // Your code
        // “ヽ(´▽｀)ノ”
    	if (head == null)
        {
        	return true;
        }
    
    	return treeEqual(head.left, head.right);
    }

	public static boolean treeEqual(Node head1, Node head2) {
    	if (head1 == null && head2 == null) {
    		return true;
        }
    
    	if (head1 == null) {
    		return false;
        }
    
    	if (head2 == null) {
        	return false;
        }
    
    	if (head1.value != head2.value) {
    		return false;
        }
    
    	return treeEqual(head1.right, head2.left) && treeEqual(head1.left, head2.right);
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
        Node node1 = new Node(3,  null,  null);
        Node node2 = new Node(4,  null,  null);
        Node node3 = new Node(4,  null,  null);
        Node node4 = new Node(3,  null,  null);
        Node node5 = new Node(2, node1, node2);
        Node node6 = new Node(2, node3, node4);
        Node node7 = new Node(1, node5, node6);
        assert treeSolution(node7);
    }
}
