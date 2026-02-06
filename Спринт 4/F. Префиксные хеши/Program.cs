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
    	var t = int.Parse(Console.ReadLine());
    	//hash = (s[0] * a^4  + s[1] * a^3 + s[2] * a^2 + s[3] * a + s[4])
    	var prefixes = new long[s.Length + 1];
    	var powersA = new long[s.Length + 1];
    	var hash = 0L;
    	prefixes[0] = hash;
    	powersA[0] = 1;
    	for (var i = 0; i < s.Length; i++)
        {
        	hash = (hash * a + s[i]) % m;
        	prefixes[i + 1] = hash;
        	powersA[i + 1] = (powersA[i] * a) % m; 
        }
    
    	for (var i = 0; i < t; i++)
        {
        	var lr = Console.ReadLine().Split(' ').Select(long.Parse).ToArray();
			var l = lr[0];
        	var r = lr[1];

        	var diff = r - l + 1;
        	var computedHash = prefixes[r];
        	var @base = powersA[diff];
        	computedHash -= @base * prefixes[l - 1];
        	if (computedHash < 0)
        	{
            	diff = (-computedHash / m);
        		computedHash += diff * m;
            	if (computedHash < 0) 
                {
            		computedHash += m;
                }
        	}
        	computedHash %= m;
        	Console.WriteLine(computedHash);
        }
    }
}
