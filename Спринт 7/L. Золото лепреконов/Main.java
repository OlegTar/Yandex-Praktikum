import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int M = sc.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }

        int[] dp = new int[M + 1]; // dp[j] = макс. вес, который можно унести при вместимости j

        for (int i = 0; i < n; i++) {
            for (int j = M; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + w[i]);
            }
        }

        System.out.println(dp[M]);
    }
}
