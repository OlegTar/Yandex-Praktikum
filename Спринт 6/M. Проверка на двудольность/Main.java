import java.io.*;
import java.util.*;

public class Main {
	public static class Pair<T1, T2> {
		private T1 first;
    	private T2 second;
    
    	public Pair(T1 first, T2 second) {
       		this.first = first;
        	this.second = second;
        }
    
    	public T1 getFirst() {
        	return first;
        }
    	
    	public T2 getSecond() {
    		return second;
        }
    }

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
        
        	boolean verdict = true;
        
        	Set<Integer> set1 = new HashSet<>();
        	Set<Integer> set2 = new HashSet<>();
        
        	Queue<Pair<Integer, Boolean>> queue = new ArrayDeque<>();
        	for (int i = 1; i <= n && verdict; i++) {
            	if (set1.contains(i) || set2.contains(i)) {
            		continue;
                }
        		queue.offer(new Pair(i, true));
            
        		while (!queue.isEmpty()) {
            		Pair<Integer, Boolean> vertexAndCheckSet = queue.poll();
        			int currentVertex = vertexAndCheckSet.getFirst();
            		boolean checkSet1 = vertexAndCheckSet.getSecond();
            
            		if (checkSet1) {
            			set1.add(currentVertex);
                	} else {
                		set2.add(currentVertex);
                	}
            
            		List<Integer> neighbors = edges.get(currentVertex);
            	
            		for (int neighbor : neighbors) {
                		if (checkSet1 && set2.contains(neighbor)) {
                			continue;
                    	}
                
                		if (!checkSet1 && set1.contains(neighbor)) {
                			continue;
                    	}
                    
            			if (checkSet1 && set1.contains(neighbor)) {
                    		verdict = false;
            				break;
                		}
                	
                		if (!checkSet1 && set2.contains(neighbor)) {
                    		verdict = false;
                			break;
                    	}
                
                    	queue.offer(new Pair<Integer, Boolean>(neighbor, !checkSet1));
                	}
            
            		if (!verdict) {
            			break;
                	}
                }
            }
        
        	System.out.println(verdict ? "YES" : "NO");
        } catch (Exception ignored) {
        }
    }
}
