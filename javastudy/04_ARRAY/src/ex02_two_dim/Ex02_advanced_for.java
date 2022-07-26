package ex02_two_dim;

public class Ex02_advanced_for {

	public static void main(String[] args) {
		
		String[][] timeTable = {
				{"수학", "국어", "CA", "방송댄스"},
				{"수영", "사회", "한자", "일본어"},
				{"배드민턴", "국어", "한국 무용"},
				{"오락", "자습", "교양", "전필"},
				{"음악", "실습", "도덕", "영어", "독서"}
		};
		
		// 일반 for문
		for(int i = 0; i < timeTable.length; i++) {
			for(int j = 0; j < timeTable[i].length; j++) {
				System.out.print(timeTable[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 향상 for문
		for(String[] weekName : timeTable) {
			for(String course : weekName) {
				System.out.print(course + " ");
			}
			System.out.println();
		}
		
		/*	향상된 for문 식
		 * 
		 * for(String[] 객체a : 배열 이름) {
				for(String 객체b : 객체a) {
					System.out.print(객체 a + " ");
				}
				System.out.println();
			}
		*/
	}

}
