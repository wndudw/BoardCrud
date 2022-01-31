<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시물 조회</title>
</head>
<body>
	<!-- <form> : DB에 입력할수 있는 기능  -->
	<form action="" method="post">
		<label>제목</label>
		${view.title} <br />
		
		<label>작성자</label>
		${view.writer}<br />
		
		<label>내용</label><br />
		${view.contents}<br />
		
		<!-- <button type="submit">작성</button>  -->
	</form>
</body>
</html>