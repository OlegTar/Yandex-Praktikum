using System;
using System.Linq;
using System.Collections.Generic;

public class Program
{
    private static Dictionary<int, int> cache = new();

	public static void Main(string[] args)
    {
    	var n = int.Parse(Console.ReadLine());
    	var nodes = new int[n];
    	for (var i = 0; i < n; i++)
        {
        	nodes[i] = i + 1;
        }
 

        Console.WriteLine(Solve(nodes));
    }

	public static int Solve(int[] nodes)
    {
        if (cache.TryGetValue(nodes.Length, out var cnt))
        {
            return cnt;
        }

        if (nodes.Length <= 1)
        {
            return 1;
        }

        var count = 0;
        
        for (var i = 0; i < nodes.Length; i++)
        {
            var nodeLess = nodes.Where(v => v < nodes[i]).ToArray();
            var nodesGreater = nodes.Where(v => v > nodes[i]).ToArray();

            count += Solve(nodeLess) * Solve(nodesGreater);
        }

        cache[nodes.Length] = count;

        return count;
    }
}
