using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

public class Solution
{
    public static TextReader reader;
	public static TextWriter writer;

	public static void Main(string[] args)
    {
    	using (writer = new StreamWriter(Console.OpenStandardOutput())) {
    	//reader = new StreamReader(Console.OpenStandardInput());
            using (reader = File.OpenText("input.txt")) {
                int days = ReadInt();
                //Console.WriteLine($"days = {days}");
                List<int> money = ReadList();
                //Console.WriteLine($"money = [{string.Join(", ", money)}]");
                int sum = ReadInt();
                //Console.WriteLine($"sum = {sum}");
                
                var firstDay = BinarySearch(money, 0, money.Count, sum);
                var secondDay = BinarySearch(money, 0, money.Count, sum * 2);

                if (firstDay >= 0)
                {
                    firstDay++;
                }

                if (secondDay >= 0)
                {
                    secondDay++;
                }
            
                writer.Write($"{firstDay} {secondDay}");
            }
        }
    }

	public static int BinarySearch(List<int> list, int left, int right, int target)
    {
    	if (left >= right)
        {
        	return -1;
        }
    	
    	var mid = (left + right) / 2;
    	
    	if (target > list[mid])
        {
            return BinarySearch(list, mid + 1, right, target);
        }
    	
    	var inLeft = BinarySearch(list, left, mid, target);
        return inLeft != -1 ? inLeft : mid;
    }

	public static int ReadInt() 
    {
    	return int.Parse(reader.ReadLine());
    }

	private static List<int> ReadList()
    {
        return reader.ReadLine()
            .Split(new[] { ' ', '\t', }, StringSplitOptions.RemoveEmptyEntries)
            .Select(int.Parse)
            .ToList();
    }
}
