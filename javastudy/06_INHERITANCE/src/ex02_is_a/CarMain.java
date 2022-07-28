package ex02_is_a;

public class CarMain {

	public static void main(String[] args) {
		
		Ev ev = new Ev();
			
		ev.Run();
		ev.charge();
		
		Hybrid hybrid = new Hybrid();
		
		hybrid.Run();
		hybrid.charge();
		hybrid.fillUp();

	}

}
