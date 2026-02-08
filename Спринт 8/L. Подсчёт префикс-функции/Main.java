import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int[] pi = new int[s.length()];
        int n = s.length();

        for (int i = 1; i < n; i++) {
            int k = pi[i - 1];

            while (k > 0 && s.charAt(i) != s.charAt(k)) {
                k = pi[k - 1];
            }

            if (s.charAt(i) == s.charAt(k)) {
                k++;
            }

            pi[i] = k;
        }

        StringBuilder sb = new StringBuilder();
        for (int k : pi) {
            sb.append(Integer.toString(k));
            sb.append(" ");
        }

        sb.setLength(sb.length() - 1);
        System.out.println(sb.toString());
    }
}
