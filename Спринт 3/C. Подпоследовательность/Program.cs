using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {
    	string s;
    	string t;
    	using (var reader = File.OpenText("input.txt"))
        {
        	s = reader.ReadLine();
        	t = reader.ReadLine();
        }
    
    	if (s.Length > t.Length)
        {
        	Console.WriteLine("False");
        	return;
        }
    
    	var i = 0;
    	var j = 0;
    	
    	while (i < s.Length && j < t.Length)
        {
        	if (s[i] == t[j])
            {
            	i++;
            }
        	j++;
        }
    
    	Console.WriteLine(i == s.Length);
    }
}
