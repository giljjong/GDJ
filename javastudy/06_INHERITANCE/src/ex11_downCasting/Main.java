package ex11_downCasting;

public class Main {

	public static void main(String[] args) {
		
		// 임의의 자동차 10대 배열을 이용하여 저장하기(33% 확률로 랜덤 생성)
		// Car 이면 drive() 호출
		// EV 이면 charge() 호출
		// Hybrid 이면 addOil() 호출
		
		Car[] cars = new Car[10];
		
		
		for(int i = 0; i < cars.length; i++) {
			int random = (int)(Math.random() * 3) + 1;
			if(random == 1) {
				cars[i] = new Car();
			} else if(random == 2) {
				cars[i] = new Ev();
			} else {
				cars[i] = new Hybrid();
			}
			
			if(cars[i] instanceof Hybrid) {
				((Hybrid)cars[i]).addOil();
				continue;
			} else if(cars[i] instanceof Ev) {
				((Ev)cars[i]).charge();
				continue;
			}
			if(cars[i] instanceof Car) {
				cars[i].drive();
			}
		}

	}

}
