import java.io.*;
import java.util.*;

public class Main {
    static class TrieNode {
        TrieNode[] children = new TrieNode[2]; // children[0] — бит 0, children[1] — бит 1
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] tokens = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        TrieNode root = new TrieNode();
        insert(root, arr[0]);

        int max_xor = 0;
        for (int i = 1; i < n; i++) {
            int current_max = query(root, arr[i]);
            if (current_max > max_xor) {
                max_xor = current_max;
            }
            insert(root, arr[i]);
        }

        System.out.println(max_xor);
    }

    // Вставка числа в Trie (31 бит: от 30 до 0)
    static void insert(TrieNode root, int num) {
        TrieNode node = root;
        for (int i = 30; i >= 0; i--) {
            int bit = (num >>> i) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
        }
    }

    // Поиск числа в Trie, дающего максимальный XOR с num
    static int query(TrieNode root, int num) {
        TrieNode node = root;
        int xor_val = 0;
        for (int i = 30; i >= 0; i--) {
            int bit = (num >>> i) & 1;
            int toggled = 1 - bit; // желаемый противоположный бит

            if (node.children[toggled] != null) {
                xor_val |= (1 << i);
                node = node.children[toggled];
            } else {
                // Обязательно идём по существующему пути
                node = node.children[bit];
            }
        }
        return xor_val;
    }
}
