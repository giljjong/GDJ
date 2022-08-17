package ex02_thread;

public class Soldier extends Thread{
	
	private String name;
	private Gun gun;
	
	public Soldier(String name, Gun gun) {
		super();
		this.name = name;
		this.gun = gun;
	}
	
	public void shoot() {
		System.out.println(name + "총 쏨");
		gun.shoot();
	}
	
	public void run() {
		try {
			while(gun.getBullet() != 0) {
				shoot();
				Thread.sleep(1000);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
