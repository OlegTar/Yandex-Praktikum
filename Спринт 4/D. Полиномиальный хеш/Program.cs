using System;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {
    	var a = int.Parse(Console.ReadLine());
    	var m = int.Parse(Console.ReadLine());
    	var s = Console.ReadLine();
    	var hash = 0L;
    
    	for (var i = 0; i < s.Length; i++)
        {
        	hash = (hash * a + s[i]) % m;
        }
    
    	Console.WriteLine(hash);
    }
}
