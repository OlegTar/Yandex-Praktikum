import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(System.getProperty("user.dir"));
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        StackMaxEffective stack = new StackMaxEffective();
        int m = scanner.nextInt();
        scanner.nextLine();
        for (var i = 0; i < m; i++) {
            String line = scanner.nextLine();
            //System.out.println(String.format("line = %s", line));
            if (line.equals("get_max")) {
                stack.get_max();
            } else if (line.equals("pop")) {
                stack.pop();
            } else if (line.equals("top")) {
                stack.top();
            } else {
                int n = Integer.parseInt(line.split(" ")[1], 10);
                stack.push(n);
            }
        }
    }

    public static class StackMaxEffective {
        private final Stack<Integer> stack = new Stack<>();
        private final Stack<Integer> maxes = new Stack<>();

        public void push(int x) {
            if (maxes.isEmpty() || maxes.peek() <= x) {
                maxes.push(x);
            }
            stack.push(x);
        }

        public void pop() {
            if (stack.isEmpty()) {
                System.out.println("error");
                return;
            }
            int x = stack.pop();
            if (x == maxes.peek()) {
                maxes.pop();
            }
        }

        public void top() {
            if (stack.isEmpty()) {
                System.out.println("error");
                return;
            }
            System.out.println(stack.peek());
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
