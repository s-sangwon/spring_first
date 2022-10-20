<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<script type="text/javascript">
function uploadFile(d) {
	var form = $("#fileForm")[0];
	console.log(form);
	//form 태그 안의 모든 입력정보를 담을 FormData 객체 생성
	var formData = new FormData(form);
	console.log(formData);
	
	$.ajax({
		url: 'testFileUp.do',
		processData: false, // multipart 전송은 false 로 지정
		contentType: false, // multipart 전송은 false 로 지정
		data: formData,
		type: "post",
		success: function(data, jqXHR, textStatus){
			alert('파일업로드 성공')
		},
		error: function(jqXHR, textStatus, errorTrown){
			console.log(jqXHR + ", " + textStatus + ", " + errorTrown);
		},
	});
}

function uploadFile2() {
	//js  ajax 파일업로드 처리
	var form = document.getElementById("fileForm2");
	console.log(form);
	var formData = new FormData(form);
	
	var xhrequest;
	if(window.XMLHttpRequest) { // firefox, opera, safari,chrome IE7
		xhrequest = new XMLHttpRequest();
	} else { //IE5, IE6
		xhrequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	//ajax 요청
	//1. 요청 처리에 대한 상태코득 ㅏ변경되면, 작동할 내요을 미리 지정
	xhrequest.onreadystatechange = function(){
		if(xhrequest.readyState == 4 && xhrequest.status == 200) {
			alert(xhrequest.responseText);
		}//요청이 성공하면 alert 창에 응답온 문자를 출력해라. 로 지정
	};
	
	//2. url 요청하고,  전송값 보내기함
	xhrequest.open("POST", 'testFileUp.do');
	xhrequest.send(formData);
}

function fileDown(a) {
	//a 태그(파일명) 클릭하면 다운받을 파일명을 서버로 전송함
	var downfile = $('#fdown').text();
	console.log(downfile);
	
	$.ajax({
		url: "filedown.do",
		type: "get",
		data: {"fname": downfile},
		xhrFields: {
			responseType: "blob"
		}, // response 데이터를 바이너리로 처리해야 함
		success: function(data) {
			console.log("완료.");
			//응답온 파일 데이터를 Blob 객체로 만듦
			var blob = new Blob([data]);
			//클라이언트쪽에 파일 저장 : 다운로드
			if(navigator.msSaveBlob) {
				return navigator.msSaveBlob(blob, downfile);
			}else {
				var link = document.createElement('a');
				link.href = window.URL.createObjectURL(blob);
				link.download = downfile;
				link.click();
			}
		},
		error: function(jqXHR, textStatus, errorTrown){
			console.log(jqXHR + ", " + textStatus + ", " + errorTrown);
		},
	})
}

function fileDown2() {
	var filedownURL = "filedown.do"
	var downfile = document.getElementById('fdown2').innerHTML;

	//if(window.XMLHttpRequest)  { //해당 객체가 있다면 }
	//해당 객체가 없다면으로 조건 처리할 수도 있음
	
	if(!(window.ActiveXObject || 'ActiveXObject' in window)) {
		//chrome, firefox, opera, safari, IE7+
		var link = document.createElement('a');
		link.href = filedownURL + "?fname=" + downfile;
		link.target = '_black'; // 생략해도 됨
		link.download = downfile || filedownURL;
		
		//link.click(); 과 같은 동작 처리 코드임
		var event = document.createEvent("MouseEvents");
	    evevt.initMouseEvent('click', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
	    link.dispatchEvent(event);
	    (window.URL || window.webkitURL).revokeObjectURL(link.href);
		
	} else { //IE5, IE6
		var _window = window.open(filedownURL, downfile);
        _window.document.close();
        _window.document.execCommand('SaveAs', true, fileName || filedownURL)
        _window.close();
        removeiframe();
	}
}
</script>
</head>
<body>
<h1>Ajax 로 파일 업로드 / 다운로드 처리(form 전송)</h1>
<hr>
<h2>jQuery 기반 Ajax 파일업로드</h2>
<form id="fileForm">
	메세지 : <input type="text" name="message"> <br>
	첨부파일 : <input type="file" name="upfile"> <br>
<input onclick="uploadFile();" type="button" value="업로드">
</form>

<!-- ********************************************************* -->
<hr>
<h2>javascript 기반 Ajax 파일업로드</h2>
<form id="fileForm2">
	메세지 : <input type="text" name="message"> <br>
	첨부파일 : <input type="file" name="upfile"> <br>
<input onclick="uploadFile2(this);" type="button" value="업로드">
</form>


<hr>
<h2>jQuery 기반 Ajax 파일다운로드</h2>
<a id="fdown" onclick="fileDown();">sample.txt</a> <br>

<hr>
<h2>javascript 기반 Ajax 파일다운로드</h2>
<a id="fdown2" onclick="fileDown();">sample.txt</a> <br>
</body>
</html>