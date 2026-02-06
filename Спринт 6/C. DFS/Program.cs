using System.Collections.Generic;
using System.IO;
using System;

public class Program {
	public static void Main(string[] args) {
   		using (TextReader br = Console.In) {
        using (TextWriter pw = Console.Out) {
    		string line = br.ReadLine();
    		string[] parts = line.Split(" ");
    		int n = int.Parse(parts[0]);
    		int m = int.Parse(parts[1]);
    
    		List<PriorityQueue<int, int>> edges = new ();
    		edges.Add(null);
    		for (int i = 0; i < n; i++) {
    			edges.Add(new PriorityQueue<int, int>());
        	}
    	
    		for (int i = 0; i < m; i++) {
    			line = br.ReadLine();
        		parts = line.Split(" ");
        		int u = int.Parse(parts[0]);
        		int v = int.Parse(parts[1]);
        		edges[u].Enqueue(v, -v);
            	edges[v].Enqueue(u, -u);
        	}
          
    		line = br.ReadLine();
    		int s = int.Parse(line);
    
    		bool[] visited = new bool[n + 1];
    		for (int i = 0; i <= n; i++) {
    			visited[i] = false;
        	}
    	
    		Stack<int> stack = new ();
    		stack.Push(s);
    		while (stack.Count > 0) {
            	int vertex = stack.Pop();
        	
        		if (!visited[vertex]) {
        			visited[vertex] = true;
                	pw.Write(vertex + " ");
                }
        		
            	var pq = edges[vertex];
        		while (pq.Count > 0) {
        			stack.Push(pq.Dequeue());
            	}
        	}
        	pw.WriteLine();
        }}
	}
}
