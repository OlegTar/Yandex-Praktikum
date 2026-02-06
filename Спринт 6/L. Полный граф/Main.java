import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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
            
            	if (u != v) {
            		edges.get(u).add(v);
            		edges.get(v).add(u);
                }
            }
        
        	String verdict = "YES";
        	int linkCount = n - 1;
        	for (int i = 1; i <= n; i++) {
            	if (edges.get(i).size() < linkCount) {
                	verdict = "NO";
            		break;
                }
        	}
        
        	System.out.println(verdict);
        } catch (Exception ignored) {
    	}
    }
}
