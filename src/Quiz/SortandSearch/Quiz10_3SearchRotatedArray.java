package Quiz.SortandSearch;

/* Given a sorted array of n integers that has been rotated an unknown number of times, write code to 
 * find an element in the array. You can assume that the array are originally sorted in increasing order.
 * 
 * Example:
 * {1, 3, 4, 7, 10, 14, 15, 16, 19, 20 ,25} --rotated 5 times--->> { 15, 16, 19, 20 , 25, 1, 3, 4, 7, 10, 14 }
 * find 4 in { 15, 16, 19, 20 , 25, 1, 3, 4, 7, 10, 14 } 
 * output: 7
 * */

public class Quiz10_3SearchRotatedArray {

	// binary search skill !!
	// need to ask interviewer to check if the element is repeatable like {3, 5, 8, 13, 20, 3, 3, 3, 3,
	// 3,}
	// in this solution, we assume it's repeatable

	static int search(int[] data, int x, int left, int right) {

		int mid = (left + right) / 2;
		if (x == data[mid])
			return mid;

		if (right < left)
			return -1;

		/*
		 * Either the left or right half must be normally ordered. Check x in the normal
		 * half first.
		 */
		if (data[left] < data[mid]) { // left is normal

			if (data[left] <= x && data[mid] > x) {
				return search(data, x, left, mid - 1); // search left
			} else {
				return search(data, x, mid + 1, right); // search right
			}

		} else if (data[left] > data[mid]) { // right is normal

			if (data[mid] < x && data[right] >= x) { // search right
				return search(data, x, mid + 1, right);
			} else {
				return search(data, x, left, mid - 1);
			}

		} else if (data[left] == data[mid]) { // left half or right half would be all repeated elements

			if (data[mid] != data[right]) { // right is not all repeated (not sure it's normal ordered or not)
				return search(data, x, mid + 1, right);
			} else { // both side, not sure which side is all repeat
              int result = search(data, x, left, mid-1); // go left first
              if(result == -1) { // go right (when left not founded)
            	  return search(data, x, mid+1, right);
              } else {
            	  return result;
              }
			}

		}
        return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int[] data1= { 15, 16, 19, 20 , 25, 1, 3, 4, 7, 10, 14 };
        int[] data2= { 25, 25, 25, 25 , 25, 1, 3, 4, 7, 10, 14, 25 };
        int[] data3= { 14, 15, 16, 19, 19, 19, 19, 20 , 25, 1, 3, 3, 3, 3, 4, 7, 10, 14 ,14 };
        
        System.out.println(search(data1, 19, 0, data1.length-1));
        System.out.println(search(data1, 14, 0, data1.length-1));
        
        System.out.println(search(data2, 25, 0, data2.length-1));
        System.out.println(search(data2, 14, 0, data2.length-1));
        
        System.out.println(search(data3, 14, 0, data3.length-1));
        System.out.println(search(data3, 20, 0, data3.length-1));
	}

}
