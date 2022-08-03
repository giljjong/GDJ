package ex16_interface;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pet {

	private String petName;

	public Pet(String petName) {
		super();
		this.petName = petName;
	}
	
}
