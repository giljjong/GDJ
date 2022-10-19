<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%-- 
		1. 동적 레이아웃
			1) 포함할 페이지에 변경되는 부분이 있다
			2) <jsp:include> 액션 태그를 사용한다
			3) <jsp:param> 액션 태그를 이용해서 파라미터를 전달한다
	 --%>
	 <% request.setCharacterEncoding("UTF-8"); %>
	 <jsp:include page="header.jsp">
	 	<jsp:param value="경제" name="title"/>
	 </jsp:include>
	
	<section>
		<div>경제1</div>
		<div>경제2</div>
		<div>경제3</div>
		<div>경제4</div>
	</section>
	
	
	<%--
		2. 정적 레이아웃
			1) 포함할 페이지에 변경되는 부분이 없다
			2) <%@ include file="" %> 지시어를 사용한다
	 --%>
	<%@ include file="footer.jsp" %>