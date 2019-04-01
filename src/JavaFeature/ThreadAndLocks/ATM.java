package JavaFeature.ThreadAndLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ATM {
	private static ATM ATMsingleton;
    private Lock lock;
    private int balance = 1000; // importance resource !!
    
	public ATM() {
		lock = new ReentrantLock();
	}

	private static synchronized ATM getSingletonInstance() { // must be synchronized, otherwise, it may create two instance at same time;
		if(ATMsingleton == null) {
			ATMsingleton = new ATM();
		}
		return ATMsingleton;
	}
	
	public int withdraw(int value) {
		lock.lock();
		int temp = balance;
		System.out.println("ATM lock ! start withdraw " + value);
		try {
			Thread.sleep(200);
			temp = temp - value;
			Thread.sleep(200);
			balance =  temp;
		} catch(InterruptedException e) {
			
		}
		
		lock.unlock();
		System.out.println("ATM unlock ! withdraw finished !");
		return balance;
	}
	
	public int deposit(int value) {
		lock.lock();
		int temp = balance;
		System.out.println("ATM lock ! start deposit " + value);
		try {
			Thread.sleep(200);
			temp = temp + value;
			Thread.sleep(200);
			balance = temp;
		} catch(InterruptedException exc) {
		}
		
		lock.unlock();
		System.out.println("ATM unlock ! deposit finish !");
		return balance;
	}
	
	static class TestThread1 extends Thread {
		
		public void run(){
			ATM atm = ATM.getSingletonInstance();
			atm.withdraw(5000);
			atm.deposit(2500);
			System.out.println("Your account balance :" + atm.balance);
		};
		
	}
	
	static class TestThread2 extends Thread {
		public void run() {
			ATM atm = ATM.getSingletonInstance();
			atm.withdraw(500);
			atm.deposit(10000);
			System.out.println("Your account balance :" + atm.balance);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread testThread1 = new TestThread1();
		Thread testThread2 = new TestThread2();
		testThread1.start();
		testThread2.start();
		
//		ATM lock ! start withdraw 500
//		ATM unlock ! withdraw finished !
//		ATM lock ! start withdraw 5000
//		ATM unlock ! withdraw finished !
//		ATM lock ! start deposit 10000
//		ATM unlock ! deposit finish !
//		ATM lock ! start deposit 2500
//		Your account balance :5500
//		ATM unlock ! deposit finish !
//		Your account balance :8000
		
		// create two ATM instance at same time
//		ATM lock ! start withdraw 5000
//		ATM lock ! start withdraw 500
//		ATM unlock ! withdraw finished !
//		ATM unlock ! withdraw finished !
//		ATM lock ! start deposit 2500
//		ATM lock ! start deposit 10000
//		ATM unlock ! deposit finish !
//		ATM unlock ! deposit finish !
//		Your account balance :10500
//		Your account balance :-1500
		
	}

}
