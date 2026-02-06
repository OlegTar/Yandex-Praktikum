public class Solution {
    public static int siftDown(int[] heap, int idx) {    
    	if (2*idx >= heap.length) {
        	return idx;
    	}
    	
    	int leftIndex = 2 * idx;
    	int rightIndex = leftIndex + 1;
    	int indexOfLargest = leftIndex;
        
    	if (rightIndex < heap.length) {
        	if (heap[leftIndex] < heap[rightIndex]) {
            	indexOfLargest = rightIndex;
            }
        }
    
    	if (heap[idx] < heap[indexOfLargest]) {
            int temp = heap[indexOfLargest];
        	heap[indexOfLargest] = heap[idx];
        	heap[idx] = temp;
        	
        	return siftDown(heap, indexOfLargest);
        }
    	
    	return idx;
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        assert siftDown(sample, 2) == 5;
    }
}
