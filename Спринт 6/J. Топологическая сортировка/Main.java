import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
       		String line = br.readLine();
        	String[] parts = line.split(" ");
        	int n = Integer.parseInt(parts[0]);
        	int m = Integer.parseInt(parts[1]);
        
        	boolean[] visited = new boolean[n + 1];
        
        	List<List<Integer>> edges = new ArrayList<>();
        	for (int i = 0; i <= n; i++) {
            	edges.add(new ArrayList<>());
            }
        	
        	for (int i = 0; i < m; i++) {
        		line = br.readLine();
            	parts = line.split(" ");
            	int u = Integer.parseInt(parts[0]);
            	int v = Integer.parseInt(parts[1]);
            	edges.get(u).add(v);
            }
        
        	Stack<Integer> stack = new Stack<>();
        	for (int i = 1; i <= n; i++) {
        		dfs(edges, i, visited, stack);
            }
        
        	while (!stack.isEmpty()) {
        		pw.print(stack.pop());
            	pw.print(" ");
            }
        } catch (Exception ignored) {
        }
    }

	public static void dfs(List<List<Integer>> edges, int vertex, boolean[] visited, Stack<Integer> stack) {
    	if (!visited[vertex]) {
    		visited[vertex] = true;
        	
        	List<Integer> neighbors = edges.get(vertex);
        	for (int i = 0; i < neighbors.size(); i++) {
        		dfs(edges, neighbors.get(i), visited, stack);
            }
        	
        	stack.push(vertex);
        }
	}
}
