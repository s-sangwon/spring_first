<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
table th {
	background-color: #99ffff;
}

table#outer {
	border: 2px solid navy;
}
</style>
<script type="text/javascript">

	function validate() {
		//암호확인의 포커스가 사라질 때 암호화 암호확인값 일치하는지 검사

		//암호와 암호확인이 일치하는지 확인

		var pwd1 = document.getElementById("upwd1").value;
		var pwd2 = document.getElementById("upwd2").value;
		if(pwd1 !== pwd2){
			alert("암호와 암호 확인의 값이 일치하지 않습니다.\n다시 입력하세요.");
			document.getElementById("upwd1").select();
		}

	}
</script>
</head>
<body>

	<h1 align="center">회원 정보 수정 페이지</h1>
	<br>
	<form action="mupdate.do" method="post" >
		<input type="hidden" name="origin_userpwd" id="" value="${ member.userpwd }">
		<table id="outer" align="center" width="500" cellspacing="5" cellpadding="0">
			<tr>
				<th width="120">이 름</th>
				<td><input type="text" name="username" value="${ member.username }" readonly="readonly" ></td>
			</tr>
			<tr>
				<th width="120">아이디</th>
				<td><input type="text" name="userid" id="userid" value="${ member.userid }" readonly> &nbsp;

			</tr>
			<tr>
				<th width="120">암 호</th>
				<td><input type="password" name="userpwd" id="upwd1" value="" placeholder="영문자, 숫자, 특수문자 조합하여 사용권장"></td>
			</tr>
			<tr>
				<th width="120">암호확인</th>
				<td><input type="password" id="upwd2" onblur="validate();" ></td>
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
				<td><input type="number" name="age" value="${ member.age }" min="19"></td>
			</tr>
			<tr>
				<th width="120">전화번호</th>
				<td><input type="tel" name="phone" value="${ member.phone }" placeholder="-빼고 입력"></td>
			</tr>
			<tr>
				<th width="120">이메일</th>
				<td><input type="email" name="email" value="${ member.email }"  placeholder="first@abc.com"></td>
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
				<td><textarea rows="5" cols="50" name="etc">${ member.etc }</textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="수정하기" onclick="validate();"> &nbsp;
					<input type="reset" value="수정취소"> &nbsp;
					<a href="javascript:history.go(-1)">이전페이지로 이동</a> &nbsp;
					<a href="main.do">시작페이지로 이동</a>
				</th>
				
			</tr>


		</table>
	</form>


	<hr style="clear: both;">
	<c:import url="/WEB-INF/views/common/footer.jsp" />
</body>
</html>