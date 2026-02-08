import java.util.*;
import java.io.*;

public class Program {
    public static class TreeNode {
        private long val;
        public long getVal() {
            return val;
        }

        private List<TreeNode> children;
        public List<TreeNode> getChildren() {
            return children;
        }

        public void addChild(TreeNode node) {
            if (children == null) {
                children = new ArrayList<TreeNode>();
            }
            children.add(node);
        }

        private TreeNode parent;
        public TreeNode getParent() {
            return parent;
        }

        public TreeNode(TreeNode parent, long val) {
            this.parent = parent;
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    static long answer = 0;
    static Map<Long, Integer> freq = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long x = scanner.nextLong();

        //0 (unused), 1 (root), 2, 3, 4, 5, 6, 7, 8, 9, 10]

        //0 (unused), 1 (root), 2, 0, 3, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0 ]

    	/*int[] tree = new int[n + 1];
    	Arrays.fill(tree, Integer.MIN_VALUE);
    	int lastIndex = 1;*/

        TreeNode[] arr = new TreeNode[n];
        int root = -1;
        int[] parentIndicies = new int[n];

        for (int i = 0; i < n; i++) {
            int pi = scanner.nextInt();
            long wi = scanner.nextLong();

            arr[i] = new TreeNode(null, wi);
            parentIndicies[i] = pi;

            if (pi == -1) {
                root = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (parentIndicies[i] != -1) {
                int parentIndex = parentIndicies[i];
                TreeNode parent = arr[parentIndex];
                parent.addChild(arr[i]);
            }
        }

        freq.put(0L, 1);
        dfs(arr[root], 0, x);

    	/*for (int i = 0; i < arr.length; i++) {
    		TreeNode node = arr[i];
        	long sum = 0;
        	while (node != null) {
            	sum += node.getVal();
            	if (sum == x) {
            		result++;
                }
        		node = node.getParent();
            }
        }*/
        System.out.println(answer);
    }

    static void dfs(TreeNode node, long currSum, long x) {
        currSum += node.getVal();

        // Сколько предков (включая над-корень) имеют сумму = currSum - X?
        answer += freq.getOrDefault(currSum - x, 0);

        // Добавляем текущую сумму в путь
        freq.put(currSum, freq.getOrDefault(currSum, 0) + 1);

        // Рекурсия по детям
        for (TreeNode child : node.getChildren()) {
            dfs(child, currSum, x);
        }

        // Backtrack: удаляем текущую сумму из частот
        freq.put(currSum, freq.get(currSum) - 1);
        if (freq.get(currSum) == 0) {
            freq.remove(currSum);
        }
    }
}
