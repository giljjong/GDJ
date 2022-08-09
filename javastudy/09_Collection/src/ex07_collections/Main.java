package ex07_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {
	
	public static void printMovies(List<String> list) {
		Iterator<String> itr = list.iterator();
		int cnt = 0;
		while(itr.hasNext()) {
			String movie = itr.next();
			cnt++;
			System.out.print(movie);
			if(cnt < list.size()) {
				System.out.print("->");
			}
		}
		System.out.println();
		/* for(int i = 0, size = list.size(); i < size; i++) {
		 * System.out.println(list.get(i));
		 * if(i < size - 1) {
		 * System.out.println("->")
		 */
	}

	public static void main(String[] args) {
	
		List<String> movies = new ArrayList<String>();
		movies.add("타이타닉");
		movies.add("테넷");
		movies.add("명량");
		movies.add("에일리언");
		movies.add("솔트");
		
		printMovies(movies);	// 타이타닉 -> 테넷 -> 명량 -> 에일리언 -> 솔트
		
		// movie 리스트를 오름차순 정렬시킴
		Collections.sort(movies);
		printMovies(movies);
		
		// movies 리스트를 내림차순 정렬시킴
		Collections.reverse(movies);
		printMovies(movies);
		
		// 특정 요소의 인덱스 반환
		// 이진 검색(binary search)을 이용하므로 검색 속도가 매우 빠름
		// 단, 크기 순으로 정렬이 되어있어야 한다.
		int idx = Collections.binarySearch(movies, "타이타닉");
		System.out.println(idx);
		

	}

}
