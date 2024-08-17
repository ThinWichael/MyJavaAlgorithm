package Algorithm.Math;

import java.util.Arrays;

// create a list of primes for a up bounded N 

// so-called Sieve of Eratosthenes

public class PrimesList {

	public PrimesList() {
		// TODO Auto-generated constructor stub
	}

    boolean[] sieveOfEratosthenes(int max) {
    	boolean[] flags = new boolean[max + 1 ];
    	
    	Arrays.fill(flags, true);
    	int prime = 2;
    	
    	while(prime <= Math.sqrt(max)) {
    		// cross off : screen all flags with prime
    		crossOff(flags, prime);
    		
    		prime = getNextPrime(flags, prime);
    	}
    	return flags;
    }	
	
    void crossOff(boolean[] flags, int prime) {
    	// start with prime*prime, if we start with k * prime, where k < prime
    	// this value, k, could already have been crossed off
    	
    	for(int i = prime*prime; i < flags.length ; i += prime) {
    		flags[i] = false;
    	}
    }
    
    int getNextPrime(boolean[] flags, int prime) {
    	int next = prime + 1;
    	while(next < flags.length && !flags[next]) {
    		next++;
    	}
    	return next;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrimesList p = new PrimesList();
		boolean[] list = p.sieveOfEratosthenes(476);
		int data = 0;
		for(int i = 0;i < list.length; i++) {
			if(list[i]) {
				data++; 
				System.out.print(i + ", ");
				if(data % 20 == 0) System.out.println("");
			}
		}
	}

}
