import java.io.*;
import java.util.*;

public class Solution {

    private static Boolean[][] memo;
    private static String s, p;

    private static boolean match(int i, int j) {
        // Оба достигли конца — совпадение
        if (i == s.length() && j == p.length()) return true;

        // Шаблон закончился, а строка — нет
        if (j == p.length()) return false;

        // Строка закончилась, но в шаблоне остался только '*'
        if (i == s.length()) {
            for (int k = j; k < p.length(); k++) {
                if (p.charAt(k) != '*') return false;
            }
            return true;
        }

        // Проверка кэша
        if (memo[i][j] != null) return memo[i][j];

        char pc = p.charAt(j);
        boolean res;

        if (pc == '*') {
            // '*' может соответствовать пустой строке или одному/нескольким символам
            res = match(i, j + 1) || match(i + 1, j);
        } else if (pc == '?' || pc == s.charAt(i)) {
            res = match(i + 1, j + 1);
        } else {
            res = false;
        }

        memo[i][j] = res;
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String template = reader.readLine().strip();
        String stringToCheck = reader.readLine().strip();

        s = stringToCheck;
        p = template;
        memo = new Boolean[s.length() + 1][p.length() + 1];

        System.out.println(match(0, 0) ? "YES" : "NO");
    }
}
