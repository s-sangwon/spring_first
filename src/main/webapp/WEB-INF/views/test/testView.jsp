<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<!-- a 태그로 서버측에 request 요청을 하는 방법 2가지 
	방법 1 : href="${ pageContext.servletContext.contextPath }/요청url"
	방법 2 : href="javascript:location.href='요청url';"
	추가적으로 요청 url과 함께 서버측으로 전송할 값(parameter)이 있다면
	쿼리스트링을 추가하면 됨 : ?
	요청url?전송이름=전송할값&?전송이름=전송할값&
-->
<h2><a href="${ pageContext.servletContext.contextPath }/testJSON.do">테스트 JSON</a></h2>
<h2><a href="${ pageContext.servletContext.contextPath }/testJS.do">javascript ajax</a></h2>
<h2><a href="${ pageContext.servletContext.contextPath }/testJQuery.do">jquery ajax</a></h2>
<h2><a href="${ pageContext.servletContext.contextPath }/testAjaxFile.do">ajax fileup/download</a></h2>

</body>
</html>