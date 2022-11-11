<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/employee.css">
<style>
	.allForm {
		margin : 36px auto 0;
		width : 1286px;
		height : 700px;
	}
	
	.queryForm {
		height : 50px;
		text-align : right;
	}
	
	table {
		width : 100%;
		height : 100%;
		border-top : 2px solid #dee2e6;
		border-bottom : 2px solid #777;
		border-collapse: collapse;
	}
	
	thead {
		border : 2px solid #c9c9c959;
		font-size : 20px;
		font-weight : 700;
		height : 44px;
		background-color : #80ceb9;
		color : #534b4b;
	}
	
	td {
    	border-bottom: 1px solid #dee2e6;
 	 }

	thead, .center{
		text-align : center;
	}
	
	.paginate {
		text-align:center;
		margin-top : 32px;
		font-size: 16px;
    	font-family: Arial,'나눔고딕',NanumGothic,-apple-system,BlinkMacSystemFont,'Apple SD Gothic Neo','맑은고딕',MalgunGothic,'돋움',Dotum,Sans-serif;
    	color: #434343;
    	min-height : 28px;
	}
	.pageWrap {
		display : inline-block;
		padding : 8px 0 6px;
		vertical-align: top;
		height : 27px;
	}
	
	.arrow {
		padding-top : 3px;
	}
	
	.pageNum, .paginate strong, .arrow {
		float : left;
		position : relative;
		width : 30px;
		height : 100%;
		font-family: tahoma,helvetica,sans-serif;
		line-height : normal;
	}
	
	.pageNum, .paginate strong {
		padding-top : 5px;
	}
	
	.nonCenter {
		padding-left : 6px;
	}
	.paginate strong {
		border : 1px solid #e0e0e0;
		color : #ff9600;
	}
	
	.pageNum:hover{
		border : 1px solid #e0e0e0;
		color : #ff9600;
	}
	
	#frm_search {
		padding-left : 11px;
		margin : 7px 0;
	}
	
</style>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function() {

		$('#area2').css('display', 'none');
		
		$('#column').change(function(){
			let combo = $(this);
			if(combo.val() == 'HIRE_DATE' || combo.val() == 'SALARY'){
				$('#area1').hide();
				$('#area2').show();
			} else {
				$('#area1').show();
				$('#area2').hide();
			}
		});
		
		$('#btn_all').click(function(){
			location.href='${contextPath}/emp/list';
		});
		
		$('#frm_search').submit(function(){
			if($('#query').val() == '' && $('#start').val() == '' && $('#stop').val() == ''){
				event.preventDefault();
				location.href='${contextPath}/emp/list';
				return;
			}
		});

	});
</script>
</head>
<header>
	<div class="head_bar">
		<div id="logo_div">
			<a href="../index.jsp"><img id="logo" src="https://www.gdu.co.kr/images/main/logo.png"></a>
		</div>
		<ul class="gnb_barAll">
			<li><a href="#"  class="gnb_bar">제품관리</a></li>
			<li><span class="snb_bar"></span><a href="#" class="gnb_bar">조직도</a></li>
			<li><span class="snb_bar"></span><a href="#" class="gnb_bar">결재함</a></li>
			<li><span class="snb_bar"></span><a href="#"  class="gnb_bar">건의사항</a></li>
			<li><span class="snb_bar"></span><a href="#"  class="gnb_bar">Q&A</a></li>
		</ul>
	</div>
</header>
<body>
	<div class="allForm">
		<div class="queryForm">
			<form id="frm_search" action="${contextPath}/emp/search">
				<select name="column" id="column">
					<option value="">:::선택:::</option>
					<option value="EMPLOYEE_ID">사원번호</option>
					<option value="DEPARTMENT_ID">부서번호</option>
					<option value="LAST_NAME">성</option>
					<option value="FIRST_NAME">이름</option>
					<option value="PHONE_NUMBER">전화번호</option>
					<option value="HIRE_DATE">입사일자</option>
					<option value="SALARY">연봉</option>
				</select>
				<span id="area1">
					<input type="text" name="query" id="query" class="searchBox">
				</span>
			 	<span id="area2">
			 		<input type="text" id="start" name="start" class="searchBox">
			 		<span class="span_search">~</span>
			 		<input type="text" id="stop" name="stop" class="searchBox">
				</span>
				<span>
					<input type="submit" value="검색" id="btn_primary" class="btn_primary">
					<input type="button" value="전체사원조회" id="btn_all" class="btn_primary">
				</span>
			</form>
		</div>
		
		<div>
		<select name="target" id="target">
			<option value="FIRST_NAME">이름</option>
			<option value="LAST_NAME">성</option>
			<option value="EMAIL">이메일</option>
		</select>
		<input type="text" id="param" name="param" list="auto_complete">
		<datalist id="auto_complete"></datalist>
		<script>
			$('#param').keyup(function(){
				$('#auto_complete').empty();
				if($(this).val() == ''){
					return;
				}
				$.ajax({
					/* 요청 */
					type: 'get',
					url: '${contextPath}/emp/autoComplete',
					data: 'target=' + $('#target').val() + '&param=' + $(this).val(),
					/* 응답 */
					dataType: 'json',
					success: function(resData){
						if(resData.status == 200){
							$.each(resData.list, function(i, emp){
								$('#auto_complete')
								.append($('<option>').val(emp[resData.target]));
							});
						}
					}
				});
			});
		</script>
	</div>
		
		<table>
			<thead class="head_font">
				<tr>
					<td>순번</td>
					<td>사원번호</td>
					<td>사원명</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td>입사일자</td>
					<td>연봉</td>
					<td>커미션</td>
					<td>부서번호</td>
					<td>부서명</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="emp" varStatus="vs">
					<tr>
						<td class="center">${totalRecord - (beginNo - vs.index) + 1}</td>
						<td class="center">${emp.employeeId}</td>
						<td class="center">${emp.firstName} ${emp.lastName}</td>
						<td class="center">${emp.email}</td>
						<td class="center">${emp.phoneNumber}</td>
						<td class="center">${emp.hireDate}</td>
						<td class="center">${emp.salary}</td>
						<td class="center">${emp.commissionPct}</td>
						<td class="center">${emp.deptDTO.departmentId}</td>
						<td class="center">${emp.deptDTO.departmentName}</td>
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