package ex02_two_dim;

public class Ex01_array {

	public static void main(String[] args) {
		
		// 2차원 배열
		// 1. 1차원 배열을 여러 개 관리하는 자료 구조
		// 2. 1차원 배열들의 길이는 서로 다를 수 있음
		
		// 2차원 배열의 선언 및 생성
		// 1. 2차원 배열의 선언
		//		int[][] arr;
		// 2. 2차원 배열의 생성
		//		1 ) arr = new int[3][2];	- 3행 2열의 구조
		// 		2 ) arr = new int[3][ ]; 	- 3행 n열의 구조
		//			arr[0] = new int[5];	- 1번째 1차원 배열
		//			arr[1] = new int[3];	- 2번째 1차원 배열
		//			arr[2] = new int[8];	- 3번째 1차원 배열
		
		// 2차원 배열의 요소
		// 1. 인덱스를  2개 사용
		// 		몇 번째 1차원 배열인가? 해당 배열의 몇 번째 요소인가?
		// 2. 2차원 배열이 관리하는 1차원 배열
		//		arr[0]
		//		arr[1]
		//		arr[2]
		// 3. 각각의 1차원 배열이 관리하는 배열 요소
		//		arr[0][0], arr[0][1]
		//		arr[1][0], arr[1][1]
		//		arr[2][0], arr[2][1]
		
		
		// 3행 2열 2차원 배열
		int [][] arr1 = new int[3][2];
		
		for(int i = 0; i < arr1.length; i++) {			//	i : 몇 번째 1차원 배열인가?
			for(int j = 0; j < arr1[i].length; j++) {	//	j :  1차원 배열의 몇 번째 요소인가
				System.out.print(arr1[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 3행 n열 2차원 배열
		int[][] arr2 = new int[3][];
		arr2[0] = new int[5];		// 1번째 1차원 배열은 길이가 5
		arr2[1] = new int[4];		// 2번째 1차원 배열은 길이가 4
		arr2[2] = new int[8];		// 3번째 1차원 배열은 길이가 
				
		for(int i = 0; i < arr2.length; i++) {			//	i : 몇 번째 1차원 배열인가?
			for(int j = 0; j < arr2[i].length; j++) {	//	j :  1차원 배열의 몇 번째 요소인가
				System.out.print(arr2[i][j] + " ");
			}
			System.out.println();
		}
		
		
		
		
		

	}

}
