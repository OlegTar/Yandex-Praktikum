using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {
    	Console.ReadLine();
    	var widths = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
    	Array.Sort(widths, (a, b) => b - a);
    
    	var perimiter = 0;
    	for (var i = 0; i < widths.Length - 2; i++)
        {
        	if (widths[i + 1] <= (widths[i] / 2))
            {
            	continue;
            }
        	if ((widths[i + 2] + widths[i + 1]) <= widths[i])
            {
            	continue;
            }
        
        	perimiter = widths[i] + widths[i + 1] + widths[i + 2];
        	break;
        }
    
    	Console.WriteLine(perimiter);
    }
}
