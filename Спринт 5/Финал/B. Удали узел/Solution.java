// <template>
class Node {
    private int value;
    private Node left;
    private Node right;

    Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
// <template>

public class Solution {
    public static Node remove(Node root, int key) {
        // Your code
        // “ヽ(´▽｀)ノ”
        if (root == null) return null;

        Node parent = new Node(root, null, -1);
        Node node = root;
        Node succeeder = null;

        while (node != null) {
            if (key > node.getValue()) {
                parent = node;
                node = node.getRight();
            } else if (key < node.getValue()) {
                parent = node;
                node = node.getLeft();
            } else {
                Node parentSucceeder = node;
                succeeder = node.getRight();

                while (succeeder != null && succeeder.getLeft() != null) {
                    parentSucceeder = succeeder;
                    succeeder = succeeder.getLeft();
                }

                succeeder = succeeder == null ? node.getLeft() : succeeder;

                if (parent.getLeft() == node) {
                    parent.setLeft(succeeder);
                } else if (parent.getRight() == node) {
                    parent.setRight(succeeder);
                }

                if (succeeder != null && succeeder != node.getLeft()) {
                    succeeder.setLeft(node.getLeft());

                    if (parentSucceeder != node) {
                        parentSucceeder.setLeft(succeeder.getRight());
                        succeeder.setRight(node.getRight());
                    }
                }

                break;
            }
        }

        return node == root ? succeeder : root;
    }
}
