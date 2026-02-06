import java.io.*;
import java.util.*;

public class Main {
    private static int[][] movements = {
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1}
    };

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            String[] parts = line.split(" ");
            int n = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);

            int count = 0;
            int[][] field = new int[n][m];
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                for (int j = 0; j < line.length(); j++) {
                    if (line.charAt(j) == '.') {
                        field[i][j] = 0;
                    } else {
                        field[i][j] = ++count;
                    }
                }
            }

            if (count == 0) {
                System.out.println("0 0");
                return;
            }

            int[] colors = new int[count + 1];
            Arrays.fill(colors, -1);
            int componentColor = 0;
            int maxIsland = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (field[i][j] == 0 || colors[field[i][j]] != -1) {
                        continue;
                    }

                    // Итеративный DFS
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{i, j});
                    int size = 0;

                    while (!stack.isEmpty()) {
                        int[] cell = stack.pop();
                        int r = cell[0], c = cell[1];

                        if (r < 0 || r >= n || c < 0 || c >= m) continue;
                        if (field[r][c] == 0) continue;

                        int vertex = field[r][c];
                        if (colors[vertex] != -1) continue;

                        colors[vertex] = componentColor;
                        size++;

                        for (int[] move : movements) {
                            stack.push(new int[]{r + move[0], c + move[1]});
                        }
                    }

                    maxIsland = Math.max(maxIsland, size);
                    componentColor++;
                }
            }

            System.out.printf("%d %d%n", componentColor, maxIsland);
        } catch (Exception ignored) {
        }
    }
}
