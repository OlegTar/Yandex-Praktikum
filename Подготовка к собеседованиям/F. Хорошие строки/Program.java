import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
    	/*if (s.length() == 0) {
    		System.out.println("");
        	return;
        }*/

        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (!stack.isEmpty() && s.charAt(i) != stack.peek()
                    && Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(stack.peek())
            ) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        String res = sb.reverse().toString();
        System.out.println(res);
    }
}
