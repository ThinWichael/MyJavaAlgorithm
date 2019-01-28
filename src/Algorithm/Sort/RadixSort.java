package Algorithm.Sort;

import java.util.ArrayList;
import java.util.Arrays;

/* Radix Sort */
// Considerate it when the sort elements have a finite number.
// like age, test grade.

// O(k*n) , when the largest element is 4 digits, the k would be 4.
// and we sort elements through each digit. 0~9 10~99 100~999 1000~9999

public class RadixSort {
	
	public RadixSort() {
	};

	/* main function*/
	public void radixSort(int[] data) {
		
		int maxVal = getMaxVal(data, data.length);
		
		// digit = 1, 10, 100 ...
		for(int digit = 1; maxVal/digit > 0; digit *= 10)
			countSort(data, data.length, digit);
	}
	
	private int getMaxVal(int[] data, int n) {
		int maxVal = data[0];
		for(int ele: data) {
			if(ele > maxVal)
				maxVal = ele;
		}
		
		return maxVal;
	}
	
	private void countSort(int[] data, int n, int digit) {
		
		int[] output = new int[n];
		int[] count = new int[10];
		Arrays.fill(count, 0);
		
		int i;
		for(i = 0; i < n; i++) {
			count[ (data[i]/digit)%10 ]++; // plus 1 in the right bucket of count
		}
		
		System.out.println("Before count:" + Arrays.toString(count) );
		for( i = 1; i < 10; i++) {
			count[i] += count[i-1];
		}
		System.out.println("After count:" + Arrays.toString(count) ); // actual position of output array
		
		for(i = n-1 ; i >= 0; i--) {
			int mod = (data[i]/digit) % 10;
			output[ count[mod] - 1 ] = data[i];
			count[ mod ]--; 
		}
		
		System.out.println("Before data[]:" + Arrays.toString(data) );
		for (i = 0; i < n; i++)
			data[i] = output[i];
		System.out.println("After data[]:" + Arrays.toString(data) );
		
	}
	
	public static void main(String[] args) {
		RadixSort radixSort = new RadixSort();
		int arr[] = {170, 45, 75, 90, 802, 24, 2, 66, 133};
		radixSort.radixSort(arr);
		
//		[170, 45, 75, 90, 802, 24, 2, 66, 133] 原始
//
//		[170, 90, 802, 2, 133, 24, 45, 75, 66] 再 個位排列
//
//		[802, 2, 24, 133, 45, 66, 170, 75, 90] 再 十位排列
//
//		[2, 24, 45, 66, 75, 90, 133, 170, 802] 再 百位排列
		
	}
	
}
