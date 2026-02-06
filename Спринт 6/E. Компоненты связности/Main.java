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
        
        	int[] colors = new int[n + 1];
        	int componentColor = 1;
        	List<List<Integer>> linked = new ArrayList<>();
        	for (int i = 1; i < colors.length; i++) {
            	if (colors[i] == 0) {
                	linked.add(new ArrayList<>());
            		dfs(edges, i, colors, componentColor++, linked.getLast());
                }
            }
        
        	pw.println(componentColor - 1);
        	
        	for (List<Integer> link : linked) {
                Collections.sort(link);
                for (int nei : link) {
                	pw.print(nei + " ");
                }
				pw.println();        	
            }
        } catch (Exception ignored) {
        }
    }

	public static void dfs(List<List<Integer>> edges, int vertex, int[] colors, int componentColor, List<Integer> linked) {
    	if (colors[vertex] == 0) {
        	linked.add(vertex);
        	colors[vertex] = componentColor;
        	List<Integer> neighbors = edges.get(vertex);
        
        	for (int neighbor : neighbors) { 
        		dfs(edges, neighbor, colors, componentColor, linked);
            }
    	}
    }
}
