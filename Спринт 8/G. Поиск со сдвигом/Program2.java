import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] a = new int[m];

        for (int i = 0; i < m; i++) {
            a[i]  = scanner.nextInt();
        }

        if (a.length > x.length) {
            return;
        }

    	/*for (int xi : x) {
    		System.out.printf("%d ", xi);
        }
    	System.out.println();

    	for (int ai : a) {
    		System.out.printf("%d ", ai);
        }
    	System.out.println();*/

        StringBuilder answer = new StringBuilder();
        int end = x.length - a.length;

        for (int i = 0; i <= end; i++) {
            boolean isMatch = true;
            int c = x[i] - a[0];
            for (int j = 1; j < a.length; j++) {
                if (x[i + j] != a[j] + c) {
                    isMatch = false;
                    break;
                }
            }

            if (isMatch) {
                answer.append(Integer.toString(i + 1));
                answer.append(" ");
            }
        }

        answer.setLength(answer.length() - 1);
        System.out.println(answer);
    }
}
