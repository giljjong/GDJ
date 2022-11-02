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
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(document).ready(function(){
	    $( "#targetDt" ).datepicker({
	    	dateFormat : 'yymmdd'	// 실제로 yyyymmdd로 적용
	    });
		fn_ajax1();
	});

	function fn_ajax1() {
		
		$('#btn').click(function(){
			
			$.ajax({
				type : 'get',
				url : '${contextPath}/movie/boxOfficeList',
				data : 'targetDt=' + $('#targetDt').val(),
				dataType : 'json',
				success : function(resData){
						$('#tbl1').text(resData);
					$('#boxOfficeList').empty();
					
					$.each(resData.boxOfficeResult.dailyBoxOfficeList, function(i, movie){
						$('<tr>')
						.append($('<td>').text(movie.rank))
						.append($('<td>').text(movie.movieNm))
						.append($('<td>').text(movie.openDt))
						.append($('<td>').text(movie.audiCnt))
						.append($('<td>').text(movie.audiAcc))
						.appendTo($('#boxOfficeList'));
					});
				}
			});	// ajax
		});	// click
	};	// function
	
</script>
</head>
<body>
	<h1>박스 오피스</h1>
	<div>
		<label for="targetDt">조회날짜</label>
		<input type="text" id="targetDt">
		<input type="button" id="btn" value="조회">
	</div>
	<div id="tbl1">랭크 : </div>
	<div id="tbl">
		<table border="1">
			<thead>
				<tr>순위</tr>
				<tr>영화 명</tr>
				<tr>개봉 일</tr>
				<tr>일일 관객 수</tr>
				<tr>누적 관객 수</tr>
			</thead>
			<tbody id="boxOfficeList">
			</tbody>
		</table>
	</div>
</body>
</html>