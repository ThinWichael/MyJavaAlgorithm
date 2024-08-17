package Quiz.Leetcode.Algorithm.Medium;

import java.util.HashMap;

//723 Candy Crush
//

//Example:
//
//Input:
//board = 
//[[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]
//
//Output:
//[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]

public class Quiz723_CandyCrush {

	public Quiz723_CandyCrush() {
		// TODO Auto-generated constructor stub
		
	}
    // fast than 98% !! my solution !!!!!!
	public static int[][] candyCrush(int[][] board) {
        // r , c
        int cl = board[0].length;
        int rl = board.length;
        int[][] memo ;
        boolean isFinished = false;
        // "crush" check (while loop)
        while(!isFinished) {
        	memo = new int[rl][cl];
        	
        	for(int i = rl-1; i>=0; i--) { // by row ,bottom to top
        		for(int j = cl-1; j >= 2; j--) { // right to left	
        			if(board[i][j] == board[i][j-1] && board[i][j] == board[i][j-2]) {
        				memo[i][j] = memo[i][j-1] = memo[i][j-2] = board[i][j];		
        			}
        		}
        	}
           // by column
        	for(int i = 0; i < cl; i++) { // left to right
        		for(int j = rl-1; j>=2; j--) { // bottom to top
        			if(board[j][i] == board[j-1][i] && board[j][i] == board[j-2][i] ) {
        				memo[j][i] = memo[j-1][i] = memo[j-2][i] = board[j][i];
        			}
        		}
        	}
           //  mark the 3 together
           //  crush the cell to 0  
           boolean isAllZero = true;
           for(int i = 0; i < rl; i++) {
        	   for(int j = 0; j < cl; j++) {
        		   if(memo[i][j] != 0) {
        			   board[i][j] = 0;
        			   isAllZero = false;
        			}
        	   }
           }
           // else break
           if(isAllZero) break;
           
           // candy fall down
           for(int i = 0; i < cl; i++) { // left to right
       		for(int j = rl-1; j>=1; j--) { // bottom to top
       			if(board[j][i] == 0) {
       				int find = j-1;
       				while(find >= 0 && board[find][i] == 0) find--;
       				int dist = j - find;
       				while(find >= 0) { // shift down
       					board[find+dist][i] = board[find][i];
       					board[find][i] = 0;
       					find--;
       				}
       			}
       		}
           }
        }
        return board;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	int[][] test1 = {
		 {110, 5 ,112,113,114},
		 {210,211, 5 ,213,214},
		 {310,311, 3 ,313,314},
		 {410,411,412, 5 ,414},
		 { 5 , 1 ,512, 3 , 3},
		 {610, 4 , 1 ,613,614},
		 {710, 1 , 2 ,713,714},
		 {810, 1 , 2 , 1 , 1},
		 { 1 , 1 , 2 , 2 , 2},
		 { 4 , 1 , 4 , 4 ,1014}};
	
	int[][] test2 = {{2,5,5,3,5},
	                 {5,4,5,2,3},
	                 {2,2,4,5,4},
	                 {2,4,4,4,5},
	                 {5,2,3,3,5}};
	
	int[][] res = candyCrush(test2);
	for(int r = 0; r < res.length ; r++) {
		
      for(int c: res[r]) {
	    System.out.print(c + ",");
      }
      System.out.println(" ");
	}
}
}