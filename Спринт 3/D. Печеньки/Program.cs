using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {
    	var n = int.Parse(Console.ReadLine());
    	if (n == 0)
        {
        	Console.WriteLine(0);
        	return;
        }
    	
    	var greedies = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
        
    
    	var m = int.Parse(Console.ReadLine());
    	if (m == 0)
        {
        	Console.WriteLine(0);
        	return;
        }
    	
    	var cookies = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
    
    	Array.Sort(greedies);
    	Array.Sort(cookies);
    
    	var count = 0;
    	var indexInCookies = 0;
    
    	foreach (var greedy in greedies)
        {
        	indexInCookies = Find(cookies, indexInCookies, greedy);
        	if (indexInCookies == -1)
            {
            	break;
            }
        	indexInCookies++;
        	count++;
        }
    
    	Console.WriteLine(count);
    }

	public static int Find(int[] cookies, int indexInCookies, int greedy)
    {
    	var left = indexInCookies;
    	var right = cookies.Length;
    	var index = -1;

    	while (left < right)
        {
        	var mid = left + (right - left) / 2;
        	if (cookies[mid] < greedy)
            {
            	left = mid + 1;
            }
        	else
            {
            	index = mid;
            	right = mid;
            }
        }
    
    	return index;
    }
}
