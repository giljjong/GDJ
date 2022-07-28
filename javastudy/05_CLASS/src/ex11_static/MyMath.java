package ex11_static;

public class MyMath {
	
	
	// static
	// 1. 정적요소이다
	// 2. 객체 생성 이전에 메모리에 미리 만들어 지는 공유 요소
	// 3. 클래스에서 1개만 만들어 진다.
	// 4. 클래스를 이용해서 호출하기 때문에 클래스 변수, 클래스 메소드 라고 부른다.
	
	// 필드
	public static final double PI = 3.141592;
	
	// 메소드
	public static int abs(int n) {
		return (n >= 0) ? n : -n;
	}
	
	public static int pow(int n, int m) {
		int total = 1;
		for(int i = 0; i < m; i++) {
			total *= n;
		};
		return total;
	}
	
	
	
}
