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
		
		var frm = $('#frm');
		
		$('#btn_list').click(function(){
			location.href="${contextPath}/ctt/list";
		});
		
		frm.submit(function(event){
			if($('#name').val() == '' || $('#tel').val() == '' || $('#addr').val() == '' || $('#email').val() == '') {
				alert('*표시가 된 것은 필수 입력입니다.');
				event.preventDefault();
				return;
			};
		});
		
		$('#btn_remove').click(function(event){
			if(confirm('연락처를 삭제할까요?')){
				$('#frm').attr('action', '${contextPath}/ctt/remove');
				$('#frm').submit();
			} else {
				event.preventDefault();
				return;
			}
		});
	});
	
</script>
</head>
<body>
	
	<div>
		<h1>연락처 등록</h1>
	</div>
	<div>
		<form id="frm" action="${contextPath}/ctt/modify" method="post">
			<div>
				<input type="hidden" name="no" value="${contact.no}">
			</div>
			<div>
				<label for="name">이름*</label><br>
				<input type="text" id="name" name="name" value="${contact.name}">
			</div><br>
			<div>
				<label for="tel">전화*</label><br>
				<input type="text" id="tel" name="tel" value="${contact.tel}">
			</div><br>
			<div>
				<label for="addr">주소*</label><br>
				<input type="text" id="addr" name="addr" value="${contact.addr}">
			</div><br>
			<div>
				<label for="email">이메일*</label><br>
				<input type="text" id="email" name="email" value="${contact.email}">
			</div><br>
			<div>
				<label for="note">비고</label><br>
				<input type="text" id="note" name="note" value="${contact.note}">
			</div><br>
			<div>
				<button>수정하기</button>
				<input type="button" id="btn_remove" value="삭제하기">
				<input type="button" id="btn_list" value="전체연락처">
			</div>
		</form>
	</div>
	
	
</body>
</html>