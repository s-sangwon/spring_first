/**
   ajaxByJavascript.js
 * 자바스크립트를 사용한 ajax 기능 제공
 */

 //브라우저에서 XMLHttpRequest 지원 여부 확인하는 함수
function checkNativeBrowser() {
	if(window.XMLHttpRequest ) {
		//firefox, opera, safari, chrome, IE7 이상 등
		alert("XMLHttpRequest 제공함");
	} else {
		alert("XMLHttpRequest 제공 안함, ActiveXObject 지원함");
	}

}

function createXHRequest() {
	var xhRequest;
	if(window.XMLHttpRequest) {
		xhRequest = new XMLHttpRequest();
	} else {
		xhRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	return xhRequest;
}