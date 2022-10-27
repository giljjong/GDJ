<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새글 작성</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#btn_list').click(function() {
			location.href="${contextPath}/board/list.do";
		})
	});
</script>
</head>
<body>
	<form method="post" action="${contextPath}/board/insert.do">
	<table border="1">
		<thead>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="name" id="name"></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" id="content" rows="10" cols="40"></textarea></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록">
					<input type="button" id="btn_list"value="목록">
				</td>
			</tr>
		</tfoot>
	</table>
	</form>
</body>
</html>