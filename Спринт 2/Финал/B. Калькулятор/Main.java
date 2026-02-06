import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        Stack stack = new Stack();//или использовать дефолтный
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        for (String part : parts) {
            if (part.equals("+") || part.equals("-") || part.equals("*") || part.equals("/")) {
                int operandB = stack.pop();
                int operandA = stack.pop();
                if (part.equals("+")) {
                    stack.push(operandA + operandB);
                }
                if (part.equals("-")) {
                    stack.push(operandA - operandB);
                }
                if (part.equals("*")) {
                    stack.push(operandA * operandB);
                }
                if (part.equals("/")) {
                    if (operandA < 0) {
                        int result = operandA / operandB;
                        if (result * operandB > operandA) {
                            result--;
                        }
                        stack.push(result);
                    } else {
                        stack.push(operandA / operandB);
                    }
                }
            } else {
                int x = Integer.parseInt(part);
                stack.push(x);
            }
        }
        System.out.println(stack.peek());
    }

    public static class Stack {
        private final List<Integer> stack = new ArrayList<>();

        public void push(int x) {
            stack.add(x);
        }

        public int pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return stack.removeLast();
        }

        public int peek() {
            if (stack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return stack.getLast();
        }
    }
}
