import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        final int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[n + 1][n + 1];
        boolean[][] used = new boolean[n + 1][n + 1];
        int[][] prevC = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int c = 0; c <= n; c++) {
                if (dp[i][c] == INF) continue;

                int newC = c + (prices[i] > 500 ? 1 : 0);
                if (newC <= n && dp[i][c] + prices[i] < dp[i + 1][newC]) {
                    dp[i + 1][newC] = dp[i][c] + prices[i];
                    used[i + 1][newC] = false;
                    prevC[i + 1][newC] = c;
                }

                if (c > 0 && dp[i][c] < dp[i + 1][c - 1]) {
                    dp[i + 1][c - 1] = dp[i][c];
                    used[i + 1][c - 1] = true;
                    prevC[i + 1][c - 1] = c;
                }
            }
        }

        int minCost = INF;
        int bestC = -1;
        for (int c = 0; c <= n; c++) {
            if (dp[n][c] < minCost) {
                minCost = dp[n][c];
                bestC = c;
            }
        }

        List<Integer> usedDays = new ArrayList<>();
        int currentC = bestC;
        for (int i = n; i >= 1; i--) {
            if (used[i][currentC]) {
                usedDays.add(i);
            }
            currentC = prevC[i][currentC];
        }
        Collections.reverse(usedDays);

        System.out.println(minCost + " " + usedDays.size());
        for (int i = 0; i < usedDays.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(usedDays.get(i));
        }
        if (!usedDays.isEmpty()) {
            System.out.println();
        }
    }
}
