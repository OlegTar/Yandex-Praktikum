import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(reader.readLine());
        k *= 2;
        int[] counts = new int[10];
        for (int i = 0; i < 4; i++) {
            String str = reader.readLine();
            for (int j = 0; j < 4; j++) {
                if (str.charAt(j) == '.')
                {
                    continue;
                }
                int digit = str.charAt(j) - '0';
                counts[digit]++;
            }
        }

        int result = 0;
        for (int t = 1; t <= 9; t++)
        {
            if (0 < counts[t] && counts[t] <= k)
            {
                result++;
            }
        }


        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        writer.print(result);
        writer.flush();
    }
}
