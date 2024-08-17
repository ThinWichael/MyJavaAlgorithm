package Quiz.Leetcode.Algorithm.Medium;

//918. Maximum Sum Circular Subarray
//
//Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
//
//Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
//
//Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
//
//Note:
//
//-30000 <= A[i] <= 30000
//1 <= A.length <= 30000
//
//Example 1:
//
//Input: [1,-2,3,-2]
//Output: 3
//Explanation: Subarray [3] has maximum sum 3
//Example 2:
//
//Input: [5,-3,5]
//Output: 10
//Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
//Example 3:
//
//Input: [3,-1,2,-1]
//Output: 4
//Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
//Example 4:
//
//Input: [3,-2,2,-3]
//Output: 3
//Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
//Example 5:
//
//Input: [-2,-3,-1]
//Output: -1
//Explanation: Subarray [-1] has maximum sum -1
 

public class Quiz918_MaxSumSubarrayCircular {

	public Quiz918_MaxSumSubarrayCircular() {
		// TODO Auto-generated constructor stub
	}

	// solution by @lee215
	// two case
	// 1 : the maximum sub array is in the middle      |--head--|  Maximum SubArray  |--tail--|
	// 2 : maximum sub array is head and tail combine. |--head--|  Minimum SubArray  |--tail--|
	
	// Prerequisite : Need to know "find the maximum value of sub-array" ==> Quiz 35
	
    public int maxSubarraySumCircular(int[] A) {
    int maxSum = A[0], maxTemp = A[0];
    int minSum = A[0], minTemp = A[0];
    int total = A[0];
    for(int i = 1; i < A.length; i++) {
    	maxTemp = Math.max(maxTemp + A[i], A[i]);
    	maxSum  = Math.max(maxSum, maxTemp);
    	minTemp = Math.min(minTemp + A[i], A[i]);
    	minSum  = Math.min(minSum, minTemp);
    	total += A[i];
    }
    return maxSum > 0? Math.max(maxSum, total - minSum) : maxSum;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz918_MaxSumSubarrayCircular q = new Quiz918_MaxSumSubarrayCircular();
		//Input: [5,-3,5]
		//Output: 10
		int[] A1 = {5,-3,5};
		//Input: [3,-1,2,-1]
		//Output: 4
		int[] A2 = {3,-1,2,-1};
		//Input: [3,-2,2,-3]
		//Output: 3
		int[] A3 = {3,-2,2,-3};
		//Input: [-2,-3,-1]
		//Output: -1
		int[] A4 = {-2,-3,-1};
		
		System.out.println(q.maxSubarraySumCircular(A1));
		System.out.println(q.maxSubarraySumCircular(A2));
		System.out.println(q.maxSubarraySumCircular(A3));
		System.out.println(q.maxSubarraySumCircular(A4));
		
	}

}
