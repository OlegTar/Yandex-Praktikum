import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            	PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out))) 
            ){
        	String line = br.readLine();
        	String[] parts = line.split(" ");
        	int n = Integer.parseInt(parts[0]);
        	int m = Integer.parseInt(parts[1]);
        
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
            	edges.get(v).add(u);
            }
        
        	for (int i = 1; i <= n; i++) {
        		Collections.sort(edges.get(i));
            }
        
        	line = br.readLine();
        	int s = Integer.parseInt(line);
        
        	boolean[] visited = new boolean[n + 1];
        	visited[s] = true;
        
        	List<Integer> path = new ArrayList<>();
        	Queue<Integer> planned = new LinkedList<Integer>();
        	planned.offer(s);
        
        	while (!planned.isEmpty()) {
            	int u = planned.poll();
            	path.add(u);
            	List<Integer> neighbors = edges.get(u);
            	
            	for (int v : neighbors) {
            		if (visited[v]) continue;
                	planned.offer(v);
                	visited[v] = true;
                }
            }
        
        	for (int vertex : path) {
        		pw.print(vertex + " ");
            }
        } catch (Exception ignored) {
        }
	}
}
