import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
        	String line = br.readLine();
        	int n = Integer.parseInt(line);
        	List<int[]> intervals = new ArrayList<>();
        	for (int i = 0; i < n; i++) {
        		line = br.readLine();
            	String[] parts = line.split(" ");
                int start = toMin(parts[0]);
            	int end = toMin(parts[1]);
            
            	intervals.add(new int[] { start, end });
            }
        
        	intervals.sort(Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt(a -> a[0]));
        	
        	//System.out.println("test");
      
        	if (intervals.size() == 0) {
            	pw.println(0);
        		return;
            }
        
        	List<int[]> result = new ArrayList<>();
        	result.add(intervals.get(0));
        
        	for (int i = 1; i < intervals.size(); i++) {
            	if (intervals.get(i)[0] >= result.get(result.size() - 1)[1]) {
            		result.add(intervals.get(i));
                }
            }
        
        	System.out.println(result.size());
        	for (int i = 0; i < result.size(); i++) {
        		pw.printf("%s %s\n", toTime(result.get(i)[0]), toTime(result.get(i)[1]));
            }
    	} catch (Exception e) {
        	System.out.println(e.getMessage());
    	}
    }

	public static int toMin(String time) throws NumberFormatException {
		String[] parts = time.split("\\.");
    	int h = Integer.parseInt(parts[0]);
    	h *= 60;
    	if (parts.length > 1) {
    		int m = Integer.parseInt(parts[1]);
        	h += m;
        }
    	
    	return h;
    }

	public static String toTime(int min) {
		int h = min / 60;
    	int m = min % 60;
    	if (m == 0) {
    		return Integer.toString(h);
        }
    	return String.format("%d.%d", h, m);
    }
}
