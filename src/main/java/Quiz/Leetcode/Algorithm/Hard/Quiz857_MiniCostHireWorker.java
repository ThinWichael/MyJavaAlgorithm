package Quiz.Leetcode.Algorithm.Hard;

import java.util.Arrays;
import java.util.PriorityQueue;

//857. Minimum Cost to Hire K Workers

//There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
//
//Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
//
//1. Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
//2. Every worker in the paid group must be paid at least their minimum wage expectation.
//Return the least amount of money needed to form a paid group satisfying the above conditions.
//
//Example 1:
//
//Input: quality = [10,20,5], wage = [70,50,30], K = 2
//Output: 105.00000
//Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
//Example 2:
//
//Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
//Output: 30.66667
//Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
//
//Note:
//
//1. 1 <= K <= N <= 10000, where N = quality.length = wage.length
//2. 1 <= quality[i] <= 10000
//3. 1 <= wage[i] <= 10000
//4. Answers within 10^-5 of the correct answer will be considered correct.

public class Quiz857_MiniCostHireWorker {

	public Quiz857_MiniCostHireWorker() {

		//
	}

	// Solution : @lee215

	// compose a double[][] with { wage[i]/q[i], q[i] }
	// sort it
	public static double mincostToHireWorkers(int[] quality, int[] wage, int K) {
		// build double[][]
		double[][] workers = new double[wage.length][2];
		for (int i = 0; i < quality.length; i++) {
			workers[i] = new double[] { (double) wage[i] / quality[i], (double) quality[i] };
		}
		// sort double[][] by double[][0] Ascending
		Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));

		double wageSum = Double.MAX_VALUE, qSum = 0;
		PriorityQueue<Double> q = new PriorityQueue<Double>() ;
		// the pay group always follow the largest wage/q worker. Otherwise, someone
		// would not reach the least wage
		// loop for all workers
		for(double[] worker: workers) {
			qSum += worker[1];
			q.add(-worker[1]);
			double temp = 0.0;
			if(q.size() > K) {
				temp = q.poll();
				qSum += temp;
			}
		    
			if(q.size() == K && temp != worker[0]) wageSum = Math.min( wageSum , worker[0]*qSum); // largest worker[0]
		}
		return wageSum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] q = {3,1,10,10,1,20}, w = {4,8,2,2,7,28};
		int K = 4;
		System.out.println(mincostToHireWorkers(q, w, K));
		int[] q2 = {10,20,5}, w2 = {70,50,30};
		int K2 = 2;
		System.out.println(mincostToHireWorkers(q2, w2, K2));	
	}

}

//"1. Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group."
//So for any two workers in the paid group,
//we have wage[i] : wage[j] = quality[i] : quality[j]
//So we have wage[i] : quality[i] = wage[j] : quality[j]
//We pay wage to every worker in the group with the same ratio compared to his own quality.
//
//"2. Every worker in the paid group must be paid at least their minimum wage expectation."
//For every worker, he has an expected ratio of wage compared to his quality.
//
//So to minimize the total wage, we want a small ratio.
//So we sort all workers with their expected ratio, and pick up K first worker.
//Now we have a minimum possible ratio for K worker and we their total quality.
//
//As we pick up next worker with bigger ratio, we increase the ratio for whole group.
//Meanwhile we remove a worker with highest quality so that we keep K workers in the group.
//We calculate the current ratio * total quality = total wage for this group.
//
//We redo the process and we can find the minimum total wage.
//Because workers are sorted by ratio of wage/quality.
//For every ratio, we find the minimum possible total quality of K workers.
//
//Time Complexity
//O(NlogN) for sort.
//O(NlogK) for priority queue.
