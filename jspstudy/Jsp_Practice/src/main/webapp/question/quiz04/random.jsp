<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	int a = (int)(Math.random() * 10) + 1;
    	int b = (int)(Math.random() * 10) + 1;
    	int c = (int)(Math.random() * 5) + 1;
    	String sign = "";
    	switch(c) {
    	case 1 : sign = "+"; break;
    	case 2 : sign = "-"; break;
    	case 3 : sign = "*"; break;
    	case 4 : sign = "/"; break;
    	case 5 : sign = "%"; break;
    	}
    	
    	session.setAttribute("a", a);
    	session.setAttribute("b", b);
    	session.setAttribute("sign", sign);
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>랜덤 계산기</h1>
	<form action="result.jsp">
		<%=a %> &nbsp; <%=sign %> &nbsp; <%=b %> = <input type="text" name="result"> <button>제출</button>
	</form>



</body>
</html>