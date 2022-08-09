package prac3;

public class Bakery {
	
	private int price;
	private int cnt;
	private int capital;
	
	public Bakery(int price, int cnt, int capital) {
		super();
		this.price = price;
		this.cnt = cnt;
		this.capital = capital;
	}
	
	// 판매
	public BreadAndChange sell(int money) {
		BreadAndChange brch = new BreadAndChange();
		int change = money % price;
		int sellCnt = money / price;
		if(sellCnt > cnt || money < 0) {
			throw new RuntimeException("구매하실 수 없습니다.");
		}
		capital += money - change;
		cnt -= sellCnt;
		return new BreadAndChange(sellCnt, change);
	}
	
	// 정보 확인
	public void info() {
		System.out.println("빵 " + cnt + " 자본금 " + capital + "원");
	}
	
	
	

}
