using System;
using System.Linq;
using System.Text;
using System.Collections.Generic;

public class Program 
{
	public static void Main(string[] args)
    {
    	Console.ReadLine();
    	var s = Console.ReadLine();
    	var strings = s.Split(" ").ToArray();
    	
    	for (var i = 0; i < strings.Length; i++) 
    	{
        	var arr = strings[i].ToCharArray();
        	Array.Sort(arr);
        	strings[i] = new string(arr);
        }
    
    	var used = new HashSet<int>();
    
    	var groups = new List<List<int>>();
    
    	for (var i = 0; i < strings.Length; i++)
        {
        	if (used.Contains(i)) continue;
        
        	var @group = new List<int>();
        	@group.Add(i);
        	
        	for (var j = i + 1; j < strings.Length; j++) 
            {
            	if (strings[i] == strings[j])
                {
                	@group.Add(j);
                	used.Add(j);
            	}
        	}
        
        	groups.Add(@group);
        }
    
    	foreach (var @group in groups) 
        {
        	Console.WriteLine(string.Join(" ", @group));
        }
    }
}
