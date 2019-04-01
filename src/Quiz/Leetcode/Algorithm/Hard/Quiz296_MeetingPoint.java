package Quiz.Leetcode.Algorithm.Hard;

import java.util.ArrayList;
import java.util.List;

//296. Best Meeting Point

//A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
//The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|

/*
 * Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance 
             of 2+2+2=6 is minimal. So return 6.
 * */

// solution :
// Sometimes, the question looks like want us to use dfs. Actually, in this question
// the mathematical is not that complicated here. We can handle it by two parts, x and y aka rows and column.

// solution1 is from @yavinci and @larrywang2014

public class Quiz296_MeetingPoint {

	public Quiz296_MeetingPoint() {
		// TODO Auto-generated constructor stub
	}
	// solution: O(2mn) , O (m+n)space
	public static int getMinTotalDistance(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		
		List<Integer> rows = new ArrayList<Integer>();
		List<Integer> cols = new ArrayList<Integer>();
		
		for(int i = 0; i < m ; i++) { // order by rows which means y in (x,y)
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == 1) 
					rows.add(i); // collect row value in ascending order
			}
		}
		
		for(int j = 0; j < n; j++) { // order by column which means x in (x,y)
			for(int i = 0; i < m; i++) {
				if(grid[i][j] == 1) {
					cols.add(j); // collect column value in ascending order.
				}
			}
		}
		
		return sumDistance(rows) + sumDistance(cols);
	}

	public static int sumDistance(List<Integer> point) {
		int low = 0;
		int high = point.size() - 1;
		int sum = 0;
		
		while(low < high) {
			sum += point.get(high--) - point.get(low++); 
		}
		
		return sum;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// n * m = 7 * 6
		int[][] grid1 = {{1,0,0,0,0,0,0},
				         {0,0,0,0,0,0,1},
				         {0,0,0,1,0,0,0},
			          	 {0,0,0,0,0,0,0},
				         {0,0,0,1,0,0,0},
				         {0,0,0,0,0,0,0}};
		
		System.out.println(getMinTotalDistance(grid1));
		
		int[][] grid2 = {{1,0,0,0,0,0,0},
		                 {0,0,0,0,0,0,1},
		                 {0,0,0,1,0,0,0},
	          	         {0,0,0,0,0,0,0},
		                 {0,0,0,1,0,0,0},
		                 {0,0,0,0,0,1,1}};
				
		System.out.println(getMinTotalDistance(grid2));
		System.out.println("solutino 2: " + minTotalDistance(grid2));
		// we can plus all x then sumX / number of x can get the mid point of x (y either)
	}

	// solution 2: different style
	public static int minTotalDistance(int[][] grid) {
	    int m = grid.length, n = grid[0].length;
	    int total = 0, Z[] = new int[m*n]; // space O (mn)
	    for (int dim=0; dim<2; ++dim) {
	        int i = 0, j = 0;
	        if (dim == 0) {
	            for (int x=0; x<n; ++x)
	                for (int y=0; y<m; ++y)
	                    if (grid[y][x] == 1)
	                        Z[j++] = x;
	        } else {
	            for (int y=0; y<m; ++y)
	                for (int g : grid[y])
	                    if (g == 1)
	                        Z[j++] = y;
	        }
	        while (i < --j)
	            total += Z[j] - Z[i++];
	    }
	    return total;
	}
	
}
