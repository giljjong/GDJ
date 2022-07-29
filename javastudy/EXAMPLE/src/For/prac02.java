package For;

import java.util.Scanner;

public class prac02 {
	
	public static void q1() {
		// 점수와 학년을 입력받아 60점 이상이면 합격, 60점 미만이면 불합격을 출력하시오.
		// 4학년인 경우 70점 이상이어야 합격이다
				
		Scanner sc = new Scanner(System.in);
				
		System.out.print("점수를 입력하세요(0~100) >>> ");
		int score = sc.nextInt();
		System.out.print("학년을 입력하세요(1~4) >>> ");
		int grade = sc.nextInt();
				
		if (score < 0 || score > 100 || grade <= 0 || grade >5) {
			System.out.println("잘못된 점수 혹은 잘못된 학년");
		} else if(grade == 4 && score >= 70) {
			System.out.println("합격");
		} else if(grade < 4 && score >= 60) {
			System.out.println("합격");
		} else {
			System.out.println("불합격");
		}
	}
	
	public static void q2() {
		// 커피 메뉴를 입력받아 가격을 알려주는 프로그램을 구현하시오
		// switch 문을 이용하여 구현하시오
		// 에스프레소, 카푸치노, 카페라떼는 3500원, 아메리카노는 2000원이다
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("무슨 커피 드릴까요? >>> ");
		String coffee = sc.next();
		
		switch(coffee) {
		case "에스프레소" : case "카푸치노" : case "카페라떼" : System.out.println(coffee + "는 3500원 입니다."); break;
		case "아메리카노" : System.out.println(coffee + "는 2000원 입니다."); break;
		default : System.out.println(coffee + "는 메뉴에 없습니다.");
		}
	}
	
	public static void q3() {
		// 돈을 입력받아
		// 오만원권, 만원권, 오천원권, 천원권, 오백원 동전, 백원 동전, 오십원 동전, 십원 동전, 오원 동전, 일원 동전이
		// 각각 몇 개로 변환되는지 출력하시오
		// 이 때 반드시 다음과 같은 배열을 사용하여 반복문으로 처리하시오.
		
		int[] unit = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};
		
		Scanner sc = new Scanner(System.in);
		System.out.println("금액을 입력하시오 >>> ");
		int money = sc.nextInt();
		
