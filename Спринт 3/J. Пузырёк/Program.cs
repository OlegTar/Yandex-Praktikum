using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;

public class Program
{
    public static void Main(string[] args)
    {
        int n = 0;
        List<int> line;
        using (var reader = File.OpenText("input.txt"))
        {
            n = int.Parse(reader.ReadLine());
            line = reader.ReadLine().Split(" ").Select(int.Parse).Take(n).ToList();
        }

        Sort(line);
    }

    public static void Sort(List<int> line)
    {
        var n = line.Count - 1;
        var wasOutput = false;
        for (var i = 0; i < n; i++)
        {
            var lastIndex = line.Count - 2 - i;
            var haveWeSwapped = false; 
            for (var j = 0; j <= lastIndex; j++)
            {
                if (line[j] > line[j + 1])
                {
                    var temp = line[j + 1];
                    line[j + 1] = line[j];
                    line[j] = temp;
                    haveWeSwapped = true;
                }
            }
            if (!haveWeSwapped)
            {
                break;
            }
            Console.WriteLine(string.Join(" ", line));
            wasOutput = true;
        }

        if (!wasOutput)
        {
            Console.WriteLine(string.Join(" ", line));
        }
    }
}
