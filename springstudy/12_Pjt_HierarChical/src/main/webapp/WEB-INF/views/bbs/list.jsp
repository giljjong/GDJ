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
		if('${recordPerPage}' != '') {
			$('#${recordPerPage}').attr('selected', 'selected');
		}
		
		$('#recordPerPage').change(function(){
			location.href="${contextPath}/bbs/list?recordPerPage=" + $('#recordPerPage').val();
		});
	})
	
</script>
<style >
	.blind {
		display: none;
	}
	.reply_hide {
		display: none;
	}
</style>
</head>
<body>
	
	<div>
		<a href="${contextPath}/bbs/write">작성하러가기</a>
	</div>

	<div>
		<select id="recordPerPage" name="recordPerPage">
			<option id="10" value="10">10</option>
			<option id="20" value="20">20</option>
			<option id="30" value="30">30</option>
		</select>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>작성자</td>
					<td>제목</td>
					<td>IP</td>
					<td>작성일</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bbs" items="${bbsList}" varStatus="vs">
					<tr>
						<td>${totalRecord - (beginNo - vs.index) + 1}</td>
						<td>${bbs.writer}</td>
						<td>
							<c:if test="${bbs.state == 0}">
								삭제된 게시글입니다.
							</c:if>
							<c:if test="${bbs.state == 1}">
								<c:forEach begin="1" end="${bbs.depth}" step="1">
									&nbsp;&nbsp;
								</c:forEach>
								<c:if test="${bbs.depth > 0}">
									[RE]
								</c:if>
								${bbs.title}
								<input type="button" class="reply_write" value="답글">
								<input type="button" class="reply_hide reply_shut" value="답글">
								<script>
									$('.reply_shut').click(function() {
										$('.reply_container').addClass('blind');
										$('.reply_shut').addClass('reply_hide');
										$('.reply_write').show();
									});
									
									$('.reply_write').click(function(){
										$('.reply_container').addClass('blind');
										$(this).parent().parent().next().removeClass('blind');
										$(this).hide();
										$(this).next().removeClass('reply_hide');
									});
								</script>
							</c:if>
						</td>
						<td>${bbs.ip}</td>
						<td>${bbs.createDate}</td>
						<td>
							<form method="post" action="${contextPath}/bbs/remove">
								<input type="hidden" name="bbsNo" value="${bbs.bbsNo}">
								<a id="lnk_${bbs.bbsNo}">X</a>
							</form>
							<script>
								$('#lnk_${bbs.bbsNo}').click(function(){
									if(confirm('삭제할까요?')){
										$(this).parent().submit();
									}
								});
							</script>
						</td>
					</tr>
					<tr class="reply_container blind" >
						<td colspan="6">
							<div>
								<form method="post" id="reply_input" action="${contextPath}/bbs/reply/add" >
									<input type="text" name="writer">
									<input type="text" name="title">
									<input type="hidden" name="depth" value="${bbs.depth}">
									<input type="hidden" name="groupNo" value="${bbs.groupNo}">
									<input type="hidden" name="groupOrder" value="${bbs.groupOrder}">
									<button>작성완료</button>
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="paginate">
			<div class="pageWrap">
				${paging}
			</div>
		</div>
		
	</div>
</body>
</html>