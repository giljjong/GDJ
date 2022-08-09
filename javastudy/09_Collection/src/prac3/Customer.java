package prac3;

public class Customer {

	private int money;
	private int cnt;		// 구매한 빵의 갯수
	
	public Customer(int money) {
		super();
		this.money = money;
	}
	
	public int getMoney() {
		return money;
	}


	public int getCnt() {
		return cnt;
	}


	// 구매 (구매 후 출력)
	// Bakery에서 판매한 빵을 가질 수 있다. (Bakery의 sell() 메소드 사용)
	public void buy(Bakery bakery, int buyMoney) {

		if(this.money < buyMoney) {
			throw new RuntimeException("돈이 모자랍니다.");
		}
		cnt += bakery.sell(buyMoney).getBread();
		money -= buyMoney - bakery.sell(buyMoney).getChange();
		System.out.println("구매한 빵 " +  cnt + "개, 남은 돈 " + money + "원");
		
	}
	
	
}
