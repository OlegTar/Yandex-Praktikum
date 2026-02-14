import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n % 2 != 0) {
            return;
        }
        List<String> combinations = new ArrayList<>();
        solve(n, 0, 0, new StringBuilder(), combinations);

        for (String comb : combinations) {
            System.out.println(comb);
        }
    }

    public static void solve(int n,
                             int openRoundBrackets,
                             int openSquareBrackets,
                             StringBuilder sb,
                             List<String> combinations) {
        if (n == 0 && openRoundBrackets == 0 && openSquareBrackets == 0) {
            combinations.add(sb.toString());
            return;
        }

        if (openSquareBrackets > 0) {
            if (n > 1) {
                sb.append('[');
                solve(n - 1,
                        openRoundBrackets,
                        openSquareBrackets + 1, sb,
                        combinations);
                sb.setLength(sb.length() - 1);
            }

            sb.append(']');
            solve(n - 1,
                    openRoundBrackets,
                    openSquareBrackets - 1, sb,
                    combinations);
            sb.setLength(sb.length() - 1);
            return;
        }

        if (n > 1) {
            sb.append('(');
            solve(n - 1,
                    openRoundBrackets + 1,
                    openSquareBrackets, sb,
                    combinations);
            sb.setLength(sb.length() - 1);

            sb.append('[');
            solve(n - 1,
                    openRoundBrackets,
                    openSquareBrackets + 1, sb,
                    combinations);
            sb.setLength(sb.length() - 1);

        }

        if (openRoundBrackets > 0) {
            sb.append(')');
            solve(n - 1,
                    openRoundBrackets - 1,
                    openSquareBrackets, sb,
                    combinations);
            sb.setLength(sb.length() - 1);
        }
    }
}
