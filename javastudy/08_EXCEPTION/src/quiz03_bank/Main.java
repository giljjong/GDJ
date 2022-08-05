package quiz03_bank;

public class Main {

	public static void main(String[] args) {
			Bank me = new Bank("1234", 10000);
			Bank mom = new Bank("4321", 100000);
			
			try {
				mom.transfer(me, 50000);	

			} catch (BankException e) {
				System.out.println(e.getMessage() + "," + e.getErrorCode());
			}
			
			me.inquiry();	
			mom.inquiry();		

	}

}
