import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        int[] abc = new int[26];

        for (char ch : text.toCharArray()) {
            abc[ch - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < abc.length; i++) {
            if (abc[i] == 0)
            {
                continue;
            }

            if (abc[i] > 1) {
                int half = abc[i] / 2;
                for (int j = 0; j < half; j++) {
                    sb.append((char)(i + 'a'));
                }
                abc[i] -= half * 2;
            }
        }

        String firstHalf = sb.toString();
        StringBuilder sb2 = new StringBuilder(firstHalf);
        sb2.reverse();

        for (int i = 0; i < abc.length; i++) {
            if (abc[i] == 1) {
                sb.append((char)(i + 'a'));
                break;
            }
        }

        sb.append(sb2.toString());

        System.out.println(sb.toString());
    }
}
