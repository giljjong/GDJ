<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	int age = request.getParameter("age").isEmpty() ? 0 : Integer.parseInt(request.getParameter("age"));
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<div>
		<span id="answer"></span>
		<br>
		<a href="quiz02.jsp">처음으로 이동</a>
	</div>

	<script>
	
		var answer = $('#answer');
		
		if(<%= age %> >= 20 ) {
			answer.text('당신의 나이는 ' + <%= age %> + '이므로 주류구매가 가능합니다.');
		} else if(20 > <%= age %> > 0) {
			answer.text('당신의 나이는 ' + <%= age %> + '이므로 주류구매가 불가능합니다.');
		}
		
	</script>
</body>
</html>