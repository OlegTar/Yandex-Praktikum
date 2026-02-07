using System;
using System.Linq;
using System.Collections.Generic;
using System.IO;

public class Program
{
    public static void Main(string[] args)
    {
        int m = int.Parse(Console.ReadLine());
        int n = int.Parse(Console.ReadLine());
        int[] nominals = Console.ReadLine().Split(" ").Select(int.Parse).Distinct().ToArray();
        Array.Sort(nominals);

        int[] sum = new int[m + 1];
        sum[0] = 1;
        //sum[i] - максимальное количество способов собрать сумму i

        foreach (int nominal in nominals)
        {
            for (int amount = nominal; amount <= m; amount++)
            {
                sum[amount] += sum[amount - nominal];
            }
        }

        Console.WriteLine(sum[m]);
    }
}
