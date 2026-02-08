import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");
        Collections.reverse(Arrays.asList(words));
        System.out.println(String.join(" ", words));
    }
}
