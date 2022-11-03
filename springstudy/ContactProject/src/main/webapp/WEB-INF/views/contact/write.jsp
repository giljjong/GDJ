<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function(){
		$('#btn_list').click(function(){
			location.href="${contextPath}/ctt/list";
		});
	});
	
</script>
</head>
<body>
	
	<div>
		<h1>연락처 등록</h1>
	</div>
	<div>
		<form action="${contextPath}/ctt/add" method="post">
			<div>
				<label for="name">이름*</label><br>
				<input type="text" id="name" name="name">
			</div><br>
			<div>
				<label for="tel">전화*</label><br>
				<input type="text" id="tel" name="tel">
			</div><br>
			<div>
				<label for="addr">주소*</label><br>
				<input type="text" id="addr" name="addr">
			</div><br>
			<div>
				<label for="email">이메일*</label><br>
				<input type="text" id="email" name="email">
			</div><br>
			<div>
				<label for="note">비고</label><br>
				<input type="text" id="note" name="note">
			</div><br>
			<div>
				<button>연락처 저장하기</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" id="btn_list" value="전체연락처">
			</div>
		</form>
	</div>
	
	
</body>
</html>