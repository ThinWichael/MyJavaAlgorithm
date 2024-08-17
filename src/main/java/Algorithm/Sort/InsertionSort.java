package Algorithm.Sort;

import java.util.Arrays;

// like play card game, the key card compare to the "j pointer" card which is already sorted

//{ 2 , 5 , 8 , 3 , 99 ,121 ,15}
//          j   key                then, below
//{ 2 , 3 , 5 , 8 , 99 ,121 ,15}
 
// average case: O(n^2)
// best : O(n),  worse : O(n^2)

public class InsertionSort {

	public void insertionSort(int[] data) {
		
		for(int i = 1; i < data.length - 1; i++) {
			
			int key = data[i];
			int j = i-1;
			
			// j is to iterate sorted part to compare key and shift. Just like insert the key.
			while(j >= 0 && data[j] > key) {
				data[j+1] = data[j];
				j = j-1;
			}
			data[j+1] = key;
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InsertionSort insertionSort = new InsertionSort();
        int data1[] = {19, 42, 115, 8, 45, 49, 77, 810, 99, 1022, 333, 734};
		
		System.out.println("Input array: " + Arrays.toString(data1));
		insertionSort.insertionSort(data1);
		System.out.println("Output array: " + Arrays.toString(data1));
		
	}

}
