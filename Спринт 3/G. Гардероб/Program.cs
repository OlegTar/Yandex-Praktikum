using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program 
{
	public static void Main(string[] args)
    {
    	var clothes = new int[0];
    	var n = int.Parse(Console.ReadLine());
        if (n > 0) 
        {
       		clothes = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
        }
    
    	var start = 0;
		var end = clothes.Length - 1;
    	var i = 0;
    	while (i <= end)
        {
        	if (clothes[i] == 0)
            {
            	Swap(start, i, clothes);
            	start++;
            	i++;
            }
        	else if (clothes[i] == 1)
            {
            	i++;
            }
        	else 
            {
            	Swap(i, end, clothes);
            	end--;
        	}
        }
    
    	Console.WriteLine(string.Join(" ", clothes));
    }

	public static void Swap(int i, int j, int[] clothes)
    {
    	if (i == j) return;
    	var temp = clothes[j];
   		clothes[j] = clothes[i];
    	clothes[i] = temp;
    }
}
