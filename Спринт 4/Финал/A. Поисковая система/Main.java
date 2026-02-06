import java.io.*;
import java.util.*;

public class Main {
    private static final int RESULT_CAPACITY = 5;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            int n = Integer.parseInt(reader.readLine());
            Map<String, List<Integer>> glossary = new HashMap<>();
            Map<String, Integer>[] counts = new HashMap[n];
            for (var i = 0; i < n; i++) {
                counts[i] = new HashMap<>();
                String document = reader.readLine();

                for (String word : document.split(" ")) {
                    if (!counts[i].containsKey(word)) {
                        counts[i].put(word, 0);
                    }
                    counts[i].put(word, counts[i].get(word) + 1);

                    if (!glossary.containsKey(word)) {
                        glossary.put(word, new ArrayList<>());
                    }

                    if ((long) glossary.get(word).size() == 0 || glossary.get(word).getLast() != i) {
                        glossary.get(word).add(i);
                    }
                }
            }

            int m = Integer.parseInt(reader.readLine());
            for (int i = 0; i < m; i++) {
                int[][] relevant = new int[n][];
                for (int documentNumber = 0; documentNumber < n; documentNumber++) {
                    relevant[documentNumber] = new int[]{documentNumber + 1, 0};
                }

                Set<String> usedWords = new HashSet<>();
                String request = reader.readLine();

                for (String word : request.split(" ")) {
                    if (usedWords.contains(word)) continue;
                    usedWords.add(word);
                    if (!glossary.containsKey(word)) continue;
                    for (int documentNumber : glossary.get(word)) {
                        if (counts[documentNumber].containsKey(word)) {
                            relevant[documentNumber][1] += counts[documentNumber].get(word);
                        }

                    }
                }

                LinkedList result = new LinkedList(RESULT_CAPACITY);
                for (int[] valueAndN : relevant) {
                    result.add(valueAndN[1], valueAndN[0]);
                }

                for (int[] document : result.getDocuments()) {
                    if (document[0] == 0) {
                        break;
                    }
                    writer.write(Integer.toString(document[1]));
                    writer.write(" ");
                }
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static class LinkedList {
        private int capacity;
        private int len = 0;
        private int value;//релевантность
        private int n;//номер документа
        private LinkedList prev;
        private LinkedList next;
        private LinkedList head;
        private LinkedList tail;
        private int minValue = -1;//используется для определения, а вставлять ли элемент, если capacity заполнено

        public LinkedList() {
        }

        public LinkedList(int capacity) {
            this.capacity = capacity;
            head = new LinkedList();
            tail = head;
        }

        public void add(int value, int n) {
            if (value < minValue && len == capacity) {
                return;
            }

            LinkedList prev = head;
            LinkedList node = head.next;
            boolean inserted = false;
            while (node != null) {
                if (value > node.value || (value == node.value && n < node.n)) {
                    LinkedList newNode = new LinkedList();
                    newNode.value = value;
                    newNode.n = n;
                    newNode.prev = prev;
                    prev.next = newNode;
                    newNode.next = node;
                    node.prev = newNode;
                    len++;
                    inserted = true;
                    break;
                }
                prev = node;
                node = node.next;
            }

            if (len > capacity) {
                tail = tail.prev;
                tail.next = null;
                len--;//возвращаем длину до capacity
            } else if (!inserted && len < capacity) {//не нашли место для вставки, значит вставляем в конец
                LinkedList newNode = new LinkedList();
                newNode.value = value;
                newNode.n = n;
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
                len++;
            }
            minValue = Math.min(minValue, value);
        }

        public int[][] getDocuments() {
            int[][] result = new int[len][];
            LinkedList node = head.next;
            int i = 0;
            while (node != null) {
                result[i++] = new int[]{node.value, node.n};
                node = node.next;
            }
            return result;
        }
    }
}
