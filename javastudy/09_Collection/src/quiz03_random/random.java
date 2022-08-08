package quiz03_random;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class random {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("몇 개의 랜덤을 생성하시겠습니까? >>> ");
		int cnt = sc.nextInt();
		
		if(cnt < 1 || cnt > 100) {
			System.out.println("다음에는 1 ~ 100 사이로 입력하세요.");
			return;
		}
		int[] arr = new int[cnt];
		Set<Integer> set = new HashSet<Integer>(); 
		while(set.size() < cnt) {
			int random = (int)(Math.random() * 100 + 1);
			set.add(random);
		}
		int idx = 0;
		for(Integer n : set) {
			arr[idx++] = n;
		}
		for(int i = 0; i < cnt; i++) {
			System.out.print(arr[i] + " ");
			if((i + 1) % 10 == 0) {
				System.out.println();
			}
		}
	}

}
