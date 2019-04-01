package Algorithm.Math;

// check a number is primality or not

public class Primality {

	public Primality() {
		// TODO Auto-generated constructor stub
	}

	boolean checkPrime(int n) {
		if(n < 2) return false;
		
		for(int i = 0; i < n; i++) {
			if(n % i == 0) return false;
		}
		return true;
	}
	
	// better method, check only number less than the square root of n
    // because if n can divided into a*b , it must be ( a < sqrt and b > sqrt )
	static boolean checkPrime2(int n) {
		if(n < 2 || n % 2 == 0) return n == 2;
		
		int sqrt = (int) Math.sqrt(n);
		for(int i = 3; i < sqrt; i += 2) {
			if(n % i == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
       System.out.println(checkPrime2(137));
       System.out.println(checkPrime2(2));
       System.out.println(checkPrime2(1));
       System.out.println(checkPrime2(53));
       System.out.println(checkPrime2(48));
	}

}
