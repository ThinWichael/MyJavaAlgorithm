package Quiz.Leetcode.Algorithm.Hard;

import java.util.ArrayList;

//The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
//
//Given an integer n, return the number of distinct solutions to the n-queens puzzle.
//
//		Input: 4
//		Output: 2
//		Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
//		[
//		 [".Q..",  // Solution 1
//		  "...Q",
//		  "Q...",
//		  "..Q."],
//
//		 ["..Q.",  // Solution 2
//		  "Q...",
//		  "...Q",
//		  ".Q.."]
//		]
		
public class Quiz52_NQueens {

	// two solution: one is form book the other is bite manipulation trick
	
	// solution 1 : can get all pattern of final (not just number of patterns)
	// O ( (n+1)*n/2 ) => O (n^2)
	static int GRID_SIZE = 0;
    public static int totalNQueens(int n) {
    	GRID_SIZE = n;
    	Integer[] columns = new Integer[n] ; // as a chess board that memory the queens location; columns[rowN]
    	ArrayList<Integer[]> results = new ArrayList<>();
    	placeQueens(0, columns, results);
    	return results.size();
    }
	
    static void placeQueens(int row, Integer[] columns, ArrayList<Integer[]> results) {
    	if(row == GRID_SIZE) { // success !!
    		results.add(columns.clone());
    	} else {
    		for(int col = 0; col < GRID_SIZE; col++) {
    			if(checkValid(columns, row, col)) { // check for each col in current row
    				columns[row] = col;
    				placeQueens(row + 1, columns, results );
    			}
    		}
    	}
    }
    
    private static boolean checkValid(Integer[] columns, int preRow, int preColumn) {
    	for(int curRow = 0; curRow < preRow; curRow++) { // r1 is current row, r2 is the row already set queen
    		int curColumn = columns[curRow];
    		
    		/* Check if (r2, c2) invalidates (r1, c2) 
    		 * as a queen spot
    		 * */
    		if( curColumn == preColumn) return false;
    		/* Check diagonals if r-distance equals c-distance*/
    		if(Math.abs(curColumn - preColumn) == (preRow - curRow) ) return false;	
    	}
    	return true;
    }
    
	// solution 2 : bite
    static int count = 0;
    public static int totalNQueens2(int n) {
    	DFS(n, 0, 0, 0, 0);
        return count;
    }
	
    private static void DFS(int N, int row, int shu, int pie, int na) {
        int ok = ((1 << N) - 1) & ~(shu | pie | na);
        while (ok != 0) {
            int p = ok & -ok;
            ok ^= p;
            if (row == N - 1) {
                count++;
            } else {
                DFS(N, row + 1, shu ^ p, (pie ^ p) << 1, (na ^ p) >> 1);
            }
        }
    }
    
	public Quiz52_NQueens() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(totalNQueens(10));
		System.out.println(totalNQueens2(10));
	}

}

/*
 * Imagine those bits form a chessboard, take 4 queens as an example below:

0000 | 1000
0000 | 0010
0000 | 0100
0000 | 0001
Above is one sample chessboard(answer).

Left side zeros are those leading zeros, which is the result of (1 << N) - 1) & ~(...). I just wrote 4 leading zeros to represent those zeros, actually there are more than that.

shu ^ p means that col cannot put queens anymore, for example, in the board above, the first col is 1(queen), so latter rows cannot have 1 in the first col.

(pie ^ p) << 1 means move the 1(the queen) in the next row to left 1 step, which is exactly what diagonal means, and in Chinese 撇 means. So as (na ^ p) >> 1, which means anti-diagonal, in Chinese 捺.
 * 
 * */
