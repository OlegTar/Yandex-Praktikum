import java.io.*;
import java.util.*;

public class Main {
	public static class Edge {
		private int vertex;
    	private int distance;
    	
    	public Edge(int vertex, int distance) {
        	this.vertex = vertex;
        	this.distance = distance;
        }
    	
    	public int getVertex() {
        	return vertex;
        }
    
    	public int getDistance() {
    		return distance;
        }
    }

	public static void main(String[] args) {
    	try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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
        
        	line = br.readLine();
        	parts = line.split(" ");
        	int s = Integer.parseInt(parts[0]);
        	int t = Integer.parseInt(parts[1]);
        
        	int dist = -1;
        	boolean[] visited = new boolean[n + 1];
        	Queue<Edge> queue = new ArrayDeque<>();
        	queue.offer(new Edge(s, 0));
        	
        	while (!queue.isEmpty()) {
        		Edge edge = queue.poll();
            	int currentVertex = edge.getVertex();
            	int distToCurrentVertex = edge.getDistance();
            
            	if (currentVertex == t) {
                	dist = distToCurrentVertex;
                	break;
            	}
            
            	if (visited[currentVertex]) {
            		continue;
                }
            	visited[currentVertex] = true;
            	
            	List<Integer> neighbors = edges.get(currentVertex);
            	for (int neighbor : neighbors) {
                	queue.offer(new Edge(neighbor, distToCurrentVertex + 1));
                }
            }
        
        	System.out.println(dist);
        } catch (Exception ignored) {
        }
    }
}
