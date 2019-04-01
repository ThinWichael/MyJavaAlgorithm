package Quiz.Leetcode.Algorithm.Hard;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Quiz956_TallestBillboard {

	public Quiz956_TallestBillboard() {
		// TODO Auto-generated constructor stub
	}

	int maxRes = Integer.MIN_VALUE;
	int sum = 0;
	int halfSum = 0;

	public int tallestBillboard(int[] rods) {

		for (int i = 0; i < rods.length; i++) {
			sum += rods[i];
		}
		if (sum > 5000)
			return -1;

		halfSum = sum / 2;

		// sort descending
		Arrays.sort(rods);
		int leftS = 0, rightS = 0, j = rods.length - 1;
		for (int i = 0; i < 3; i++) { // 0, not use. 1, join left. 2, join right.
			dfs(rods, leftS, rightS, i, j);
		}
		return maxRes == Integer.MIN_VALUE ? -1 : maxRes;
	}

	public void dfs(int[] rods, int leftSum, int rightSum, int i, int j) {

		// if j rod > Sum/2, return
		if (j < 0 || rods[j] > halfSum)
			return;

		switch (i) {
		case 0:
			break; // i = 0 , not use
		case 1:
			leftSum += rods[j]; // i = 1 , join left
			break;
		case 2:
			rightSum += rods[j]; // i = 2 , join right
			break;
		}

		if (leftSum > halfSum || rightSum > halfSum)
			return;
		if (leftSum == rightSum)
			maxRes = Math.max(maxRes, leftSum + rightSum);
		// dfs, j + 1
		for (int next = 0; next < 3; next++) { // 0, not use. 1, join left. 2, join right.
			dfs(rods, leftSum, rightSum, next, j - 1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz956_TallestBillboard q = new Quiz956_TallestBillboard();
		int[] test1 = { 1, 2, 3, 6 }; // { 1, 2, 3, 6 }
		int[] test2 = { 1, 2, 3, 4, 5, 6 };
		System.out.println(q.tallestBillboard(test1));
		System.out.println(q.tallestBillboard(test2));
		int[] test3 = { 1, 2, 3, 4, 5, 6, 33, 12, 11, 22, 6, 9, 10, 13, 2, 2, 4, 17, 19 };
		long startTime = System.nanoTime();
//		System.out.println(q.tallestBillboard3(test3));
		System.out.println(q.tallestBillboard4(test3));
		
//		System.out.println(q.tallestBillboard(test3));
		long endTime = System.nanoTime();

		System.out.println("time: " + (endTime - startTime) / 1000000 + "ms");

//		System.out.println(q.tallestBillboard2(test2));
	}

	int NINF = Integer.MIN_VALUE / 3;
	Integer[][] memo;

	public int tallestBillboard2(int[] rods) {
		int N = rods.length;
		// "memo[n][x]" will be stored at memo[n][5000+x]
		// Using Integer for default value null
		memo = new Integer[N][10001];
		return (int) dp(rods, 0, 5000);
	}

	public int dp(int[] rods, int i, int s) {
		if (i == rods.length) {
			return s == 5000 ? 0 : NINF;
		} else if (memo[i][s] != null) {
			return memo[i][s];
		} else {
			int ans = dp(rods, i + 1, s);
			ans = Math.max(ans, dp(rods, i + 1, s - rods[i]));
			ans = Math.max(ans, rods[i] + dp(rods, i + 1, s + rods[i]));
			memo[i][s] = ans;
			return ans;
		}
	}

	// explanation see below :
	public int tallestBillboard3(int[] rods) {
		int[] dp = new int[5001]; // int[5001]
		for (int d = 1; d < 5001; d++)
			dp[d] = -10000;
		for (int x : rods) {
			int[] cur = dp.clone();
			for (int d = 0; d + x < 5001; d++) { // when d = 0, x would update the dp[d] first !!
				dp[d + x] = Math.max(dp[d + x], cur[d]);
				dp[Math.abs(d - x)] = Math.max(dp[Math.abs(d - x)], cur[d] + Math.min(d, x));
			}
			System.out.println("==");
			System.out.print("Rod " + x + ":");
			for (int i = 0; i < 101; i++)
				System.out.print(dp[i] + " | ");
		}
//        for(int i = 0; i < 101; i++) System.out.print(dp[i] + " | ");
		return dp[0];
	}

	// small case print : int[] test1 = { 1, 2, 3, 6 };
//    Rod 1: 0 | 0 | -9999 | -9999 | -9999 | -9999 |...
//    Rod 2: 0 | 1 | 0 | 0 | -9997 | -9997 | -9997 |...
//    Rod 3: 3 | 2 | 2 | 0 | 1 | 0 | 0 | -9994 | -9994 |...
//    Rod 6: 6 | 5 | 5 | 3 | 4 | 3 | 3 | 2 | 2 | 0 | 1 | 0 | 0 | -9994 | -9994 |...
	// hashMap version
	public int tallestBillboard4(int[] rods) {
		HashMap<Integer, Integer> dp = new HashMap<>();
		HashMap<Integer, Integer> curMap;
		dp.put(0, 0);
		for (int x : rods) {
			curMap = new HashMap<>(dp);
			for (int d : curMap.keySet()) {
				dp.put(x + d, Math.max(dp.getOrDefault(x + d, 0), curMap.get(d)));
				dp.put(Math.abs(x - d), Math.max(dp.getOrDefault(Math.abs(x - d), 0), curMap.get(d) + Math.min(d, x)));
			}
		}
		return dp.get(0);
	}
}
//tallestBillboard3 @lee215
//Explanation:
//dp[d] mean the maximum pair of sum we can get with pair difference d
//For example, if have a pair of sum (a, b) with a > b, then dp[a - b] = b
//If we have dp[diff] = a, it means we have a pair of sum (a, a + diff).
//And this is the biggest pair with difference = a
//
//dp is initializes with dp[0] = 0;
//
//Assume we have an init state like this
//------- y ------|----- d -----|
//------- y ------|
//
//case 1
//If put x to tall side
//------- y ------|----- d -----|----- x -----|
//------- y ------|
//
//We update dp[d + x] = max(dp[d + x], y )
//
//case 2.1
//Put x to low side and d >= x:
//-------y------|----- d -----|
//-------y------|--- x ---|
//
//We update dp[d-x] = max( dp[d - x], y + x)
//
//case 2.2
//Put x to low side and d < x:
//------- y ------|----- d -----|
//------- y ------|------- x -------|
//We update dp[x - d] = max(dp[x - d], y + d)
//
//case 2.1 and case2.2 can merge into dp[abs(x - d)] = max(dp[abs(x - d)], y + min(d, x))
//
//
//Time Complexity:
//O(NM), where we have
//N = rod.length <= 20
//S = sum(rods) <= 5000
//M = all possible sum = min(3^N, S)
//
//There are 3 ways to arrange a number: in the first group, in the second, not used.
//The number of difference will be less than 3^N.
//The only case to reach 3^N is when rod = [1,3,9,27,81...]
