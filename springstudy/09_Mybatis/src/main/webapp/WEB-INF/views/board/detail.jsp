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
		
		var frm = $('#frm_btn');
		
		$('#btn_remove').click(function(event){
			if(confirm('삭제하시겠습니까?')){
				frm.attr('action', '${contextPath}/brd/remove');
				frm.submit();
				return;
				} else{
				event.preventDefault();
				return;
			}
		});
		
		$('#btn_edit').click(function(){
			frm.attr('action', '${contextPath}/brd/edit');
			frm.submit();
		});

		$('#btn_list').click(function(){
			location.href="${contextPath}/brd/list";
		});
		
	});
	
</script>
</head>
<body>
	
	<ul>
		<li>글번호 : ${board.boardNo}</li>
		<li>제목 : ${board.title}</li>
		<li>작성자 : ${board.writer}</li>
		<li>작성일자 : ${board.create_date}</li>
		<li>수정일자 : ${board.modify_date}</li>
	</ul>
	<div>
		${board.content}
	</div>
	
	<hr>
	
	<div>
		<form id="frm_btn" method="post">
			<input type="hidden" name="board_no" value="${board.boardNo}">
			<input type="button" id="btn_edit" value="수정">
			<input type="button" id="btn_remove" value="삭제">
			<input type="button" id="btn_list" value="목록">
		</form>
	</div>
	
</body>
</html>