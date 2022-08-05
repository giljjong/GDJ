package quiz04_employee;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Company {

	private Employee[] employees;
	private int idx;
	private Scanner sc;
	private int total;
	
	// Regular / Temporary
	// empNo가 일치하면 삭제
	// empNo를 입력받아 검색
	
	
	public Company() {
		employees = new Employee[5];
		sc = new Scanner(System.in);
		
	}
	
	public void addEmployee() throws EmployeeException, InputMismatchException {
		if(idx == employees.length) {
			throw new EmployeeException("Full", 1);
		}
		
		System.out.println("=== 추가 ===");
		System.out.print("1. 월급제 사원 2. 시급제 사원 >>> ");
		int kind = sc.nextInt();
		System.out.print("이름 >>> ");
		String name = sc.next();
		sc.nextLine();
		System.out.print("사번 >>> ");
		int empNo = sc.nextInt();
		switch(kind) {
		case 1 : System.out.print("월급 >>> ");
				int salary = sc.nextInt();
				Regular regular = new Regular(empNo, name, salary);
				employees[idx++] = regular;
				total += salary;
				break;
		case 2: System.out.print("시급 >>> ");
				int pay = sc.nextInt();
				System.out.print("근무 시간 >>> ");
				int workTimes = sc.nextInt();
				pay *= workTimes;
				Temporary temp = new Temporary(empNo, name);
				temp.setPay(pay);
				temp.setWorkTimes(workTimes);
				employees[idx++] = temp;
				total += pay;
				break;
		default : throw new EmployeeException("Bad Request", 3);
		}
		System.out.println("사원 등록 완료. 현재 등록된 사원 " + idx + "명");
	}
	
	public void dropEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("Empty", 2);
		}
		
		System.out.println("=== 삭제 ===");
		System.out.print("사번을 입력해주세요 >>> ");
		int empNo = sc.nextInt();
		for(int i = 0; i < idx; i++) {
			if(empNo == employees[i].getEmpNo()) {
				if(employees[i] instanceof Temporary) {
					total -= ((Temporary) employees[i]).getPay();
				} else if(employees[i] instanceof Regular) {
					total -= ((Regular) employees[i]).getSalary();
				}
				System.arraycopy(employees, i + 1, employees, i, idx - 1 - i);
				employees[idx--] = null;
				System.out.println("사원 번호 " + empNo + "의 직원 정보가 삭제되었습니다. 현재 등록된 사원 " + idx + "명");
				return;
			}
		} throw new EmployeeException("Not Found", 4);
	}
	
	public void findEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("Empty", 2);
		}
		
		System.out.println("=== 찾기 ===");
		System.out.print("사번을 입력해주세요 >>> ");
		int empNo = sc.nextInt();
		for(int i = 0; i < idx; i++) {
			if(empNo == employees[i].getEmpNo()) {
				System.out.println("조회된 사원 정보");
				System.out.println(employees[i]);
				return;
			}
		} throw new EmployeeException("Not Found", 4);
	}
	
	public void printAllEmployees() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("Empty", 2);
		}
		System.out.println("=== 전체 조회 ===");
		System.out.println("전체 사원 목록 (" + idx + "명)");
		for(int i = 0; i < idx; i++) {
			System.out.println(employees[i]);
		}
		System.out.println("전체 사원 월급 : " + total + "원");
	}
	
	public void manage() {
		while(true) {
			try {
				System.out.print("1. 추가 2. 삭제 3. 찾기 4. 전체 조회 0. 종료 >>> ");
				int choice = sc.nextInt();
				switch(choice) {
				case 1 : addEmployee(); break;
				case 2 : dropEmployee(); break;
				case 3 : findEmployee(); break;
				case 4 : printAllEmployees(); break;
				case 0 : System.out.println("Employee 추가 시스템 종료합니다."); return;
				default : throw new EmployeeException("Bad Request", 3);
				}
			} catch (InputMismatchException e) {
				sc.next();
				try {
					throw new EmployeeException("Bad Request", 3);
					} catch (EmployeeException e2) {
					System.out.println(e2.getMessage() + "," + e2.getErrorCode());
					}
			}catch (EmployeeException e) {
				System.out.println(e.getMessage() + "," + e.getErrorCode());
			}
		}
	}
}
