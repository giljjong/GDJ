<%@page import="service.NaverCaptchaServiceImpl"%>
<%@page import="service.NaverCaptchaService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	pageContext.setAttribute("contextPath", request.getContextPath());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function(){
		fn_refresh();
	});
	function fn_refresh() {
		$('#btn_refresh').click(function(){
			$.ajax({
				type : 'get',
				url : '${contextPath}/member/refreshCaptcha.do',
				dataType : 'json',
				success : function(resData) {
					$('#ncaptcha').attr('src', '../'+resData.dirname+'/'+resData.fileName+'');
					$('#key').val(resData.key);
				}
			});
		});
	}
</script>
</head>
<body>

	<div class="wrap">
		<h1>로그인</h1>
		<form id="frm" action="${contextPath}/member/validateCaptcha.do">
			<div>
				<input type="text" name="id" id="id" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="pw" id="pw" placeholder="패스워드">
			</div>
			<div>
				 <p>아래 이미지를 보이는 대로 입력해주세요</p>
				 <div style="display : flex;">
				 	<div>
				 		<img id="ncaptcha" alt="캡쳐이미지" src="../${dirname}/${fileName}">
				 	</div>
				 	<div>
				 		<input id="btn_refresh" type="button" value="새로고침">
				 	</div>
				 </div>
			</div>
			<div>
				<input type="text" name="value" id="user_input" placeholder="자동입력 방지문자">
				<input type="hidden" id="key" name="key" value="${key}">
			</div>
			<div>
				<button>로그인</button>
			</div>
		</form>
	</div>

</body>
</html>