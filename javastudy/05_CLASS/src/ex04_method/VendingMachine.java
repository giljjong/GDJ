package ex04_method;

public class VendingMachine {

	String coffee;

	String getCoffee(int money, int button) {
		
		String[] menu = {"", "아메리카노", "카페라떼"};
		
		return menu[button] + " " + (money / 1000) + "잔";
		
		/*if(button == 1) {
			coffee = "아메리카노 " + (money / 1000) + "잔";
		} else if(button == 2) {
			coffee = "카페라떼 " + (money / 1000) + "잔";
		}
		
		return coffee;
		*/
	}
	
}
