<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();

	Cookie cookie1 = new Cookie("id", "");
	Cookie cookie2 = new Cookie("pwd", "");
	cookie1.setMaxAge(0);
	cookie2.setMaxAge(0);
	response.addCookie(cookie1);
	response.addCookie(cookie2);

	response.sendRedirect("01_form.jsp");
%>