package library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor		// new Book()
@AllArgsConstructor		// new Book(1, "어린왕자", "생텍쥐베리")
@Getter					// getBookNo(), getTitl(), getAuthor()
@Setter					// setBookNo(1), setTitl("어린왕자"), setAuthor("생텍쥐베리")
@ToString				// String toString() {...}
public class Book {
	
	private int bookNo;		// 1 ~ 100 까지 자동 부여
	private String title;	// 사용자 입력
	private String author;	// 사용자 입력

	

}
