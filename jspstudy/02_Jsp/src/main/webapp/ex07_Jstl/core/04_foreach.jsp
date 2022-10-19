<%@page import="domain.Board"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 1. 1 ~ 5 --%>
	<c:forEach var="n" begin="1" end="5" step="1">
		<div>${n}</div>
	</c:forEach>
	
	<hr>
	
	<%-- 2. 5 ~ 1 --%>
	<c:forEach var="n" begin="1" end="5" step="1">
		<div>${6 -n}</div>
	</c:forEach>
	
	<hr>
	
	<%-- 3. <select> 1월 ~ 12월  --%>
	<div>
		<select name="month">
			<c:forEach var="n" begin="1" end="12" step="1">
					<option value="${n}">${n}월</option>
			</c:forEach>
		</select>
	</div>
	
	<hr>
	
	<%-- 4. 배열  --%>
	<%
		String[] menus = {"참치찌개", "초밥", "규동", "뼈해장국"};
		pageContext.setAttribute("menus", menus);
	%>
	<c:forEach	var="menu" items="${menus}" varStatus="vs">
		인덱스 : ${vs.index}, 순번 : ${vs.count}, 요소 : ${menu} <br>
	</c:forEach>
	
	<hr>
	
	<%-- 5. 리스트  --%>
	<%
		List<String> seasons = Arrays.asList("봄", "여름", "가을", "겨울");
		pageContext.setAttribute("seasons", seasons);
	%>
	<c:forEach var="season" items="${seasons}" varStatus="vs">
		인덱스 : ${vs.index }, 순번 : ${vs.count }, 요소 : ${season} <br>
	</c:forEach>
	
	<hr>
	
	<%-- 6. Map(반복문이 필요한 건 아니다.) --%>
	<%
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", 1);
		map.put("end", 10);
		pageContext.setAttribute("map", map);
	%>
	${map.begin } ~ ${map.end } <br>
	
	<hr>
	
	<%-- 7. 객체(반복문이 필요한 건 아니다.) --%>
	<%
		Board board = new Board();
		board.setBoardNo(1);
		board.setTitle("도돌이표");
		board.setHit(13528);
		pageContext.setAttribute("board", board);
	%>
	
	${board.boardNo }, ${board.title }, ${board.hit } <br>
	
	<%--
		${board.boardNo }은 ${board.getBoardNo()}를 자동으로 호출한다.
		즉, getter, setter 메소드가 있어야 호출이 가능하다.
	 --%>
	
	<hr>
	
	<%--
		문제. 임의의 Board 객체를 3개 저장한 리스트
	 --%>
	 <%
	 	Board board1 = new Board(2, "그림자", 324);
	 	Board board2 = new Board(3, "숨쉬기법", 8635);
	 	Board board3 = new Board(4, "이세계전송트럭운전사", 876451325);
	 	List<Board> boards = Arrays.asList(board1, board2, board3);
	 	pageContext.setAttribute("boards", boards);
	 %>
	 
	 <table border="1">
	 	<thead>
	 		<tr>
	 			<td>순번</td>
	 			<td>게시글번호</td>
	 			<td>제목</td>
	 			<td>조회수</td>
	 		</tr>
	 	</thead>
	 	<tbody>
	 			<c:forEach var="board" items="${boards}" varStatus="vs">
	 			<tr>
	 				<td>${vs.count }</td>
	 				<td>${board.boardNo }</td>
	 				<td>${board.title }</td>
	 				<td>${board.hit }</td>
	 			</tr>
	 			</c:forEach>
	 	</tbody>
	 </table>
</body>
</html>