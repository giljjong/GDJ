package quiz04_employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class Employee {


	private int empNo;			// 사번
	private String name;		// 이름
	
	public Employee() {}
	
	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", name=" + name;
	}
}
