package exam01;

public class Soldier {
	
	private String name;
	private Gun gun;
	
	Soldier(String name, Gun gun) {
		this.name = name;
		this.gun = gun;
	}
	
	public void info() {
		System.out.println(name + " : " + gun.getGun() + "(" + gun.getBullet() + ")");
	}
	
	public void shoot() {
		gun.shoot();
	}
	
	public void reload(int bullet) {
		gun.reload(bullet);
	}

}
