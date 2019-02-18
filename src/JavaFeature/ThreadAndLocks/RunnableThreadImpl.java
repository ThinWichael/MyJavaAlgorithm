package JavaFeature.ThreadAndLocks;

/* Two way to implement threads :
 * 1. By implementing java.lang.Runnable interface 
 * 2. By extending the java.lang.Thread class
 * 
 * You can see 1. below :
 * 
 * must of time, 1. strategy is better. There is no nessesary to
 * */

public class RunnableThreadImpl implements Runnable {

	public int count = 0;
	
	public void run() {
		System.out.println("RunnableThreadImpl Thread start !");
		
		try {
			while(count < 5) {
				Thread.sleep(500);
				count++;
			}
		} catch (InterruptedException ex) {
			System.out.println("RunnableThreadImpl Thread Interrupted !");
			
		}
		System.out.println("RunnableThreadImpl Thread Finished !");
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        RunnableThreadImpl runnableThreadImpl = new RunnableThreadImpl();
        Thread thread = new Thread(runnableThreadImpl);
        thread.start();
        
        /* waits until thread count to 5 */
        while(runnableThreadImpl.count != 5) {
           try {
        	   Thread.sleep(300);
           } catch (InterruptedException exc) {
        	   exc.printStackTrace();
           }
        }
	}

}
