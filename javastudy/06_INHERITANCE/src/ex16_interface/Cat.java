package ex16_interface;

// extends Pet : 애완동물이다
// implements Walkable : 산책이 가능하다

public class Cat extends Pet implements Walkable {

	public Cat(String petName) {
		super(petName);
	}

}
