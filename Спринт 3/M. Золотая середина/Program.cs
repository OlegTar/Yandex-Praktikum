using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {
    	Console.ReadLine();
    	Console.ReadLine();
    
    	var north = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
    	var south = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
    
    	var commonLength = north.Length + south.Length;
    	
    	double result = 0;
    	var enumerator = GetMin(north, south).GetEnumerator();
    	if (commonLength % 2 == 0)
        {
        	
        	for (var i = 0; i <= (commonLength / 2) - 1; i++)
            {
            	enumerator.MoveNext();
            }
        
        	var mid1 = (double)enumerator.Current;
        	enumerator.MoveNext();
        	var mid2 = (double)enumerator.Current;
        	result = (mid1 + mid2) / 2;
        }
    	else {
        	for (var i = 0; i <= (commonLength / 2); i++)
            {
            	enumerator.MoveNext();
            }
        	result = enumerator.Current;
    	}
    
    	Console.WriteLine(result);
    }

	public static IEnumerable<int> GetMin(int[] north, int[] south)
    {
    	var p1 = 0;
    	var p2 = 0;
    
    	while (p1 < north.Length || p2 < south.Length)
        {
        	if (p1 == north.Length)
            {
            	yield return south[p2++];
            }
        	else if (p2 == south.Length)
            {
            	yield return north[p1++];
            }
        	else if (north[p1] < south[p2])
            {
            	yield return north[p1++];
            }
            else 
            {
               yield return south[p2++];
            }
        }
    }
}
