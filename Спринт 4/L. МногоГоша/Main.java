import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = (1L << 61) - 1;
    static final long BASE = 131;

    static long modMul(long a, long b) {
        long au = a >>> 31, ad = a & ((1L << 31) - 1);
        long bu = b >>> 31, bd = b & ((1L << 31) - 1);
        long mid = ad * bu + au * bd;
        long midu = mid >>> 30, midd = mid & ((1L << 30) - 1);
        return (au * bu * 2 + midu + (midd << 31) + ad * bd) % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        char[] s = br.readLine().toCharArray();
        int L = s.length;

        if (n > L) {
            return;
        }

        // Предвычисление степеней
        long[] pow = new long[n];
        pow[0] = 1;
        for (int i = 1; i < n; i++) {
            pow[i] = modMul(pow[i - 1], BASE);
        }

        // Хеш первой подстроки
        long hash = 0;
        for (int i = 0; i < n; i++) {
            hash = (modMul(hash, BASE) + s[i]) % MOD;
        }

        // Map: hash -> [count, firstIndex]
        Map<Long, int[]> map = new HashMap<>();
        map.put(hash, new int[]{1, 0});

        // Rolling
        for (int i = n; i < L; i++) {
            // Удаляем s[i - n]
            long toRemove = modMul(s[i - n], pow[n - 1]);
            hash = (hash - toRemove + MOD) % MOD;
            // Добавляем s[i]
            hash = (modMul(hash, BASE) + s[i]) % MOD;

            int startIndex = i - n + 1;
            map.compute(hash, (h, arr) -> {
                if (arr == null) {
                    return new int[]{1, startIndex};
                } else {
                    arr[0]++;
                    return arr;
                }
            });
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        for (int[] arr : map.values()) {
            if (arr[0] >= k) {
                pw.print(arr[1] + " ");
            }
        }
        pw.flush();
    }
}
