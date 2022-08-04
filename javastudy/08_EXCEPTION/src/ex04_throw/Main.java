package ex04_throw;

import java.util.Scanner;

public class Main {
	
	// throw
	// 1. 예외 객체를 만들어서 직접 throw 할 수 있다.
	// 2. 자바는 예외로 인식하지 않지만 실제로는 예외인 경우에 주로 사용된다.
	

	public static void main(String[] args) {
		
		// finally 블록
		// 1. try-catch문 마지막에 추가하는 블록
		// 2. 언제나 마지막에 실행되는 블록
		// 3. 생략 가능
		
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("나이 입력 >>> ");
			String strAge = sc.next();
			int age = Integer.parseInt(strAge);
			if(age < 0 || age > 100) {
				throw new RuntimeException("나이는 0 이상 100 이하만 가능합니다.");
			}
			System.out.println(age >= 20 ? "성인" : "미성년자");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sc.close();
			System.out.println("finally 블록 실행");
		}

	}

}
