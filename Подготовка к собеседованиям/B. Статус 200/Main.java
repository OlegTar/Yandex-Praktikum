import java.io.*;
import java.util.*;

public class Program
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        //(ai - aj) % 200 == 0
        //
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        Map<Integer, Integer> cache = new HashMap<>();
        for (int num : nums) {
            int key = num % 200;
            if (!cache.containsKey(key)) {
                cache.put(key, 0);
            }
            cache.put(key, cache.get(key) + 1);
        }

        long cnt = 0;

        for (int num : cache.keySet())
        {
            int value = cache.get(num);
            for (int i = 1; i < value; i++)
            {
                cnt += i;
            }
        }

        System.out.println(cnt);
    }
}
