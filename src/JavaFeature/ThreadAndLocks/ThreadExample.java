package JavaFeature.ThreadAndLocks;

/* Two way to implement threads :
 * 1. By implementing java.lang.Runnable interface 
 * 2. By extending the java.lang.Thread class
 * 
 * You can see 2. below :
 * */

public class ThreadExample extends Thread {

	int count = 0;
	
	public void run() {
		
		System.out.println("ThreadExample Thread start !");
		
		try {
			while( count < 3) {
				Thread.sleep(500);
				System.out.println("In ThreadExample, count is " + count);
				count++;
			}
		} catch (InterruptedException exc) {
			System.out.println("ThreadExample interrupted");
		}
		System.out.println("ThreadExample terminating");
	}
	

}

