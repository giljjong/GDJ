<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<%-- 너비/높이 입력 폼 : 삼각형 버튼, 사각형 버튼 --%>
	
	<div>
		 <form id="frm1">
		 	<div>
		 		넓이<input type="text" name="width"> X
		 		높이<input type="text" name="height">
		 		<br>
				<button id="tri_btn">삼각형 계산</button>
				<button id="rec_btn">사각형 계산</button>
		 	</div>
		 </form>
	</div>
	
	 <%-- 반지름 입력 폼 : 원 버튼 --%>
	
	<div>
		 <form action="${contextPath }/circle.do">
		 	<div>
		 		반지름<input type="text" name="radius">
				<button>계산</button>
		 	</div>
		 </form>
	</div>
	
	<script>
		document.getElementById('tri_btn').onclick = function() {
			document.getElementById('frm1').setAttribute("action", "${contextPath }/triangle.do")
		}
		document.getElementById('rec_btn').onclick = function() {
			document.getElementById('frm1').setAttribute("action", "${contextPath }/rectangle.do")
		}
	</script>
	
</body>
</html>