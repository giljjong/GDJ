package api;


public class Accident {
	
	private String occrrncDt;
	private String occrrncDayCd;
	private int dthDnvCnt;
	private int injpsnCnt;
	
	
	public Accident(String occrrncDt, String occrrncDayCd, int dthDnvCnt, int injpsnCnt) {
		super();
		this.occrrncDt = occrrncDt;
		this.occrrncDayCd = occrrncDayCd;
		this.dthDnvCnt = dthDnvCnt;
		this.injpsnCnt = injpsnCnt;
	}

		/*  상세구현

		 - 1) 2021년 서울특별시 금천구의 사망교통사고정보를 모두 조회한다.

		 - 2) 조회된 결과를 이용해서 accident.txt 파일을 생성한다. 파일의 경로는 작성하지 않는다. 예시 File file = new File("accident.txt");

		 - 3) 다음 값을 저장할 수 있는 Accident 클래스를 생성한다.

		         String occrrncDt;  // 발생월일시 (2019011622)

		         String occrrncDayCd;  // 발생요일코드 (4)

		         int dthDnvCnt;  // 사망자수 (0)

		         int injpsnCnt;  // 부상자수 (1)

		 - 4) 조회된 사고 1건의 정보는 Accident 객체에 저장한다. 조회된 사고 건수만큼 Accident 객체를 생성한다.

		 - 5) 생성된 모든 Accident 객체를 컬렉션 ArrayList에 모두 저장한다.
		 */
		

}
