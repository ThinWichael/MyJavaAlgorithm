package Quiz.Leetcode.Algorithm.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/* 975. Odd Even Jump
 * 
 * You are given an integer array A.  From some starting index, you can make a series of jumps.  The (1st, 3rd, 5th, ...) jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even numbered jumps.

You may from index i jump forward to index j (with i < j) in the following way:

During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the smallest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the largest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
(It may be the case that for some index i, there are no legal jumps.)
A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more than once.)

Return the number of good starting indexes.
 * 
 * */

/*
 * Note:

1 <= A.length <= 20000
0 <= A[i] < 100000
 * 
 * */

public class Quiz975_OddEvenJump {

	public Quiz975_OddEvenJump() {
		// TODO Auto-generated constructor stub
	}

	// my solution : 1000 ~ 1100 ms
    public static int oddEvenJumps(int[] A) {
        int n = A.length;
        int counts = 0;
        int[] memory = new int[n]; // jump success > 0:default , 1: oddJump to success, 2: evenJump to success
        List<Integer> traceOdd = new ArrayList<Integer>();
        List<Integer> traceEven = new ArrayList<Integer>();
        for(int i=0; i < n; i++){
//        	System.out.println("==" + i + "turn==");
            counts += goJump(A, i, 1, memory ,traceOdd , traceEven);
//            System.out.println("counts :" +counts );
        }
//        for(int e: memory) System.out.print(e + ", ");
        
        return counts;
    }
    
    private static int goJump(int[] A, int i, int jump, int[] memory, List<Integer> traceOdd, List<Integer> traceEven) {
        int n = A.length;
        
        if(memory[i] == 3) {
        	return 1;
        }
        
        if(i == (n-1) ) {// found !! Goal !!
        	for(Integer oJump: traceOdd) {
        		if(memory[oJump] == 0) memory[oJump] = 1;
        		if(memory[oJump] == 2) memory[oJump] = 3;
        	}
        	for(Integer eJump: traceEven) {
        		if(memory[eJump] == 1 ) {
        			memory[eJump] = 3; // this index has success to end with both oddJump and evenJump
        		} else if(memory[eJump] == 0) {
        			memory[eJump] = 2;
        		}
        	}
        	traceOdd.clear();
        	traceEven.clear();
            return 1;
        } 
        
        int current = A[i];
        if(jump % 2 == 1) { // find larger but smallest 
        	
        	if(memory[i] == 1) return 1;   	
            int sL = 100001; // smallestLarge
            int j = i + 1;
            int next = 0;
            while( j < n) { // find smallest one
                if(A[j] > current && A[j] < sL ) {sL = A[j]; next = j;}
                if(A[j] == current) break;
                j++;
            }
            if(sL == 100001) { // not found
            	traceOdd.clear();
            	traceEven.clear();
            	return 0;
            } 
            
            traceOdd.add(i);
            return goJump(A,next, jump+1, memory, traceOdd, traceEven);
        } else { // find smaller but largest
        	if(memory[i] == 2) return 1;
            int lS = -1; // largest Small one
            int j = i + 1;
            int next = 0;
            while( j < n) {
                if(A[j] < current && A[j] > lS) { lS = A[j]; next = j;}
                if(A[j] == current) break;
                j++;
            }
            if(lS == -1) { // not found
            	traceOdd.clear();
            	traceEven.clear();
            	return 0; 
            }
            traceEven.add(i);
            return goJump(A,next, jump+1, memory, traceOdd, traceEven);
        }
//        return 0;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // need to check and optimize
		int[] A = {10,13,12,14,15};
		int[] A2 = {2,3,1,1,4};
		int[] A3 = {5,1,3,4,2};
//		int[] A4 = {10,13,12,14,15};
		System.out.println(oddEvenJumps(A));
		System.out.println(oddEvenJumps(A2));
		System.out.println(oddEvenJumps(A3));
		
		// == Big case ==
		int[] bigA = new int[20000];
		Random rand = new Random();
		for(int i = 0; i < 20000; i++) {
			int n = rand.nextInt(100001);
			bigA[i] = n;
		}
		long startT = System.nanoTime();
//		System.out.println(oddEvenJumps(bigA)); // 12XX mx
		System.out.println(oddEvenJumps2(bigA)); // 20 ms
		long endT = System.nanoTime();
		long duration = (endT - startT)/1000000;
		System.out.println("excution time : " + duration + "ms");
	
	}

