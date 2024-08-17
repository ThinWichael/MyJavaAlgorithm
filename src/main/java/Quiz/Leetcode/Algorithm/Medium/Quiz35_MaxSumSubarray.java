package Quiz.Leetcode.Algorithm.Medium;

public class Quiz35_MaxSumSubarray {

	public Quiz35_MaxSumSubarray() {
		// TODO Auto-generated constructor stub
	}

	// My solution
	public int maxSubArray(int[] nums) {
		int n = nums.length;
		int maxValue = Integer.MIN_VALUE;
		int maxSum = 0;
		int i = 0;

		while (i < n) {

			while (i < n && nums[i] < 0) {
				maxValue = Math.max(maxValue, nums[i]);
				i++;
			}
			// find the first positive int
			int sum = 0;
			while (sum >= 0 && i < n) {

				maxValue = Math.max(maxValue, nums[i]);

				sum += nums[i];
				if (sum > maxSum) {
					maxSum = sum;
				}
				i++;
			}

		}

		if (maxValue < 0) {
			// all are negative number
			return maxValue;
		}

		return maxSum;

	}

	// @FujiwaranoSai solution

	public int maxSubArray2(int[] A) {
		int n = A.length;
		int[] dp = new int[n];// dp[i] means the maximum subarray ending with A[i];
		dp[0] = A[0];
		int max = dp[0];

		for (int i = 1; i < n; i++) {
			dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
			max = Math.max(max, dp[i]);
		}

		return max;
	}

	// best solution !
	public int maxSubArray3(int[] A) {
		int max = A[0];
		int maxTemp = A[0];
		for(int i = 1; i < A.length; i++) {
			maxTemp = Math.max(maxTemp + A[i], A[i]); 
			max = Math.max(maxTemp, max);
		}
		return max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz35_MaxSumSubarray q = new Quiz35_MaxSumSubarray();
		int[] test1 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(q.maxSubArray(test1)); 
		System.out.println(q.maxSubArray2(test1));
		System.out.println(q.maxSubArray3(test1));
	}

}
