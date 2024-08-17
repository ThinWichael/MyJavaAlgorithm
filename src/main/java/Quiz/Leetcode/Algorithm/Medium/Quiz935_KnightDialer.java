package Quiz.Leetcode.Algorithm.Medium;

import java.util.Arrays;

//  1  2  3
//  4  5  6
//  7  8  9
//     0

//1 <= N <= 5000

public class Quiz935_KnightDialer {

	public Quiz935_KnightDialer() {
		// TODO Auto-generated constructor stub
	}
	
	// bottom up : memory
//	Time Complexity: O(10*N) = O(N) fill the memo
//	Space Complexity: O(N) depth of the recursion
	public static final int MOD = 1000000007;
	public static int knightDialer2(int N) {
		int res = 0;
		int[][] graph = {{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4},{4,6}};
		int[][] memo = new int[N+1][10]; // every turn, 0 ~ 9
		
		
		for(int i = 0; i <=9; i++) {
		   res = (res + dp(N-1, i, memo, graph)) % MOD;	
		}
		return res;
	}
	// iteratively , memo the number of dial number, 0 ~ 9, and go next turn
	// top to bottom , space O (1) 
	public static int knightDialer3(int N) {
		int res = 0;
		if(N == 1) return 10;
		int[][] graph = {{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};
		int[] memo = new int[10]; // memory the appearance of 0 to 9 each turn
		Arrays.fill(memo, 1);
		
		for(int n = N; n > 1; n--) {
			int[] temp = new int[10];
			for(int i = 0; i < graph.length; i++) { // 0 ~ 9
				for(int j = 0; j < graph[i].length; j++) { // next step
					int next = graph[i][j]; // e.g. i = 1, next = 6 and 8
					temp[next] = (temp[next] + memo[i] % MOD) % MOD; // count the appearance of 6 and 8 on each turn
				}
			}
			memo = temp; // memo the appearance time of dial number, 0 ~ 9, and go next turn
		}
		
		for(int count: memo) {
			res = (res + count % MOD) % MOD;
		}
		return res;
	}
	
	public static int dp(int i, int cur, int[][] memo, int[][] graph) {
		if(i == 0) return 1;
		
		if(memo[i][cur] != 0) return memo[i][cur];
		int count = 0;
		for(int next: graph[cur]) {
			count = (count + dp(i-1, next,memo,graph)) % MOD;
		}
		memo[i][cur] = count;
		return memo[i][cur];
	}
	
    // failed ! can not conclude to a single formula
	public static int knightDialer(int N) {
		if (N == 1)
			return 10;
        int mod = 1000000007; //1e9 + 7
        long ans = 0;
        long Expo2 = 1;
        long Expo3 = 1;
        int i = 0;
        while( i < (N-1)) {
        	Expo2 = (Expo2 * 2) % mod;
        	Expo3 = (Expo3 * 3) % mod;
        	i++;
        }
        
        ans = ( Expo2 * 7 % mod) + (Expo3 * 2 % mod ) ;
        int answer = (int)ans % mod;
        return answer;
//		Double ans = Math.pow(2, N - 1)  * 7 + Math.pow(3, N - 1) * 2;
//		if(ans > mod) {
//			ans = ans % mod;
//		}
//		4.785986624018847E23
//		int answer = ans.intValue();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(knightDialer2(4));
		System.out.println(knightDialer2(50));
		System.out.println(knightDialer2(5000));
		
		System.out.println(knightDialer3(4));
		System.out.println(knightDialer3(50));
		System.out.println(knightDialer3(5000));
	}

}
