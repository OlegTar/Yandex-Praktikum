using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program 
{
	public static void Main(string[] args)
    {
    	List<int[]> intervals = new List<int[]>();
    	using (var reader = File.OpenText("input.txt"))
        {
        	var n = int.Parse(reader.ReadLine());
        	for (var i = 0; i < n; i++)
            {
            	var interval = reader.ReadLine().Split(' ').Select(int.Parse).ToArray();
            	intervals.Add(interval);
            }
        }
    
    	intervals.Sort((a, b) => a[0].CompareTo(b[0]));
    	var result = new List<int[]>() { intervals[0] };
    
    	var lastIndex = 0;
    	for (var i = 1; i < intervals.Count; i++)
        {
        	if (intervals[i][0] <= result[lastIndex][1])
            {
            	result[lastIndex][1] = Math.Max(result[lastIndex][1], intervals[i][1]);
            }
        	else 
            {
            	result.Add(intervals[i]);
            	lastIndex++;
        	}
        }
    
    	using (var writer = new StreamWriter(File.OpenWrite("output.txt")))
        {
        	foreach (var interval in result)
            {
            	writer.WriteLine(string.Join(" ", interval));
            }
        }
    }
}
