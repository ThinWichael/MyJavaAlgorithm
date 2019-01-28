package Algorithm.Sort;

import java.util.Arrays;

// pick a pivot as standard
// conquer left side and right side

// worse case : O (n^2)
// average case : O (n logn)

// can set a threshold, if array is lower than it, go to insertion Sort

public class QuickSort {

	public void quickSort(int[] data, int left, int right) {

		if (data.length >= 10) {

			int index = partition(data, left, right); // make element on left less than pivot and on right more than
														// pivot

			if (left < index - 1) { // sort left side of pivot index
				quickSort(data, left, index - 1);
			}

			if (index < right) { // sort right side of pivot index
				quickSort(data, index, right);
			}

		} else {
			InsertionSort insertionSort = new InsertionSort();
			insertionSort.insertionSort(data);
		}

	}

	private int partition(int[] data, int left, int right) {
		// pick a pivot rule
		int pivot = data[(left + right) / 2];

		while (left <= right) {
			// Check element on left that should be on right
			while (data[left] < pivot)
				left++;

			// Check element on right that should be on left
			while (data[right] > pivot)
				right--;

			// go swap
			if (left <= right) {
				swap(data, left, right);
				left++;
				right--;
			}

		}
		return left; // the pivot location
	}

	private void swap(int[] data, int pointA, int pointB) {

		if (pointA < data.length && pointB < data.length) {

			int temp = data[pointB];

			data[pointB] = data[pointA];
			data[pointA] = temp;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickSort quickSort = new QuickSort();
		int data1[] = { 19, 42, 115, 8, 45, 49, 77, 810, 99, 1022, 333, 734 };

		System.out.println("Input array: " + Arrays.toString(data1));
		quickSort.quickSort(data1, 0, data1.length - 1);
		System.out.println("Output array: " + Arrays.toString(data1));
	}

}
