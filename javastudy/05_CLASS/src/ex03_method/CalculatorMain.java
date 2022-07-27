package ex03_method;

public class CalculatorMain {

	public static void main(String[] args) {
		
		// 객체 생성
		Calculator cal = new Calculator();
		
		// calculator 객체의 add() 메소드 호출
		// 1. 2, 3 : 인수(add 메소드로 전달하는 값), 인수는 매개 변수에 저장된다
		// 2. answer : add 메소드의 반환 값(return result)이 저장된다.
		
		int answer = cal.add(2, 3);
		System.out.println(answer);
		
		int answer1 = cal.sub(5, 1);
		System.out.println(answer1);

		int answer2 = cal.mul(2, 3);
		System.out.println(answer2);
		
		double answer3 = cal.div(4, 2);
		System.out.println(answer3);
		
	}

}
