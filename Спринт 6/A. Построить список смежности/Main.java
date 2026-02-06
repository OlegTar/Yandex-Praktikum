import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		//Scanner scanner = new Scanner(System.in);
    	//String line = scanner.nextLine();
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
    		String line = br.readLine();
    		String[] parts = line.split(" ");
    		int n = Integer.parseInt(parts[0]);
    		int m = Integer.parseInt(parts[1]);
    		//System.out.println(String.format("n = %d, m = %d", n, m));
    
    		List<List<Integer>> nodes = new ArrayList<>(n + 1);
    		for (int i = 0; i <= n; i++) {
    			nodes.add(new ArrayList<>());
        	}
    
    		for (int i = 0; i < m; i++) {
        		line = br.readLine();
        		parts = line.split(" ");
        		int v = Integer.parseInt(parts[0]);
        		int u = Integer.parseInt(parts[1]);

        		nodes.get(v).add(u);
        	}

    		for (int i = 1; i <= n; i++) {
    			List<Integer> vertices = nodes.get(i);
        		Collections.sort(vertices);
        		pw.print(String.format("%d ", vertices.size()));
        		for (int j = 0; j < vertices.size(); j++) {
            		pw.print(vertices.get(j) + " ");
            	}
        		pw.println();
        	}
        } catch (Exception ignored) {
        }
    }
}
