package ex02_loop;

public class Ex04_continue {

	public static void main(String[] args) {
		
		// continue문
		// 반복문의 시작 지점으로 이동한다.
		// 실행에서 제외할 코드가 있는 경우에 사용한다.
		
		// 1 ~ 100 중에서 3의 배수를 제외하고 모두 더하기
		
		int begin = 0;
		int end = 99;
		int sum = 0;
		
		while(begin <= end) {
			begin++;
			
			if(begin % 3 == 0) {
				continue;
			}
			sum += begin;
		}
		System.out.println(sum);
		
		
	}

}
