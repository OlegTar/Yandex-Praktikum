import java.lang.Math;

public class Solution {
	private static int max = Integer.MIN_VALUE;
    public static int treeSolution(Node root) {
        if (root == null) return 0; // по условию — ≥1 узел, но для безопасности
        dfs(root);
    	return max;
    }

    // Возвращает: максимальную сумму пути, начинающегося в node и идущего вниз
    // (включая node, и выбор — только влево, только вправо, или только node)
    private static int dfs(Node node) {
        if (node == null) {
            return 0;
        }

        // Рекурсивно получаем max-суммы для поддеревьев (если <0 — игнорируем: возьмём 0)
        int leftMax  = node.left  == null ? 0 : dfs(node.left);
        int rightMax = node.right == null ? 0 : dfs(node.right);

        // Путь, проходящий через node: left → node → right
        max = Math.max(max, node.value);
    	max = Math.max(max, node.value + leftMax);
    	max = Math.max(max, node.value + rightMax);
    	max = Math.max(max, node.value + leftMax + rightMax);


        // Возвращаем лучшее продолжение *вверх* для родителя: node + max(left, right)
        return Math.max(node.value + Math.max(leftMax, rightMax), node.value);
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
        Node node1 = new Node(5, null, null);
        Node node2 = new Node(1, null, null);
        Node node3 = new Node(-3, node2, node1);
        Node node4 = new Node(2, null, null);
        Node node5 = new Node(2, node4, node3);
        assert treeSolution(node5) == 6;
    }
}
