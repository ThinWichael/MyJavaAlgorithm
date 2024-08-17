package Algorithm.Sort;

import java.util.Arrays;

/* MergeSort */
// first: divide
// second: conquer and merge

// worse case : O(n logn)
// average case : O(n logn)

// space : O(n) , not appropriate to memory less computer . Otherwise, it's a good sorting.

public class MergeSort {

	final int max = 1000;
	
	public MergeSort() {
		// TODO Auto-generated constructor stub
	}

	/* Core algorithm*/
	public void mergeSort(int[] data, int front, int end ) {
		
		if (front < end) {
			int mid = (front + end) / 2;
			mergeSort(data, front, mid);
			mergeSort(data, mid+1, end);
			merge(data, front, mid , end);
		}
	
	}
	
	private void merge(int[] data, int front, int mid, int end) {
		// the tail +1 more for setting a Max value used to compare in loop
		int[] leftSub = Arrays.copyOfRange(data, front, (mid + 1) + 1);
		int[] rightSub = Arrays.copyOfRange(data, mid + 1, (end + 1) + 1);
	
	    leftSub[leftSub.length -1] = max;
	    rightSub[rightSub.length - 1] = max;
	    
	    int indexL = 0;
	    int indexR = 0;
	    
	    for(int i = front; i <= end; i++) {
	    	
	    	if( leftSub[indexL] <= rightSub[indexR]) {
	    		data[i] = leftSub[indexL];
	    		indexL++;
	    	} else {
	    		data[i] = rightSub[indexR];
	    		indexR++;
	    	}
	    		
	    }
	}
	
	public static void main(String[] args) {
	
		MergeSort mergeSort = new MergeSort();
		int data1[] = {19, 42, 115, 8, 45, 49, 77, 810};
		
		int data2[] = {19, 42, 115, 8, 45, 49, 77, 810, 99, 1022};
		
		System.out.println("Input array: " + Arrays.toString(data1));
		mergeSort.mergeSort(data1, 0, data1.length - 1);
		System.out.println("Output array: " + Arrays.toString(data1));
		
		System.out.println("Input array: " + Arrays.toString(data2));
		mergeSort.mergeSort(data2, 0, data2.length - 1);
		System.out.println("Output array: " + Arrays.toString(data2));
		
	}
	
}
