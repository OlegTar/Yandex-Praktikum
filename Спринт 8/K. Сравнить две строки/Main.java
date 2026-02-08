import java.util.*;

public class Program
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String b = scanner.nextLine();

        String normalizedA = a.chars()
                .filter(c -> (c - 'a' + 1) % 2 == 0)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String normalizedB = b.chars()
                .filter(c -> (c - 'a' + 1) % 2 == 0)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(normalizedA.compareTo(normalizedB) < 0 ? -1 : normalizedA.compareTo(normalizedB) > 0 ? 1 : 0);
    }
}
