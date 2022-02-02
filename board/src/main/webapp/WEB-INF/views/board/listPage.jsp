<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록 페이징처리</title>
</head>
<body>

<div id="nav">
 <%@ include file="../include/nav.jsp" %>
</div>

<table>
	<thead>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>	
	</thead>
	
	<tbody>
	
		<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.bno}</td>
				<td>
					<a href="/board/view?bno=${list.bno}">${list.title}</a>
				</td>
				<td>
					<fmt:formatDate value="${list.bdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>${list.writer}</td>
				<td>${list.views}</td>
			</tr>
		</c:forEach>
	</tbody>
	
</table>

<div>
	<!-- test: 속성 EL의 결과가 참이면 실행.  -->
	<c:if test="${prev}">
		<span>[<a href="/board/listPage?num=${startPageNum -1}">이전</a>]</span>
	</c:if>
	
	<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
		<span>
			<c:if test="${select != num}">
				<a href="/board/listPage?num=${num}">${num}</a>
			</c:if>
			
			<c:if test="${select == num}">
				<b>${num}</b>
			</c:if>
		</span>
	</c:forEach>
	
	<c:if test="${next}">
		<span>[<a href="/board/listPage?num=${endPageNum + 1}">다음</a>]</span>
	</c:if>
</div>


<%-- 페이징 번호가 쭈욱 나열되게 구현한것.
<div>
 <!-- 1부터 ${pageNum}의 갯수까지 html에 나타낸다.  -->
 <c:forEach begin="1" end="${pageNum}" var="num">
    <span>
     <a href="/board/listPage?num=${num}">${num}</a>
  </span>
 </c:forEach>
</div>
--%>

</body>
</html>