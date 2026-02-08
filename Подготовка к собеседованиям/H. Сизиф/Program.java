import java.util.*;
import java.io.*;

public class Program
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextLong();
        }

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (long num : nums)
        {
            pq.offer(num);
        }

        long sum = 0;
        while (pq.size() > 1) {
            long ai = pq.poll();
            long aj = pq.poll();
            long localSum = ai + aj;
            sum += localSum;
            pq.offer(localSum);
        }

        System.out.println(sum);
    }
}
