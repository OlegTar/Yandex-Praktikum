import java.io.*;
import java.util.*;

public class Main {
    private static int[][] memo;
    private static final int[][] movements = new int[][]{
            new int[]{-1, 0},
            new int[]{0, 1},
            new int[]{1, 0},
            new int[]{0, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        memo = new int[n][m];

        long[][] matrix = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextLong();
            }
        }

        int result = 1;
        Set<Integer> coords = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                coords.add(i * m + j);

                result = Math.max(result, dfs(i, j, matrix));

                coords.remove(i * m + j);
            }
        }
        System.out.println(result);
    }

    public static int dfs(int i, int j, long[][] matrix) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int result = 1;
        for (int[] move : movements) {
            int newI = i + move[0];
            int newJ = j + move[1];

            if (newI < 0 || newI >= matrix.length || newJ < 0 || newJ >= matrix[0].length) {
                continue;
            }

            if (matrix[newI][newJ] <= matrix[i][j]) {
                continue;
            }

//            if (coords.contains(newI * matrix[0].length + newJ)) {
//                continue;
//            }
//
//            coords.add(newI * matrix[0].length + newJ);

            result = Math.max(result, 1 + dfs(newI, newJ, matrix));

            //coords.remove(newI * matrix[0].length + newJ);
        }

        memo[i][j] = result;

        return result;
    }
}
