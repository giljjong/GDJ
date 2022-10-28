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
		$('#image').css('width', '600px');
	})
</script>
</head>
<body>
	
	<h1>보고 싶은 동물 ${param.fileName}</h1>
	<img id="image" alt="동물" src="${contextPath}/resources/images/${param.fileName}"/>
	
</body>
</html>