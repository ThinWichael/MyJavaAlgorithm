package Quiz.Leetcode.Algorithm.Medium;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//947. Most Stones Removed with Same Row or Column
//
//On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
//
//Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
//
//What is the largest possible number of moves we can make?

 

//
//1 <= stones.length <= 1000
//0 <= stones[i][j] < 10000

public class Quiz947_StonesRemoved {

	public Quiz947_StonesRemoved() {
		// TODO Auto-generated constructor stub
	}

	public int removesStones(int[][] stones) {
		int n = stones.length;
		DSU un = new DSU(20000); // x as x , y as 10000 + y

		for (int[] stone : stones) {
			un.union(stone[0], 10000 + stone[1]); // x , 10000 + y
		} // N * logN

		Set<Integer> seen = new HashSet<Integer>();
		for (int[] stone : stones) {
			seen.add(un.find(stone[0]));
		}

		return n - seen.size(); // size equals the number of group, on group will left one stone
	}

	// union class
	class DSU {
		int[] parent;

		public DSU(int N) {
			parent = new int[N];

			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
		}

		public int find(int x) { // best way to write a find
			while (parent[x] != x)
				x = parent[x];

			return x;
		}

		public void union(int x, int y) {
			parent[find(x)] = find(y);
		}
	}
//	Algorithm
//
//	Let's connect row i to column j, which will be represented by j+10000. The answer is the number of components after making all the connections.
//
//	Note that for brevity, our DSU implementation does not use
//	union-by-rank. This makes the asymptotic time complexity larger.
//	

//	Time Complexity: O(N \log N)O(NlogN), where NN is the length of stones. 
//	(If we used union-by-rank, this can be 
//	O(N∗α(N)), where	 α is the Inverse-Ackermann function.)
//	Space Complexity: O(N)O(N). 

//	這兩種方法的優勢互補，同時使用二者的程式每個操作的平均時間僅為 {\displaystyle O(\alpha (n))} O(\alpha (n))， {\displaystyle \alpha (n)} \alpha (n)是 {\displaystyle n=f(x)=A(x,x)} n=f(x)=A(x,x)的反函式，其中 {\displaystyle A} A是急速增加的阿克曼函式。因為 {\displaystyle \alpha (n)} \alpha (n)是其的反函式，故 {\displaystyle \alpha (n)} \alpha (n)在 {\displaystyle n} n十分巨大時還是小於5。因此，平均執行時間是一個極小的常數。
//
//	實際上，這是漸近最佳演算法：Fredman和Saks在1989年解釋了 {\displaystyle \Omega (\alpha (n))} \Omega (\alpha (n))的平均時間內可以獲得任何並查集。
	// https://zh.wikipedia.org/wiki/%E5%B9%B6%E6%9F%A5%E9%9B%86
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] stones1 = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 2, 2 } };
		int[][] stones2 = { { 0, 0 }, { 0, 2 }, { 1, 1 }, { 2, 0 }, { 2, 2 } };
		
		Quiz947_StonesRemoved q = new Quiz947_StonesRemoved();
		System.out.println(q.removesStones(stones1));
		System.out.println(q.removesStones(stones2));
		
//		Big case
		int[][] stonesBig = new int[999][2];
		for(int[] e: stonesBig) {
			Random r = new Random();
			int a = r.nextInt(10001);
			int b = r.nextInt(10001);
			e[0] = a;
			e[1] = b;
		}
		System.out.println(q.removesStones(stonesBig));
	}
}
