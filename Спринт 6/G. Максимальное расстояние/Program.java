using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main(string[] args)
    {    	
    	using (TextReader br = Console.In)
    	{
        	string line = br.ReadLine();
        	string[] parts = line.Split(" ");
        	int n = int.Parse(parts[0]);
        	int m = int.Parse(parts[1]);
        	int[] distance = new int[n + 1];
        	List<List<int>> edges = new();
        
        	for (int i = 0; i <= n; i++)
            {
            	edges.Add(new List<int>());
            }
        	
        	for (int i = 0; i < m; i++)
            {
            	line = br.ReadLine();
            	parts = line.Split(" ");
            	int u = int.Parse(parts[0]);
            	int v = int.Parse(parts[1]);
            	edges[u].Add(v);
            	edges[v].Add(u);
            }
        
        	for (int i = 1; i <= n; i++)
            {
            	edges[i].Sort();
            }
        
        	line = br.ReadLine();
        	int s = int.Parse(line);
        
        	bool[] visited = new bool[n + 1];
        	Queue<int> planned = new();
        	planned.Enqueue(s);
        	visited[s] = true;
        	
        	while (planned.Count > 0)
            {
            	int u = planned.Dequeue();
            	List<int> neighbors = edges[u];
            	foreach (int neighbor in neighbors)
                {
                	if (visited[neighbor]) continue;
                	visited[neighbor] = true;
                	distance[neighbor] = distance[u] + 1;
                	planned.Enqueue(neighbor);
                }
            }
        
        	Console.WriteLine(distance.Max());
    	}
    }
}
