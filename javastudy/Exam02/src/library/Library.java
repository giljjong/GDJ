package library;

import java.util.Scanner;

public class Library {
	
	private Scanner sc; // new Scanner(System.in);
	private Book[] books;
	private int idx;
	
	public Library() {
		sc = new Scanner(System.in);
		books = new Book[100];
	}

	private void addBook() {
		if(idx == books.length) {
			System.out.println("더 이상 등록할 수 없습니다.");
			return;
		}
		System.out.println("===책등록===");
		System.out.print("책 제목을 입력해주세요. >>> ");
		String title = sc.next();
		System.out.print("저자를 입력해주세요. >>> ");
		String author = sc.next();
		Book book = new Book(idx + 1, title, author);
		books[idx++] = book;
	}
	
	private void removeBook() {
		if(idx == 0) {
			System.out.println("등록된 책이 한 권도 없습니다.");
			return;
		}
		System.out.println("===책삭제===");
		System.out.print("삭제 할 책의 번호를 입력해주세요. >>> ");
		int bookNo = sc.nextInt() - 1;
		for(int i = 0; i < idx; idx++) {
			if(books[i].getBookNo() == bookNo) {
				
				System.arraycopy(books, i + 1, books, bookNo, idx - i -1);
				books[idx--] = null;
				System.out.println("책 번호가 " + bookNo + "인 책을 삭제했습니다.");
				break;
			}
		}
		System.out.println("책 번호가 " + bookNo + "인 책이 없습니다.");
		
		}
	
	
	private void findBook() {
		if(idx == 0) {
			System.out.println("등록된 책이 한 권도 없습니다.");
			return;
		}
		System.out.println("===책조회===");
		System.out.print("조회 할 책 제목을 입력해주세요. >>> ");
		String title = sc.next();
		for(int i = 0; i < idx; i++) {
			// 저장된 책 제목
			// 조회할 책 제목
			if(books[i].getTitle().equals(title)) {
				System.out.println(books[i]);
				return;		// findBook () 메소드 종료
			}
		}
		System.out.println("제목이" + title + "인 책이 없습니다.");
	}
	
	private void printAllBooks() {
		if(idx == 0) {
			System.out.println("등록된 책이 한 권도 없습니다.");
			return;
		}
		System.out.println("===전체조회===");
		for(int i = 0; i < idx; i++) {
			System.out.println(books[i]);
		}
	}
	
	public void manage() {
		while(true) {
			System.out.print("1.추가 2.삭제 3.조회 4.전체목록 0.프로그램 종료 >>> ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 0 : System.out.println("Library 프로그램을 종료합니다. 감사합니다."); return;
			case 1 : addBook(); break;
			case 2 : removeBook(); break;
			case 3 : findBook(); break;
			case 4 : printAllBooks(); break;
			default : System.out.println("알 수 없는 명령입니다. 다시 입력 해주십시오.");
			}
		}
	}
	
	

}
