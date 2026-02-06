import java.util.Stack;

public class Solution {
    public static int treeSolution(Node head) {
        if (head == null)
        {
            return Integer.MIN_VALUE;
        }
        // Your code
        int max = head.value;
        max = Math.max(treeSolution(head.left), max);
        max = Math.max(treeSolution(head.right), max);
        return max;
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
        Node node4 = new Node(2);
        node4.left = node3;
        assert treeSolution(node4) == 3;
    }
}
