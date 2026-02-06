import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            int n = Integer.parseInt(reader.readLine());
            MyHash hash = new MyHash();
            for (int i = 0; i < n; i++) {
                String request = reader.readLine();
                String[] parts = request.split(" ");
                String command = parts[0];
                int key = Integer.parseInt(parts[1]);
                switch (command) {
                    case "get":
                        writer.write(hash.get(key));
                        writer.newLine();
                        break;
                    case "put":
                        int value = Integer.parseInt(parts[2]);
                        hash.put(key, value);
                        break;
                    case "delete":
                        writer.write(hash.delete(key));
                        writer.newLine();
                        break;
                }
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static class MyHash {
        public final int BUCKET_SIZE = 100007;
        public LinkedList[] buckets = new LinkedList[BUCKET_SIZE];

        public MyHash() {

        }

        public void put(int key, int value) {
            int bucketNumber = hash(key);
            if (buckets[bucketNumber] == null) {
                LinkedList newNode = new LinkedList(key, value);
                buckets[bucketNumber] = newNode;
                buckets[bucketNumber].tail = newNode;//newNode.tail - сам на себя
            } else {
                LinkedList node = buckets[bucketNumber];
                while (node != null) {
                    if (node.key == key) {
                        node.value = value;
                        return;
                    }
                    node = node.next;
                }
                buckets[bucketNumber].tail.next = new LinkedList(key, value);
                buckets[bucketNumber].tail = buckets[bucketNumber].tail.next;//смещаем хвост
            }
        }

        public String delete(int key) {
            int bucketNumber = hash(key);
            if (buckets[bucketNumber] == null) {
                return "None";
            }

            LinkedList node = buckets[bucketNumber];
            LinkedList prev = null;
            while (node != null) {
                if (node.key == key) {
                    if (prev == null) {
                        //первый элемент в связном списке
                        LinkedList tail = buckets[bucketNumber].tail;
                        buckets[bucketNumber] = node.next;//выкидываем первый элемент
                        if (node.next != null) {// это был один элемент
                            buckets[bucketNumber].tail = tail;
                        }
                    } else {
                        prev.next = node.next;//выкидываем элемент посередине
                    }
                    return Integer.toString(node.value);
                }
                prev = node;
                node = node.next;
            }
            return "None";

        }

        public String get(int key) {
            int bucketNumber = hash(key);
            if (buckets[bucketNumber] == null) {
                return "None";
            }

            LinkedList node = buckets[bucketNumber];
            while (node != null) {
                if (node.key == key) {
                    return Integer.toString(node.value);
                }
                node = node.next;
            }
            return "None";
        }

        private int hash(int key) {
            return Math.abs(key) % buckets.length;
        }
    }

    public static class LinkedList {
        private int value;
        private int key;
        private LinkedList next;
        private LinkedList tail;

        public LinkedList(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
