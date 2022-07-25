package ex02_datetime;

import java.util.ArrayList;

public class Array {

	public static void main(String[] args) {
		
		ArrayList<String> pitches = new ArrayList<>();
		pitches.add("138");
		pitches.add("129");
		
		String one = pitches.get(0);
		String two = pitches.get(1);
		
		System.out.println(one);
		System.out.println(two);

		System.out.println(pitches.size());
	}

}
