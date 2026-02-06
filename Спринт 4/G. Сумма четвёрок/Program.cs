using System;
using System.Linq;
using System.Collections.Generic;
using System.IO;

public class Program
{
    public static void Main(string[] args)
    {
        var n = int.Parse(Console.ReadLine());
        if (n < 4)
        {
            Console.WriteLine(0);
            return;
        }
        var s = long.Parse(Console.ReadLine());

        var nums = Console.ReadLine().Split(' ').Select(long.Parse).ToArray();
        Array.Sort(nums);

        var result = new List<long[]>();
        var indicesPairs = new Dictionary<long, List<int[]>>();

        for (var i = 0; i < n - 1; i++)
        {
            for (var j = i + 1; j < n; j++)
            {
                var sum = nums[i] + nums[j];
                if (!indicesPairs.ContainsKey(sum))
                {
                    indicesPairs[sum] = new List<int[]>();
                }

                indicesPairs[sum].Add(new int[] { i, j });
            }
        }

        
    	foreach (var sum in indicesPairs.Keys)
    	{	
        		foreach (var ij in indicesPairs[sum]) 
                {
                	var i = ij[0];
                	var j = ij[1];

                	var complementSum = s - sum;

                	if (!indicesPairs.ContainsKey(complementSum))
                	{
                    	continue;
                    }

                	for (var k = 0; k < indicesPairs[complementSum].Count; k++)
                	{
                    	var indices = indicesPairs[complementSum][k];

                    	if (indices[0] == i || indices[1] == j || indices[0] == j || indices[1] == i)
                    	{
                        	continue;
                    	}

                    	var resultItem = new long[] {
                       	 nums[i], nums[j],
                      	  nums[indices[0]], nums[indices[1]]
                       	};
                    	Array.Sort(resultItem);

                    	var found = false;
                    	for (var l = 0; l < result.Count; l++)
                    	{
                        	if (ArrayAreEqual(result[l], resultItem))
                        	{
                            	found = true;
                            	break;
                        	}
                    	}

                    	if (!found) 
                    	{
                        	result.Add(resultItem);
                    	}
                	}
                }
    	}


        using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
        {
            writer.WriteLine(result.Count);

            for (var i = 0; i < result.Count; i++)
            {
                writer.WriteLine(string.Join(" ", result[i]));
            }
        }
    }

    public static bool ArrayAreEqual(long[] arr1, long[] arr2)
    {
        if (arr1.Length != arr2.Length)
        {
            return false;
        }

        for (var i = 0; i < arr1.Length; i++)
        {
            if (arr1[i] != arr2[i])
            {
                return false;
            }
        }

        return true;
    }
}
