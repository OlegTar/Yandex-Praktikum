using System;
using System.Linq;
using System.IO;
using System.Collections.Generic;

public class Program
{
    public static void Main(string[] args)
    {
        int n = int.Parse(Console.ReadLine());
        List<string> list = new();
        int minLength = int.MaxValue;

        for (int i = 0; i < n; i++)
        {
            string line = Console.ReadLine();
            list.Add(line);
            minLength = Math.Min(minLength, line.Length);
        }

        int prefix = 0;
        for (int i = 0; i < minLength; i++)
        {
            bool allMatch = true;
            char ch = list[0][i];
            for (int j = 1; j < list.Count; j++)
            {
                if (list[j][i] != ch)
                {
                    allMatch = false;
                    break;
                }
            }

            if (allMatch)
            {
                prefix++;
            }
            else
            {
                break;
            }
        }

        Console.WriteLine(prefix);
    }
}
