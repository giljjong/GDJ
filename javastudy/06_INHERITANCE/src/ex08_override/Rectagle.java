package ex08_override;

public class Rectagle extends Shape {
	
	private double height;
	private double width;
	
	public Rectagle(String type, double width, double height) {
		super(type);
		this.width = width;
		this.height = height;
	}
	
	@Override
	public double getArea() {
		return width * height;
	}
	
	public double getWidth() {
		return width;
	}

	@Override
	public void info() {
		super.info();
		System.out.println("길이 : " + width + ", 높이 : " + height);
		System.out.println("넓이 : " + getArea());
	}
	
}
