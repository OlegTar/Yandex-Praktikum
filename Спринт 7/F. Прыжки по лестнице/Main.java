import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] dp = new int[n];
        d`p[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k && (i - j) >= 0; j++) {
                dp[i] += dp[i - j];
                dp[i] %= 1e9 + 7;
            }
        }

        System.out.println(dp[n - 1]);
    }
}
