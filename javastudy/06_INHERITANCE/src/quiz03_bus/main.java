package quiz03_bus;

public class main {

	public static void main(String[] args) {
		
		Bus bus = new Bus(25);		// 좌석(seat)이 25개인 버스
		bus.ride(1, new Person("kim"));
		bus.ride(5, new Student("choi"));
		bus.ride(10, new Alba("min"));
		bus.info();
		// 1 kim
		// 5 choi
		// 10 min
		
		
		

	}

}
