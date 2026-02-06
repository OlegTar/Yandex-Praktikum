public class Solution {
    public static boolean treeSolution(Node head) {
        // Your code
        // “ヽ(´▽｀)ノ”
        return check(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean check(Node head, int min, int max) {
        if (head == null) {
        	return true;
        }
    
    	if (head.value <= min) {
        	return false;
        }
    
    	if (head.value >= max) {
        	return false;
    	}
    
    	return check(head.left, min, head.value) 
        	&& check(head.right, head.value, max);
    }
    // <template>
    // <template>


    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        node2.value = 5;
        assert !treeSolution(node5);
    }
}
