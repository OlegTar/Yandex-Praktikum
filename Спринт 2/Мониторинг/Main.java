import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        var matrix = new int[n][];
        for (var i = 0; i < matrix.length; i++) {
            matrix[i] = new int[m];
            for (var j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        var otherMatrix = new int[m][];
        for (var i = 0; i < otherMatrix.length; i++) {
            otherMatrix[i] = new int[n];
        }

        for (var i = 0; i < n; i++) {
            for (var j = 0; j < m; j++) {
                otherMatrix[j][i] = matrix[i][j];
            }
        }

        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                writer.print(otherMatrix[i][j]);
                writer.print(" ");
            }
            writer.println();
        }
        writer.flush();
    }
}
