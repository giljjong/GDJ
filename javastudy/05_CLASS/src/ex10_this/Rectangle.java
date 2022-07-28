package ex10_this;

public class Rectangle {

	// 필드
	private int width;
	private int height;
	
	// 생성자
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(int n) {
		this(n , n);	// 인수 2개인 다른 생성자(현재 Rectagele(int width, int height)를 호출한다.
	}
	
	// 메소드
	public int getArea() {
		int area = width * height;
		return area;
	}
	
	public int getCircumference() {
		int Circumference = 2 * (width + height);
		return Circumference;
	}
	
}
