using System;
using System.Linq;
using System.IO;
using System.Collections.Generic;

public class Program
{
    public static void Main(string[] args)
    {
        Console.ReadLine();
        long[] nums = Console.ReadLine().Split(" ").Select(long.Parse).ToArray();

        PriorityQueue<long, long> pq = new();
        foreach (var num in nums)
        {
            pq.Enqueue(num, num);
        }

        long sum = 0;
        while (pq.Count > 1)
        {
            long ai = pq.Dequeue();
            long aj = pq.Dequeue();
            long localSum = ai + aj;
            sum += localSum;
            pq.Enqueue(localSum, localSum);
        }

        Console.WriteLine(sum);
    }
}
