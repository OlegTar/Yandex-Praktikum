using System.Linq;
using System.Collections.Generic;
using System;

public class Program
{
    private const int q = 1_000_000_007;
    private const int R = int.MaxValue;

    public static void Main(string[] args)
    {
        string line = Console.ReadLine();

        int count = 1;
        int half = line.Length / 2;

        int[] freq = new int[26];
        foreach (char ch in line)
        {
            freq[ch - 'a']++;
        }

        int min = int.MaxValue;
        for (int i = 0; i < freq.Length; i++)
        {
            if (freq[i] == 0)
            {
                continue;
            }
            min = Math.Min(min, freq[i]);
        }

        if (min == 1)
        {
            Console.WriteLine(1);
            return;
        }

        for (int len = 1; len <= half; len++)
        {
            if (line.Length % len > 0)
            {
                continue;
            }

            bool match = true;
            long hash = ComputeHash(line, 0, len);
            for (int i = len; i < line.Length; i += len)
            {
                long currentHash = ComputeHash(line, i, i + len);

                /*long currentHash = 0;
    			for (int j = i; j < i + len; j++)
        		{
        			currentHash = ((currentHash * q) + (int)line[j]) % R;
        		}*/

                if (currentHash != hash)
                {
                    match = false;
                    break;
                }
            }

            if (match)
            {
                count = line.Length / len;
                break;
            }
        }

        Console.WriteLine(count);
    }

    public static long ComputeHash(string s, int startIndex, int endIndex)
    {
        long hash = 0;

        for (int i = startIndex; i < endIndex; i++)
        {
            hash = ((hash * q) + (int)s[i]) % R;
        }

        return hash;
    }
}
