<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h1 align="center">내 정보 보기</h1>
<br>
<table id="outer" align="center" width="500" cellspacing="5" 
			cellpadding="0" border="1">

			<tr>
				<th width="120">이 름</th>
				<td>${ member.username }</td>
			</tr>
			<tr>
				<th width="120">아이디</th>
				<td>${ member.userid }</td>
			</tr>
			<tr>
				<th width="120">성 별</th>
				<td> 
				<c:if test="${ member.gender eq 'M' }">
					<input type="radio" name="gender" value="M" checked>남자 &nbsp;
					<input type="radio" name="gender" value="F">여자
				</c:if>
				
				<c:if test="${ member.gender eq 'F' }">
					<input type="radio" name="gender" value="M" >남자 &nbsp;
					<input type="radio" name="gender" value="F" checked>여자
				</c:if>
				
				</td>
			</tr>
			<tr>
				<th width="120">나 이</th>
				<td> ${ member.age }</td>
			</tr>
			<tr>
				<th width="120">전화번호</th>
				<td>${ member.phone }</td>
			</tr>
			<tr>
				<th width="120">이메일</th>
				<td>${ member.email }</td>
			</tr>
			<tr>
				<th width="120">취 미</th>
				<td>
				<!-- 취미 문자열을 각각의 문자열로 분리하면서,
					취미 체크박스에 적용할 변수 9개를 만듦
					hobby : "game,sport,movie"
					=> ["game", "sport", "movie"]
				 -->
				 
				 <!-- 만든 9개의 변수값을 checkbox에 적용함 -->
				<c:forTokens items="${ member.hobby }" delims="," var="hb">
					<c:if test="${ hb eq 'game' }">
						<c:set var='chk1' value="checked"/>
					</c:if>
					<c:if test="${ hb eq 'reading' }">
						<c:set var='chk2' value="checked"/>
					</c:if>
					<c:if test="${ hb eq 'climb' }">
						<c:set var='chk3' value="checked"/>
					</c:if>
					<c:if test="${ hb eq 'sport' }">
						<c:set var='chk4' value="checked"/>
					</c:if>
					<c:if test="${ hb eq 'music' }">
						<c:set var='chk5' value="checked"/>
					</c:if>
					<c:if test="${ hb eq 'movie' }">
						<c:set var='chk6' value="checked"/>
					</c:if>
					<c:if test="${ hb eq 'travel' }">
						<c:set var='chk7' value="checked"/>
					</c:if>
					<c:if test="${ hb eq 'cook' }">
						<c:set var='chk8' value="checked"/>
					</c:if>
					<c:if test="${ hb eq 'etc' }">
						<c:set var='chk9' value="checked"/>
					</c:if>
				</c:forTokens>
					<table width="350">
						<tr>
							<td><input type="checkbox" name="hobby" value="game" ${ chk1 }> 게임</td>
							<td><input type="checkbox" name="hobby" value="reading" ${ chk2 }> 독서</td>
							<td><input type="checkbox" name="hobby" value="climb" ${ chk3 }> 등산</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="hobby" value="sport" ${ chk4 }> 운동</td>
							<td><input type="checkbox" name="hobby" value="music" ${ chk5 }> 음악듣기</td>
							<td><input type="checkbox" name="hobby" value="moive" ${ chk6 }> 영화보기</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="hobby" value="travel" ${ chk7 }> 여행</td>
							<td><input type="checkbox" name="hobby" value="cook" ${ chk8 }> 요리</td>
							<td><input type="checkbox" name="hobby" value="etc" ${ chk9 } disabled> 기타</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<th width="120">추가사항</th>
				<td>${ member.etc }</td>
			</tr>
			<tr>
				<th colspan="2">
					<c:url var="moveup" value="/moveup.do">
						<c:param name="userid" value="${ member.userid }"/>
					</c:url>
					<a href=${ moveup }>수정페이지로 이동</a> &nbsp;
					<c:url var="mdel" value="/mdel.do">
						<c:param name="userid" value="${ member.userid }"/>
					</c:url>
					<a href="${ mdel }" >탈퇴하기</a> &nbsp;
					<a href="main.do">시작페이지로 이동</a>
				</th>
				
			</tr>


		</table>

<hr style="clear:both;">
<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>