package quiz08_schedule;

import java.util.Scanner;

public class WeekScheduler {

	private int nthWeek;			// 1 ~ n주차
	private Day[] week;
	private String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};
	private Scanner sc;
	
	public WeekScheduler(int nthWeek) {
		this.nthWeek = nthWeek;
		week = new Day[7];
		sc = new Scanner(System.in);
	}
	
	private void makeSchedule() {
		System.out.println("=== 등록 ===");
		System.out.print("요일 입력 >>> ");
		String dayName = sc.next().substring(0,1);
		sc.nextLine();
		
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {		// 등록된 스케쥴이 없으면
					System.out.print("스케쥴을 입력하세요 >>> ");
					String schedule = sc.nextLine();			// 스케쥴에 공백 입력이 가능하다
					Day day = new Day();
					day.setSchedule(schedule);
					week[i] = day;
					System.out.println(dayName + "요일에 새 스케쥴이 등록되었습니다.");
				} else {
					System.out.println(dayName + "요일은 이미 스케쥴이 있습니다.");
				}
				return;
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");
	}
	
	private void changeSchedule() {
		System.out.println("=== 수정 ===");
		System.out.print("수정 할 요일을 입력해주세요. >>> ");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일은 저장된 스케쥴이 없습니다.");
					System.out.println("새 스케쥴을 등록할까요(y/n)?");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {
					} else if (yesNo.equalsIgnoreCase("n")) {
						System.out.println("새 스케쥴 작성이 취소되었습니다.");
					} else {
						System.out.println("잘못 입력 하셨습니다.");
					}
				} else {
					System.out.println(dayName + "요일의 스케쥴은 " + week[i].getSchedule() + "입니다.");
					System.out.print("수정하시겠습니까(y/n)?");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {
						week[i] = null;
					} else if (yesNo.equalsIgnoreCase("n")) {
						System.out.println("스케쥴 삭제가 취소되었습니다.");
					} else {
						System.out.println("잘못 입력 하셨습니다.");
					}
				}
				System.out.println("새 스케쥴을 입력해주세요 >>> ");
				String schedule = sc.nextLine();
				Day day = new Day();
				day.setSchedule(schedule);
				week[i] = day;
				System.out.println(dayName + "요일에 새 스케쥴이 등록되었습니다.");
			} return;
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");
	}
	
	private void deleteSchedule() {
		System.out.println("=== 삭제 ===");
		System.out.print("삭제 할 요일을 입력해주세요. >>> ");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일은 저장된 스케쥴이 없습니다.");
				} else {
					System.out.println(dayName + "요일의 스케쥴은 " + week[i].getSchedule() + "입니다.");
					System.out.print("삭제하시겠습니까(y/n)?");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {
						week[i] = null;
						System.out.println(dayName + "요일의 스케쥴이 삭제되었습니다.");
					} else if (yesNo.equalsIgnoreCase("n")) {
						System.out.println("스케쥴 삭제가 취소되었습니다.");
					} else {
						System.out.println("잘못 입력 하셨습니다.");
					}
				}
				return;
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");
	}
	
	private void printWeekSchedule() {
		System.out.println("=== 전체조회 ===");
		System.out.println(nthWeek + "주차 스케쥴 안내");
		for(int i = 0; i < week.length; i++) {
			System.out.print(dayNames[i] + "요일 - ");
			System.out.println(week[i] == null ? "X" : week[i].getSchedule());
		}
	}
	
	public void manage() {
		
		while(true) {
			
			System.out.print("1. 등록 2. 수정 3. 삭제 4. 전체 출력 0. 종료 >>> ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 0 : System.out.println("Scheduler 프로그램을 종료합니다. 감사합니다."); return;
			case 1 : makeSchedule(); break;
			case 2 : changeSchedule(); break;
			case 3 : deleteSchedule(); break;
			case 4 : printWeekSchedule(); break;
			default : System.out.println("알 수 없는 명령입니다. 다시 입력 해주십시오.");
			}
		}

	}
	
}
