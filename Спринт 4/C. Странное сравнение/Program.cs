using System;
using System.Linq;
using System.Collections.Generic;

public class Program
{
    public static void Main(string[] args)
    {
        var s = Console.ReadLine();
        var t = Console.ReadLine();

        if (s.Length != t.Length)
        {
            Console.WriteLine("NO");
            return;
        }

        Dictionary<char, char> map = new();
        HashSet<char> usedCharsFromT = new();

        for (var i = 0; i < s.Length; i++)
        {
            if (!map.ContainsKey(s[i]))
            {
                if (usedCharsFromT.Contains(t[i]))
                {
                    Console.WriteLine("NO");
                    return;
                }

                map.Add(s[i], t[i]);
                usedCharsFromT.Add(t[i]);
            }
            else if (map[s[i]] != t[i])
            {
                Console.WriteLine("NO");
                return;
            }
        }

        Console.WriteLine("YES");
    }
}
