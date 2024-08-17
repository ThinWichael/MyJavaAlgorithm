package JavaFeature.Synchronization;

public class MyThreadImpl implements Runnable {
	private String name;
    private MyObject myObject;
    
    public MyThreadImpl(MyObject obj, String name) {
    	this.name = name;
    	this.myObject = obj;
    }
    
    public void run() {
    	myObject.foo(this.name);
    }
}
