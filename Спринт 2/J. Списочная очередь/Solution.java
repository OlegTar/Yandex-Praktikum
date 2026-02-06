import java.io.*;
import java.lang.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) {
    	try (
        	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
        	int n = Integer.parseInt(reader.readLine());
        	Queue queue = new Queue();
        
        	for (int i = 0; i < n; i++) {
            	String line = reader.readLine();
            	String[] parts = line.split(" ");
            
            	if (parts[0].equals("get")) {
                	Integer v = queue.get();
                	if (v == null) {
                    	writer.write("error");
                    } else {
                    	writer.write(Integer.toString(v));
                    }
					writer.newLine();
                } else if (parts[0].equals("size")) {
                	int size = queue.size();
                	writer.write(Integer.toString(size));
					writer.newLine();
                } else {
                	int value = Integer.parseInt(parts[1]);
                	queue.put(value);
                }            
        	}
        	writer.flush();
        } catch (Exception ignored) {
        }
    }

	public static class Node {
    	private int value;
    	private Node next;
    
    	public Node(int value) {
    		this.value = value;
        }
    	
    	public int getValue() {
    		return value;
        }
    
    	public Node getNext() {
    		return next;
        }
    
    	public void setNext(Node node) {
        	next = node;
    	}
	}

	public static class Queue {
    	private int size = 0;
    	private Node head = null;
    	private Node tail = null;
    	
    	public Integer get() {
        	if (size == 0) {
            	return (Integer) null;
        	}
        
        	int value = head.getValue();
        	head = head.getNext();
        
        	if (size > 1) {
				    size--;
          } else if (size == 1) {
            	size = 0;
            	tail = null;
          }
        
        	return value;
        }
    
    	public int size() {
    		return size;
      }
    
    	public void put(int value) {
        	Node node = new Node(value);
        	size++;
        
        	if (tail == null) {
            	head = node;
            	tail = node;
                return;
        	}
        
        	tail.setNext(node);
        	tail = tail.getNext();
        }
    }
}
