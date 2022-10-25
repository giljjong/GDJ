<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function() {

		$('#btn_add').click(function(event){
			location.href="${contextPath}/student/write.do";
		});
		$('#btn_find').click(function(event){
			var begin = $('#begin').val();
			var end = $('#end').val();
			if(begin == '') {
				begin = 0;
			}
			if(end == '') {
				end = 100;
			}
			if(isNaN(begin) || begin < 0 || begin > 100) {
				alert('begin 값을 확인하세요.')
				return;
			} else if(isNaN(end) || end < 0 || end > 100) {
				alert('end 값을 확인하세요.')
				return;
			}
			
			location.href="${contextPath}/student/find.do?begin=" + begin + "&end=" + end;

		});
		$('#begin').focus(function(event){
			$('#begin').attr("placeholder", " ");
		});
		$('#begin').blur(function(event){
			$('#begin').attr("placeholder", "begin");
		});
		$('#end').focus(function(event){
			$('#end').attr("placeholder", " ");
		});
		$('#end').blur(function(event){
			$('#end').attr("placeholder", "end");
		})
	});

	</script>
	<link rel="stylesheet" href="../assets/css/student2.css">
	</head>
	<body>
	<header>
		<div class="head_bar">
			<div id="logo_div">
				<a href="../index.jsp"><img id="logo" src="https://www.gdu.co.kr/images/main/logo.png"></a>
			</div>
			<ul class="gnb_barAll">
				<li><a href="#"  class="gnb_bar">교직원 목록</a></li>
				<li><span class="snb_bar"></span><a href="${contextPath}/student/list.do" class="gnb_bar">학생관리</a></li>
				<li><span class="snb_bar"></span><a href="#" class="gnb_bar">결재</a></li>
				<li><span class="snb_bar"></span><a href="#"  class="gnb_bar">건의사항</a></li>
				<li><span class="snb_bar"></span><a href="#"  class="gnb_bar">Q&A</a></li>
			</ul>
		</div>
	</header>
		<div class="wrap">
			<h1 class="title">학생관리</h1>
			<div class="btn_area">
				<input type="button" value="신규학생등록" class="btn_primary btn_add" id="btn_add">
			</div>
			<div class="find_area">
				<span class="span_avg">평균 :&nbsp;</span>
				<input type="text" name="begin" class="beginEnd" id="begin" size="4" placeholder="begin">
				<span class="span_avg">~</span>
				<input type="text" name="end" class="beginEnd" id="end" size="4" placeholder="end">
				<input type="button" value="조회" class="btn_primary" id="btn_find">
			</div>
			<div class="main_area">
				<table>
					<caption><span class="txt_cap">전체 학생 ${count}명</span></caption>
					<thead>
						<tr>
							<td class="txt_avg">학번</td>
							<td class="txt_avg">성명</td>
							<td class="txt_avg">국어</td>
							<td class="txt_avg">영어</td>
							<td class="txt_avg">수학</td>
							<td class="txt_avg">평균</td>
							<td class="txt_avg">학점</td>
							<td class="txt_avg">버튼</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${count eq 0}">
							<tr>
								<td colspan="8">등록된 학생이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${count ne 0}">
							<c:forEach items="${students}" var="s">
								<tr>
									<td>${s.stuNo}</td>
									<td>${s.name}</td>
									<td>${s.kor}</td>
									<td>${s.eng}</td>
									<td>${s.math}</td>
									<td><fmt:formatNumber value="${s.ave}" pattern="0.00" /></td>
									<td>${s.grade}</td>
									<td>
										<input type="button" value="상세" class="btn_primary" onclick="fn_detail(${s.stuNo})">
										<script>
											function fn_detail(stuNo) {
												location.href="${contextPath}/student/detail.do?stuNo=" + stuNo;
											}
											function fn_remove(stuNo, name){
												if(confirm(name + ' 학생의 정보를 삭제하시겠습니까?')) {
													location.href="${contextPath}/student/remove.do?stuNo=" + stuNo;
												};
											};
										</script>
										<input type="button" value="삭제" class="btn_primary btn_remove" onclick="fn_remove(${s.stuNo}, '${s.name}')">
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5" class="txt_avg">전체평균</td>
							<td class="txt_avg"><fmt:formatNumber value="${average}" pattern="0.00" /></td>
							<td colspan="2"></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>

	</body>
	</html>