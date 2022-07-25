package For;

public class For_01 {

	public static void main(String[] args) {
		
		// 1-4
		for(int n = 2; n < 7; n++) {
			for(int m = 1; m < n; m++) {
				System.out.print(m);
			}
			System.out.println();
		}
		System.out.println();
		
		// 1-5
		int a = 0;
		
		for(int n = 2; n < 7; n++) {
			for(int m = 1; m < n; m++) {
				a++;
				System.out.print(a);
			}
			System.out.println();
		}
		System.out.println();
		
		// 1-6
		for(int n = 6; n > 1; n--) {
			for(int m = 1; m < n; m++) {
				System.out.print(m);
			}
			System.out.println();
		}
		System.out.println();
		
		// 1-7
		int b = 0;
		for(int n = 6; n > 1; n--) {
			for(int m = 1; m < n; m++) {
				b++;
				System.out.print(b + "\t");
			}
			System.out.println();
		}
		System.out.println();
		
		// 1-8
		for(int n = 1; n < 6; n++) {
			
			for(int m = n; m < n + 5; m++) {
				
				System.out.print(m + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 1-9
		for(int n = 1; n < 6; n++) {
			for(int m = n; m < n + 5; m++) {
				a = m;
				if(a > 5) {
					a = m - 5;
				}
				System.out.print(a + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 1-10
		int c = 0;
		for(int n = 0; n < 5; n++) {
			for(int m = 5; m >= 0; m--) {
				if(m > n) {
					System.out.print("\t" + " ");
				} else {
					c++;
					System.out.print("\t" + c);
				}
			}
			System.out.println();
		}
		
		// 2-1
		for(int n = 0; n < 5; n++) {
			for(int m = 0; m < 5; m++) {
				String sum = "";
				sum += "*";
				System.out.print(sum);
			}
			System.out.println();
		}
		System.out.println();
		
		// 2-2
		for(int n = 1; n < 6; n++) {
			for(int m = 0; m < n; m++) {
				String sum = "*";
				System.out.print(sum);
			}
			System.out.println();
		}
		System.out.println();
		
		// 2-3
		for(int n = 0; n < 5; n++) {
			for(int m = 5; m >= 0; m--) {
				if(m > n) {
					System.out.print(" ");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		System.out.println();
		
		// 2-4
		for(int n = 0; n < 6; n++) {
			for(int m = n + 5; m >= 0; m--) {
				if(m >= n) {
					System.out.print(" ");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

}
