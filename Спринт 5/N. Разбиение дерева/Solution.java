

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {

    public static List<Node> split(Node root, int k) {
        // Базовые случаи
        if (k <= 0) {
            return Arrays.asList(null, root);
        }
        if (root == null) {
            return Arrays.asList(null, null);
        }

        int leftSize = (root.getLeft() != null) ? root.getLeft().getSize() : 0;

        if (leftSize + 1 <= k) {
            // Корень и всё левое поддерево входят в первое дерево (≤ k)
            int neededFromRight = k - leftSize - 1; // сколько ещё нужно взять из правого
            List<Node> parts = split(root.getRight(), neededFromRight);
            // parts.get(0) — первые `neededFromRight` узлов из правого
            // parts.get(1) — остаток правого дерева
            root.setRight(parts.get(0)); // подключаем "голову" правого как новое правое поддерево
            updateSize(root); // важно: размер изменился!
            return Arrays.asList(root, parts.get(1));
        } else {
            // k ≤ leftSize: нужные узлы целиком в левом поддереве
            List<Node> parts = split(root.getLeft(), k);
            // parts.get(0) — первые k узлов из левого
            // parts.get(1) — остаток левого поддерева
            root.setLeft(parts.get(1)); // "хвост" левого становится новым левым сыном
            updateSize(root);
            return Arrays.asList(parts.get(0), root);
        }
    }

    // Вспомогательный метод для поддержания инварианта size
    private static void updateSize(Node node) {
        if (node == null) return;
        int leftSize = (node.getLeft() != null) ? node.getLeft().getSize() : 0;
        int rightSize = (node.getRight() != null) ? node.getRight().getSize() : 0;
        node.setSize(1 + leftSize + rightSize);
    }


    // <template>
    private static class Node {

        private Node left;
        private Node right;
        private int value;
        private int size;

        Node(Node left, Node right, int value, int size) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.size = size;
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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
    // <template>


    public static void test() {
        Node node1 = new Node(null, null, 3, 1);
        Node node2 = new Node(null, node1, 2, 2);
        Node node3 = new Node(null, null, 8, 1);
        Node node4 = new Node(null, null, 11, 1);
        Node node5 = new Node(node3, node4, 10, 3);
        Node node6 = new Node(node2, node5, 5, 6);
        List<Node> res = split(node6, 4);
        assert res.get(0).getSize() == 4;
        assert res.get(1).getSize() == 2;
    }
}
