using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program 
{
	public static void Main(string[] args)
    {
    	int[] clothes;
    	using (var reader = File.OpenText("input.txt"))
        {
        	reader.ReadLine();
       		clothes = reader.ReadLine().Split(' ').Select(int.Parse).ToArray();
        }
    
    	var right = clothes.Length - 1;
    	for (var i = 0; i < right; i++)
        {
        	if (clothes[i] < clothes[right])
            {
            	var temp = clothes[right];
            	clothes[right] = clothes[i];
            	clothes[i] = temp;
                right--;
            	i--;
            }
        }
    
    	Console.WriteLine(string.Join(" ", clothes));
    }
}
