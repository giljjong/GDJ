package quiz04_employee;

public class Regular extends Employee {


	private int salary;

	public Regular(int empNo, String name, int salary) {
		super(empNo, name);
		this.salary = salary;
	}
	
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override	
	public String toString() {
		return super.toString() + ", salary=" + salary + "]";
	}
	
	
	
	
	
}
