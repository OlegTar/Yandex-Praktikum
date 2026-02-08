import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        Map<String, Integer> convert = new HashMap<>();
        convert.put("M", 1000);
        convert.put("CM", 900);
        convert.put("D", 500);
        convert.put("CD", 400);
        convert.put("C", 100);
        convert.put("XC", 90);
        convert.put("L", 50);
        convert.put("XL", 40);
        convert.put("X", 10);
        convert.put("IX", 9);
        convert.put("V", 5);
        convert.put("IV", 4);
        convert.put("I", 1);

        int order = 1000;

        int iCount = 0;
        int xCount = 0;
        int mCount = 0;
        int cCount = 0;

        int result = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            StringBuilder sb = new StringBuilder();
            sb.append(ch);
            String romeDigit = sb.toString();

            if (i <= (n - 2)) {
                sb.append(s.charAt(i + 1));
                if (convert.containsKey(sb.toString())) {
                    romeDigit = sb.toString();
                    i++;
                }
            }

            if (!convert.containsKey(romeDigit)) {
                result = -1;
                break;
            }

            if (convert.get(romeDigit) > order) {
                result = -1;
                break;
            }

            if (romeDigit.equals("I")) iCount++;
            if (romeDigit.equals("X")) xCount++;
            if (romeDigit.equals("C")) cCount++;
            if (romeDigit.equals("M")) mCount++;

            if (iCount > 3 || xCount > 3 || cCount > 3 || mCount > 3) {
                result = -1;
                break;
            }

            if (romeDigit.equals("CM")) {
                order = 500;
            } else if (romeDigit.equals("D") || romeDigit.equals("CD") || romeDigit.equals("C")) {
                order = 100;
            } else if (romeDigit.equals("XC")) {
                order = 50;
            } else if (romeDigit.equals("L") || romeDigit.equals("XL") || romeDigit.equals("X")) {
                order = 10;
            } else if (romeDigit.equals("IX")) {
                order = 5;
            } else if (romeDigit.equals("V") || romeDigit.equals("I")) {
                order = 1;
            } else if (romeDigit.equals("IV")) {
                order = 0;
            }

            result += convert.get(romeDigit);

        }

        System.out.println(result);
    }
}
