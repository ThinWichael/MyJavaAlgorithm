package JavaFeature.Synchronization;

public class MyObject {

	/*Two different style*/
	
	public synchronized void foo(String name) { /* A synchronized method */

		try {
			System.out.println("thread " + name + ".foo() start");
			Thread.sleep(2000);
			System.out.println("thread " + name + ".foo() end");
		} catch (InterruptedException exc) {
			System.out.println("thread " + name + ".foo() interrupte");
		}

	}

	public void boo(String name) {
		synchronized (this) {
			try {
				System.out.println("thread " + name + ".boo() start");
				Thread.sleep(2000);
				System.out.println("thread " + name + ".boo() end");
			} catch (InterruptedException exc) {
				System.out.println("thread " + name + ".boo() interrupte");
			}
		}
	}

}
