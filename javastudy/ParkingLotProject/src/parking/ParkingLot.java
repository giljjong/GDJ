package parking;

import java.util.Scanner;

/*3. 주차장을 의미하는 ParkingLot 클래스를 구현하시오.
    1) field
        (1) String name : 주차장이름
        (2) Car[] cars : Car 인스턴스를 저장할 수 있는 배열
        (3) int idx : 배열의 인덱스
        (4) Scanner sc : 키보드 입력을 처리하는 인스턴스

    2) constructor
        매개변수로 주차장이름을 전달 받아서 필드를 초기화하고,
        길이가 10인 cars 배열을 생성하고,
        키보드로부터 정보를 입력 받을 수 있는 sc 인스턴스를 생성한다.

    3) public void addCar() { }
        (1) 가장 먼저 "현재 등록된 차량 0대"와 같은 메시지를 출력한다.
        (2) 주차장이 가득찬 경우 "더 이상 차량 등록이 불가능합니다." 메시지를 출력하고 addCar() 메소드를 종료한다.
        (3) sc 인스턴스를 이용해서 차량번호와 모델명을 입력 받은 뒤 cars 배열에 순차적으로 저장한다.
        (4) 차량번호와 모델명은 공백 없는 문자열로 처리한다.
        (5) 차량번호가 "288러1111"과 같은 차량의 등록이 성공하면 "차량번호 288러1111 차량이 등록되었습니다." 메시지를 출력한다.
        (6) 기타 명시되지 않은 예외는 발생하지 않는 것으로 가정한다.
        
    4) public void deleteCar() { }
        (1) 주차장이 비어 있는 경우 "등록된 차량이 없습니다." 메시지를 출력하고 deleteCar() 메소드를 종료한다.
        (2) sc 인스턴스를 이용해서 제거할 차량번호를 입력 받은 뒤 해당 차량번호와 일치하는 정보를 삭제한다.
        (3) 차량번호가 "288러1111"인 차량을 삭제한 경우 "차량번호 288러1111 차량이 삭제되었습니다." 메시지를 출력한다.
        (4) 입력된 차량번호와 일치하는 정보가 없는 경우 "대상 차량이 존재하지 않습니다." 메시지를 출력한다.
 
    5) public void printAllCars() { }
        (1) 주차장이 비어 있는 경우 "등록된 차량이 없습니다." 메시지를 출력하고 printAllCars() 메소드를 종료한다.
        (2) 가장 먼저 주차장이름을 출력한다.
        (3) 저장된 모든 차량(cars) 정보를 System.out.println() 메소드를 이용해서 출력한다.
 
    6) public void manage() { }
        (1) "1.추가 2.삭제 3.전체 0.종료" 메뉴를 운용한다.
        (2) 무한루프 내부에서 사용자 입력(1,2,3,0)에 따라서 적절한 메소드를 호출한다.
        (3) 잘못된 메뉴를 입력한 경우 "존재하지 않는 메뉴입니다." 메시지를 출력한다.
	 */

	public class ParkingLot {

    	private String name;
		private Car[] cars;
		private int idx;
		private Scanner sc;
		
		public ParkingLot(String name) {
			super();
			this.name = name;
			cars = new Car[10];
			sc = new Scanner(System.in);
		}
	
		public void addCar() {
			System.out.println("현재 등록된 차량 " + idx + "대");
			
			if(idx == 10) {
				System.out.println("더 이상 차량 등록이 불가능합니다.");
				return;
			}
			
			System.out.print("차량 번호 >>>");
			String carNo = sc.nextLine().trim().replaceAll("\\s", "");
			Car car = new Car();
			car.setCarNo(carNo);
			
			System.out.print("모델 >>> ");
			String model = sc.nextLine().trim().replaceAll("\\s", "");
			car.setModel(model);
			cars[idx++] = car;
			System.out.println("차량번호 " + carNo + " 차량이 등록되었습니다.");
		}
		
		public void deleteCar() {
			
			if(cars[0] == null) {
				System.out.println("등록된 차량이 없습니다.");
				return;
			}
			
			System.out.print("제거할 차량번호 >>> ");
			String carNo = sc.nextLine().trim().replaceAll("\\s", "");
			
			for(int i = 0; i < idx; i++) {
				if(carNo.equals(cars[i].getCarNo())) {
					System.arraycopy(cars, i + 1, cars, i, idx);
					cars[idx--] = null;
					System.out.println("차량번호 " + carNo + " 차량이 삭제되었습니다.");
					return;
				}
			}
			System.out.println("대상 차량이 존재하지 않습니다.");
		}
		
		public void printAllCars() {
			
			if(cars[0] == null) {
				System.out.println("등록된 차량이 없습니다.");
				return;
			}
			
			System.out.println(name + "차량 목록");
			for(int i = 0; i < idx; i++) {
				System.out.println(cars[i]);
			}
		}
		
		public void manage() {
			while(true) {
				System.out.print("1.추가 2.삭제 3.전체 0.종료 >>> ");
				int choice = sc.nextInt();
				sc.nextLine();
				
				switch(choice) {
				case 1 : addCar(); break;
				case 2 : deleteCar(); break;
				case 3 : printAllCars(); break;
				case 0 : System.out.println("Parking 시스템을 종료합니다."); return;
				default : System.out.println("존재하지 않는 메뉴입니다.");
				}
			}
		}
	}
