using System;

class Program
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        string[] tokens = Console.ReadLine().Split(' ');
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
        {
            a[i] = int.Parse(tokens[i]);
        }

        int blocks = 0;
        int currentMax = -1;

        for (int i = 0; i < n; i++)
        {
            if (a[i] > currentMax) currentMax = a[i];
            if (currentMax == i)
            {
                blocks++;
            }
        }

        Console.WriteLine(blocks);
    }
}
