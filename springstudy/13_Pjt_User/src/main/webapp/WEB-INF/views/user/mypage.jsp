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
		fn_pwCheck();
		fn_pwCheckAgain();
		fn_hideShow();
		fn_init();
		fn_pwSubmit();
	});
	
	var pwPass = false;
	var rePwPass = false;
	
	function fn_hideShow(){
		$('#ch_pw_block').hide();
		
		$('#btn_edit_pw').click(function(){
			fn_init();
			$('#ch_pw_block').show();
		});
		
		$('#btn_cancel').click(function(){
			fn_init();
			$('#ch_pw_block').hide();
		});
	};
	
	function fn_init(){
		$('#pw').val('');
		$('#msg_pw').text('');
		$('#re_pw').val('');
		$('#msg_re_pw').text('');
	}
	
	function fn_pwCheck(){
		
		$('#pw').keyup(function(){
			
			let pwValue = $(this).val();

			let regPw = /^[0-9a-zA-Z!@#$%^&*]{8,20}$/;
			
			let validatePw = /[0-9]/.test(pwValue)  
			               + /[a-z]/.test(pwValue) 
			               + /[A-Z]/.test(pwValue)      
			               + /[!@#$%^&*]/.test(pwValue); 
			
			if(regPw.test(pwValue) == false || validatePw < 3){
				$('#msg_pw').text('8~20자의 소문자, 대문자, 숫자, 특수문자(!@#$%^&*)를 3개 이상 조합해야 합니다.');
				pwPass = false;
			} else {
				$('#msg_pw').text('사용 가능한 비밀번호입니다.');
				pwPass = true;
			}
		});  // keyup
	}
	
	function fn_pwCheckAgain(){
		
		$('#re_pw').keyup(function(){
			
			let rePwValue = $(this).val();
			
			if(rePwValue != '' && rePwValue != $('#pw').val()){
				$('#msg_re_pw').text('비밀번호를 확인하세요.');
				rePwPass = false;
			} else {
				$('#msg_re_pw').text('');
				rePwPass = true;
			}
			
		});  // keyup
		
	}  // fn_pwCheckAgain
	
	function fn_pwSubmit(){
		$('#frm_edit_pw').submit(function(event){
			if(pwPass == false || rePwPass == false) {
				event.preventDefault();
				return;
			}
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
		height : 26px;
		border-radius: 5.5px;
		text-align : center;
		background-color : #4290df;
		margin : 8px 2px;
	}
	.btn_primary:hover {
		cursor : pointer;
	}
</style>
</head>
<body>
	<div>
		<h1>마이페이지</h1>
		<div>
			<input type="button" class="btn_primary" value="비밀번호변경" id="btn_edit_pw">
		</div>
		<div id="ch_pw_block">
			<form id="frm_edit_pw" action="${contextPath}/user/modify/pw" method="post">
				<div>
					<label for="pw">비밀번호*</label>
					<input type="password" name="pw" id="pw">
					<span id="msg_pw"></span>
				</div>
				<div>
					<label for="re_pw">비밀번호 재확인*</label>
					<input type="password" id="re_pw">
					<span id="msg_re_pw"></span>
				</div>
				<div>
					<button class="btn_primary" >비밀번호 변경하기</button>
					<input type="button" id="btn_cancel" class="btn_primary"  value="취소하기">
				</div>
			</form>
		</div>
	</div>
</body>
</html>