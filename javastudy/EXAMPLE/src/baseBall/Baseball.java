package baseBall;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Baseball {

	public static void main(String[] args) {
		// 0~9까지의 숫자를 한 번씩 사용하여 만든 세 자리 숫자를 맞추는 코드를 작성하시오. 숫자와 자리가 맞으면 S이고 숫자만 맞으면 B입니다.
		// 세 자리 숫자를 설정하고 사용자에게 숫자를 입력받아 S와 B의 개수를 알려주십시오
		// 정답을 맞히면 정답이라고 알려주고 사용자가 숫자를 룰에 어긋나게 입력 시 경고문을 출력하고 다시 숫자를 입력할 수 있게 하십시오.
		// 예) 정답이 123일 때 사용자가 234를 입력 시 0S2B, 사용자가 109를 입력 시 1S0B, 사용자가 327을 입력 시 1S1B입니다.
		
		Scanner sc = new Scanner(System.in);
		String[] balls = new String[3];
		String ball = "";
		int sCnt = 0;
		int bCnt = 0;
		
		while(true) {
				for(int i = 0; i < balls.length; i++) {
					balls[i] = (int)(Math.random() * 10) + "";
					ball += balls[i];
				}
				System.out.print("세 자리 숫자를 입력 해주세요 >>> ");
				try{
				String num = sc.next();
				if(num.length() != 3) {
					throw new InputMismatchException();
				}
				if(ball.equals(num)) {
					System.out.println("정답!");
					break;
				} else {
					for(int i = 0; i < balls.length; i++) {
						int idx = num.indexOf(balls[i]);
						if(balls[i].equals(num.substring(i, i+1))) {
							sCnt++;
						} else if(num.contains(balls[i])) {
							bCnt++;
						}
					}
						break;
				}
			} catch(InputMismatchException e) {
				System.out.println("세 자리 정수만 입력 가능합니다.");
			}
		} System.out.println(sCnt + "S" + bCnt + "B");
	}

}

