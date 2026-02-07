import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<int[]> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int mi = scanner.nextInt();
            int ci = scanner.nextInt();
            items.add(new int[] { mi, ci });
        }

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }
        //dp[i][j] - максимальная стоимость i предметов при вместимости рюкзака j
        for (int i = 1; i <= n; i++) {
            int mi = items.get(i - 1)[0];
            int ci = items.get(i - 1)[1];

            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(
                        dp[i - 1][j],//не берем i-ый предмет
                        (j - mi >= 0 ? dp[i - 1][j - mi] : Integer.MIN_VALUE) + ci//берем i-ый предмет
                );
            }
        }

        // Найти лучшее значение и соответствующий вес
        int bestValue = Integer.MIN_VALUE;
        int bestW = 0;
        for (int w = 0; w <= m; w++) {
            if (dp[n][w] > bestValue) {
                bestValue = dp[n][w];
                bestW = w;
            }
        }

        // Если лучшая ценность отрицательна — ничего не берём
        if (bestValue < 0) {
            System.out.println(0);
            return;
        }

        // Восстановление выбранных предметов
        List<Integer> selected = new ArrayList<>();
        int w = bestW;
        for (int i = n; i >= 1; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selected.add(i); // нумерация с 1
                int mi = items.get(i - 1)[0];
                w -= mi;
            }
        }

        Collections.reverse(selected);

        System.out.println(selected.size());
        if (!selected.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selected.size(); i++) {
                if (i > 0) sb.append(" ");
                sb.append(selected.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}
