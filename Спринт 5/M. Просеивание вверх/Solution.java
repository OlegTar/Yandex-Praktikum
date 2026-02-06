public class Solution {
    public static int siftUp(int[] heap, int idx) {
        // Your code
        // “ヽ(´▽｀)ノ”
    	if (idx == 1) {
        	return 1;
    	}
    
    	int parentIndex = idx / 2;
    
    	if (heap[parentIndex] < heap[idx]) {
    		int temp = heap[idx];
        	heap[idx] = heap[parentIndex];
        	heap[parentIndex] = temp;
        	
        	return siftUp(heap, parentIndex);
        }
    
    	return idx;
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        assert siftUp(sample, 5) == 1;
    }
}
