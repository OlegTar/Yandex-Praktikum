import java.util.*;
import java.io.*;
import java.lang.*;

public class Solution {
    public static void main(String[] args) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            int n = Integer.parseInt(reader.readLine());

            MinHeap<Element> priorityQueue = new MinHeap<Element>();

            for (int i = 0; i < n; i++) {
                String line = reader.readLine();
                String[] parts = line.split(" ");
                String name = parts[0];
                int p = Integer.parseInt(parts[1]);
                int f = Integer.parseInt(parts[2]);
                Element element = new Element(name, p, f);
                priorityQueue.add(element);
            }

            Element out = priorityQueue.pop();
            while (out != null) {
                writer.write(out.getName());
                writer.newLine();
                out = priorityQueue.pop();
            }
            writer.flush();
        } catch (Exception ignored) {
        }
    }

    public static class Element implements Comparable<Element> {
        private String name;
        private int p;
        private int f;

        public String getName() {
            return name;
        }

        public int getP() {
            return p;
        }

        public int getF() {
            return f;
        }

        public Element(String name, int p, int f) {
            this.name = name;
            this.p = p;
            this.f = f;
        }

        public int compareTo(Element other) {
            if (p > other.getP()) {
                return -1;
            } else if (p < other.getP()) {
                return 1;
            }

            if (f < other.getF()) {
                return -1;
            } else if (f > other.getF()) {
                return 1;
            }

            return name.compareTo(other.getName());
        }
    }

    public static class MinHeap<T extends Comparable<T>> {
        private final List<T> heap = new ArrayList<>();

        public MinHeap() {
            heap.add(null);
        }

        public void add(T Element) {
            heap.add(Element);
            siftUp(heap.size() - 1);
        }

        public T pop() {
            if (heap.size() == 1) {
                return null;
            }

            T result = heap.get(1);

            heap.set(1, heap.getLast());
            heap.removeLast();
            siftDown(1);

            return result;
        }

        private void siftUp(int index) {
            if (index == 1) {
                return;
            }

            int parentIndex = index / 2;

            if (heap.get(parentIndex).compareTo(heap.get(index)) > 0) {
                T temp = heap.get(index);
                heap.set(index, heap.get(parentIndex));
                heap.set(parentIndex, temp);

                siftUp(parentIndex);
            }
        }

        private void siftDown(int index) {
            int leftIndex = index * 2;
            int rightIndex = leftIndex + 1;

            if (leftIndex >= heap.size()) {
                return;
            }

            int indexOfSmallest = leftIndex;
            if (rightIndex <= heap.size() - 1) {
                if (heap.get(leftIndex).compareTo(heap.get(rightIndex)) > 0) {
                    indexOfSmallest = rightIndex;
                }
            }

            if (heap.get(index).compareTo(heap.get(indexOfSmallest)) > 0) {
                T temp = heap.get(index);
                heap.set(index, heap.get(indexOfSmallest));
                heap.set(indexOfSmallest, temp);

                siftDown(indexOfSmallest);
            }
        }
    }
}
