package Quiz.SortandSearch;

import java.util.Arrays;

//Sorted Merge: you are given two sorted arrays, A and B, where A has a large enough buffer at the end
//to hold B. Write a method to merge B and A in sorted order


public class Quiz10_1SortedMerge {

	// solution 1 mergeSort
	public static void mergeSorted(int[] Adata, int[] Bdata) {
		int mid = combineArray(Adata, Bdata);
		
		int front = 0;
		int end = mid + Bdata.length;
		
		int[] leftSub = Arrays.copyOfRange(Adata, front, ( mid + 1 ) + 1);
		int[] rightSub = Arrays.copyOfRange(Adata, mid + 1 , (end + 1) + 1);
		
		leftSub[leftSub.length -1] = 9999;
		rightSub[rightSub.length -1] = 9999;
		
		int indexL = 0;
		int indexR = 0;
		
		for(int i = 0; i <= end; i++) {
			if(leftSub[indexL] <= rightSub[indexR]) {
				Adata[i] = leftSub[indexL];
				indexL++;
			} else {
				Adata[i] = rightSub[indexR];
				indexR++;
			}
		}
		
	}
	
	// solution 2 insertion sort ()
	
    //return mid
	public static int combineArray(int[] a, int[] b) {
		int mid;
		int i = 0;
		while(a[i] != 0) { // find a tail
			i++;
		}
		
		mid = i -1;
		
		for(int j = 0; j < b.length; j++) {
			a[i] = b[j];
			i++;
		}
		
		return mid;
	}
	
	public static void main(String[] args) {
		// two sorted array, a has some buffer space
        int[] dataA = { 3, 17, 72, 88,  112, 912, 0, 0 , 0 , 0, 0}; 
	    int[] dataB = { 14, 17, 65, 91 };
	    
	    System.out.println("before combine: " + Arrays.toString(dataA));
//	    combineArray(dataA, dataB);
	    mergeSorted(dataA,dataB);
	    System.out.println("after combine: " + Arrays.toString(dataA));
	
	}
}
