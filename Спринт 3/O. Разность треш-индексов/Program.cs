using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {
    	var n = int.Parse(Console.ReadLine());
    	var squares = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
    	var k = long.Parse(Console.ReadLine());	
    
    	Array.Sort(squares);
    
    	var left = 0;
    	var right = squares[squares.Length - 1] - squares[0];
    
    	while (left <= right)
        {
        	var mid = (left + right) / 2;
        	
        	var count = CountPairsWithDiff(squares, mid);
        	if (count < k)
            {
            	left = mid + 1;
            }
        	else 
        	{
            	right = mid - 1;
        	}
        }
    
    	Console.WriteLine(left);
    }

	public static long CountPairsWithDiff(int[] squares, int diff)
    {
    	var count = 0L;
        var left = 0;

        for (var right = 0; right < squares.Length; right++)
        {
            while (squares[right] - squares[left] > diff)
            {
                left++;
            }
            count += right - left; 
        }

        return count;
    }
}
