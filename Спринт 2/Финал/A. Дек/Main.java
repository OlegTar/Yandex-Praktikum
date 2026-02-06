import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        Dequeue dequeue = new Dequeue(n);
        scanner.nextLine();

        for (int i = 0; i < m; i++) {
            String line = scanner.nextLine();
            if (line.equals("pop_back")) {
                int element = dequeue.popBack();
            	System.out.println(element);
            } else if (line.equals("pop_front")) {
                int element = dequeue.popFront();
            	System.out.println(element);
            } else if (line.startsWith("push_back")) {
                int number = Integer.parseInt(line.split(" ")[1], 10);
                dequeue.pushBack(number);
            } else {
                int number = Integer.parseInt(line.split(" ")[1], 10);
                dequeue.pushFront(number);
            }
        }

    }

    public static class Dequeue {
        private int[] queue;
        private int head = 0;
        private int tail = 0;
        private int maxSize = 0;
        private int size = 0;

        public Dequeue(int maxSize) {
            this.maxSize = maxSize;
            queue = new int[this.maxSize];
        }

        public void pushBack(int x) {
            if (size == maxSize) {
                System.out.println("error");
                return;
            }

            if (size > 0) {
                tail = (tail + 1) % maxSize;
            }
            queue[tail] = x;
            size++;
        }

        public void pushFront(int x) {
            if (size == maxSize) {
                System.out.println("error");
                return;
            }

            if (size > 0) {
                head = (head - 1 + maxSize) % maxSize;
            }
            queue[head] = x;
            size++;
        }

        public int popBack() {
            if (size == 0) {
                System.out.println("error");
                return;
            }
            if (size > 1) {
                tail = (tail - 1 + maxSize) % maxSize;
            }
            size--;
        	return queue[tail];
        }

        public int popFront() {
            if (size == 0) {
                System.out.println("error");
                return;
            }
            if (size > 1) {
                head = (head + 1) % maxSize;
            }
            size--;
        	return queue[head];
        }
    }
}
