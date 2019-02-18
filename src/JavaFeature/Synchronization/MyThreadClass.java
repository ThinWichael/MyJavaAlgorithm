package JavaFeature.Synchronization;

public class MyThreadClass extends Thread {
    private String name;
    private MyObject myObject;
    
    public MyThreadClass(MyObject obj, String name) {
    	this.name = name;
    	this.myObject = obj;
    }
    
    public void run() {
    	myObject.foo(this.name);
    }
    
	public static void main(String[] args) {
	  /* Different reference (obj1 / obj2) by extending thread 
	   * - both threads can call MyObject's synchronized method
	   * */
      MyObject obj1 = new MyObject();
      MyObject obj2 = new MyObject();
      MyThreadClass thread1 = new MyThreadClass(obj1, "1.");
      MyThreadClass thread2 = new MyThreadClass(obj2, "2.");
      thread1.start();
      thread2.start();
      /* same reference (obj1 ) by extending thread - thread4 need to wait thread3
	   * */
      MyThreadClass thread3 = new MyThreadClass(obj1, "3.");
      MyThreadClass thread4 = new MyThreadClass(obj1, "4.");
      thread3.start();
      thread4.start();
      /* Different reference (obj1 / obj2) by implementing thread 
	   * - both threads can call MyObject's synchronized method
	   * */
      MyThreadImpl threadImpl1 = new MyThreadImpl(obj1,"5.");
      MyThreadImpl threadImpl2 = new MyThreadImpl(obj2,"6.");
      Thread thread5 = new Thread(threadImpl1);
      Thread thread6 = new Thread(threadImpl2);
      thread5.start();
      thread6.start();
      /* Same reference (obj1 ) by implementing thread 
	   * - thread8 still wait thread7 tile it finished;
	   * */
      MyThreadImpl threadImpl3 = new MyThreadImpl(obj1,"7.");
      MyThreadImpl threadImpl4 = new MyThreadImpl(obj1,"8.");
      Thread thread7 = new Thread(threadImpl3);
      Thread thread8 = new Thread(threadImpl4);
      thread7.start();
      thread8.start();
      
//      thread 1..foo() start   // start together
//      thread 2..foo() start
//      thread 1..foo() end
//      thread 2..foo() end
//      thread 4..foo() start   // in order
//      thread 4..foo() end
//      thread 3..foo() start
//      thread 3..foo() end
      
//      thread 6..foo() start  // start together
//      thread 5..foo() start
//      thread 6..foo() end
//      thread 5..foo() end
//      thread 7..foo() start  // in order
//      thread 7..foo() end
//      thread 8..foo() start
//      thread 8..foo() end


	}

}
