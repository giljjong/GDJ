package ex08_override;

public class Square extends Rectagle {
	
	public Square(String type, double width) {
		super(type, width, width);
	}
	

	@Override
	public double getArea() {
		return Math.pow(getWidth(), 2);
	}

}
