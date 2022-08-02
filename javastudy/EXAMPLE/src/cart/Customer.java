package cart;

public class Customer {
	
	private Product[] cart = new Product[10];
	private int money;
	private int bonusPoint;
	private int idx;
	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public void buy(Product product) {
		int price = product.getPrice();
		if(price > money) {
			return;	
		}
		cart[idx++] = product;
		money -= price;
		bonusPoint += price * 0.1;
	}
	
	public void receipt() {
		int total = 0;
		for(int i = 0; i < idx; i++) {
			System.out.println(cart[i].getName() + "," + cart[i].getPrice() + "원");
			total += cart[i].getPrice();
		}
		
		System.out.println("---------------");
		System.out.println("구매 총액" + total);
		System.out.println("보너스" + bonusPoint);
		System.out.println("남은 돈" + money);
	}
	

}
