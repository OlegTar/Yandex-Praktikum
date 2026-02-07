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

    	/*int[][] dp = new int[n][m];
    	for (int i = n - 1; i >= 0; i--) {
        	for (int j = 0; j < m; j++) {
        		dp[i][j] = Math.max(i + 1 < n ? dp[i + 1][j] : 0, j - 1 >= 0 ? dp[i][j - 1] : 0) + field[i][j];
            }
        }*/

        System.out.println(dp[0][m]);
    }
}
