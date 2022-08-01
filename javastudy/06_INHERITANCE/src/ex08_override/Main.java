package ex08_override;

public class Main {

	public static void main(String[] args) {
		
		Circle circle = new Circle("도넛", 7.5);
		circle.info();
		
		Square square = new Square("정사각형", 5);
		square.info();
		
		Rectagle rectagle = new Rectagle("직사각형", 6, 5);
		rectagle.info();
	}

}
