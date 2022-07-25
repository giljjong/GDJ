package ex03_string;

import java.util.Scanner;

public class Ex01_String {

	public static void main(String[] args) {
		
		String str1 = "hello";
		String str2 = "hello";
		
		System.out.println(str1 == str2);
		
		String str3 = new String("hi");
		String str4 = new String("hi");
		
		System.out.println(str3 == str4);
		
		// 1. 문자열 동등 비교
		boolean result1 = str1.equals(str2);
		boolean result2 = str3.equals(str4);
		
		System.out.println(result1);
		System.out.println(result2);
		
		if(str1.equals(str2)) {
			System.out.println("str1, str2가 같습니다.");
		} else {
			System.out.println("str1, str2가 다릅니다.");
		}

		if(!str3.equals(str4)) {
			System.out.println("str3, str4가 다릅니다.");
		} else {
			System.out.println("str3, str4가 같습니다.");
		}
		
		// 2. 대소문자를 무시한 문자열 동등비교
		String str5 = "Hello World";
		String str6 = "hELLO wORLD";
		boolean result3 = str5.equalsIgnoreCase(str6);
		
		System.out.println(result3);
		
		// 3. 문자열 길이 반환
		String name = "정종길";
		int length = name.length();
		
		System.out.println("글자수 : " + length);
		
		// 4. 특정 위치의 문자(char)만 반환
		// 특정위치
		// 인덱스(Index)라고 함
		// 글자마다 부여된 정수 값
		// 0으로 시작
		System.out.println(name.charAt(0));
		System.out.println(name.charAt(1));
		System.out.println(name.charAt(2));
		
		// 5. 문자열의 일부 문자열(String)을 반환
		// 1) substring(begin)	:	인덱스 begin(포함)부터 끝까지 반환
		// 2) substring(begin, end)	:	인덱스 begin(포함)부터 인덱스 end(불포함)까지 반환
		System.out.println(name.substring(0, 1));
		System.out.println(name.substring(1));
		
		// 6. 특정 문자열을 찾아서 해당 인덱스(int)를 반환
		//	1) indexOf
		//		(1) 발견된 첫 번째 문자열의 인덱스를 반환한다.
		//		(2) 발견된 문자열이 없는 겨우 -1을 반환한다.
		//	2) lastIndexOf
		//		(1) 발견된 마지막 문자열의 인덱스를 반환한다.
		//		(2) 발견된 문자열이 없는 겨우 -1을 반환한다.
		int idx1 = name.indexOf("정");
		int idx2 = name.indexOf("종길");
		int idx3 = name.lastIndexOf("정");
		int idx4 = name.lastIndexOf("종길");
		System.out.println(idx1);
		System.out.println(idx2);
		System.out.println(idx3);
		System.out.println(idx4);
		
		// 7. 문자열이 특정 패턴으로 시작하는지 여부를 boolean(true, false)로 반환
		//	  startWith(문자열)
		
		if(name.startsWith("정")) {
			System.out.println("정씨입니다.");
		} else {
			System.out.println("정씨가 아닙니다.");
		}
		
		// 8. 문자열이 특정 패턴으로 끝나는지 여부를 boolean(true, false)로 반환
		// 	  endWith(문자열)
		String filename = "image.jpg";	// jpg, png로 끝나면 이미지로 가정
		if(filename.endsWith("jpg") || filename.endsWith("png")) {
			System.out.println("이미지입니다.");
		} else {
			System.out.println("이미지가 아닙니다.");
		}
		
		// 9. 문자열이 특정 패턴을 포함하는지 여부를  boolean(true, false)로 반환
		String email = "doriyet@naver.com";
		if(email.contains("@") && email.contains(".")) {
			System.out.println("이메일입니다.");
		} else {
			System.out.println("이메일이 아닙니다.");
		}
		
		// 10. 불필요한 좌우 공백 제거
		String message = "  안녕  하세요  ";
		System.out.println(message.trim());
		System.out.println(message.trim().length());
		
		// 11. 대소문자 변환하기
		// toUpperCase() : 대문자로 변환하기
		// toLowerCase() : 소문자로 변환하기
		String source = "best of best";
		System.out.println(source.toUpperCase());
		System.out.println(source.toLowerCase());
		
		// 12. 찾아 바꾸기
		// replace(old, new) : old를 new로 변환하기
		String replaced = source.replace("best", "worst");
		System.out.println(source);
		System.out.println(replaced);
		
		//주의. replaceAll() 메소드는 특정 문자열을 찾아서 변환하는 것이 아님.
		String ip = "192.168.101.91";
		String replacedIp = ip.replaceAll(".", "_");	// 192_168_101_91을 기대하고 변환.
		System.out.println(replacedIp);
		
		// 13. 빈 문자열인지 여부를 검사한 뒤 boolean(true, false) 반환
		String id = " ";
		if(id.trim().isEmpty()) {
			System.out.println("빈 문자열");
		} else {
			System.out.println("빈 문자열이 아닙니다.");
		}
		
		// 연습. 파일이름을 파일명과 확정자로 분리
		// 단, jpg, gif, png 이미지인 경우에만 작업을 진행한다.
		String fullName = "apple.jpg";
		String fileName = ""; // apple
		String extName = ""; // jpg
		
		int idxOfDot = fullName.lastIndexOf(".");

		if(fullName.endsWith("jpg") || fullName.endsWith("gif") || fullName.endsWith("png")) {
			fileName = fullName.substring(0, idxOfDot);
			extName = fullName.substring(idxOfDot + 1);
			System.out.println(fileName);
			System.out.println(extName);
		}
		
		// 연습. 문자열 "abc12345def67890ghijk"
		// 아라비아 숫자 1234567890을 제외하고 한 글자씩 화면에 출력하시오.
		String str = "abc12345def67890ghijk";
		
		for(int n = 0; n < str.length(); n++) {
			char ch = str.charAt(n);
			if((char)(ch) >= 'a')
			System.out.print(ch);
		}
		System.out.println();
		
		
		// 연습. 대문자 6자리로 구성된 인증코드 작성하기
		StringBuilder sbCode = new StringBuilder();
		
		for(int n = 1; n <= 6; n++) {
				sbCode.append((char)((int)(Math.random() * 26) + 'A'));
			}
		
		String code = sbCode.toString();
		System.out.println("인증코드 : " + code);
		
		// 연습. 1 2 3 4 5 6 7 8 9 10 만들기
		StringBuilder sbPaging = new StringBuilder();
		
		for(int n = 1; n <= 10; n++) {
			sbPaging.append(n);
		};
		
		String paging = sbPaging.toString();
		System.out.println(paging);
		
		// 연습. char 타입의 성별을 입력 받기
		Scanner sc = new Scanner(System.in);
		
		System.out.println("성별 (남/여)을 입력하세요 >>> ");
		char gender = sc.next().charAt(0);
		
		System.out.println(gender);
		
		sc.close();
		
	}

}
