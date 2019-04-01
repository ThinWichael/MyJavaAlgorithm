package Quiz.Leetcode.Algorithm.Medium;

//866. Prime Palindrome
//Medium

//Find the smallest prime palindrome greater than or equal to N.
//
//Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1. 
//
//For example, 2,3,5,7,11 and 13 are primes.
//
//Recall that a number is a palindrome if it reads the same from left to right as it does from right to left. 
//
//For example, 12321 is a palindrome.
//
// 
//
//Example 1:
//
//Input: 6
//Output: 7
//Example 2:
//
//Input: 8
//Output: 11
//Example 3:
//
//Input: 13
//Output: 101
// 
//
//Note:
//
//1 <= N <= 10^8
//The answer is guaranteed to exist and be less than 2 * 10^8

public class Quiz866_PrimePalindrome {

	public Quiz866_PrimePalindrome() {
		// TODO Auto-generated constructor stub
	}

	// solution by @lee215
	// all even digits number can be divide by 11. so even digits is no need to be
	// checked
	public int primePalindrome(int N) {
		if (8 <= N & N <= 11)
			return 11;
		for (int x = 1; x < 100000; x++) { // the limit is 9 digits , so palindrome 5 + 4 = 9 digits
			// only check odd digits
			String str = Integer.toString(x); // e.g "12345"
			String r = new StringBuilder(str).reverse().toString().substring(1); // "4321"
			int pV = Integer.parseInt(str + r); // 123454321
			if (pV >= N && checkPrime(pV))
				return pV;
		}
		return -1;
	}

	boolean checkPrime(int n) {
		if (n < 2 || n % 2 == 0)
			return n == 2;

		int sqrt = (int) Math.sqrt(n);
		for (int i = 3; i < sqrt; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "12345";
		String r = new StringBuilder(s).reverse().toString().substring(1);
		int y = Integer.parseInt(s + r); //
		System.out.println(r);
		System.out.println(y);

		Quiz866_PrimePalindrome q = new Quiz866_PrimePalindrome();
		System.out.println(q.primePalindrome(4332));
		System.out.println(q.primePalindrome(4332345));
	}

}
