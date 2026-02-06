using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {
    	var nAndBudget = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
    	var n = nAndBudget[0];
        var budget = nAndBudget[1];
    
    	var prices = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
    	Array.Sort(prices);
    
    	for (var i = 1; i < prices.Length; i++)
        {
        	prices[i] += prices[i - 1];
        }
    
    	var left = 0;
    	var right = prices.Length;
    	var index = -1;
    	
    	while (left < right)
        {
        	var mid = left + (right - left) / 2;
        	if (prices[mid] > budget)
            {
            	right = mid;
            }
        	else
            {
            	index = mid;
            	left = mid + 1;
            }
        }
    
    	Console.WriteLine(index + 1);
    }
}
