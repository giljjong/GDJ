<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("UTF-8");
    	String name = request.getParameter("name");
    	String enter = request.getParameter("enter");
    	
    	session.setAttribute("name", name);
    	session.setAttribute("enter", enter);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="research3.jsp">
			<h2>2. 좋아하는 운동선수는 누구인가요?</h2>
			<input type="text" name="sport">
			<button>결과보기</button>
		</form>
	</div>
</body>
</html>