import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();

        int[][] field = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                field[i][j] = line.charAt(j) - '0';
            }
        }

        int[][] dp = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                dp[i][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]) + field[i][j];
            }
        }

        List<Character> path = new ArrayList<>();
        int currentI = 0;
        int currentJ = m;
        int iEnd = n - 1;
        while (currentI < iEnd && currentJ > 1) {
            if (dp[currentI + 1][currentJ] > dp[currentI][currentJ - 1]) {
                currentI++;
                path.add('U');
            } else {
                currentJ--;
                path.add('R');
            }
        }

        if (currentJ == 1) {
            for (int i = currentI; i < iEnd ; i++) {
                path.add('U');
            }
        } else {
            for (int i = currentJ; i > 1; i--) {
                path.add('R');
            }
        }

        System.out.println(dp[0][m]);
        Collections.reverse(path);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
        }
        System.out.println();
    }
}
