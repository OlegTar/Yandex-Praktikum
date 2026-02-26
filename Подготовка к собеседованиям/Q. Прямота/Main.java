import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    // Класс для быстрого ввода данных
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
        
        // Чтение количества точек
        String nStr = scanner.next();
        if (nStr == null) return;
        int n = Integer.parseInt(nStr);

        long[] x = new long[n];
        long[] y = new long[n];

        // Чтение координат
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextLong();
            y[i] = scanner.nextLong();
        }

        // Если точек 2 или меньше, они всегда на одной прямой
        if (n <= 2) {
            System.out.println("YES");
            return;
        }

        // Находим первую точку, отличную от первой (индекс 0),
        // чтобы задать вектор направления прямой.
        int baseIndex = -1;
        for (int i = 1; i < n; i++) {
            if (x[i] != x[0] || y[i] != y[0]) {
                baseIndex = i;
                break;
            }
        }

        // Если все точки совпадают с первой, значит все они в одной точке,
        // что технически удовлетворяет условию (лежат на любой прямой через эту точку).
        if (baseIndex == -1) {
            System.out.println("YES");
            return;
        }

        // Вектор от точки 0 до точки baseIndex
        long dx1 = x[baseIndex] - x[0];
        long dy1 = y[baseIndex] - y[0];

        // Используем BigInteger для предотвращения переполнения при умножении
        // (координаты до 10^12, произведение до 10^24, long вмещает до 9*10^18)
        BigInteger bigDx1 = BigInteger.valueOf(dx1);
        BigInteger bigDy1 = BigInteger.valueOf(dy1);

        // Проверяем все остальные точки
        for (int i = 1; i < n; i++) {
            // Точку, которая задала направление, можно пропустить, но проверка ей не повредит
            // if (i == baseIndex) continue; 

            long dx2 = x[i] - x[0];
            long dy2 = y[i] - y[0];

            // Вычисляем векторное произведение: dx1 * dy2 - dy1 * dx2
            // Если оно не равно 0, точки не коллинеарны
            BigInteger term1 = bigDx1.multiply(BigInteger.valueOf(dy2));
            BigInteger term2 = bigDy1.multiply(BigInteger.valueOf(dx2));

            if (!term1.equals(term2)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}
