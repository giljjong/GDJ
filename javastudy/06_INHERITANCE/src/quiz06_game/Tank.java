package quiz06_game;

public class Tank extends GameUnit {


	public Tank(String name) {
		super(name, 100, 10);
		
	}

	@Override
	public void attack(GameUnit unit) {
		if(Math.random() < 0.1) {
			setPower(50);
			System.out.println(unit.getName() + "을 한 방에 죽였다");
		}
		
		unit.setEnergy(getPower());
		
	}
	
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return super.isAlive();
	}
	
	@Override
	public void info() {
		super.info();
	}
	
	
}
