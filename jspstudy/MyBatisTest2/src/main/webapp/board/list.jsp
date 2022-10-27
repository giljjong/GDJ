<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#btn_text').click(function() {
			location.href="${contextPath}/board/write.do";
		})
	});
	
</script>
</head>
<body>
	<div>
		총 게시글 : ${count}개
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>작성자</td>
					<td>제목</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${count eq 0}">
					<tr>
						<td colspan="4">게시물이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${count ne 0}">
					<c:forEach items="${boards}" var="b">
						<tr>
							<td>${b.boardNo}</td>
							<td>${b.name}</td>
							<td><a href="${contextPath}/board/detail.do?boardNo=${b.boardNo}">${b.title}</a></td>
							<td>${b.createDate}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4"><button id="btn_text">새글작성</button></td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>