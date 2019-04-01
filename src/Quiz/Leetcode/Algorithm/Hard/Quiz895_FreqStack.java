package Quiz.Leetcode.Algorithm.Hard;

import java.util.HashMap;
import java.util.Stack;

//895. Maximum Frequency Stack
//
//Implement FreqStack, a class which simulates the operation of a stack-like data structure.
//
//FreqStack has two functions:
//
//- push(int x), which pushes an integer x onto the stack.
//- pop(), which removes and returns the most frequent element in the stack.
//  - If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.

//Note:
//
//Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
//It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
//The total number of FreqStack.push calls will not exceed 10000 in a single test case.
//The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
//The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.


public class Quiz895_FreqStack {

	// Solution by @lee215
	// Using two map for O (1) solution
	
	class FreqStack {

		HashMap<Integer, Integer> freq = new HashMap<>();
		HashMap<Integer, Stack<Integer>> m = new HashMap<>();
		int maxfreq = 0;
		
	    public FreqStack() {
	        
	    }
	    
	    public void push(int x) {
	        int f = freq.getOrDefault(x, 0) + 1;
	        freq.put(x, f);
	        maxfreq = Math.max(maxfreq, f);
	        if(!m.containsKey(f)) m.put(f, new Stack<Integer>());
	        
	        m.get(f).add(x); // put x into stack that belong to the frequence
	    }
	    
	    public int pop() {
	        int x = m.get(maxfreq).pop();
	        freq.put(x, maxfreq - 1);
	        if(m.get(maxfreq).size() == 0) maxfreq--;
	        return x;
	    }
	}

	/**
	 * Your FreqStack object will be instantiated and called as such:
	 * FreqStack obj = new FreqStack();
	 * obj.push(x);
	 * int param_2 = obj.pop();
	 */
	
	public Quiz895_FreqStack() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz895_FreqStack q = new Quiz895_FreqStack();
        FreqStack fs = q.new FreqStack();
        fs.push(5);
        fs.push(7);
        fs.push(5);
        fs.push(7);
        fs.push(4);
        fs.push(5);
        fs.push(3);
        
        System.out.println(fs.pop());
        System.out.println(fs.pop());
        System.out.println(fs.pop());
        System.out.println(fs.pop());
        System.out.println(fs.pop());
        System.out.println(fs.pop());
        System.out.println(fs.pop());
        
	}

}

//Input: 
//["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
//[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
//Output: [null,null,null,null,null,null,null,5,7,5,4]
//Explanation:
//After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
//
//pop() -> returns 5, as 5 is the most frequent.
//The stack becomes [5,7,5,7,4].
//
//pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
//The stack becomes [5,7,5,4].
//
//pop() -> returns 5.
//The stack becomes [5,7,4].
//
//pop() -> returns 4.
//The stack becomes [5,7]
