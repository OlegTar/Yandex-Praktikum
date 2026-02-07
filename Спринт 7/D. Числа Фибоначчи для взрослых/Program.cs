using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program
{
    public static void Main(string[] args)
    {
        int n = int.Parse(Console.ReadLine());
        if (n <= 1)
        {
            Console.WriteLine(1);
        }

        int fib = 0;
        int prevPrev = 1;
        int prev = 1;
        for (int i = 2; i <= n; i++)
        {
            fib = (prev + prevPrev) % 1_000_000_007;
            prevPrev = prev;
            prev = fib;
        }

        Console.WriteLine(fib);
    }
}
