<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<% request.setCharacterEncoding("UTF-8"); %>
	<jsp:include page="header.jsp">
	 	<jsp:param value="사회" name="title"/>
	 </jsp:include>
	
	<section>
		<div>사회1</div>
		<div>사회2</div>
		<div>사회3</div>
		<div>사회4</div>
	</section>
	
	<%@ include file="footer.jsp" %>