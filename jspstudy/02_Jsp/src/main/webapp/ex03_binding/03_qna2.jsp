<%@page import="java.io.FileWriter"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8"); 
	String createdDate = request.getParameter("created_date");
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	// 작성자 IP
	String ip = request.getRemoteAddr();
	
	// 파일 명
	// 작성자IP_작성자.txt
	String filename = ip.replaceAll(":", "_") + "_" + writer + ".txt";
	
	// 파일 경로
	String realPath = application.getRealPath("storage" + File.separator + createdDate);
	File dir = new File(realPath);
	File file = new File(dir, filename);
	if(content.length() > 1) {
	if(dir.exists() == false) {
		dir.mkdirs();
	};
	
	// 문자 출력 스트림 생성
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	bw.write("작성 일자 : " + createdDate + "\n");
	bw.write("작성자 : " + writer + "\n");
	bw.write("제목 : " + title + "\n");
	bw.write("내용\n" + content + "\n");
	bw.close();
	}
%>

	<script>
		var isCreated = <%= file.exists() %>;
		if(isCreated) {
			alert('<%=file.getName()%> 파일이 생성되었습니다.');
		} else {
			alert('파일이 생성되지 않았습니다.');
		}
		history.back();
	</script>