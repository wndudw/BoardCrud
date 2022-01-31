<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시물 조회</title>
</head>
<body>

<div id="nav">
 <%@ include file="../include/nav.jsp" %>
</div>
	
		<label>제목</label>
		${view.title} <br />
		
		<label>작성자</label>
		${view.writer}<br />
		
		<label>내용</label><br />
		${view.contents}<br />
		
		<div>
			<a href="/board/modify?bno=${view.bno}">게시글 수정</a>
		</div>
</body>
</html>