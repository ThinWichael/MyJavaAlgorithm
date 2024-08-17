package Algorithm.Search;

import java.util.Arrays;

import Algorithm.Sort.QuickSort;

// Not Binary "Tree"
//    Not Binary "Tree"
//       Not Binary "Tree"
// one is iterative and the other is recursive.

// The data array should be sorted !!!

public class BinarySearch {
    // iteratively
	public int binarySearch(int[] data, int x) {
		int low = 0;
		int high = data.length - 1;
		int mid;
		
		while(low <= high) {
			mid = (low + high) / 2;
			if(data[mid] < x) {
				low = mid + 1;
			} else if ( data[mid] > x) {
				high = mid - 1;
			} else {
				System.out.println("found " + x + " at " + mid);
				return mid; // location of x
			}
		}
		return -1; // error
	} 
	
	// Recursively
	public int binarySearch (int[] data, int x, int low , int high) {
		if(low > high) {
			return -1;
		} // error not found
		
		int mid = (low + high)/2;
		
		if(data[mid] < x) {
			return binarySearch(data, x, mid + 1, high);
		} else if(data[mid] > x) {
			return binarySearch(data, x, low, mid -1);
		} else {
			System.out.println("found " + x + " at " + mid);
			return mid;
		}
		
	}
	
	public static void main(String[] args) {

		QuickSort quickSort = new QuickSort();
		int data1[] = {19, 42, 115, 8, 45, 49, 77, 810, 99, 1022, 333, 734};
        // sort		
		System.out.println("Input array: " + Arrays.toString(data1));
		quickSort.quickSort(data1, 0, data1.length-1);
		System.out.println("Output array: " + Arrays.toString(data1));
		
		// search
		BinarySearch binarySearch = new BinarySearch();
		System.out.println(binarySearch.binarySearch(data1, 99));
		System.out.println(binarySearch.binarySearch(data1, 60, 0, data1.length-1));
	}

}
