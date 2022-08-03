package ex03_singleton;

public class User {

	private static User user = new User();
	
	private User() {					// User 객체 생성은 User 내부에서만 가능하다.
		
	}
	
	// static 메소드(클래스 메소드)
	// 클래스가 생성될 때 함께 생성되는 메소드
	// 객체 생성 이전에 만들어지기 때문에 객체로 접근하지 않는다.
	// 클래스 이름으로 접근한다.
	// User user = User.getInstance();
	
	public static User getInstance() {
		return user;
	}
	
	
}
