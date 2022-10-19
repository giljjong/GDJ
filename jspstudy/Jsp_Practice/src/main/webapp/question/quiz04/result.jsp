<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <% 
   	request.setCharacterEncoding("UTF-8");
   	String result = request.getParameter("result");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="box">
		${a} ${sign} ${b} = <span id="total"></span>
	</div>
	<script>
		var total = ${a}${sign}${b};
		document.getElementById('total').innerHTML = total;
		
		if(result = total) {
			document.getElementById('box').innerHTML = ${a} + ${sign} + ${b} + '=' + total;
		}
	</script>
</body>
</html>