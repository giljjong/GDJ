<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
</script>
<style>
	body {
		background-color: #f1f7f4;
	}
	#lnk_retire, #lnk_logout {
		color : #ffffff;
		font-size : 20px;
		font-weight : 700;
		text-decoration: none;
		display : inline-block;
		width : 98px;
		height : 36px;
		border-radius: 5.5px;
		text-align : center;
		padding-top : 4px;
		background-color : #4290df;
		margin : 8px 0;
	}
	
	#welcomebox {
		text-align: right;
		padding-right : 10px;
	}
	
	#retireBlock{
	
		margin : 200px auto 0;
		width : 150px;
		height : 60px;	
	}
</style>
</head>
<body>
	
	<!-- 로그인이 안 된 상태 -->
	<c:if test="${loginUser == null}">
		<a href="${contextPath}/user/agree">회원가입 페이지</a>
		<a href="${contextPath}/user/login/form?url=${url}">로그인 페이지</a>
	</c:if>
	
	<!-- 로그인이 된 상태 -->
	<c:if test="${loginUser ne null}">
		<div id="welcomebox">
			<a href="${contextPath}/user/mypage">${loginUser.name}</a>님 반갑습니다.
		</div><br>
		<div id="retireBlock">
			<a href="${contextPath}/user/logout" id="lnk_logout">로그아웃</a>
			<a href="${contextPath}/user/retire" id="lnk_retire">회원탈퇴</a><br>
		</div>
	</c:if>
		<script>
			$('#lnk_retire').click(function(){
				if(!confirm('탈퇴하시겠습니까?')) {
					event.preventDefault();
					return;
				}
			});
		</script>
</body>
</html>