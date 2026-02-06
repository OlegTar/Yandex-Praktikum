using System.Collections.Generic;
using System.Linq;
using System;
using System.IO;

public class Program
{
	public static void Main(string[] args)
    {
    	using (TextReader stdin = Console.In) {
        using (TextWriter stdout = Console.Out) {
        	var line = stdin.ReadLine();
        	var parts = line.Split(" ");
        	var n = int.Parse(parts[0]);
        	var m = int.Parse(parts[1]);

        	int[][] matrix = new int[n][];
        	for (var i = 0; i < n; i++)
        	{
            	matrix[i] = new int[n];
        	}

        	for (var i = 0; i < m; i++)
        	{
            	line = stdin.ReadLine();
            	parts = line.Split(" ");
            	var v = int.Parse(parts[0]) - 1;
            	var u = int.Parse(parts[1]) - 1;
            	matrix[v][u] = 1;
        	}

        	for (var i = 0; i < n; i++)
        	{
            	stdout.WriteLine(string.Join(" ", matrix[i]));
        	}
        }}
    }
}
