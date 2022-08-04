package parking;

public class ParkingLotMain {

	public static void main(String[] args) {
		/* 4. main 메소드를 가지고 있는 ParkingLotMain 클래스를 작성하시오.
		 * main 메소드에서는 임의의 ParkingLot 인스턴스를 생성한 뒤 manage 메소드를 호출하시오.
    		ParkingLot 인스턴스 생성시 주차장의 이름은 "대박주차장"으로 처리하시오.
		 */
		ParkingLot parkingLot = new ParkingLot("대박주차장");
		parkingLot.manage();

	}

}
