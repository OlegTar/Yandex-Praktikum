import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] array = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; ++i) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] result = new int[n];

        int firstIndexZero = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] == 0) {
                firstIndexZero = i;
                break;
            }
        }

        for (int i = 0; i < firstIndexZero; i++) {
            result[i] = firstIndexZero - i;
        }

        int prevIndexZero = firstIndexZero;
        int lastIndexZero = firstIndexZero;
        while (true) {
            boolean foundZero = false;
            for (int i = prevIndexZero + 1; i < n; i++) {
                if (array[i] == 0) {
                    lastIndexZero = i;
                    foundZero = true;
                    break;
                }
            }

            if (!foundZero)
            {
                break;
            }

            int length = lastIndexZero - prevIndexZero - 1;

            int mid = length / 2;
            //0 1 2 8 7 0, length = 4, mid = 2
            //j = 0, j < mid => j = 0, 1
            //0 4 4 8 3 3 0
            //0 1 2 3 2 1 0
            int i = prevIndexZero + 1;
            int j = 0;
            for (; j < mid; j++, i++) {
                result[i] = j + 1;
            }

            if (length % 2 != 0) {
                result[i++] = j + 1;
            }

            for (j = mid; j > 0; j--, i++) {
                result[i] = j;
            }

            prevIndexZero = lastIndexZero;
        }

        for (int i = lastIndexZero + 1, j = 1; i < n; i++, j++)
        {
            result[i] = j;
        }

        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        for (int i = 0; i < (n - 1); i++)
        {
            writer.print(result[i]);
            writer.print(' ');
        }
        writer.print(result[n - 1]);
        writer.flush();
    }
}
