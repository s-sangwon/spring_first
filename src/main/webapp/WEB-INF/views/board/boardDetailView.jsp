<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="currentPage" value="${ requestScope.currentPage }"></c:set>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<c:import url="/WEB-INF/views/common/menubar.jsp"/>
<!-- jstl 에서 절대경로 표기 : /WEB-INF/views/common/menubar.jsp -->
<hr>
<h2 align="center">${ requestScope.board.board_num } 번 공지글 상세보기 </h2>
<br>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
	<tr><th>제 목</th><td>${ board.board_title }</td></tr>
	<tr><th>작성자</th><td>${ board.board_writer }</td></tr>
	<tr><th>날 짜</th>
		<td><fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${ board.board_date }"/></td></tr>
	<tr><th>첨부파일</th>
		<td>
			<!-- 첨부파일이 있다면, 파일명 클릭시 다운로드 실행되게 함 -->
			<c:if test="${ !empty board.board_original_filename }">
				<c:url var="bfd" value="/bfdown.do">
					<c:param name="ofile" value="${ board.board_original_filename }"/>
					<c:param name="rfile" value="${ board.board_rename_filename }"/>
				</c:url>
				<a href=${ bfd }>${ board.board_original_filename }</a>
			</c:if>
			<!-- 없다면 공백으로 처리 -->
			<c:if test="${ !empty board.board_original_filename }">
				&nbsp;
			</c:if>
		</td>
	</tr>
	<tr><th>내 용</th><td>${ board.board_content }</td></tr>
	<tr><th colspan="2">
		<button onclick="javascript:location.href='blist.do?page=${ currentPage }';">목록</button>
		&nbsp;
		<!-- 글 작성자가 아닌 로그인 회원인 경우 댓글달기 기능 제공-->
		<c:if test="${ requestScope.board.board_writer ne sessionScope.loginMember.userid and !empty sessionScope.loginMember }">
			<c:url var="brf" value="/breplyform.do">
				<c:param name="board_num" value="${ board.board_num }"/>
				<c:param name="page" value="${ currentPage }"/>
			</c:url>
			<a href="${ brf }">[댓글달기]</a>
		</c:if>
		<!-- 본인이 등록한 게시글일 때는 수정과 삭제 기능 제공 -->
		<c:if test="${ requestScope.board.board_writer eq sessionScope.loginMember.userid and !empty sessionScope.loginMember }">
			<c:url var="bup" value="/bupview.do">
				<c:param name="board_num" value="${ board.board_num }"/>
				<c:param name="page" value="${ currentPage }"/>
			</c:url>
			<a href="${ bup }">[수정페이지로 이동]</a> &nbsp;
		<c:url var="bdl" value="/bdel.do">
				<c:param name="board_num" value="${ board.board_num }"/>
				<c:param name="board_lev" value="${ currentPage }"/>
				<c:param name="board_rename_filename" value="${board.board_rename_filename }"></c:param>
		</c:url>
		<a href="${ bdl }">[글삭제]</a> &nbsp;
		</c:if>
	</th></tr>
</table>

<hr>
<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>