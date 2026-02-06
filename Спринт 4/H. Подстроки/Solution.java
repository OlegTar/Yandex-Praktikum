import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

public class Solution {
	public static void main(String args[]) {
    	try (
        	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
        	String s = reader.readLine();
        	int max = 0;
        	int head = 0;
        	int end = 0;
        
        	Set<Character> hash = new HashSet<Character>();
        	
        	while (end < s.length()) {
        		char newChar = s.charAt(end);
            
            	if (hash.contains(newChar)) {
                	char headChar = s.charAt(head);
                	
                	while (headChar != newChar) {
                    	hash.remove(headChar);
                		head++;
                    	headChar = s.charAt(head);
                    }

                	head++;             	
                } else {
                	hash.add(newChar);
                	max = Math.max(max, end - head + 1);
                }
            
            	end++;
            }
                      
            writer.write(Integer.toString(max));
            writer.flush();
        } catch (Exception ignored) {
        }
    }
}
