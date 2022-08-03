package ex15_interface;

public class Main {

	public static void main(String[] args) {
		
		Phone p1 = new SmartPhone();
		p1.call();
		p1.sms();
		if(p1 instanceof SmartPhone) {
			((SmartPhone) p1).game();
			((SmartPhone) p1).internet();
		}
		System.out.println();
		Computer p2 = new SmartPhone();
		
		p2.game();
		p2.internet();
		if(p2 instanceof SmartPhone) {
			((SmartPhone) p2).call();
			((SmartPhone) p2).sms();
		}
		System.out.println();
		SmartPhone p3 = new SmartPhone();
		
		p3.call();
		p3.sms();
		p3.game();
		p3.internet();

	}

}
