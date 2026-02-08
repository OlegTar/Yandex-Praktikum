using System;
using System.Linq;
using System.Collections.Generic;

public class Program
{
    public static void Main(string[] args)
    {
        int n = int.Parse(Console.ReadLine());
        Dictionary<string, int> freq = new();

        for (int i = 0; i < n; i++)
        {
            String line = Console.ReadLine();
            if (!freq.ContainsKey(line))
            {
                freq[line] = 0;
            }
            freq[line]++;
        }

        (String word, int count) wordAndCount = ("", 0);

        foreach (var kv in freq)
        {
            if ((kv.Value > wordAndCount.count) ||
                (kv.Value == wordAndCount.count && kv.Key.CompareTo(wordAndCount.word) < 0))
            {
                wordAndCount = (kv.Key, kv.Value);
            }
        }

        Console.WriteLine(wordAndCount.word);
    }
}
