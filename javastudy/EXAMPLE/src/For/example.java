package For;

import java.util.Scanner;

public class example {

	public static void main(String[] args) {
		
		
		int n = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("비밀 번호를 입력하세요 >>>> ");
		
		String userPass = "12388665";
		String password= sc.next();
		
		if(userPass == password) {
			System.out.println("패스워드가 일치합니다.");
			} else {
			n++;
			while() {
			switch(n) {
			case 1 : System.out.println(n + " 회 비밀번호 불일치"); break;
			case 2 : System.out.println(n + " 회 비밀번호 불일치"); break;
			case 3 : System.out.println(n + " 회 비밀번호 불일치"); break;
			case 4 : System.out.println(n + " 회 비밀번호 불일치"); break;
			case 5 : System.out.println(n + " 회 비밀번호 불일치"); break;
			case 6 : System.out.println(n + " 회 비밀번호 불일치"); break;
			case 7 : System.out.println(n + " 회 비밀번호 불일치"); break;
			case 8 : System.out.println(n + " 회 비밀번호 불일치"); break;
			case 9 : System.out.println(n + " 회 비밀번호 불일치"); break;
			}
			}
		
			
		}
	
		
		sc.close();

	}

}
