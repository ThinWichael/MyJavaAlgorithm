package Quiz.Leetcode.Algorithm.Medium;

import java.util.ArrayList;
import java.util.List;

// Knapsack Problem

// Give a list of item and put them to a knapsack.
// These items have weight and cost and the
// knapsack has a limited loading weight.

// the items number is N 

// 1. Please find the maximum cost of the knapsack
// 2. Please get the content when we get the maximum cost
// 3.

public class Quiz_KnapsackProblem {

	public Quiz_KnapsackProblem() {
		// TODO Auto-generated constructor stub
	}
    // DP : brute force and memo[N+1][w + 1]
//	static int w = 0;
	int[] cost, weight;
	int[][] memo;
	int N;
	// Recursive DP ,time O(NW), space(NW)
	public int findMaxCostOfBag(int[] cost, int[] weight, int w) {
		N = cost.length;
		memo = new int[N+1][w+1];
		this.cost = cost;
		this.weight = weight;
		int maxCost = 0;
		int i = 0;
		
		maxCost = DP(i, w);
		
		return maxCost;
	}
	
	public int DP(int i, int W) {
		
		if(W < 0) return Integer.MIN_VALUE;
		if(i >= N) return 0;
		
		if(memo[i][W] != 0) return memo[i][W];
		
		// Max( not put , put)
		return memo[i][W] = Math.max(DP(i+1,W),
				                     DP(i+1,W - weight[i]) + cost[i] );
	}
    //Iteratively	time O(NW), space(NW)
	public int findMaxCostOfBag2(int[] cost, int[] weight, int w) {
		N = cost.length;
		memo = new int[N+1][w+1];
		this.cost = cost;
		this.weight = weight;
		// bottom-up memo
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= w; j++) {
				if( (j - weight[i]) < 0 ) { 
					// can't afford
					memo[i + 1][j] = memo[i][j]; // can't put
				} else { 
					// affordable
					memo[i+1][j] = Math.max(memo[i][j], // don't put
						           	memo[i][j - weight[i]] + cost[i]);// put in !
				}
			}
		}
		int max = 0;
		/*just check max*/
		for(int[] row: memo) {
			for(int e: row) {
				max = Math.max(max, e);
				System.out.print(e +", ");
			}
			System.out.println("");
		}/*just check max*/
		return max;
	}
	
	// Optimize bottom-up approach ,time O(NW), space(W)
	public int findMaxCostOfBag3(int[] cost, int[] weight, int w) {
		N = cost.length;
		int[] memo2 = new int[w+1];
		this.cost = cost;
		this.weight = weight;
		
		for(int i =0; i < N; i++) {
			for(int j =w; (j - weight[i]) >= 0 ; j--) {	// because we need "left side" data to compute, we need to iterate from tail.
				                                        // otherwise, we will override the data we need and we can find that when we need
				                                        // We can find this in the printing array of solution 2 
				memo2[j] = Math.max(memo2[j],
							        memo2[j - weight[i]] + cost[i]);
				System.out.print(memo2[j] +", ");
			}
			System.out.println("");
		}
		/*just check max*/
		int max = 0;
		for(int e: memo2) {	
			max = Math.max(max, e);
			System.out.print(e +", ");
		}
		System.out.println("");
		/*just check max*/
		return max;
	}
	
	// Question 2 : Get the content when you get the maximum
	public List<Integer> getTheMaxCostContent(int[] cost, int[] weight, int w){
		Quiz_KnapsackProblem q= new Quiz_KnapsackProblem();
		// "findMaxCostOfBag2"
		N = cost.length;
		memo = new int[N+1][w+1];
		this.cost = cost;
		this.weight = weight;
		// bottom-up memo
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= w; j++) {
				if( (j - weight[i]) < 0 ) { 
					// can't afford
					memo[i + 1][j] = memo[i][j]; // can't put
				} else { 
					// affordable
					memo[i+1][j] = Math.max(memo[i][j], // don't put
						           	memo[i][j - weight[i]] + cost[i]);// put in !
				}
			}
		}
		// "findMaxCostOfBag2" -End-
		List<Integer> res = new ArrayList<>();
		int j = w;
		for(int i = N-1 ; i >=0; i--) {
			if(j - weight[i] >= 0 &&
			   memo[i+1][j] == memo[i][j- weight[i]] + cost[i]) {
				res.add(i);
				j = j - weight[i];
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz_KnapsackProblem q= new Quiz_KnapsackProblem();
		int[] cost1 = { 50, 100 , 150 , 10 , 20 , 60, 75};
		int[] weig1 = { 5 ,  8  ,  10,   2 , 3 ,  4,  7};
		System.out.println(q.findMaxCostOfBag2(cost1, weig1, 13));
		System.out.println(q.findMaxCostOfBag3(cost1, weig1, 13));
		System.out.println(q.getTheMaxCostContent(cost1, weig1, 13).toString());
	}
//	0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
//	0, 0, 0, 0, 0, 50, 50, 50, 50, 50, 50, 50, 50, 50, 
//	0, 0, 0, 0, 0, 50, 50, 50, 100, 100, 100, 100, 100, 150, 
//	0, 0, 0, 0, 0, 50, 50, 50, 100, 100, 150, 150, 150, 150, 
//	0, 0, 10, 10, 10, 50, 50, 60, 100, 100, 150, 150, 160, 160, 
//	0, 0, 10, 20, 20, 50, 50, 60, 100, 100, 150, 150, 160, 170, 
//	0, 0, 10, 20, 60, 60, 70, 80, 100, 110, 150, 150, 160, 170, 
//	0, 0, 10, 20, 60, 60, 70, 80, 100, 110, 150, 150, 160, 170, 
//	170
//	50, 50, 50, 50, 50, 50, 50, 50, 50, 
//	150, 100, 100, 100, 100, 100, 
//	150, 150, 150, 150, 
//	160, 160, 150, 150, 100, 100, 60, 50, 50, 10, 10, 10, 
//	170, 160, 150, 150, 100, 100, 60, 50, 50, 20, 20, 
//	170, 160, 150, 150, 110, 100, 80, 70, 60, 60, 
//	170, 160, 150, 150, 110, 100, 80, 
//	0, 0, 10, 20, 60, 60, 70, 80, 100, 110, 150, 150, 160, 170, 
//	170
}
