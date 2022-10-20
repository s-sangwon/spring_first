<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testJSView</title>
<!-- servelt-context.xml 파일에 webapp/resources 가 위치 설정되어 있을경우
	리소스(images, css, js, python, multi 등) 사용시
	하위폴더명/파일명.확장자 로 표시하고 사용해도 됨
 -->
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/ajaxByJavascript.js"></script>
<!-- 또는 절대경로로 표기해서 사용해도 됨 -->
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.1.min.js">

</script>
<script type="text/javascript">
//html 태그에 on이벤트 속성으로 동작 실행함수를 연결할 수도 있지만,
// on이벤트명 = "실행할 함수명();"
// 이벤트 설정을 태그에 직접 작성하지 않고, 자바스크립트 쪽에서 연결할 수 도있읍ㅁ
// 페이지가 로딩이 완료되면 작동되게 함 (window.onload)
function test1() {
	alert("페이지 로딩 완료됨")
}

window.onload = function(){
	//alert("페이지 준비 완료");
	
	//주로 html 태그에 이벤트 연동 설정을 함
	document.getElementById("test1").onclick = function(){
		checkNativeBrowser();
		
	}; //onclick
	
	document.getElementById("test2").onclick = function(){
		console.log("리턴 정보 : "+ Object.prototype.toString.call(createXHRequest()));
	}
	
	
	
	
	
}

</script>


</head>
<!-- body onload=null -->
<body>
<h1>javascript 로 ajax 다루기</h1>

<hr>
<h2>1. 브라우저의 XMLHttpRequest 지원 여부 확인</h2>
<button id="test1">Using Test</button>

<hr>
<h2>2. XMLHttpRequest 객체 생성 확인</h2>
<button id="test2">화긴</button>


</body>
</html>