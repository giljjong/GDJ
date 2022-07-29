package exam01;

public class Gun {
	
	private String model;
	private int bullet;
	final int MAX_BULLET = 6;
	
	Gun(String model, int bullet) {
		this.model = model;
		this.bullet = bullet;
	}
	
	public String getGun() {
		return model;
	}

	public int getBullet() {
		return bullet;
	}

	public void shoot() {
		if(bullet == 0) {
			System.out.println("헛빵!");
			return;
		}
		bullet--;
		System.out.println("빵야!" + bullet + "발 남았다.");
	}
	
	public void reload(int bullet) {
		if(this.bullet == MAX_BULLET) {
			System.out.println("0발이 장전되었다. 현재 " + this.bullet + "발.");
			return;
		}
		this.bullet += bullet;
		this.bullet = (this.bullet > MAX_BULLET) ? MAX_BULLET : this.bullet;
		System.out.println(bullet + "발이 장전되었다. 현재" + this.bullet + "발.");
	}
}
