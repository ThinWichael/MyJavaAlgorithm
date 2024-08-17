package Quiz.Leetcode.Algorithm.Medium;

//923. 3Sum With Multiplicity
//
//Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and A[i] + A[j] + A[k] == target.
//
//As the answer can be very large, return it modulo 10^9 + 7.
//
//Example 1:
//
//Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
//Output: 20
//Explanation: 
//Enumerating by the values (A[i], A[j], A[k]):
//(1, 2, 5) occurs 8 times;
//(1, 3, 4) occurs 8 times;
//(2, 2, 4) occurs 2 times;
//(2, 3, 3) occurs 2 times.
//Example 2:
//
//Input: A = [1,1,2,2,2,2], target = 5
//Output: 12
//Explanation: 
//A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
//We choose one 1 from [1,1] in 2 ways,
//and two 2s from [2,2,2,2] in 6 ways.
// 
//
//Note:
//
//3 <= A.length <= 3000
//0 <= A[i] <= 100
//0 <= target <= 300

public class Quiz923_3SumWithMutiplicity {

	public Quiz923_3SumWithMutiplicity() {
		// TODO Auto-generated constructor stub
	}
    // solution by @lee215, combination of 3 number
	// not easy ! It's really easy to confuse with the permutation
	public int threeSumMulti(int[] A, int target) {
		// conduct A to C[101]
		int[] c = new int[101];
		for (int a : A)
			c[a]++;
		// for i
		// for j = i
		// exclusive condition
		// three premutation
		long res = 0;
		for (int i = 0; i < 101; i++) {
			for (int j = i; j < 101; j++) { // j always >= i
				int k = target - i - j;
				if (k > 100 || k < 0)
					continue;

				if (i == j && j == k) { // three are same
					res += c[i] * (c[i] - 1) * (c[i] - 2) / (1 * 2 * 3); // n*(n-1)*(n-2).../1*2*3*...
				
				} else if (i == j && j != k) { // one is not same with other two
					res += c[i] * (c[i] - 1) / (1*2) * c[k];
				
				} else if (j < k) { // three are different , we only take k > J
					res += c[i] * c[j] * c[k];
					// because the case of (K < J) already count before. e.g. 30 = 90 - 20 -40 is repeat as
					// 40 = 90 -20 -30
				}
			}
		}
		return (int) (res % (1e9 + 7)); // AKA res % 1000000007
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz923_3SumWithMutiplicity q = new Quiz923_3SumWithMutiplicity();
		int[] A1 = { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5 };
		int target1 = 8;
		System.out.println(q.threeSumMulti(A1, target1));

		int[] A2 = {1,1,2,2,2,2};
		int target2 = 5;
		System.out.println(q.threeSumMulti(A2, target2));
		
	}

}
