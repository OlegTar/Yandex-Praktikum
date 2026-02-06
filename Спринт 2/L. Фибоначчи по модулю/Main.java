import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        System.out.println(Fibonacci(n, k));
    }

    public static int Fibonacci(int n, int k) {
        if (n <= 1) {
            return 1;
        }
        int divider = (int) Math.pow(10, k);
        int prev = 1;
        int prevPrev = 1;
        int current = 0;
        for (int i = 2; i <= n; i++) {
            current = (prev + prevPrev) % divider;
            prevPrev = prev;
            prev = current;
        }
        return current;
    }
}
