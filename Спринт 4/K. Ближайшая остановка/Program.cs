using System;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public class SubwayData
    {
    	public int X {get; set;}
    	public int Y { get; set; }
    	public int Index {get; set;}
   		public int Count { get; set; }
    
    	public SubwayData(int x, int y, int index)
        {
        	X = x;
        	Y = y;
        	Index = index;
        	Count = 0;
        }
    }

	public const int dist = 20;

	public static void Main(string[] args)
    {
    	var n = int.Parse(Console.ReadLine());
    	var subways = new List<SubwayData>();
    	for (var i = 1; i <= n; i++)
    	{
        	var coords = Console.ReadLine().Split(" ").Select(int.Parse).ToArray();
        	subways.Add(new SubwayData(coords[0], coords[1], i));
    	}
                        
        var m = int.Parse(Console.ReadLine());
    	var busStopsMap = new Dictionary<int, Dictionary<int, int>>();
    
        for (var i = 0; i < m; i++)
        {
        	var coords = Console.ReadLine().Split(" ").Select(int.Parse).ToArray();
        	
        	var x = coords[0];
        	var y = coords[1];
                	
        	if (!busStopsMap.ContainsKey(coords[0]))
            {
            	busStopsMap[x] = new Dictionary<int, int>();
            }
        
        	if (!busStopsMap[x].ContainsKey(y))
            {
            	busStopsMap[x][y] = 0;
            }
        
        	busStopsMap[x][y]++;
        }
          
    	var max = -1;
    	var indexMax = 0;
        for (var i = 0; i < subways.Count; i++)
        {
  			var subway = subways[i];
        	var minX = subway.X - dist;
        	var maxX = subway.X + dist;
        
        	for (var x = minX; x <= maxX; x++)
            {
            	if (!busStopsMap.ContainsKey(x)) 
                {
                	continue;
            	}
            
            	var minY = subway.Y - dist;
            	var maxY = subway.Y + dist;
            
            	for (var y = minY; y <= maxY; y++)
                {
                	if (!busStopsMap[x].ContainsKey(y)) 
                	{
                    	continue;
            		}
                
                	var distance = Distance(subway.X, subway.Y, x, y);
                	if (distance <= dist) 
                    {
              			subway.Count += busStopsMap[x][y];
                    }
                }
            }
        
        	if (subway.Count > max)
            {
            	indexMax = i;
            	max = subway.Count;
            }
        }
                        
        Console.WriteLine(indexMax + 1);
    }
                        
    public static double Distance(int x1, int y1, int x2, int y2)
    {
    	return Math.Sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
