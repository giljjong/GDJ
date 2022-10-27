<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="assets/js/jquery-3.6.1.min.js"></script>
<script>
$(document).ready(function() {
	$('#btn').click(function(){
		location.href="${contextPath}/board/list.do";
	});
})
</script>
</head>
<body>

	<button id="btn">게시판으로가기</button>

</body>
</html>