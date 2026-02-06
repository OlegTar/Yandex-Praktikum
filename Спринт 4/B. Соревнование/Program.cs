using System;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {
    	var n = int.Parse(Console.ReadLine());
    	if (n <= 1)
        {
        	Console.WriteLine(0);
        	return;
        }
    	var nums = Console.ReadLine().Split(' ')
        	.Select(int.Parse)
        	.Select(x => x == 0 ? 1 : -1)
        	.ToArray();
    	var preSumCounts = new Dictionary<int, int>();
    	preSumCounts.Add(0, -1);
    
    	var length = 0;
    	var preSum = 0;
    	for (var i = 0; i < nums.Length; i++)
        {
        	preSum += nums[i];
        	if (preSumCounts.ContainsKey(preSum))
            {
            	length = Math.Max(length, i - preSumCounts[preSum]);
            }
        	else 
            {
        		preSumCounts[preSum] = i;
            }
        }

    	Console.WriteLine(length);
    }
}
