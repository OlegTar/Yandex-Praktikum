import java.io.*;
import java.util.*;

public class Main {
	public enum Color {
		white,
        gray,
    	black
    }

	private static int[] entries;
	private static int[] leaves;
	private static Color[] color;
	private static int time = 0;
	
	public static void main(String[] args) {
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
            ) {
        	String line = br.readLine();
        	String[] parts = line.split(" ");
        	int n = Integer.parseInt(parts[0]);
        	int m = Integer.parseInt(parts[1]);
        
        	List<PriorityQueue<Integer>> edges = new ArrayList<>();
        	for (int i = 0; i <= n; i++) {
        		edges.add(new PriorityQueue<>());
            }
        
        	entries = new int[n + 1];
        	leaves  = new int[n + 1];
        	color = new Color[n + 1];
        
        	for (int i = 0; i < color.length; i++) {
            	color[i] = Color.white;
            }
        	
        	for (int i = 0; i < m; i++) {
        		line = br.readLine();
            	parts = line.split(" ");
            	int u = Integer.parseInt(parts[0]);
            	int v = Integer.parseInt(parts[1]);
            	edges.get(u).offer(v);
            }
        
        	dfs(1, edges);
        
        	for (int i = 1; i <= n; i++) {
            	pw.println(entries[i] + " " + leaves[i]);
            }
        } catch (Exception ignored) {
        }
    }

	private static void dfs(int vertex, List<PriorityQueue<Integer>> edges) {
    	entries[vertex] = time++;
        color[vertex] = Color.gray;
    	
    	PriorityQueue<Integer> neighbors = edges.get(vertex);
    	while (!neighbors.isEmpty()) {
        	int nei = neighbors.poll();
        	if (color[nei] == Color.white) {
    			dfs(nei, edges);
            }
        }
    	
    	leaves[vertex] = time++;
    }
}
