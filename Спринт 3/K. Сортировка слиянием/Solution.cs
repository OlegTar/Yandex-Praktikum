using System.Collections.Generic;
using System.Linq;

public class Solution
{
    public static void MergeSort(List<int> array, int left, int right)
    {
        if (right - left <= 1)
        {
        	return;
        }
    	
    	var mid = (left + right) / 2;
    
    	MergeSort(array, left, mid);
    	MergeSort(array, mid, right);
    
    	var sortedArray = Merge(array, left, mid, right);
    	var j = 0;
    	for (var i = left; i < right; i++, j++)
        {
        	array[i] = sortedArray[j];
        }
    }

    public static List<int> Merge(List<int> array, int left, int mid, int right)
    {
        var newArray = new List<int>();
    	var indexInLeft = left;
    	var indexInRight = mid;
    
    	while (indexInLeft < mid && indexInRight < right)
        {
        	if (array[indexInLeft] <= array[indexInRight])
            {
            	newArray.Add(array[indexInLeft++]);
            }
        	else
            {
            	newArray.Add(array[indexInRight++]);
            }
        }
    	
    	if (indexInLeft == mid)
        {
        	while (indexInRight < right)
            {
            	newArray.Add(array[indexInRight++]);
            }
        }
    
    	if (indexInRight == right)
        {
        	while (indexInLeft < mid)
            {
            	newArray.Add(array[indexInLeft++]);
            }
        }
                     
        return newArray;
    }

    #if !REMOTE_JUDGE
    public static void Main(string[] args)
    {
        var a = new List<int> { 1, 4, 9, 2, 10, 11 };
        var b = Merge(a, 0, 3, 6);
        var expectedMergeResult = new List<int> {1, 2, 4, 9, 10, 11};
        System.Console.WriteLine(b.SequenceEqual(expectedMergeResult));
        var c = new List<int> {1, 4, 2, 10, 1, 2};
        MergeSort(c, 0, 6);
        var expectedMergeSortResult = new List<int> {1, 1, 2, 2, 4, 10};
        System.Console.WriteLine(c.SequenceEqual(expectedMergeSortResult));
    }
    #endif
}
