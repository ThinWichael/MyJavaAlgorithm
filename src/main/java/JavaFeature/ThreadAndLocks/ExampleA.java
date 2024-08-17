package JavaFeature.ThreadAndLocks;

/* Two way to implement threads :
 * 1. By implementing java.lang.Runnable interface 
 * 2. By extending the java.lang.Thread class
 * 
 * You can see 2. below :
 * */

public class ExampleA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadExample instance = new ThreadExample();
		instance.start();
		
		while (instance.count != 3) {
			try {
				Thread.sleep(500);
				System.out.println("Sleep in main !");
			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}
	}

}
