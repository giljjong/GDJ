<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="${contextPath }/quiz01">
		사이트명 :
		<select name="domain">
			<option value="google.com">구글</option>
			<option value="naver.com">네이버</option>
			<option value="daum.net">다음</option>
			<option value="nate.com">네이트</option>
		</select>
		<button>확인</button>
		</form>
	</div>
</body>
</html>