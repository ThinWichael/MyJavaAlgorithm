package Algorithm.Sort;

import java.util.Arrays;

// advanced sort is radix sort
// same concept with bucket sort

public class BucketSort {

	int[] bucket ;

	public void sort(int[] data, int maxVal) {
		
		System.out.println("Before: " + Arrays.toString(data));
		
		bucket = new int[data.length + 1];
		
		for(int i: bucket) {
			i = 0;
		}
		
		for(int i=0; i < data.length; i++) {
			bucket[ data[i] ] ++; // plus one at matched bucket
		}
		
		int outPos = 0;
		for(int i = 0; i < bucket.length; i++) {
			for(int j = 0; j < bucket[i]; j++) {
				data[outPos++] = i; 
			}
		}
		
		System.out.println("After:  " + Arrays.toString(data));
	}
	
	public static void main(String[] args) {
		
		BucketSort bucketSort = new BucketSort();
		
		int maxVal = 7;
		int[] data = {5,3,0,2,4,1,0,7,5,2,3,1,4,6};
		
		bucketSort.sort(data, maxVal);
		
	}
	
}
