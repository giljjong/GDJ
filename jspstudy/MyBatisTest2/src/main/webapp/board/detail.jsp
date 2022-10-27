<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.title}</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#btn_list').click(function() {
			location.href="${contextPath}/board/list.do";
		})
		$('#btn_delete').click(function(){
			if(confirm('정말 삭제하시겠습니까?')){
				location.href="${contextPath}/board/remove.do?boardNo=${board.boardNo}"
			} else {
				return;
			}
		})
	});
	
</script>
</head>
<body>
	<form method="post" action="${contextPath}/board/edit.do">
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td><input type="text" name="boardNo" id="boardNo" value="${board.boardNo}" readonly></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>작성자</td>
					<td>${board.name}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" id="title" value="${board.title}"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content" id="content" rows="10" cols="40">${board.content}</textarea></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정">
						<input type="button" id="btn_list" value="목록">
						<input type="button" id="btn_delete" value="삭제">
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>