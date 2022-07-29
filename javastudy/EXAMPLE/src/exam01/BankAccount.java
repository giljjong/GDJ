package exam01;

public class BankAccount {
	
	String accNo;
	long balance;

	BankAccount(String accNo, long balance) {
		this.accNo = accNo;
		this.balance = balance;
	}
	
	public void deposit(long money) {
		if (money < 0) {
			return;
		}
		balance += money;
	}
	
	public void withdraw(long money) {
		if(money < 0 ||money > balance) {
			return;
		}
		balance -= money;
	}
	
	public void inquiry() {
		System.out.println("계좌 번호 : " + accNo + ", 잔액 : " + balance + "원");
	}
	
	public void transfer(BankAccount bankAccount, long money) {
		if(money < 0 || money > balance) {
			return;
		}
		bankAccount.balance += money;
		balance -= money;
		
		// if(this.withdraw(money))
		//			other.deposit(money);
	}
}
