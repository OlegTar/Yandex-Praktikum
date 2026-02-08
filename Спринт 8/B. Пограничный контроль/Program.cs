using System;
using System.Linq;
using System.Text;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {
    	string passportName = Console.ReadLine();
    	string name = Console.ReadLine();
    
    
    	Console.WriteLine(IsAtMostOneChange(passportName, name) ? "OK" : "FAIL");
    }

	public static bool IsAtMostOneChange(string a, string b) 
	{
		if (Math.Abs(a.Length - b.Length) >= 2)
		{
		   return false;
		}

		int i = 0;
		int len = Math.Min(a.Length, b.Length);
		for (; i < len; i++)
		{
			  if (a[i] != b[i]) 
			  {
				  break;

			  }
		}

		if (i == len) 
		{
			  return true;
		}
		int diff = b.Length - a.Length;

		if (a.Length >= b.Length)
		{
			//длины равны, возможно отличаются только одним символом, скипаем его
			//либо строка "a" больше на один символ. мы должны проверять a[i] и b[i - 1]
			i++;
		}

		for (; i < len; i++) 
		{
			  if (a[i] != b[i + diff]) 
			  {
					return false;
			  }
		}
		return true;  
	} 
}
