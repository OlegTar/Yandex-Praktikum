import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        // Начальная сумма: k карт справа
        long currentSum = 0;
        for (int i = n - k; i < n; i++) {
            currentSum += cards[i];
        }

        long maxSum = currentSum;

        // Теперь пробуем брать 1, 2, ..., k карт слева
        // На i-м шаге: берём i карт слева, (k - i) карт справа
        for (int i = 1; i <= k; i++) {
            // Убираем самую левую из правых (она была на позиции n - k + i - 1)
            // Добавляем i-ю карту слева (индекс i - 1)
            currentSum = currentSum - cards[n - k + i - 1] + cards[i - 1];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        System.out.println(maxSum);
    }
}
