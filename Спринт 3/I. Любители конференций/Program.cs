using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {
    	Console.ReadLine();
    	var ids = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
    	var k = int.Parse(Console.ReadLine());
    	
    	//var groups = ids.GroupBy(id => id)
        //	.Select(gr => new int[] { gr.Key, gr.Count() })
        //	.ToArray();
    
    	var counts = new Dictionary<int, int>();
    	foreach (var id in ids)
        {
        	if (counts.ContainsKey(id))
            {
            	counts[id]++;
            }
        	else 
            {
            	counts[id] = 0;
        	}
        }
    
    	int[][] groups = new int[counts.Keys.Count][];
    	var i = 0;
    	foreach (var (id, count) in counts)
        {
        	groups[i++] = new int[] { id, count };
        }
    	
    	Array.Sort(groups, (a, b) => {
        	if (a[1] == b[1])
            {
            	return a[0].CompareTo(b[0]);
            }
        	return b[1].CompareTo(a[1]);
    	});
    
    	Console.WriteLine(string.Join(" ", 
                                	groups.Take(k)
                                    .Select(gr => gr[0])
                                    .ToArray()));
    }
}
