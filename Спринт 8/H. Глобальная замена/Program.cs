using System;
using System.Collections.Generic;
using System.Text;

public class Program
{
    public static void Main(string[] args)
    {
        string text = Console.ReadLine();
        string p = Console.ReadLine();
        string replace = Console.ReadLine();

        string s = p + "#" + text;

        List<int> result = new List<int>();
        int[] pi = new int[p.Length];
        int pi_prev = 0;
        for (int i = 1; i < s.Length; i++)
        {
            int k = pi_prev;
            while (k > 0 && s[k] != s[i])
            {
                k = pi[k - 1];
            }

            if (s[k] == s[i])
            {
                k++;
            }

            if (i < p.Length)
            {
                pi[i] = k;
            }

            pi_prev = k;
            if (k == p.Length)
            {
                result.Add(i - 2 * p.Length);
            }
        }

        //List<string> strings = new();

        //int diff = replace.Length - pattern.Length;
        StringBuilder sb = new();
        int lastIndex = 0;
        foreach (int idx in result)
        {
            sb.Append(text.Substring(lastIndex, idx - lastIndex));
            sb.Append(replace);
            lastIndex = idx + p.Length;
        }

        if (lastIndex < text.Length)
        {
            sb.Append(text.Substring(lastIndex));
        }

        Console.WriteLine(sb.ToString());
    }
}
