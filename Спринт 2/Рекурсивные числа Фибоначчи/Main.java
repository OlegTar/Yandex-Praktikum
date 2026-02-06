import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        System.out.println(Fibonacci(n));
    }

    public static int Fibonacci(int n) {
        if (n <= 1) {
            return 1;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }
}
