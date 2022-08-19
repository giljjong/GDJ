package baseBall;

import java.util.Scanner;

public class Chess {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] chess = {1, 1, 2, 2, 2, 8};
		int[] in = new int[6];
		
		for(int i = 0; i < in.length; i++) {
			in[i] = chess[i] - sc.nextInt();
		}
		
		for(int i = 0; i < in.length; i++) {
			System.out.println(in[i]);
		}
		
	}

}
