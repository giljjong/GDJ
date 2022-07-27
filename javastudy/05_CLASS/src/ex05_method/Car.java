package ex05_method;

public class Car {
	
	// 필드
	int oil;
	int speed;
	
	// 메소드
	
	// 1. 기름 넣기
	// 반환 타입 : void (반환하는 값이 없음)
	// 메소드 명 : addOil
	// 매개 변수 : int o 
	
	void addOil(int o) {
		
		oil += o;
		if(oil > 60) {
			oil = 60;
		}
	}
	
	// 2. 달리기
	// 반환 타입 : void
	// 메소드 명 : pushAccel
	// 매개 변수 : X
	
	void pushAccel() {
		if(oil < 0) {
			return;			// 반환 타입이 void일 때만 사용 가능
		} else if(speed == 120) {
			--oil;
			return;
		}
		// 속도는 25씩 증가한다.
		// 기름은 1씩 사용
		
		--oil;
		speed += 25;
		if(speed >= 120) {
			speed = 120;
		}
	}
	
	// 3. 멈추기
	// 반환 타입 : void
	// 메소드 명 : pushBreak
	// 매개 변수 : X
	
	void pushBreak() {
		if(speed == 0) {
			return;
		}
		speed -= 25;
		if(speed < 0) {
			speed = 0;
		}
		
	}
	
	// 4. 계기판(기름, 속도) 확인
	// 반환타입 : void
	// 메소드 명 : panel
	// 매개변수 : X
	
	void panel() {
		System.out.println("기름 " + oil);
		System.out.println("속도 " + speed);
	}
	
}