		for(int i = 0; i < unit.length; i++) {
			if((money / unit[i]) > 0) { 
			System.out.println(unit[i] + "원 " + (money / unit[i]) + " 개");
			money %= unit[i];
			}
		}
	}
	
	public static void q4() {
		// 정수를 몇 개 저장할지(최대 100개) 입력 받아서
		// 해당 길이를 가진 배열을 생성하고,
		// 이곳에서 1에서 100사이 범위의 정수를 랜덤하게 삽입하시오.
		// 같은 값은 생성하지 못하도록 설정하고 생성된 배열을 출력하시오.
		
		Scanner sc = new Scanner(System.in);
		System.out.print("몇 개의 랜덤을 생성할까요? >>> ");
		int cnt = sc.nextInt();
		
		if(cnt < 1 || cnt > 100) {
			System.out.print("다음에는 1 ~ 100사이로 입력하세요!");
		}
		
		int[] arr = new int[cnt];
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 100) + 1;
			for(int j = 0; j < i; j++) {
				if (arr[i] == arr[j]) {
					i--;
				}
			}
		}
		for(int i = 0; i < arr.length; i++) {
			if(i != 0 && i % 10 == 0) {
				System.out.println();
			}
			System.out.print(arr[i] + "\t");
		}
		
		sc.close();
		
		System.out.println();
		
	}
	
	public static void q5() {
		// 3명의 학생의 점수를 입력 받아서 평균 점수와 1등의 이름과 꼴등의 이름을 출력하시오.
		Scanner sc = new Scanner(System.in);
		
		int[] arr1 = new int[3];
		String[] arr2 = {"피카츄", "뽀로로", "브레드"};
		
		System.out.print(arr2[0] + "의 점수 입력 >>> ");
		arr1[0] = sc.nextInt();
		System.out.print(arr2[1] + "의 점수 입력 >>> ");
		arr1[1] = sc.nextInt();
		System.out.print(arr2[2] + "의 점수 입력 >>> ");
		arr1[2] = sc.nextInt();
		
		int total = 0;
		int max = arr1[0];
		int min = arr1[0];
		String maxName = arr2[0];
		String minName = arr2[0];
		
		for(int i = 0; i < arr1.length; i++) {
			if (arr1[i] < 0 || arr1[i] > 100) {
				System.out.println("잘못된 점수");
			}
			System.out.println(arr1[i]);
			total += arr1[i];
		}
		
		for(int i = 1; i < arr1.length; i++) {
			if(max < arr1[i]) {
				max = arr1[i];
				maxName = arr2[i];
			} else if(min > arr1[i]) {
				min = arr1[i];
				minName = arr2[i];
			}
		}
		
		double average = (double) total / arr1.length;
		System.out.println("평균 : " + average + "점");
		
		System.out.println("1등 : " + maxName);
		System.out.println("3등 : " + minName);
		
		sc.close();
	
	}
	
	static void q6() {
		// 랜덤으로 윷놀이를 구현하시오.
		// 도개걸윷모 중 랜덤 생성하여 이동 횟수와 함께 화면에 출력하시오.
		// 윷이나 모가 나오면 계속 랜덤 생성하여 총 이동 횟수를 계산하여 출력하시오.
		// 도 : 1 칸 이동
		// 개 : 2 칸 이동
		// 걸 : 3 칸 이동
		// 윷 : 4 칸 이동
		// 모 : 5 칸 이동
		
		String[] arr = {"", "도", "개", "걸", "윷", "모"};
		int total = 0;
		
		while(true) {
			int move = (int)(Math.random() * 5) + 1;
			total += move;
			if(move < 4) {
				System.out.print(arr[move] +", ");
			} else {
				System.out.print(arr[move] + ", " + total + "칸 이동한다.");
				break;
			}
		}
	}
	
	public static void q7() {
		// 경과시간을 맞추는 게임을 작성하시오
		// 첫 번째 <Enter>를 누르면 해당 시점의 초 시간을 보여주고,
		// 두 번째 <Enter>를 누르면 해당 시점의 초 시간을 다시 보여준다.
		// 여기서 10초에 근접하도록 <Enter>를 누른 사람이 이기는 게임이다.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("강아지님 시작하려면 <Enter>를 누르세요.");
		sc.nextLine();
		
		System.out.println("10초가 된 것 같으면 <Enter>를 누르세요.");
		long start1 = System.currentTimeMillis();
		sc.nextLine();
		long end1 = System.currentTimeMillis();
		
		System.out.println("고양이님 시작하려면 <Enter>를 누르세요.");
		sc.nextLine();
		
		System.out.println("10초가 된 것 같으면 <Enter>를 누르세요.");
		long start2 = System.currentTimeMillis();
		sc.nextLine();
		long end2 = System.currentTimeMillis();
		
		double total1 = (end1 - start1) * 0.001;
		double total2 = (end2 - start2) * 0.001;
		
		System.out.print("강아지님 결과 " + total1 + "초, " + "고양이님 결과 " + total2 + "초, ");
		if(10 - total1 < 10 - total2) {
			System.out.print("승자는 강아지님 입니다.");
		} else {
			System.out.print("승자는 고양이님 입니다.");
		}
	}
		

	public static void main(String[] args) {
		System.out.println("=====문제1=====");
		//q1();
		System.out.println("=====문제2=====");
		//q2();
		System.out.println("=====문제3=====");
		//q3();
		System.out.println("=====문제4=====");
		q4();
		System.out.println("=====문제5=====");
		//q5();
		System.out.println("=====문제6=====");
		//q6();
		System.out.println("=====문제7=====");
		//q7();
	}

}
