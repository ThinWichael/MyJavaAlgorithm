package MyTool;

public class SpeedTest {
	long startT;
	long endT;
	public SpeedTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void start() {
		startT = System.nanoTime();
	}
	
	public void end() {
		long endT = System.nanoTime();
		long duration = (endT - startT)/1000000;
		System.out.println("excution time : " + duration + "ms");
	}
	
}
