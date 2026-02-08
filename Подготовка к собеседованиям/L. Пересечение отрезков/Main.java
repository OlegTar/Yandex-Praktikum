import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine().trim());
        List<long[]> first = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            first.add(new long[]{start, end});
        }

        int m = Integer.parseInt(br.readLine().trim());
        List<long[]> second = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            second.add(new long[]{start, end});
        }

        int i = 0, j = 0;
        while (i < n && j < m) {
            long a1 = first.get(i)[0], b1 = first.get(i)[1];
            long a2 = second.get(j)[0], b2 = second.get(j)[1];

            long start = Math.max(a1, a2);
            long end = Math.min(b1, b2);

            if (start <= end) {
                out.println(start + " " + end);
            }

            if (b1 < b2) {
                i++;
            } else {
                j++;
            }
        }

        out.close();
    }
}
