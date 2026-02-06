using System.Collections.Generic;
using System.IO;
using System;

public class Solution {
	public static void Main(string[] args)
    {
        using (var reader = new StreamReader(Console.OpenStandardInput())) 
        {
            var n = int.Parse(reader.ReadLine());
            var list = Solve(n);
            list.Sort();
            using (var writer = new StreamWriter(Console.OpenStandardOutput()))
            {
                foreach (var option in list)
                {
                    writer.WriteLine(option);
                }
            }
        }
    }
	
	public static List<string> Solve(int n)
    {
    	if (n == 0) 
        {
        	return new List<string>();
        }
    	
    	if (n == 1)
        {
        	return new List<string>() { "()"};
        }
    
        var hash = new HashSet<string>();
        var result = new List<string>();
        var otherOptions = Solve(n - 1);

        foreach (var option in otherOptions)
        {
            var newOption = "(" + option + ")";
            if (!hash.Contains(newOption))
            {
                result.Add(newOption);
                hash.Add(newOption);
            }
        }

        for (var i = n - 1; i >= 1; i--)
        {
            var optionsLeft = Solve(i);//слева уменьшаем, 3, 2, 1
            var optionsRight = Solve(n - i);//справа увеличиваем 1, 2, 3

            foreach (var strLeft in optionsLeft)
            {
                foreach (var strRight in optionsRight)
                {
                    var resultStr = strLeft + strRight; 
                    if (!hash.Contains(resultStr))
                    {
                        result.Add(resultStr);
                        hash.Add(resultStr);
                    }      
                }
            }
        }
        

        return result;        
    }
}
