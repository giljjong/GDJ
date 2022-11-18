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
	$(function(){
		$('#frm_restore').submit(function(event){
			if($('#pw').val() == ''){
				alert('비밀번호를 입력하세요.');
				event.preventDefault();
				return;
			};
		});
	});
</script>
<style>
	body {
		background-color: #f1f7f4;
	}
	#retire_text, #lnk_logout {
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
	
	#retire_text:hover {
		cursor : pointer;
	}
	
</style>
</head>
<body>
		<h1>휴면계정안내</h1>
		
		<div>
			안녕하세요!<br>
			${sleepUser.id}님은 1년 이상 로그인하지 않아 관련 법령에 의해 휴면계정으로 전환되었습니다.
			<ul>
				<li>가입일 ${sleepUser.joinDate}</li>
				<li>마지막 로그인 ${sleepUser.lastLoginDate}</li>
				<li>휴면 전환일 ${sleepUser.sleepDate}</li>
			</ul>
		</div>
		
		<hr>
		
		<div>
			<div>
				비밀번호를 입력한 뒤 휴면해제 버튼을 클릭해 주세요.
			</div>
			<form action="${contextPath}/user/restore" method="post">
				<div>
					<label for="pw">비밀번호</label>
					<input type="password" id="pw" name="pw">
				</div>
				<div>
					<button>휴면해제</button>
				</div>
			</form>
		</div>
</body>
</html>