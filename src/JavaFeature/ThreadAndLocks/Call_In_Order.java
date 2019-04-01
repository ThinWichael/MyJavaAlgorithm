package JavaFeature.ThreadAndLocks;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//At Java, A lock is owned by the "same" thread which locked it. It means B thread 
//can not unlock a lock which locked by A thread.So lock is used at preventing 
//other thread to use the same resource with this thread.
//
//If you want to control the dependence of method 1 , method 2 and method 3, then make 
//method 2 execution after method 1 finished and 
//method 3 execution after method 2 finished you need to use ¡§semaphores¡¨.

public class Call_In_Order {

	public Call_In_Order() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Call_In_Order ca = new Call_In_Order();
		Foo foo = ca.new Foo();
        ThreadA A = ca.new ThreadA(foo);
        ThreadB B = ca.new ThreadB(foo);
        ThreadC C = ca.new ThreadC(foo);
        //normal case
        A.start();
        B.start();
        C.start();
        
        //reverse case , should be the same result like normal case
        B.start();
        A.start();
        C.start();
        
        // no first() case
//        B.start();
//        C.start();
	}
	
	public class Foo {
		public Semaphore sem1, sem2;
		
		public Foo() {
			sem1 = new Semaphore(1); // permits only 1 user
			sem2 = new Semaphore(1);

			try {
				sem1.acquire(); // -1 at sem1 
				sem2.acquire(); // -1 at sem2
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public void first() throws InterruptedException {
			System.out.println("first run !");
			Thread.sleep(200);
			System.out.println("first finished !");
			
			sem1.release(); // +1 at sem1
		}

		public void second() throws InterruptedException {
			// dependent method 1 
			// wait until sem1 become permit number > 0 ,
//			sem1.acquire();
//			sem1.release();
			
			// wait with time limited
			if(!sem1.tryAcquire(1, TimeUnit.SECONDS)) { System.out.print("acquire failed !"); return;}
			sem1.release();
			
			System.out.println("sec run !");
			Thread.sleep(200);
			System.out.println("sec finished !");
			
			sem2.release();// +1 at sem2
		}

		public void third() throws InterruptedException {
			// dependent 2 
			sem2.acquire();
			sem2.release();
			
			System.out.println("third run !");
			Thread.sleep(200);
			System.out.println("third finished !");
		}
	}

	public class ThreadA extends Thread{
		Foo foo;
		ThreadA(Foo foo){
			this.foo = foo;
		}
		
		public void run() {
//			if(foo == null) throw new RuntimeException();
			try {
				foo.first();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class ThreadB extends Thread{
		Foo foo;
		ThreadB(Foo foo){
			this.foo = foo;
		}
		
		public void run() {
//			if(foo == null) throw new RuntimeException();
			try {
				foo.second();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class ThreadC extends Thread{
		Foo foo;
		ThreadC(Foo foo){
			this.foo = foo;
		}
		
		public void run() {
//			if(foo == null) throw new RuntimeException();
			try {
				foo.third();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

//15.5 
//Call In Order; Suppose we have the following code:
//public class Foo {
//public Foo() { . . . }
//public void f i r s t Q { . . . >
//public void second() { . . . }
//public void t h i r d ( ) { . . . }
//}
//The same instance of Foo will be passed to three different threads. ThreadA will call first ,
//threadB will call second, and threadC will call t h i r d . Design a mechanism to ensure that
//f i r s t is called before second and second is called before t h i r d .