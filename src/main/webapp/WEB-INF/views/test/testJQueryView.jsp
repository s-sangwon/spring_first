<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testJQueryiew</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	

</script>


</head>
<body>
<h1>jQuery 로 ajax 테스트</h1>
<hr>
	<h2>1. 서버로 전송값 없고, 결과로 문자열 받아 출력 : get 방식</h2>
	<p id="p1" style="width:300px; height:50px; border: 1px solid red;"></p>
	<button id="test1">테스트</button>
	<script type="text/javascript">
	$(function() {
		$("#test1").click(function() {
			//컨트롤러로 서비스를 요청하고, 결과로 문자열을 받는 경우에는
			//jQuery.get() 사용할 수 있음
			//$.get( 요청url, {전송이름: 전송값})
			//  function(data) {요청 성공시 리턴값 받아 처리할 코드작성}
			
			
			$.get("test1.do", function(data) {
				$("#p1").text(data);
				
			});
			
			//ajax() 요청
			$.ajax({
				url: "test1.do",
				type: "get",
				success: function(data) {
					$('#p1').text( $('#p1').text() + ", " + data)
				},
			});
			
		})
	});
	</script>
	
	<h2>2. 서버로 전송값 있고, 결과로 문자열 받아 출력</h2>
	이름 : <input type="text" id="name"> <br>
	나이 : <input type="number" id="age"> <br>
	<p id="p2" style="width:300px; height:50px; border: 1px solid red;"></p>
	<button id="test2">테스트</button>
	<script type="text/javascript">
		$(function () {
			//서비스 요청시 post 전송일 떄는
			//$.post() 또는 $.ajax() 사용할 수 있음
			//메소드 안 인자의 사용형식은 같음
			
			$('#test2').on("click", function(){ 
				// $.post({settings})
				$.ajax({
					url: "test2.do",
					data: {
						name: $("#name").val(),
						age: $("#age").val()	
					},
					type: "post",
					success: function(result){
						if(result == "ok")
							$("#p2").html("<h5>"+ result + "</h5>");
						else{
							alert("서버측 실패 답변 : " + result)
						}
					},
					error: function(request, status, errorData) {
						console.log("error code : ", request.status + "\nMessage : " + request.responseText
								+ "\nError : " + errorData);
					}
				});
				
			}); //on
			
	
		}); // document.ready
	</script>
	
	<h2>3. 서버로 전송값 없고, 결과로 json 받아 출력</h2>
	<p id="p3" style="width:300px; height:150px; border: 1px solid red;"></p>
	<button id="test3">테스트</button>
	<script type="text/javascript">
		
		$(function (){
			$("#test3").on("click", ()=>{
				$.ajax({
					url: "test3.do",
					type: "post", 
					dataType: "json",
					success: (jsonData) => {
						console.log(jsonData);
						$('#p3').html("<b>최신 공지글</b><br>"
								+"번호 : "+jsonData.noticeno
								//+"<br>제목 : "+  decodeURIComponent(jsonData.noticetitle).replace(/\+/gi, ' ')
								+"<br>제목2 : "+  jsonData.noticetitle2
								+"<br>작성자 : "+jsonData.noticewriter
								+"<br>날짜 : "+jsonData.noticedate
								//+"<br>내용 : "+decodeURIComponent(jsonData.noticecontent).replace(/\+/gi, ' ')
								+"<br>내용2 : "+jsonData.noticecontent2)
					},
					error: function(request, status, errorData) {
						console.log("error code : ", request.status + "\nMessage : " + request.responseText
								+ "\nError : " + errorData);
					}
				});
				
			});
		});
	</script>
	
	<h2>4. 서버로 전송값 있고, 결과로 json 배열 받아 출력</h2>
	<label>검색 키워드 입력 : <input type="search" id="keyword"></label><br>
	<div id="p4" style="width:500px; height:750px; border: 1px solid red;">
		<table id="tblist" border="1" cellspacing="0">
			<tr bgcolor="gray">
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
			</tr>
		</table>
	</div>
	<button id="test4">테스트</button>
	<script type="text/javascript">
		$(function() {
			$('#test4').on('click', function(){
				$.ajax({
					url: "test4.do",
					type: "post",
					data: {
						keyword: $("#keyword").val()
					},
					dataType: "json",
					success: function(dataObj) {
						//json 배열을 담은 객체를 리턴받은 경우
						//object => string => parsing : json  // 버전업되고 내부에서 해줌 안해도됨.
						console.log(dataObj); // [object, Object]
						
						//받은 객체를 문자열로 바꾸기
						//var objStr = JSON.stringify(dataObj); // 안해도됨.
						// 객체문자열을 다시 json 객체로 파싱함
						//var jsonObj = JSON.parse(objStr);
						
						// 출력용 대상 태그객체 준비 : 테이블에 행을 추가함
						var output = $("#p4 > #tblist").html();
						console.log(output);
						dataObj.list.forEach( function(item) {
							output +=  "<tr><td>" + item.noticeno + "</td>"
										+ "<td>" + item.noticetitle + "</td>"
										+ "<td>" + item.noticewriter + "</td>"
										+ "<td>" + item.noticedate + "</td></tr>";
						}) 
						$("#p4 > #tblist").html(output);
						/* for(var i in dataObj.list) {
							console.log(i);
						} */

						
					},
					error: function(request, status, errorData) {
						console.log("error code : ", request.status + "\nMessage : " + request.responseText
								+ "\nError : " + errorData);
					}
				}) //ajax
				
			}); // on click
		}); //document.ready
	
	</script>
	
	<h2>5. 서버로 전송값 있고, 결과로 문자열 받아 출력 : get 방식</h2>
	<div>
		<fieldset>
			<legend>공지글 등록하기</legend>
			제목 : <input type="text" id="title"> <br>
			작성자 : <input type="text" id="writer" value="admin"> <br>
			내용 : <textarea rows="5" cols="30" id="content"></textarea><br>
		</fieldset>
	</div>
	<p id="p5" style="width:300px; height:50px; border: 1px solid red;"></p>
	<button id="test5">테스트</button>
	<script type="text/javascript">
		$(function() {
			$("#test5").on("click", function() {
				//자바스크립트에서 sjon 객체 만들기
				// var job = {name: ...};
				
				var job = new Object();
				job.title = $('#title').val();
				job.writer = document.getElementById('writer').value;
				job.content = $('#content').val();
				console.log(job);
				$.ajax({
					url: "test5.do",
					type: "post",
					data: JSON.stringify(job),
					contentType: "application/json; charset=utf-8",
					success: function(result) {
						alert("요청 성공 : " + result);
					},
					error: function(request, status, errorData) {
						console.log("error code : ", request.status + "\nMessage : " + request.responseText
								+ "\nError : " + errorData);
					}
				});
				
			});
			
		});
	</script>
	
	<h2>6. 서버로 json 배열 보내기</h2>
	<div>
		<fieldset>
			<legend>공지글 여러 개 한번에 등록하기</legend>
			제목 : <input type="text" id="ntitle"> <br>
			작성자 : <input type="text" id="nwriter" value="admin"> <br>
			내용 : <textarea rows="5" cols="30" id="ncontent"></textarea><br>
		</fieldset>
		<input type="button" id="addBtn" value="추가하기">
	</div>
	<p id="p6" style="width:400px; height:150px; border: 1px solid red;"></p>
	<button id="test6">테스트</button>
	
	<script type="text/javascript">
		$(function() {
			var jar = [];
			var pStr = $('#p6').html();
			// var jar = new Array(); 위에랑 똑같음 stack 구조가 됨, push() 넣기 pop() 꺼내기
			
			// var jar = new Array(5); index 이용가능 jar[0] = { name: 홍길동 ...}
			$("#addBtn").on("click", function() {
				var n = { 
						noticetitle: $("#ntitle").val(), 
						noticewriter: $("#writer").val(),
						noticecontent: $("#ncontent").val()
				};
				jar.push(n); // 하나씩
				pStr += JSON.stringify(n);
				$('#p6').html(pStr + "<br>");								
				$("#ntitle").val("");
				$("#ncontent").val(""); // 저장후 내용 지우기
			});
			
			$("#test6").on("click", function() {
				console.log(jar);
				console.log("start");
				
				$.ajax({
					url: "test6.do",
					type: "post",
					data:  JSON.stringify(jar),
					contentType: "application/json; charset=utf-8",
					success: function(data) {
						alert("성공!");
						console.log(data);
					},
					error: function(request, status, errorData) {
						console.log("error code : ", request.status + "\nMessage : " + request.responseText
								+ "\nError : " + errorData);
					}
				});
			});
		});
	</script>
	
	<h2>7. 서버로 전송값 없고, 결과로 문자열 받아 출력 : get 방식</h2>
	<p id="p7" style="width:300px; height:50px; border: 1px solid red;"></p>
	<button id="test7">테스트</button>
	<script type="text/javascript">

	</script>
</body>
</html>