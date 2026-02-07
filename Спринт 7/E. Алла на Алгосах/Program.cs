using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

public class Program
{
    public static void Main(string[] args)
    {
        int x = int.Parse(Console.ReadLine());
        int k = int.Parse(Console.ReadLine());

        int[] nominals = Console.ReadLine().Split(" ").Select(int.Parse).Distinct().ToArray();
        Array.Sort(nominals);
        int[] dp = new int[x + 1];
        Array.Fill(dp, int.MaxValue);
        dp[0] = 0;

        //dp[i] - наименьшее количество купюр для суммы i
        for (int i = 1; i <= x; i++)
        {
            foreach (int nominal in nominals)
            {
                if (nominal > i)
                {
                    break;
                }

                if (dp[i - nominal] != int.MaxValue)
                {
                    dp[i] = Math.Min(dp[i], dp[i - nominal] + 1);
                }
            }
        }

        Console.WriteLine(dp[x] == int.MaxValue ? -1 : dp[x]);
    }
}
