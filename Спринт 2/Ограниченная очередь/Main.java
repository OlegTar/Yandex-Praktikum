import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        int size = scanner.nextInt();
        scanner.nextLine();
        MyQueueSized queue = new MyQueueSized(size);

        for (int i = 0; i < n; i++) {
            String command = scanner.nextLine();
            switch (command) {
                case "peek" -> queue.peek();
                case "pop" -> queue.pop();
                case "size" -> queue.size();
                default -> {
                    int number = Integer.parseInt(command.split(" ")[1], 10);
                    queue.push(number);
                }
            }
        }
    }

    public static class MyQueueSized {
        private int[] queue;
        private int head = 0;
        private int tail = 0;
        private int size = 0;
        private int maxSize = 0;

        public MyQueueSized(int maxSize) {
            this.maxSize = maxSize;
            queue = new int[maxSize];
        }

        public void push(int x) {
            if (size == maxSize) {
                System.out.println("error");
                return;
            }
            queue[tail] = x;
            tail = (tail + 1) % maxSize;
            size++;
        }

        public void pop() {
            if (size == 0) {
                System.out.println("None");
                return;
            }
            System.out.println(queue[head]);
            head = (head + 1) % maxSize;
            size--;
        }

        public void peek() {
            if (size == 0) {
                System.out.println("None");
                return;
            }
            System.out.println(queue[head]);
        }

        public void size() {
            System.out.println(size);
        }
    }
}
