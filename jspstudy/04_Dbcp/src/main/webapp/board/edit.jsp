<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.board_no} 번 게시글 수정</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(document).ready(function() {
		$('#frm_edit').submit(function(event){
			// 제목, 내용 모두 변경이 없다.
			if(${board.title} == $('#title').val() && ${board.content} == $('#content').val()) {
				alert('변경된 내용이 없습니다.');
				event.preventDefault();
				return;
			}
			
			// 제목이 비어있다.
			if($('#title').val() == '') {
				alert('제목은 필수입니다.');
				event.preventDefault();
				return;
			}
		});
		
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board/edit.do?board_no=${board.board_no}';
		});
	});
	
	
</script>
</head>
<body>
	<h1>게시글 수정 화면</h1>
	<div>
		<form method="POST" action="${contextPath}/board/modify.do">
			<div>
				게시글 번호 : ${board.board_no}
				<input type="hidden" name="board_no" value="${board.board_no}">
			</div>
			<div>
				게시글 제목 : <input type="text" name="title" id="title" value="${board.title}">
			</div>
			<div>
				게시글 내용<br>
				<textarea name="content" id="content" rows="5" cols="30">${board.content}</textarea>
			</div>
			<div>
				작성일자 : ${board.create_date}
			</div>
			<div>
				<input type="submit" value="수정" id="btn_modify">
				<input type="button" value="목록" id="btn_list">
			</div>
		</form>
	</div>
</body>
</html>