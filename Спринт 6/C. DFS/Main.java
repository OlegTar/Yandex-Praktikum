import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        ) {
    		String line = br.readLine();
    		String[] parts = line.split(" ");
    		int n = Integer.parseInt(parts[0]);
    		int m = Integer.parseInt(parts[1]);
    
    		List<PriorityQueue<Integer>> edges = new ArrayList<>();
    		edges.add(null);
    		for (int i = 0; i < n; i++) {
    			edges.add(new PriorityQueue<>());
        	}
    	
    		for (int i = 0; i < m; i++) {
    			line = br.readLine();
        		parts = line.split(" ");
        		int u = Integer.parseInt(parts[0]);
        		int v = Integer.parseInt(parts[1]);
        		edges.get(u).offer(-v);
            	edges.get(v).offer(-u);
        	}
        
        	//System.out.println(String.format("edges.size() = %d", edges.size()));
    
    		line = br.readLine();
    		int s = Integer.parseInt(line);
    
    		boolean[] visited = new boolean[n + 1];
    		for (int i = 0; i <= n; i++) {
    			visited[i] = false;
        	}
    	
    		Stack<Integer> stack = new Stack<>();
    		stack.push(s);
    		while (!stack.isEmpty()) {
    			int vertex = stack.pop();
        	
        		if (!visited[vertex]) {
        			visited[vertex] = true;
                	pw.print(vertex + " ");
                }
        		
            	PriorityQueue<Integer> pq = edges.get(vertex);
        		while (!pq.isEmpty()) {
        			stack.push(-pq.poll());
            	}
        	}
        	pw.println();
    	} catch (Exception ignored) {
        }
    }
}
