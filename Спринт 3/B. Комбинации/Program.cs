using System;
using System.IO;
using System.Collections.Generic;
using System.Text;

public class Program
{
    public static Dictionary<char, char[]> letters = new Dictionary<char, char[]>()
    {
        {'2', new char[] { 'a', 'b', 'c'}},
        {'3', new char[] {'d', 'e', 'f'}},
        {'4', new char[] {'g', 'h', 'i'}},
        {'5', new char[] {'j', 'k', 'l'}},
        {'6', new char[] {'m', 'n', 'o'}},
        {'7', new char[] {'p', 'q', 'r', 's'}},
        {'8', new char[] {'t', 'u', 'v'}},
        {'9', new char[] {'w', 'x', 'y', 'z'}}
    };
    public static void Main(string[] args)
    {
        string line;
        using (var reader = new StreamReader(Console.OpenStandardInput()))
        {
            line = reader.ReadLine();
        }

        var combinations = GetCombinations(line);
        Console.WriteLine(string.Join(" ", combinations));
    }

    public static List<string> GetCombinations(string line)
    {
        if (line.Length == 0)
        {
            return new List<string>() {};
        }

        var result = new List<string>();
        var lettersOnButton = letters[line[0]];

        var otherCombinations = GetCombinations(line.Substring(1));

        foreach (var number in lettersOnButton)
        {
            if (otherCombinations.Count == 0)
            {
                result.Add(number.ToString());
            }
            else
            {
                foreach (var otherCombination in otherCombinations)
                {
                    result.Add(number + otherCombination);
                }
            }
        }
        return result;
    }
}
