import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
    	try {
			Scanner scanner = new Scanner(System.in);
    		int n = scanner.nextInt();
        	//System.out.println(n);
        	scanner.nextLine();
    	
    		String line = scanner.nextLine();
        	//System.out.println(line);
    		int[] stocks = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
    
    		int[] buys = new int[stocks.length];
        	int[] sells = new int[stocks.length];
        
        	buys[0] = -stocks[0];
        	sells[0] = 0;
        
        	//7 1 5 3 6 4
        
        	for (int i = 1; i < stocks.length; i++) {
        		buys[i] = Math.max(buys[i - 1], sells[i - 1] - stocks[i]);
            	sells[i] = Math.max(sells[i - 1], buys[i - 1] + stocks[i]);
            	//System.out.printf("buys[%d] = %d\n", i, buys[i]);
            	//System.out.printf("sells[%d] = %d\n", i, sells[i]);
            }
        
    		System.out.println(sells[sells.length - 1]);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
}
