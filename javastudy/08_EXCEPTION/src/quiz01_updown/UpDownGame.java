package quiz01_updown;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpDownGame {

	// 필드
	private int rand;
	private int cnt;
	private Scanner sc;
	
	
	
	public UpDownGame() {
		rand = (int)(Math.random() * 101);
		sc = new Scanner(System.in);
	}
	
	// 입력
	public int input() {
		try {
			System.out.print("숫자를 입력해주세요 >>> ");
			int input = sc.nextInt();
			if(input < 1 || input > 100) {
				throw new RuntimeException("1 ~ 100 사이의 정수만 입력 할 수 있습니다.");
			}
			return input;
		}  catch (InputMismatchException e) {
			System.out.println("정수만 입력 가능합니다.");
			sc.next();		// 잘못 입력된 문자열 먹어 치우기
			input();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			input();
		} 
		return 0;	// 이클립스 안심시키는 용도
	}
	
	// 실행
	public void play() {
		System.out.println("1 ~ 100까지의 랜덤 숫자 맞추기!");
		while(true) {
			int n = input();
			if(rand < n) {
				cnt++;
				System.out.println("Down!");
			} else if(rand > n) {
				cnt++;
				System.out.println("Up!");
			} else {
				System.out.println(cnt + "번 만에 정답!");
				break;
			}
		}
		// 맞힐 때 까지 무한 루프
	}
	
}
