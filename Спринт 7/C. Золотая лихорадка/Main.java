import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
    		String line = br.readLine();
        	int m = Integer.parseInt(line);
        	line = br.readLine();
        	int n = Integer.parseInt(line);
        
        	List<int[]> heaps = new ArrayList<>();
        	for (int i = 0; i < n; i++) {
        		line = br.readLine();
            	String[] parts = line.split(" ");
                int ci = Integer.parseInt(parts[0]);
            	int mi = Integer.parseInt(parts[1]);
            
            	heaps.add(new int[] { ci, mi });
            }
        
        	Collections.sort(heaps, (a, b) -> b[0] - a[0]);
        
        	List<int[]> result = new ArrayList<>();
        	int amount = 0;
        	for (int i = 0; i < heaps.size(); i++) {
            	if (amount + heaps.get(i)[1] > m) {
                	result.add(heaps.get(i));
                	result.get(result.size() - 1)[1] = m - amount;
                	amount = m;
                    break;
                }
            
            	amount += heaps.get(i)[1];
            	result.add(heaps.get(i));
            }
        	
        	long sum = 0;
        	for (int i = 0; i < result.size(); i++) {
        		sum += (long)result.get(i)[0] * (long)result.get(i)[1];
            }
        
        	System.out.println(sum);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
}
