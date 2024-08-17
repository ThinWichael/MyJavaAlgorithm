package Quiz.Leetcode.Algorithm.Medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// https://www.youtube.com/watch?v=IWvbPIYQPFM

// give a grid with different color
// find the biggest continuous colored area

// G G B R
// G B R B
// R B B B

// output is 5 which is 5 Black cells

public class Quiz_MaxContinuousColorCells {

	public Quiz_MaxContinuousColorCells() {
		// TODO Auto-generated constructor stub
	}
    // solution DFS
	// hashSet 1 for memo passed cell

	static int[][] Dir = {{0,-1},{-1,0},{0,1},{1,0}}; // left , up , right, down
	static int rl,cl;
	public static int findMaxContinuousArea(String[][] grid) {
//		Queue<int[]> q = new LinkedList<int[]>();
	    rl = grid.length;
	    cl = grid[0].length;
		HashSet<String> passed = new HashSet<>();
		int counts = 0;
		int maxArea = 0;
		for(int r = 0; r < rl; r++) {
			for(int c = 0; c < cl; c++) {
				if(!passed.contains(r + "_" + c)) {
					counts = 0;
					counts = DFS(grid, new int[]{r,c}, counts, passed);
					System.out.println("color :" + grid[r][c] + ", counts:" + counts);
					maxArea = Math.max(maxArea, counts);
				}
			}
		}
		return maxArea;
	}
	
	public static int DFS(String[][] grid, int[] s,int count, HashSet<String> memo){
		memo.add(s[0] + "_" + s[1]);
		String color = grid[s[0]][s[1]];
		count++;
		
		for(int[] d: Dir) {
			int[] next = {s[0] + d[0], s[1]+d[1]};
			if( next[0] < 0 || next[0] >= rl || next[1] < 0 || next[1] >= cl) continue; // edge check
			if( memo.contains(next[0] + "_" + next[1])) continue; // repeat check
			if(!color.equals(grid[next[0]][next[1]])) continue; // color check
			
			count = DFS(grid, next, count, memo);
		}
		return count;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String[][] colorGrid = {
        		{"G","G","B","R"},
        		{"G","B","R","B"},
        		{"R","B","B","B"}};
        String[][] colorGrid2 = {
        		{"G","G","B","R"},
        		{"G","B","R","B"},
        		{"R","B","R","B"},
        		{"R","R","R","R"}};
        System.out.println(findMaxContinuousArea(colorGrid));
        System.out.println(findMaxContinuousArea(colorGrid2));
//        color :G, counts:3
//        color :B, counts:1
//        color :R, counts:1
//        color :B, counts:2
//        color :R, counts:7
//        color :B, counts:2
//        7
	}

}
