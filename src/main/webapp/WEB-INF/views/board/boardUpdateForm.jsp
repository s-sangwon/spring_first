<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="currentPage" value="${ page }"/>

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

<h2 align="center">${ notice.noticeno }번 공지글 수정 페이지</h2>
<!-- form 태그에서 입력된 값들(문자열)과 첨부파일을 같이 전송하려면,
	반드시 enctype 속성을 form 태그에 추가해야 됨
	enctype="multipart/form-data" 값을 지정해야함
-->

<c:if test="${ board.board_lev eq 1 }">
<form action="boriginup.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="board_num" value="${ board.board_num }">
	<input type="hidden" name="page" value="${ currentPage }">
	<c:if test="${ !empty board.board_original_filename }">
		<!-- 첨부파일이 있는 공지글이라면 -->
		<input type="hidden" name="board_original_filename" value="${ board.board_original_filename }">
		<input type="hidden" name="board_rename_filename" value="${ board.board_rename_filename }">
	</c:if>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
	<tr><th>제 목</th><td><input type="text" name="board_title" value="${ board.board_title }"></td></tr>
	<tr><th>작성자</th><td><input type="text" name="board_writer" value="${ sessionScope.loginMember.userid }" readonly></td></tr>
	<tr><th>첨부파일</th>
		<td>
			<!-- 원래 첨부 파일이 있는경우 -->
			<c:if test="${ !empty board.board_original_filename }">
				${ board.board_original_filename } &nbsp;
				<input type="checkbox" name="delFlage" value="yes">
			</c:if>
			<br>
			새로 첨부 : <input type="file" name="upfile">
		</td>
	</tr>
	<tr><th>내 용</th><td><textarea rows="5" cols="50" name="board_content">${ board.board_content }</textarea></td></tr>
	<tr><th colspan="2">
		<input type="submit" value="수정하기"> &nbsp;
		<input type="reset" value="수정취소"> &nbsp;
		<button onclick="javascript:history.go(-1); return false;">이전페이지로 이동</button>
	</th></tr>
</table>
</form>
</c:if>

<c:if test="${ board.board_lev ne 1 }">
<form action="boriginup.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="board_num" value="${ board.board_num }">
	<input type="hidden" name="page" value="${ currentPage }">
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
	<tr><th>제 목</th><td><input type="text" name="board_title" value="${ board.board_title }"></td></tr>
	<tr><th>작성자</th><td><input type="text" name="board_writer" value="${ board.board_writer }" readonly></td></tr>
	
	<tr><th>내 용</th><td><textarea rows="5" cols="50" name="board_content">${ board.board_content }</textarea></td></tr>
	<tr><th colspan="2">
		<input type="submit" value="수정하기"> &nbsp;
		<input type="reset" value="수정취소"> &nbsp;
		<button onclick="javascript:history.go(-1); return false;">이전페이지로 이동</button>
	</th></tr>
</table>
</form>
</c:if>

<hr>
<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>

@RequestParam("bill_timestamp2") String ts

ts = ts.replace("T", " ");
		Timestamp t = Timestamp.valueOf(ts);
		logger.info(t.toString());
		bill.setBill_timestamp(t);