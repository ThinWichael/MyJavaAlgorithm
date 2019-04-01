package Quiz.Leetcode.Algorithm.Hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 761. Special Binary String

/*
 * Special binary strings are binary strings with the following two properties:

The number of 0's is equal to the number of 1's.
Every prefix of the binary string has at least as many 1's as 0's.
Given a special string S, a move consists of choosing two consecutive, non-empty, special substrings of S, and swapping them. (Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.)

At the end of any number of moves, what is the lexicographically largest resulting string possible?

Example 1:
Input: S = "11011000"
Output: "11100100"
Explanation:
The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.
This is the lexicographically largest string possible after some number of swaps.
Note:

S has length at most 50.
S is guaranteed to be a special binary string as defined above.
 * */

public class Quiz761_SpecialBinaryStr {

	public Quiz761_SpecialBinaryStr() {
		// TODO Auto-generated constructor stub
	}

	//

	public static String makeLargestSpecial(String S) {
		int count = 0, i = 0;
		List<String> result = new ArrayList<String>();

		for (int j = 0; j < S.length(); j++) {
			if (S.charAt(j) == '1') // detecting "{" "}" pairs
				count++;
			else
				count--;

			if (count == 0) {
				result.add('1' + makeLargestSpecial(S.substring(i + 1, j)) + '0');
				i = j + 1;
			}
		}
		Collections.sort(result, Collections.reverseOrder()); // descending
		result.forEach(e -> System.out.print(e + " "));
		String res = String.join("", result);
		System.out.println(" -> " + res + " with S = " + S);
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "11011000";
		String S2 = "101100";
		String S3 = "110111110101000000";
		System.out.println(makeLargestSpecial(S3));

		// S3's print
//               ->  with S = 
//        		 ->  with S = 
//        		 ->  with S = 
//        		 ->  with S = 
//        		10 10 10  -> 101010 with S = 101010
//        		11010100  -> 11010100 with S = 11010100
//        		1110101000  -> 1110101000 with S = 1110101000
//        		111101010000  -> 111101010000 with S = 111101010000
//        		11111010100000 10  -> 1111101010000010 with S = 1011111010100000
//        		111111010100000100  -> 111111010100000100 with S = 110111110101000000
//        		111111010100000100
	}

}

/*
 * Solution by lee215
 * 
 * Explanation: Just 4 steps:
 * 
 * 1. Split S into several special strings (as many as possible). Special string
 * starts with 1 and ends with 0. Recursion on the middle part. 
 * 
 * 2. Sort all special strings in lexicographically largest order. Join and output all strings.
 * 3. Update The middle part of a special string may not be another special string.
 * But in my recursion it is. For example, 1M0 is a splitted special string. M
 * is its middle part and it must be another special string.
 * 
 * Because:
 * 
 * The number of 0's is equal to the number of 1's in M If there is a prefix P
 * of Mwhich has one less 1's than 0's, 1P will make up a special string. 1P
 * will be found as special string before 1M0 in my solution. It means that
 * every prefix of M has at least as many 1's as 0's. Based on 2 points above, M
 * is a special string.
 * 
 */
