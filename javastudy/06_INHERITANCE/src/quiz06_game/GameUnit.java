package quiz06_game;

public abstract class GameUnit {
	
	private int energy;
	private int power;
	private String name;
	
	
	public GameUnit( String name, int energy, int power) {
		this.energy = energy;
		this.power = power;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int power) {
		this.energy -= power;
		if(this.energy < 0) {
			this.energy = 0;
		}
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	public abstract void attack(GameUnit unit);

	public void info() {
		System.out.println(name + " 에너지 : " + energy + " 공격력 : " + power);
	}
	
	public boolean isAlive() {
		if(getEnergy() > 0) {
			return true;
			} else {
				return false;
			}
	}

}
