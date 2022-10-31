<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<h1>하이하이이</h1>
	<a href="${contextPath}/board/detail">게시판</a>
	
	<hr>
	
	<a href="${contextPath}/notice/detail">공지사항</a>
</body>
</html>