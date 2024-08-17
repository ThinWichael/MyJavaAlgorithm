package Quiz.Leetcode.Algorithm.Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

//833. Find And Replace in String
//Medium
//
//To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).
//
//Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.
//
//For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".
//
//Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
//
//All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
//
//Example 1:
//
//Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
//Output: "eeebffff"
//Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
//"cd" starts at index 2 in S, so it's replaced by "ffff".
//Example 2:
//
//Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
//Output: "eeecd"
//Explanation: "ab" starts at index 0 in S, so it's replaced by "eee". 
//"ec" doesn't starts at index 2 in the original S, so we do nothing.
//Notes:
//
//0 <= indexes.length = sources.length = targets.length <= 100
//0 < indexes[i] < S.length <= 1000
//All characters in given inputs are lowercase letters.

public class Quiz833_FindAndReplace {

	public Quiz833_FindAndReplace() {
		// TODO Auto-generated constructor stub
	}
    // use "fix" to memory the start point shifted after replace with different length string.
	// pass by myself
	// time O(N) , space O(N)
	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        HashMap<Integer, String> sMap = new HashMap<Integer, String>();
        HashMap<Integer, String> tMap = new HashMap<Integer, String>();
        int n = indexes.length;
        for(int i = 0; i < n; i++) {
        	sMap.put(indexes[i], sources[i]);
        	tMap.put(indexes[i], targets[i]);
        }
		Arrays.sort(indexes);
		int fix = 0;
        // for loop
		
		for(int i = 0; i < n; i++) {
			int ii = indexes[i];
			int startI = ii + fix;
			int endI = startI + sMap.get(ii).length();
			if(sMap.get(ii).equalsIgnoreCase(S.substring(startI, endI)) ) {
				// replace and update fix
				StringBuilder sb = new StringBuilder();
				sb.append(S.substring(0, startI)).append(tMap.get(ii)).append(S.substring(endI));
				S = sb.toString();
				fix += tMap.get(ii).length() - sMap.get(ii).length();
			} 
		}
		return S;
	}
    // space optimize
	public String findReplaceString2(String S, int[] indexes, String[] sources, String[] targets) {
        TreeMap<Integer, Integer> sorted = new TreeMap<Integer, Integer>();
        int n = indexes.length;
        for(int i = 0; i < n; i++) {
        	sorted.put(indexes[i] , i);
        }
		int fix = 0;
        // for loop
		
		for(Integer i: sorted.keySet()) {
			int ii = sorted.get(i);
			int startI = i + fix;
			int endI = startI + sources[ii].length();
			if(sources[ii].equalsIgnoreCase(S.substring(startI, endI)) ) {
				// replace and update fix
				StringBuilder sb = new StringBuilder();
				sb.append(S.substring(0, startI)).append(targets[ii]).append(S.substring(endI));
				S = sb.toString();
				fix += targets[ii].length() - sources[ii].length();
			} 
		}
		return S;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz833_FindAndReplace q = new Quiz833_FindAndReplace();
		String S = "abcd";
		int[] indexes = {0,2};
		String[] sources = {"a","cd"};
		String[] targets = {"eee","ffff"};
				//Output: "eeebffff"
		System.out.println(q.findReplaceString2(S, indexes, sources, targets));
		String S2 = "abcdef";
		int[] indexes2 = {0,2,4};
		String[] sources2 = {"ab","cd","ef"};
		String[] targets2 = {"eee","f","gggg"};
		System.out.println(q.findReplaceString2(S2, indexes2, sources2, targets2));//Output:eeefgggg
		String S3 = "abcdef";
		int[] indexes3 = {0,2,4};
		String[] sources3 = {"ab","vt","ef"};
		String[] targets3 = {"eee","f","gggg"};
		System.out.println(q.findReplaceString2(S3, indexes3, sources3, targets3));//Output:eeecdgggg
		String S4 = "vmokgggqzp";
		int[] indexes4 = {3,5,1};
		String[] sources4 = {"kg","ggq","mo"};
		String[] targets4 = {"s","so","bfr"};
		System.out.println(q.findReplaceString2(S4, indexes4, sources4, targets4));//vbfrssozp
//		"vmokgggqzp"
//		[3,5,1]
//		["kg","ggq","mo"]
//		["s","so","bfr"]
	}

}
