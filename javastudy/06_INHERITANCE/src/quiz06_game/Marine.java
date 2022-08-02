package quiz06_game;

public class Marine extends GameUnit {

	public Marine(String name) {
		super(name, 50, 5);
		
	}

	@Override
	public void attack(GameUnit unit) {
		if(Math.random() < 0.4) {
			setPower(100);
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
		// TODO Auto-generated method stub
		super.info();
}
	
}
