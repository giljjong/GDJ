<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#title').text('예쁜 꽃 구경');
		$('#image').attr('src', 'resources/images/flower3.jpg').css('width', '400px');
	});
	
</script>
</head>
<body>
	<h1 id="title"></h1>
	<img id="image">
</body>
</html>