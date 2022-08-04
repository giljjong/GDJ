package schedule;

import java.util.Scanner;

import schedule2.Day;

public class WeekScheduler {
	
	private int nthWeek;
	private Day[] week;
	private String[] dayNames = {"월", "화", "수", "목", "금", "토", "일"};
	private Scanner sc;
	
	public WeekScheduler(int nthWeek) {
		this.nthWeek = nthWeek;
		week = new Day[7];
		sc = new Scanner(System.in);
	}

	public void addSchedule() {
		System.out.println("=== 등록 ===");
		System.out.print("입력할 요일을 입력해주세요 >>> ");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
				System.out.print("스케쥴을 입력해주세요 >>> ");
				String schedule = sc.nextLine();
				Day day = new Day();
				day.setSchedule(schedule);
				week[i] = day;
				System.out.println(dayName + "요일에 새 스케쥴이 등록되었습니다.");
				} else {
					System.out.println("이미 스케쥴이 존재합니다.");
				} return;
			}
		}
		System.out.println(dayName + "요일은 잘못된 입력입니다.");
	}
	
	public void changeSchedule() {
		
	}
	 
	public void removeSchedule() {
		System.out.println("=== 삭제 ===");
		
	}
	
	public void printAllSchedule() {
		System.out.println("=== 전체 조회 ===");
		System.out.println(nthWeek + "주차 스케쥴 안내");
		for(int i = 0; i < week.length; i++) {
			System.out.print(dayNames[i] + "요일 -");
			System.out.println(week[i] == null ? "X" : week[i].getSchedule());
		}
	}
	
	public void manage() {
		while(true) {
			System.out.print("1.추가 2.수정 3.삭제 4.전체 조회 0.프로그램 종료 >>> ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1 : addSchedule(); break;
			case 2 : changeSchedule(); break;
			case 3 : removeSchedule(); break;
			case 4 : printAllSchedule(); break;
			case 0 : System.out.println("Schedule 프로그램을 종료합니다."); return;
			default : System.out.println("잘못된 입력입니다.");
			}
		}
	}
	
	

}
