package exam01;

public class BankAccountMain {

	public static void main(String[] args) {
		
		BankAccount me = new BankAccount("1234", 10000);
		BankAccount mom = new BankAccount("4321", 100000);
		
		me.deposit(10000);				// 내 계좌에 10000원 입금
		me.deposit(-100);				// 내 계좌에 마이너스 입금(불가)
		
		me.withdraw(5000);				// 내 계좌에서 5000원 출금
		me.withdraw(100); 			// 내 계좌에서 잔액보다 큰 금액 출금 (불가)
		
		me.inquiry();					// 계좌번호 : 1234, 잔액 : 15000원
		
		mom.transfer(me, 50000);		// 엄마가 나에게 50000원 이체
		mom.transfer(me, -100);			// 실패
		mom.transfer(me, 100000000);	// 실패
		
		mom.inquiry();					// 계좌번호 : 4321, 잔액 : 50000원
		me.inquiry();					// 계좌번호 : 1234, 잔액 64900원

	}

}
