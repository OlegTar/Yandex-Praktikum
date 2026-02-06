import java.io.*;
import java.util.*;

public class Main {
	public static class Pair<T1, T2> {
    	private T1 key;
    	private T2 value;
    	public Pair(T1 key, T2 value) {
        	this.key = key;
        	this.value = value;
    	}

    	public T1 getKey() {
        	return key;
    	}

    	public T2 getValue() {
        	return value;
    	}
	}

	public static void main(String[] args) {
    	try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
        	String line = reader.readLine();
        	String[] parts = line.split(" ");
        	int n = Integer.parseInt(parts[0]);
        	int m = Integer.parseInt(parts[1]);
        
        	List<List<Pair<Integer, Integer>>> edges = new ArrayList<>();
        	for (int i = 0; i <= n; i++) {
            	edges.add(new ArrayList<>());
            }
        
        	for (int i = 0; i < m; i++) {
        		line = reader.readLine();
           		parts = line.split(" ");
            	int u = Integer.parseInt(parts[0]);
            	int v = Integer.parseInt(parts[1]);
            	int weight = Integer.parseInt(parts[2]);
            
            	edges.get(u).add(new Pair<>(v, weight));
            	edges.get(v).add(new Pair<>(u, weight));
            }
        
        	int[] distance = new int[n + 1];      
        	boolean[] visited = new boolean[n + 1];
        
        	Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        	for (int i = 1; i <= n; i++) {
            	Arrays.fill(visited, false);
				Arrays.fill(distance, Integer.MAX_VALUE);
        		distance[i] = 0;
            
            	while (true) {
            		int vertex = getUnvisitedVertexWithMinDist(distance, visited);
                	if (vertex == -1) {
                		break;
                    }
                
                	visited[vertex] = true;
                
                	List<Pair<Integer, Integer>> neighbors = edges.get(vertex);
                	for (Pair<Integer, Integer> neighbor : neighbors) {                    	           	
                    	relax(distance, vertex, neighbor);
                    }
                }
            	
            	for (int j = 1; j <= n; j++) {
                	int path = distance[j];
                	if (path == Integer.MAX_VALUE) {
                    	path = -1;
                	}
                	pw.print(String.format("%d ", path));
                }
            	pw.println();
            }
        } catch (Exception ignored) {
        }
    }

	public static int getUnvisitedVertexWithMinDist(int[] distance, boolean[] visited) {
		int currentMinimum = Integer.MAX_VALUE;
    	int currentVertex = -1;
    	for (int i = 1; i < distance.length; i++) {
        	if (visited[i]) continue;
        	if (distance[i] < currentMinimum) {
            	currentMinimum = distance[i];
            	currentVertex = i;
        	}
        }
    
    	return currentVertex;
    }

	public static void relax(int[] distance, int u, Pair<Integer, Integer> vAndWeight) {
    	int v = vAndWeight.getKey();
    	int weight = vAndWeight.getValue();
    	
    	if (distance[v] > distance[u] + weight) {
    		distance[v] = distance[u] + weight;
        }
    }
}
