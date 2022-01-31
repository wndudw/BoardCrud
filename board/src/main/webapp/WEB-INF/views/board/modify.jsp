<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시물 수정</title>
</head>
<body>

<div id="nav">
 <%@ include file="../include/nav.jsp" %>
</div>

	<!-- <form> : DB에 입력할수 있는 기능  -->
	<form method="post">
		<label>제목</label>
		<input type="text" name="title" value="${view.title}"/><br />
		
		<label>작성자</label>
		<input type="text" name="writer" value="${view.writer}" /><br />
		
		<label>내용</label>
		<textarea rows="5" cols="50" name="contents">${view.contents}</textarea>
		
		<button type="submit">완료</button>
	</form>
</body>
</html>