	// Excellent solution : @lee215
	// treemap and "jump from end" is the main strategy.
	public static int oddEvenJumps2(int[] A) {
        int n  = A.length, res = 1;
        boolean[] higher = new boolean[n];
        boolean[] lower = new boolean[n];
        higher[n - 1] = lower[n - 1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n - 1], n - 1);
        for (int i = n - 2; i >= 0; --i) { // from end
            Map.Entry hi = map.ceilingEntry(A[i]);
            Map.Entry lo = map.floorEntry(A[i]);
            if (hi != null) higher[i] = lower[(int)hi.getValue()];
            if (lo != null) lower[i] = higher[(int)lo.getValue()];
            if (higher[i]) res++;
            map.put(A[i], i); // support
        }
        return res;
    }
	/*We need to jump higher and lower alternately to the end.

Take [5,1,3,4,2] as example.

If we start at 2,
we can jump either higher first or lower first to the end,
because we are already at the end.
higher(2) = true
lower(2) = true

If we start at 4,
we can't jump higher, higher(4) = false
we can jump lower to 2, lower(4) = higher(2) = true

If we start at 3,
we can jump higher to 4, higher(3) = lower(4) = true
we can jump lower to 2, lower(3) = higher(2) = true

If we start at 1,
we can jump higher to 2, higher(1) = lower(2) = true
we can't jump lower, lower(1) = false

If we start at 5,
we can't jump higher, higher(5) = false
we can jump lower to 4, lower(5) = higher(4) = false
	 * */
}

/*Example 1:

Input: [10,13,12,14,15]
Output: 2
Explanation: 
From starting index i = 0, we can jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3], A[4] that is greater or equal to A[0]), then we can't jump any more.
From starting index i = 1 and i = 2, we can jump to i = 3, then we can't jump any more.
From starting index i = 3, we can jump to i = 4, so we've reached the end.
From starting index i = 4, we've reached the end already.
In total, there are 2 different starting indexes (i = 3, i = 4) where we can reach the end with some number of jumps.
Example 2:

Input: [2,3,1,1,4]
Output: 3
Explanation: 
From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:

During our 1st jump (odd numbered), we first jump to i = 1 because A[1] is the smallest value in (A[1], A[2], A[3], A[4]) that is greater than or equal to A[0].

During our 2nd jump (even numbered), we jump from i = 1 to i = 2 because A[2] is the largest value in (A[2], A[3], A[4]) that is less than or equal to A[1].  A[3] is also the largest value, but 2 is a smaller index, so we can only jump to i = 2 and not i = 3.

During our 3rd jump (odd numbered), we jump from i = 2 to i = 3 because A[3] is the smallest value in (A[3], A[4]) that is greater than or equal to A[2].

We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.

In a similar manner, we can deduce that:
From starting index i = 1, we jump to i = 4, so we reach the end.
From starting index i = 2, we jump to i = 3, and then we can't jump anymore.
From starting index i = 3, we jump to i = 4, so we reach the end.
From starting index i = 4, we are already at the end.
In total, there are 3 different starting indexes (i = 1, i = 3, i = 4) where we can reach the end with some number of jumps.
Example 3:

Input: [5,1,3,4,2]
Output: 3
Explanation: 
We can reach the end from starting indexes 1, 2, and 4.
 
 * 
 * 
 * 
 * */

//my solution : 1000 ~ 1100 ms
//public static int oddEvenJumps(int[] A) {
//    int n = A.length;
//    int counts = 0;
//    for(int i=0; i < n; i++){
////    	System.out.println("==" + i + "turn==");
//        counts += goJump(A, i, 1);
//    }
//    return counts;
//}
//
//private static int goJump(int[] A, int i, int jump) {
//    int n = A.length;
//    if(i == (n-1) ) return 1; // found !! Goal !!
//    
//    int current = A[i];
//    if(jump % 2 == 1) { // find larger but smallest 
//        int sL = 100001; // smallestLarge
//        int j = i + 1;
//        int next = 0;
//        while( j < n) { // find smallest one
//            if(A[j] > current && A[j] < sL ) {sL = A[j]; next = j;}
//            if(A[j] == current) break;
//            j++;
//        }
//        if(sL == 100001) return 0; // not found
//        return goJump(A,next, jump+1);
//    } else { // find smaller but largest
//        int lS = -1; // largest Small one
//        int j = i + 1;
//        int next = 0;
//        while( j < n) {
//            if(A[j] < current && A[j] > lS) { lS = A[j]; next = j;}
//            if(A[j] == current) break;
//            j++;
//        }
//        if(lS == -1) return 0; // not found
//        return goJump(A,next, jump+1);
//    }
////    return 0;
//}
//
