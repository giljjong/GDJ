package quiz03_bank;

// 마이너스 입금 불가, 코드 값 1000
// 마이너스 출금 불가, 코드 값 2000
// 잔액보다 큰 출금 불가, 코드 값 2001
// deposit() throws BankException
// withdrawal() throws BankException
// transfer() throws BankException

public class Bank {

	private String accNo;
	private long balance;
	
	public Bank(String accNo, long balance) {
		super();
		this.accNo = accNo;
		this.balance = balance;
	}
	
	public void deposit(long money) throws BankException {
		if(money <= 0) {
			throw new BankException("마이너스 입금 불가", 1000);
		}
		balance += money;
	}
	
	public long withdraw(long money) throws BankException {
		if(money <= 0) {
			throw new BankException("마이너스 출금 불가", 2000);
		} else if (money > balance) {
			throw new BankException("잔액보다 큰 출금 불가", 2001);
		}
		balance -= money;
		return money;
	}
	
	public void inquiry() {
		System.out.println(this);
	}
	
	public void transfer(Bank acc, long money) throws BankException {
		acc.deposit(withdraw(money));
	}

	@Override
	public String toString() {
		return "Bank [accNo=" + accNo + ", balance=" + balance + "]";
	}
}
	
