package Algorithm.Search;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// concept : https://www.geeksforgeeks.org/a-search-algorithm/
// f = g + h , g : distance of start to current
//             h : distance of current to end

// h can be estimated in three heuristic way:
// 1. Manhattan Distance : h = abs(cur.x - goal.x) + abs(cur.y - goal.y);
//                         using in only four direction movement
//   S
//   |
//   L--------E

// 2.Diagonal Distance : h = max{ abs(cur.x - goal.x), abs(cur.y - goal.y)}
//                       using in eight direction movement
//   S---------
//
//            E

// 3.Euclidean Distance : h = sqrt( (cur.x - goal.x)^2 + (cur.y - goal.y)^2 )
//                        real moving ! can go 360 direction

// implementation is just like Dijkstra algorithm but adding 'h' argument. 
// Normally, Dijkstra is more using in graph and A* search is properly using in grid.
public class AstarSearch {

	public AstarSearch() {
		// TODO Auto-generated constructor stub
	}
	int mode = 2;
	class Pair {
		final int r,c;
		
		int f;
		int g = 0;
		int h;
		Pair parent;
		Pair(int[] s, int[] goal){
			this.r = s[0];
			this.c = s[1];
			setH(mode, goal);
		}
		// 1. Manhattan Distance 2.Diagonal Distance 3.Euclidean Distance
		public void setH(int mode, int[] goal){
			switch(mode) {
			case 1: // four direction
				this.h = Math.abs(this.r - goal[0]) + Math.abs(this.c - goal[1]);
				break;
			case 2: // eight direction
				this.h = Math.max( Math.abs(this.r - goal[0]), Math.abs(this.c - goal[1]));
			    break;
			case 3: // 360 degree direction
				Double hValue = (Double) Math.sqrt( Math.pow((this.r - goal[0]),2) + Math.pow((this.c - goal[1]),2) );
			    this.h = hValue.intValue();
			    break;
			default:
				this.h = Math.max( Math.abs(this.r - goal[0]), Math.abs(this.c - goal[1]));
			    break;
			}
			
		}
		@Override
		public boolean equals(Object o) {
			// If the object is compared with itself then return true   
	        if (o == this) { 
	            return true; 
	        } 
	  
	        /* Check if o is an instance of Pair or not 
	          "null instanceof [type]" also returns false */
	        if (!(o instanceof Pair)) { 
	            return false; 
	        } 
	          
	        // typecast o to Pair so that we can compare data members  
	        Pair p = (Pair) o; 
	          
	        // Compare the data members and return accordingly  
	        return r == p.r
	                && c == p.c ; 
		}
	}
	final static int[][] dir8 = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};// 8 direction
	// Grid version
	public static Pair findShortestPath(int[][] grid, int[] s, int[] goal) {
		AstarSearch aa = new AstarSearch();
		int rl = grid.length;
		int cl = grid[0].length;
		
		if(grid[s[0]][s[1]] > 0) return null;
		// close list (HashSet)
		Set<String> closeSet = new HashSet<>();
		// open list (PriorityQueue)
		PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> (a.f - b.f));
		Pair start = aa.new Pair(s , goal );
		start.g = 0;
		pq.add(start);
		
		Pair end = aa.new Pair( goal, goal);
		
		while(!pq.isEmpty()) {
			Pair curr = pq.poll();
			
			if(curr.r == end.r && curr.c == end.c) break;
			
			for(int[] dir: dir8) {
				int[] next = {curr.r + dir[0], curr.c + dir[1]};
				if(next[0] < 0 || next[0] >= rl ||
				   next[1] < 0 || next[1] >= cl ||
				   grid[next[0]][next[1]] > 0) continue; // a obstacle					
				
				Pair nextP = aa.new Pair(next,goal);
				if(next[0] == goal[0] && next[1] == goal[1]) nextP = end;
				int temp_g = curr.g + 1;
				int temp_f = temp_g + nextP.h;
				
				if(closeSet.contains(nextP.r +"_"+nextP.c) && temp_f >= nextP.f) continue;
				if(pq.contains(nextP) && temp_f >= nextP.f) 
					continue;
				
				nextP.parent = curr;
				nextP.g = temp_g;
				nextP.f = temp_f;
				pq.add(nextP);
			}
			closeSet.add(curr.r+"_"+curr.c);
		}
		return end;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int[][] maze = {
        		{0,0,0,0,0},
        		{0,0,0,1,1},
        		{1,1,0,0,0},
        		{0,0,0,1,0},
        		{0,1,0,1,0}};
        Pair end = findShortestPath(maze,new int[]{0,0},new int[]{4,4});
        if(end == null) System.out.print("Not found Path");
        else {
        	int[][] ansMaze = maze.clone();
        	for(Pair curr = end; curr != null; curr=curr.parent) {
        		ansMaze[curr.r][curr.c] = 5;
        	}
        	for(int[] row: ansMaze) {
        		for(int num: row)System.out.print(num + " , ");
        	    System.out.println("");
        	}
        }
        
//        5 , 0 , 0 , 0 , 0 , 
//        0 , 5 , 0 , 1 , 1 , 
//        1 , 1 , 5 , 5 , 0 , 
//        0 , 0 , 0 , 1 , 5 , 
//        0 , 1 , 0 , 1 , 5 , 
	}

}
