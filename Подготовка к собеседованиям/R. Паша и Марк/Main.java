import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            
            // Если число четное, выигрывает Паша.
            // Если число нечетное, выигрывает Марк.
            if (n % 2 == 0) {
                System.out.println("Pasha");
            } else {
                System.out.println("Mark");
            }
        }
        
        scanner.close();
    }
}
