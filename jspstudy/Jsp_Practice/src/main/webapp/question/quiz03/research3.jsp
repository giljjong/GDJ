<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("UTF-8");
    	String sport = request.getParameter("sport");
    
    	response.setContentType("text/html; charset=UTF-8");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>${name}님의 선호도 조사 결과</h1>
	<ul>
		<li>좋아하는 연예인 : ${enter}</li>
		<li>좋아하는 운동선수 : <%=sport %></li>
	</ul>
	
	<a href="research1.jsp"><button>처음부터 다시 하기</button></a>
</body>
</html>