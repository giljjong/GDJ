package quiz01_library;

public class Book {
	
	private int bookNo;
	private String title;
	private String author;
	
	public Book(int bookNo, String title, String author) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
	}
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", title=" + title + ", author=" + author + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookNo != other.bookNo)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

	// Book[] books -> List<Book> books;
	
	// addBook() - FullCheck 없어짐.
	// removeBook() - Empty check 필요함(예외처리로 변경)
		// removeBookByIndex() - 인덱스 정보를 이용해서 제거
		// removeBookByObject() - 객체(Book)정보를 이용햇허 제거
	// findBook() - Empty check 필요요함(예외처리로 변경)
	// printAllBooks() - Empty check 필요요함(예외처리로 변경)
	// * 추가
	// modifyBook() - 책 제목을 입력 받아서 일치하는 책의 정보를 변경
	

	
	
}
