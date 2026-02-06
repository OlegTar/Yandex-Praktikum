import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        System.out.println(is_correct_bracket_seq(line) ? "True" : "False");
    }

    public static boolean is_correct_bracket_seq(String line) {
        Stack<Character> stack = new Stack<>();
        for (char c : line.toCharArray()) {
            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char lastOpenBracket = stack.pop();
                if (lastOpenBracket == '(' && c != ')') {
                    return false;
                }
                if (lastOpenBracket == '{' && c != '}') {
                    return false;
                }
                if (lastOpenBracket == '[' && c != ']') {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
