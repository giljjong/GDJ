package ex02_loop;

public class Ex05_nested_for {

	public static void main(String[] args) {
		
		// 1일차 1교시
		// 1일차 2교시
		// ...
		// 1일차 8교시
		// 2일차 1교시
		// ...
		// 3일차 8교시
		
		for(int day = 1; day < 4; day++) {
			for(int hour = 1; hour < 9; hour++) {
				System.out.println(day + "일차" + hour +"교시");
			}
		}
		
		// 연습
		// 2x1=2
		// 2x2=4
		// ...
		// 9x9=81
		
		for(int n = 2; n <= 9; n++) {
			for(int m = 1; m <= 9; m++) {
				System.out.println(n + "X" + m + "=" + (n*m));
			}
		}
		
		// 연습
		// 2x1=2
		// 2x2=4
		// ...
		// 5x5=25
		//라벨(label)을 이용한 풀이
		outer : for(int n = 2; n <= 9; n++) {
			for(int m = 1; m <= 9; m++) {
				System.out.println(n + "X" + m + "=" + (n*m));
				if(n == 5 && m == 5) {
					break outer;
				}
			}
		}
		
		// 2x1=2	3x1=3	...	9x1=9
		// 2x2=4	3x2=6	... 9x2=18
		// ...
		
		for(int n = 1; n <= 9; n++) {
			System.out.println();
			for(int m = 2; m <= 9; m++) {
				System.out.print(m + "X" + n + "=" + (n*m) + "\t");
			}
		}
		
		
		
	}

}
