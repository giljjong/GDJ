<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>

	<h1>하아하아이</h1>
	<img alt="기분이조크든요" src="resources/images/hooray.jpg" width="600px">
	
	<hr>
	
	<h3>Member 관련 요청</h3>
	
	<div>
		<a href="${contextPath}/member/detail1?id=admin&pw=1234">전송</a>
	</div>
	
	<div>
		<input type="button" value="전송" id="btn">
	</div>
	
	<script>
		$('#btn').click(function() {
			// location.href="${contextPath}/member/detail2?id=admin&pw=1234";	
			// location.herf="${contextPath}/member/detail3?id=admin&pw=1234";
			location.href='${contextPath}/member/detail3';
		});
		
	</script>
	
	<form action="${contextPath }/member/detail4" method="POST">
		<div> <input type="text" name="id" placholder="아이디"> </div>
		<div><input type="password" name="pw" placholder="패스워드"></div>
		<button>전송</button>
	</form>
	
	<hr>
	
	<div>
		<a href="${contextPath}/board/detail1?title=공지사항&hit=10">전송</a>
	</div>
	
	<div>
		<a href="${contextPath}/board/detail3?title=공지사항&hit=10">전송</a>
	</div>
	
	
</body>
</html>