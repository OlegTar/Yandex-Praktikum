import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        Node[] children = new Node[26];
        boolean isTerminated = false;
        List<String> words = new ArrayList<>();
    }

    static class Trie {
        private Node root;

        public Trie() {
            root = new Node();
        }

        public void add(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (ch < 'A' || ch > 'Z') {
                    continue;
                }
                int idx = ch - 'A';
                if (node.children[idx] == null) {
                    node.children[idx] = new Node();
                }
                node = node.children[idx];
            }
            node.isTerminated = true;
            node.words.add(word);
        }

        public List<String> search(String pattern) {
            Node node = root;
            boolean notFound = false;

            for (char ch : pattern.toCharArray()) {
                if (ch < 'A' || ch > 'Z') {
                    continue;
                }
                int idx = ch - 'A';
                if (node.children[idx] == null) {
                    notFound = true;
                    break;
                }
                node = node.children[idx];
            }

            List<String> result = new ArrayList<>();

            if (notFound) {
                // В C# вы добавляли пустую строку, но это странно.
                // Скорее всего, это ошибка — лучше ничего не возвращать.
                // Но чтобы сохранить логику: если не найдено — вернуть пустую строку?
                // Однако в примере использования вы выводите всё из очереди.
                // Допустим, при отсутствии совпадений — ничего не добавляем.
                // Закомментируем добавление пустой строки.
                result.add("");
                return result;
            }

            // DFS для сбора всех слов в поддереве
            Stack<Node> stack = new Stack<>();
            stack.push(node);

            while (!stack.isEmpty()) {
                Node current = stack.pop();

                if (current.isTerminated) {
                    result.addAll(current.words);
                }

                // Добавляем детей в стек (порядок: от Z к A, чтобы при извлечении был A..Z)
                // Но так как мы потом сортируем — порядок обхода не важен.
                for (int i = 0; i < 26; i++) {
                    if (current.children[i] != null) {
                        stack.push(current.children[i]);
                    }
                }
            }

            // Сортируем лексикографически (как CompareOrdinal в C#)
            result.sort(String::compareTo);

            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Trie trie = new Trie();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            trie.add(br.readLine());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            String request = br.readLine();
            List<String> results = trie.search(request);
            for (String word : results) {
                System.out.println(word);
            }
        }
    }
}
