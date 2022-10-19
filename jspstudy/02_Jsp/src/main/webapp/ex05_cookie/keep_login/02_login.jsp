<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String chkKeepLogin = request.getParameter("chk_keep_login");
	
	// id="gil", pwd="1234"이면 로그인 성공
	if(id.equals("gil")&& pwd.equals("1234")) {
		
		// 로그인 유지하기를 체크했다면 chkKeepLogin이 null이 아니다.
		// 로그인 유지하기를 체크했다면 로그인 정보를 쿠키에 저장한다.(브라우저를 닫아도 정보를 유지하기 위해서)
		if(chkKeepLogin != null) {
			Cookie cookie1 = new Cookie("id", id);
			Cookie cookie2 = new Cookie("pwd", pwd);
			cookie1.setMaxAge(60 * 60 * 24 * 7);
			cookie2.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie1);
			response.addCookie(cookie2);
		}
		// 로그인 유지하기를 체크하지 않았다면 일반 로그인 처리
		else {
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
		}
		response.sendRedirect("01_form.jsp");
	}
%>