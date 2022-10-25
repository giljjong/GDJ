<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 조회 정보</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function() {
		
		$('#frm_detail').submit(function(event){
			var kor = $('#kor');
			var eng = $('#eng');
			var math = $('#math');
			if(kor.val() == '' || isNaN(kor.val()) || kor.val() < 0 || kor.val() > 100) {
				alert('국어 점수를 확인하세요.');
				kor.focus();
				event.preventDefault();
				return;
			}
			if(eng.val() == '' || isNaN(eng.val()) || eng.val() < 0 || eng.val() > 100) {
				alert('영어 점수를 확인하세요.');
				eng.focus();
				event.preventDefault();
				return;
			}
			if(math.val() == '' || isNaN(math.val()) || math.val() < 0 || math.val() > 100) {
				alert('수학 점수를 확인하세요.');
				math.focus();
				event.preventDefault();
				return;
			}
		})
		
		$('#btn_list').click(function(event){
			location.href="${contextPath}/student/list.do";
		});
		
		$('#btn_modify').click(function(event){
			$('.view_chg').hide();
			$('.modify_area').show();
		});
		
		$('#btn_cancel').click(function(event){
			$('.view_chg').show();
			$('.modify_area').hide();
		});
		
	});
</script>
<link rel="stylesheet" href="../assets/css/student2.css">
</head>
<body>

	<h1>상세 조회</h1>
	<div id="modify_area">
		<form id="frm_detail" method="post" action="${contextPath}/student/modify.do?stuNo=${student.stuNo}">
			<div>
				<label for="stuNo">학번 : </label>
				<span class="view_chg" >${student.stuNo}</span>
				<input type="text" name="stuNo" id="stuNo" class="modify_area" value="${student.stuNo}" readonly>
			</div>
			<div>
				<label for="name">이름 : </label>
				<span class="view_chg" >${student.name}</span>
				<input type="text" name="name" id="name" class="modify_area" value="${student.name}">
			</div>
			<div>
				<label for="kor">국어 : </label>
				<span class="view_chg" >${student.kor}</span>
				<input type="text" name="kor" id="kor" class="modify_area" value="${student.kor}">
			</div>
			<div>
				<label for="eng">영어 : </label>
				<span class="view_chg" >${student.eng}</span>
				<input type="text" name="eng" id="eng" class="modify_area" value="${student.eng}">
			</div>
			<div>
				<label for="math">수학 : </label>
				<span class="view_chg" >${student.math}</span>
				<input type="text" name="math" id="math" class="modify_area" value="${student.math}">
			</div>
			<div>
				<label for="ave">평균 : </label>
				<span class="view_chg" >${student.ave}</span>
				<input type="text" name="ave" id="ave" class="modify_area" value="${student.ave}" readonly>
			</div>
			<div>
				<label for="grade">학점 : </label>
				<span class="view_chg" >${student.grade}</span>
				<input type="text" name="grade" id="grade" class="modify_area" value="${student.grade}" readonly>
			</div>
			<hr>
			<div>
				<input type="button" class="view_chg"  value="수정" id="btn_modify">
				<input type="button" class="view_chg"  value="목록" id="btn_list">
				<input type="reset" class="modify_area" value="입력초기화">
				<input type="button" class="modify_area"  value="수정취소" id="btn_cancel">
				<input type="submit" class="modify_area" value="수정 완료">
			</div>
		</form>
		
	</div>

</body>
</html>