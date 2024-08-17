package Quiz.Leetcode.Algorithm.Medium;

import java.util.ArrayList;
import java.util.List;

//722. Remove Comments

public class Quiz722_RemoveComments {

	public Quiz722_RemoveComments() {
		// TODO Auto-generated constructor stub
	}

	// in this quiz, we don't need to consider the double quote and single quote
//	Time Complexity: O(S), where SS is the total length of the source code.
//	Space Complexity: O(S), the space used by recording the source code into ans.
	public static List<String> removeComments(String[] source) {
		boolean inBlock = false;
		StringBuilder newline = new StringBuilder();
		List<String> res = new ArrayList<String>();
		for (String line : source) {
			int i = 0;
			char[] chars = line.toCharArray();
			if(!inBlock) newline = new StringBuilder();
			while (i < line.length()) {
				if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '*') {
					inBlock = true;
					i++;
				} else if (inBlock && i + 1 < line.length() && chars[i] == '*' && chars[i + 1] == '/') {
					inBlock = false;
					i++;
				} else if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '/') {
					break;
				} else if (!inBlock) {
					newline.append(chars[i]);
				}
				i++;
			}
			if (!inBlock && newline.length() > 0) {
				res.add(newline.toString());
			}
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] source = { "/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;",
				"/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}" };
		List<String> res1 = removeComments(source);
		for (String s : res1)
			System.out.println(s);

	}

}

//Given a C++ program, remove comments from it. The program source is an array where source[i] is the i-th line of the source code. This represents the result of splitting the original source code string by the newline character \n.
//
//In C++, there are two types of comments, line comments, and block comments.
//
//The string // denotes a line comment, which represents that it and rest of the characters to the right of it in the same line should be ignored.
//
//The string /* denotes a block comment, which represents that all characters until the next (non-overlapping) occurrence of */ should be ignored. (Here, occurrences happen in reading order: line by line from left to right.) To be clear, the string /*/ does not yet end the block comment, as the ending would be overlapping the beginning.
//
//The first effective comment takes precedence over others: if the string // occurs in a block comment, it is ignored. Similarly, if the string /* occurs in a line or block comment, it is also ignored.
//
//If a certain line of code is empty after removing comments, you must not output that line: each string in the answer list will be non-empty.
//
//There will be no control characters, single quote, or double quote characters. For example, source = "string s = "/* Not a comment. */";" will not be a test case. (Also, nothing else such as defines or macros will interfere with the comments.)
//
//It is guaranteed that every open block comment will eventually be closed, so /* outside of a line or block comment always starts a new comment.
//
//Finally, implicit newline characters can be deleted by block comments. Please see the examples below for details.
//
//After removing the comments from the source code, return the source code in the same format.
//
//Example 1:
//Input: 
//source = ["/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"]
//
//The line by line code is visualized as below:
///*Test program */
//int main()
//{ 
//  // variable declaration 
//int a, b, c;
///* This is a test
//   multiline  
//   comment for 
//   testing */
//a = b + c;
//}
//
//Output: ["int main()","{ ","  ","int a, b, c;","a = b + c;","}"]
//
//The line by line code is visualized as below:
//int main()
//{ 
//  
//int a, b, c;
//a = b + c;
//}
//
//Explanation: 
//The string /* denotes a block comment, including line 1 and lines 6-9. The string // denotes line 4 as comments.
//Example 2:
//Input: 
//source = ["a/*comment", "line", "more_comment*/b"]
//Output: ["ab"]
//Explanation: The original source string is "a/*comment\nline\nmore_comment*/b", where we have bolded the newline characters.  After deletion, the implicit newline characters are deleted, leaving the string "ab", which when delimited by newline characters becomes ["ab"].
//Note:
//
//The length of source is in the range [1, 100].
//The length of source[i] is in the range [0, 80].
//Every open block comment is eventually closed.
//There are no single-quote, double-quote, or control characters in the source code.