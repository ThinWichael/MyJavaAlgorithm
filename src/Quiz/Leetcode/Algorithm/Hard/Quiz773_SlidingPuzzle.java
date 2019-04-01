package Quiz.Leetcode.Algorithm.Hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//import DataStructure.Queue.LinkedListQueue;
//import DataStructure.Queue.Queue;

//773. Sliding Puzzle
//
//On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
//
//A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
//
//The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
//
//Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

//Examples:
//
//Input: board = [[1,2,3],[4,0,5]]
//Output: 1
//Explanation: Swap the 0 and the 5 in one move.

//Input: board = [[1,2,3],[5,4,0]]
//Output: -1
//Explanation: No number of moves will make the board solved.

//Input: board = [[4,1,2],[5,0,3]]
//Output: 5
//Explanation: 5 is the smallest number of moves that solves the board.

//An example path:
//After move 0: [[4,1,2],[5,0,3]]
//After move 1: [[4,1,2],[0,5,3]]
//After move 2: [[0,1,2],[4,5,3]]
//After move 3: [[1,0,2],[4,5,3]]
//After move 4: [[1,2,0],[4,5,3]]
//After move 5: [[1,2,3],[4,5,0]]
//Input: board = [[3,2,4],[1,5,0]]
//Output: 14
//Note:
//
//board will be a 2 x 3 array as described above.
//board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

public class Quiz773_SlidingPuzzle {

	public Quiz773_SlidingPuzzle() {
		// TODO Auto-generated constructor stub
	}

	// two solution but same concept : BFS with different step move style

	// solution 1: int[] Dirs = {1,-1,3,-3} , need to watch out the corner case.
	final static int[] Dirs = { 1, -1, 3, -3 };

	public static int slidingPuzzle(int[][] board) {
		String start = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				start += board[i][j];
			}
		}

		Queue<String> q = new LinkedList<>();
		HashSet<String> visited = new HashSet<String>();
		q.add(start);
		visited.add(start);
		int counts = 0;
		// BFS
		while (!q.isEmpty()) {

			for (int n = q.size(); n > 0; n--) { // every elements in Queue, BFS need to use count down ! otherwise, the
													// q size will change
				String current = q.poll();
				if (current.equals("123450"))
					return counts;

				int i = current.indexOf('0');
				for (int k = 0; k < 4; k++) { // 4 direction to queue if able
					int j = i + Dirs[k];

					// edge case and corner case
					if (j < 0 || j > 5 || i == 2 && j == 3 || i == 3 && j == 2)
						continue;
					start = swap(current, i, j);
					if (visited.add(start)) {
						q.add(start);
					}
				}
			}
			counts++;
		}
		return -1;
	}

	private static String swap(String str, int i, int j) {
		char[] chars = str.toCharArray();
		chars[i] = chars[j];
		chars[j] = '0';
		return String.valueOf(chars);
	}

	// solution 2: int[][] dirs = new int[][] { { 1, 3 }, { 0, 2, 4 },
	// { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } }; considerate the scope of array
	static final int[][] dirs = new int[][] { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };

	public static int slidingPuzzle2(int[][] board) {
       String start = "";
       String target = "123450";
       Queue<String> q = new LinkedList<>();
       HashSet<String> visited = new HashSet<>();
       
       for(int i = 0; i < board.length; i++) {
    	   for(int j = 0; j < board[0].length; j++) {
    		   start += board[i][j];
    	   }
       }
       q.add(start);
       visited.add(start);
       
       int counts = 0;
       while(!q.isEmpty()) {
    	   
    	   for(int n = q.size(); n > 0; n--) { // deal with q
    		   String current = q.poll();
    		   if(current.equals(target)) return counts;
    		   
    		   int i = current.indexOf('0');
    		   for(int j: dirs[i]) { // for each step
    			  String next = swap(current, i, j); 
    		      if(visited.contains(next)) continue;
    		      
    		      visited.add(next);
    		      q.add(next);
    		   }
    		   
    	   }
    	   counts++;
       }
	return -1;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] board = { { 1, 2, 3 }, { 4, 0, 5 } };
		int[][] board2 = { { 1, 2, 3 }, { 5, 4, 0 } };
		int[][] board3 = { { 4, 1, 2 }, { 5, 0, 3 } };
		int[][] board4 = { { 3, 2, 4 }, { 1, 5, 0 } };
		System.out.println(slidingPuzzle(board));
		System.out.println(slidingPuzzle(board2));
		System.out.println(slidingPuzzle(board3));
		System.out.println(slidingPuzzle(board4));
		
		System.out.println("==solution 2==");
		System.out.println(slidingPuzzle2(board));
		System.out.println(slidingPuzzle2(board2));
		System.out.println(slidingPuzzle2(board3));
		System.out.println(slidingPuzzle2(board4));
	}

}
