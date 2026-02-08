using System;
using System.Linq;
using System.Collections.Generic;
using System.Text;

public class Program
{
    public static void Main(string[] args)
    {
        string a = Console.ReadLine();
        string b = Console.ReadLine();

        string normalizedA = string.Join("", a.Where(c => (c - 'a' + 1) % 2 == 0));
        string normalizedB = string.Join("", b.Where(c => (c - 'a' + 1) % 2 == 0));

        Console.WriteLine(normalizedA.CompareTo(normalizedB));
    }
}
