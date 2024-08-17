package Quiz.Leetcode.Algorithm.Hard;

import java.util.Arrays;

//920. Number of Music Playlists
//
//Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during your trip.  You create a playlist so that:
//
//Every song is played at least once
//A song can only be played again only if K other songs have been played
//Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.
//
// 
//
//Example 1:
//
//Input: N = 3, L = 3, K = 1
//Output: 6
//Explanation: There are 6 possible playlists. [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1].
//Example 2:
//
//Input: N = 2, L = 3, K = 0
//Output: 6
//Explanation: There are 6 possible playlists. [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], [1, 2, 2]
//Example 3:
//
//Input: N = 2, L = 3, K = 1
//Output: 2
//Explanation: There are 2 possible playlists. [1, 2, 1], [2, 1, 2]
// 
//
//Note:
//
//0 <= K < N <= L <= 100

// I think the must important things that My solution would be TLE
// is that .... it's not necessary to truely arrange the playlist by recursive.
//
// you should treat it as a possibility question, considerate how many case in one step and
// how many choice in a case.  Just see the solution of other people below. (by )


/*
 *
dp[i][j] denotes the solution of i songs with j different songs. So the final answer should be dp[L][N]

Think one step before the last one, there are only cases for the answer of dp[i][j]
case 1 (the last added one is new song): listen i - 1 songs with j - 1 different songs, then the last one is definitely new song with the choices of N - (j - 1).
Case 2 (the last added one is old song): listen i - 1 songs with j different songs, then the last one is definitely old song with the choices of j
if without the constraint of K, the status equation will be
dp[i][j] = dp[i-1][j-1] * (N - (j-1)) + dp[i-1][j] * j

If with the constaint of K, there are also two cases
Case 1: no changes since the last added one is new song. Hence, there is no conflict
Case 2: now we don't have choices of j for the last added old song. It should be updated j - k because k songs can't be chosed from j - 1 to j - k. However, if j <= K, this case will be 0 because only after choosing K different other songs, old song can be chosen.

if (j > k)
dp[i][j] = dp[i-1][j-1] * (N- (j-1)) + dp[i-1][j] * (j-k)
else
dp[i][j] = dp[i-1][j-1] * (N- (j-1))

 * */


public class Quiz920_MusicPlaylists {

	public Quiz920_MusicPlaylists() {
		// TODO Auto-generated constructor stub
	}

	//**** brute force  (will be TLE, Time Limit Exceeded) ****
	int count = 0;

	public int numMusicPlaylists(int N, int L, int K) {
		int[][] memo = new int[N + 1][2];// [ CD time, played time(s)? ]
		count = 0;
		int played = 0;
		dp(N, L, K, memo, played);
		return count;
	}

	public void dp(int N, int L, int K, int[][] memo, int played) {
		if (played == L) {
			count++;
			System.out.println("count:" + count);
//			System.out.println("memo :");
//			for(int[] e: memo) System.out.print(Arrays.toString(e));
//			System.out.println("");
			return;
		}

		if(played != 0) {
			for(int[] e: memo) e[0]++;
		}
		
		for (int i = 1; i <= N; i++) {
			
			if ((memo[i][1] == 0 || memo[i][0] >= K) && checkEachPlayed(i, N, L, played, memo)) {
				int temp = memo[i][0];
				memo[i][0] = -1; // to minus one
				memo[i][1]++;
				played++;
//				System.out.print(i);
				dp(N, L, K, memo, played);
				played--;
				memo[i][1]--;
				memo[i][0] = temp;
			} 
		}
		
		if(played != 0) {
			for(int[] e: memo) e[0]--;
		}
	}

	// -------L-------|
	// |---N---|
	// ^ check start
	// -play-|
	private boolean checkEachPlayed(int i, int N, int L, int played, int[][] memo) { // i is now we want to play
		int notYetPlayed = 0;
		for (int j = 1; j <= N; j++) { // start from 1 !!!!
			if (memo[j][1] == 0) notYetPlayed++;
		}
		
		if ((L - played) > notYetPlayed)
			return true; // play what ever you want, we are not reach the limit yet

		// check if i is played before
		if (notYetPlayed == 0)
			return true; // already all song played
		else if (memo[i][1] == 0)
			return true; // this song not played yet
		else
			return false; // not all song played and this song already played
	}

	//**** brute force  (will be TLE, Time Limit Exceeded) **** -End-
	
	// Solution By @optimisea
	
	public int numMusicPlaylists2(int N, int L, int K) {
		int mod = (int) Math.pow(10, 9) + 7; //10^9
		
		long[][] dp = new long[L+1][N+1];
		dp[0][0] = 1;
		for(int i = 1; i <= L; i++) {
			for(int j = 1; j <= N; j++) {
				dp[i][j] = (dp[i-1][j-1]*( N - (j - 1))) % mod; //case : the probability of a new song being played
				
				// case : old song repeatedly played
				if(j > K) {
					dp[i][j] = (dp[i][j] + (dp[i-1][j]*(j-K) ) % mod) % mod;
				}
//				System.out.println("i = " + i + ", j = " + j);
//				System.out.println("dp[" + i + "]["+j+"] =" + dp[i][j] );
			}
		}
		
		return (int)dp[L][N];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz920_MusicPlaylists q = new Quiz920_MusicPlaylists();
//		System.out.println(q.numMusicPlaylists(2, 3, 0)); // expect 6
//		System.out.println(q.numMusicPlaylists(2, 3, 1)); // expect 2
//		System.out.println(q.numMusicPlaylists(3, 3, 1)); // expect 6
//		System.out.println(q.numMusicPlaylists(3, 4, 0)); // 36
//		System.out.println(q.numMusicPlaylists(3, 4, 1)); //expect 18
//		System.out.println(q.numMusicPlaylists(4, 7, 1));
		
		System.out.println(q.numMusicPlaylists2(2, 3, 0)); // expect 6
		System.out.println(q.numMusicPlaylists2(2, 3, 1)); // expect 2
		System.out.println(q.numMusicPlaylists2(3, 3, 1)); // expect 6
		System.out.println(q.numMusicPlaylists2(3, 4, 0)); // 36
		System.out.println(q.numMusicPlaylists2(3, 4, 1));
	}

}
