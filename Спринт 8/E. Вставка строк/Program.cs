using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

public class Program
{
    public static void Main(string[] args)
    {
        string s = Console.ReadLine();
        int n = int.Parse(Console.ReadLine());

        List<(string substr, int position)> substrings = new();

        for (int i = 0; i < n; i++)
        {
            string line = Console.ReadLine();
            string[] parts = line.Split(" ");
            string ti = parts[0];
            int ki = int.Parse(parts[1]);
            substrings.Add((ti, ki));
        }

        substrings.Sort((a, b) => a.position - b.position);

        StringBuilder sb = new StringBuilder();
        int positionInS = 0;
        foreach ((string ti, int ki) in substrings)
        {
            if (positionInS < ki)
            {
                int len = ki - positionInS;
                sb.Append(s.Substring(positionInS, len));
                positionInS = ki;
            }

            sb.Append(ti);
            if (positionInS < s.Length)
            {
                sb.Append(s[positionInS++]);
            }
        }

        if (positionInS < s.Length)
        {
            sb.Append(s.Substring(positionInS));
        }

        Console.WriteLine(sb.ToString());
    }
}
