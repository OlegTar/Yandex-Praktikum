import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] ratings = new int[n];
        for (int i = 0; i < n; i++) {
            ratings[i] = scanner.nextInt();
        }

        //1, 100, 1, 2, 3, 1, 1


        // dp[i] — длина LIS, заканчивающейся в позиции i
        int[] dp = new int[n];
        // prev[i] — предыдущий индекс в LIS, заканчивающейся в i
        int[] prev = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (ratings[j] < ratings[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }

        // Находим индекс с максимальной длиной LIS
        int maxLen = dp[0];
        int maxIdx = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        // Восстанавливаем путь
        List<Integer> path = new ArrayList<>();
        int cur = maxIdx;
        while (cur != -1) {
            path.add(cur);
            cur = prev[cur];
        }
        Collections.reverse(path);

        // Переводим в 1-индексацию
        StringBuilder sb = new StringBuilder();
        for (int idx : path) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(idx + 1);
        }

        System.out.println(maxLen);
        System.out.println(sb.toString());
    }
}
