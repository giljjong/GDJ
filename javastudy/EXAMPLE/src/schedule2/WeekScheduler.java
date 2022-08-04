package schedule2;

import java.util.Scanner;

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



	private void addSchedule() {
		System.out.println("=== 추가 ===");
		System.out.print("요일을 입력해주세요. >>> ");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				System.out.print("스케쥴을 입력해주세요. >>>");
				String sch = sc.nextLine();
				Day day = new Day();
				day.setSchedule(sch);
				week[i] = day;
			} return;
		} System.out.println(dayName + "요일은 존재하지 않습니다.");
	}
	
	private void changeSchedule() {
		System.out.println("=== 수정 ===");
		System.out.print("수정할 요일을 입력해주세요 >>> ");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일에 스케쥴이 존재하지 않습니다.");
					System.out.print("새 스케쥴을 등록하시겠습니까(y/n)?");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					
					if(yesNo.equalsIgnoreCase("y")) {
						System.out.print("스케쥴을 입력해주세요. >>> ");
						String sch = sc.nextLine();
						Day day = new Day();
						day.setSchedule(sch);
						week[i] = day;
					} else if(yesNo.equalsIgnoreCase("n")) {
						System.out.println("입력 취소");
					} else {
						System.out.println("잘못된 입력입니다.");
					}
				} else {
					System.out.print(dayName + "요일의 스케쥴은 " + week[i].getSchedule() + "입니다. 수정하시겠습니까(y/n)?");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					
					if(yesNo.equalsIgnoreCase("y")) {
						week[i] = null;
						
						System.out.print("새 스케쥴을 입력해주세요. >>>");
						String sch = sc.nextLine();
						Day day = new Day();
						day.setSchedule(sch);
						week[i] = day;
						System.out.println("새 스케쥴이 등록되었습니다.");
					} else if(yesNo.equalsIgnoreCase("n")) {
						System.out.println("취소");
					} else {
						System.out.println("잘못된 입력입니다.");
					}
				} return;
			} 
		} System.out.println(dayName + "요일은 존재하지 않습니다.");
	}
	
	private void removeSchedule() {
		System.out.println("=== 삭제 ===");
		System.out.print("삭제할 요일을 입력해주세요 >>> ");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일에 스케쥴이 존재하지 않습니다.");
				} else {
					System.out.println(dayName + "요일의 스케쥴은 " + week[i].getSchedule() + "입니다. 삭제하시겠습니까(y/n)?");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {
						week[i] = null;
						System.out.println(dayName + "요일의 스케쥴이 삭제되었습니다.");
					} else if(yesNo.equalsIgnoreCase("n")) {
						System.out.println("삭제 취소");
					} else {
						System.out.println("잘못된 입력입니다.");
					}
				} return;
			}
		} System.out.println(dayName + "요일은 존재하지 않습니다.");
	}
	
	private void printAllSchedule() {
		System.out.println("=== 전체 조회 ===");
		for(int i = 0; i < week.length; i++) {
			System.out.print(dayNames[i] + "요일 - ");
			System.out.println(week[i] == null ? "X" : week[i].getSchedule());
			
		}
	}
	
	public void manage() {
		while(true) {
			System.out.print("1.추가 2.수정 3.삭제 4.전체 조회 >>> ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1 : addSchedule(); break;
			case 2 : changeSchedule(); break;
			case 3 : removeSchedule(); break;
			case 4 : printAllSchedule(); break;
			case 0 : System.out.println("Scheduler를 종료합니다.");return;
			default : System.out.println("잘못된 입력입니다.");
			}
		}
	}

}
