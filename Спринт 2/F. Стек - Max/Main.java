import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(System.getProperty("user.dir"));
        //File file = new File("input.txt");
        Scanner scanner = new Scanner(System.in);
        StackMax stack = new StackMax();
        int m = scanner.nextInt();
        scanner.nextLine();
        for (var i = 0; i < m; i++)
        {
            String line = scanner.nextLine();
            //System.out.println(String.format("line = %s", line));
            if (line.equals("get_max")) {
                stack.get_max();
            } else if (line.equals("pop")) {
                stack.pop();
            } else {
                int n = Integer.parseInt(line.split(" ")[1], 10);
                stack.push(n);
            }
        }
    }

    public static class StackMax {
        private final Stack<Integer> stack = new Stack<>();
        private final Stack<Integer> maxes = new Stack<>();

        public void push(int x) {
            if (maxes.isEmpty() || maxes.peek() <= x) {
                maxes.push(x);
            }
            stack.push(x);
        }

        public void pop() {
            if (stack.isEmpty()){
                System.out.println("error");
                return;
            }
            int x = stack.pop();
            if (x == maxes.peek()) {
                maxes.pop();
            }
        }

        public void get_max() {
            if (maxes.isEmpty()) {
                System.out.println("None");
                return;
            }
            System.out.println(maxes.peek());
        }
    }
}
