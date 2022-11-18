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
		fn_checkPw();
	});
	
	function fn_checkPw(){
		$('#btn_check_pw').click(function(event){
			$.ajax({
				type : 'post',
				url : '${contextPath}/user/check/pw',
				data : 'pw=' + $('#pw').val(),
				dataType : 'json',
				success : function(resData){
					if(resData.isUser == true){
						location.href='${contextPath}/user/mypage';
					} else {
						$('#pw').val('');
						alert('비밀번호를 확인해주세요.');
					}
				}
			});
			
		});
	}
</script>
<style>
	body {
		background-color: #f1f7f4;
	}
	.btn_primary {
		border : none;
		color : #ffffff;
		font-size : 15px;
		font-weight : 700;
		text-decoration: none;
		display : inline-block;
		width : 40px;
		height : 26px;
		border-radius: 5.5px;
		text-align : center;
		background-color : #4290df;
		margin : 8px 2px;
	}
	.btn_primary:hover {
		cursor : pointer;
	}
	.please {
		margin-bottom : 16px;
	}
	#please {
		font-weight : 700;
		font-size : 20px;
	}
	
	#checkBlock{
		margin : 200px auto 0;
		width : 600px;
		height : 100px;	
	}
</style>
</head>
<body>
	
	<div id="checkBlock">
		<div class="please"><span id="please">개인정보보호를 위해서 비밀번호를 다시 한 번 입력하세요</span></div>
		
		<div>
			<label for="pw">비밀번호</label>
			<input type="password" id="pw" name="pw">
			<input type="button" value="확인" class="btn_primary" id="btn_check_pw">
			<input type="button" value="취소" class="btn_primary" onclick="history.back()">
		</div>
		
	</div>
</body>
</html>