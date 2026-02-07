import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        //dp[i][j] a[1..i] b[1..j] НОП от 1..i до 1..j

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        List<Integer> pathA = new ArrayList<>();
        List<Integer> pathB = new ArrayList<>();

        int len = dp[n][m];
        int i = n;
        int j = m;

        while (pathA.size() < len) {
            if (a[i - 1] == b[j - 1]) {
                pathA.add(i);
                pathB.add(j);
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        Collections.reverse(pathA);
        Collections.reverse(pathB);

        System.out.println(dp[n][m]);
        for (int ai : pathA) {
            System.out.printf("%d ", ai);
        }
        System.out.println();
        for (int bi : pathB) {
            System.out.printf("%d ", bi);
        }
        System.out.println();
    }

}
