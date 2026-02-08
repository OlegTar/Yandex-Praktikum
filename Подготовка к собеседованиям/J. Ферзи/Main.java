import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        List<int[]> solutions = new ArrayList<>();
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n];     // row - col + n (чтобы избежать отрицательных индексов)
        boolean[] diag2 = new boolean[2 * n];     // row + col

        backtrack(0, n, new int[n], cols, diag1, diag2, solutions);

        // Вывод
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        pw.println(solutions.size());
        for (int[] sol : solutions) {
            for (int i = 0; i < n; i++) {
                if (i > 0) pw.print(" ");
                pw.print(sol[i] + 1); // перевод в 1-индексацию
            }
            pw.println();
        }
        pw.flush();
    }

    static void backtrack(int row, int n, int[] queens,
                          boolean[] cols,
                          boolean[] diag1,
                          boolean[] diag2,
                          List<int[]> solutions) {
        if (row == n) {
            solutions.add(queens.clone());
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n; // сдвиг, чтобы индекс был >= 0
            int d2 = row + col;

            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // ставим ферзя
            queens[row] = col;
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            backtrack(row + 1, n, queens, cols, diag1, diag2, solutions);

            // убираем ферзя (backtrack)
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}
