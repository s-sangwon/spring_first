<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="currentPage" value="${ requestScope.currentPage }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="/WEB-INF/views/common/menubar.jsp"/>
<!-- jstl 에서 절대경로 표기 : /WEB-INF/views/common/menubar.jsp -->
<hr>

<h2 align="center">${ board.board_num }번 게시글의 댓글 등록 페이지</h2>
<form action="breply.do" method="post">
	<input type="hidden" name="board_ref" value="${ board.board_num }">
	<input type="hidden" name="page" value="${ currentPage }">
	
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
	<tr><th>제 목</th><td><input type="text" name="board_title"></td></tr>
	<tr><th>작성자</th><td><input type="text" name="board_writer" value="${ sessionScope.loginMember.userid }" readonly></td></tr>
	<tr><th>내 용</th><td><textarea rows="5" cols="50" name="board_content"></textarea></td></tr>
	<tr><th colspan="2">
		<input type="submit" value="댓글등록하기"> &nbsp;
		<input type="reset" value="작성취소"> &nbsp;
		<button onclick="javascript:history.go(-1);">이전페이지로 이동</button>
	</th></tr>
</table>

</form>

<hr>
<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>