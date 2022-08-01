package quiz02_coffee;

public class Americano {
	
	private int shots;
	private String type;
	
	private Espresso espresso;

	public Americano(Espresso espresso, int shots, String type) {
		this.shots = shots;
		this.type = type;
		this.espresso = espresso;
	}
	
	public void info() {
		espresso.info();
		System.out.println(shots + "샷 " + type + "아메리카노");
	}

}
