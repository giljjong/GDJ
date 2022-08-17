package ex03_runnable;

public class Main {

	public static void main(String[] args) {
		
		// Runnable 인터페이스를 구현한 클래스는 
		
		Robot robot1 = new WashRobot("로봇1");
		Robot robot2 = new WashRobot("로봇2");

		Thread thread1 = new Thread((Runnable) robot1);
		Thread thread2 = new Thread((Runnable) robot2);
		thread1.start();
		thread2.start();
		
	}

}
