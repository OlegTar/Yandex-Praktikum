import java.util.*;

public class SameSums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] scores = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
            sum += scores[i];
        }

        if (sum % 2 != 0) {
            System.out.println("False");
            return;
        }

        int half = sum / 2;

        boolean[] dp = new boolean[half + 1];
        dp[0] = true;

        for (int score : scores) {
            for (int i = half; i >= score; i--) {
                if (dp[i - score]) {
                    dp[i] = true;
                }
            }
        }

        System.out.println(dp[half] ? "True" : "False");
    }
}
