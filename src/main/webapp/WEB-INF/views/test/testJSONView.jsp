<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testJSONView</title>
</head>
<body>
<h1>JSON 객체 다루기</h1>
<hr>
<h2>1. JSON 기본 형태 다루기</h2>
<p id="p1" style="width: 600px; border: 1px solid red"/>
<button type="button" onclick="jsonTest1();">출력하기</button>
<!-- button 태그에 type 속성에 사용하는 값: utton, submit, rest 000
	태그에 사용하는 이벤트 속성은 자바스크립특 제공합
	해당 이벤트가 태그에 발생했을 ㄸ ㅐ기능(함수) 작동 바업은
	첫번쨰 : on이벤트명="함수명()"
			실시킬킬 함수쪽으로 전달할 값이 있는경우
	두번째 : on이벤트명="javascript:객체명,메소드명();
	* 클릭 이벤트는 같은 태그 영역 안에 있는 다른 버튼에게 저낟ㄹ됨
	같이 클릭 동작이 발생 => 클릭 이벤트가 전달되지 않게 하려면
	on이벤트명="함수명"; return false;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
 -->
<script type="text/javascript">
//JSON : JavaScript Object Notation (js 객체 표기법)
//{} 로 묶음, "이름": 값을 한쌍으로 표기함
//"이름" 은 따옴표 묶어서 문자형으로 표기함,
//값은 자료형에 따라서 표기함 : "string", number, boolean, [array]
//여러 개의 항목일 때는 , 로 구분함
//var, let, const 키워드로 선언할 수 있음
//var 객체명 = {"이름": 값,"이름": [값들]};

var member = {
		"userid": "test007",
		"username": "홍길동",
		"age": 30,
		"phone": "010-1234-5678",	
}

function jsonTest1() {
	console.log('member : ' + member); //[object Object] 확인
	
	//JSON 객체를 문자열로 반환해서 출력하ㅣㄱ : stringfy() 함수 사용
	document.getElementById("p1").innerHTML = "<B>JSON 객체를 문자열로 변환해서 출력하기</B><BR>";

	document.getElementById("p1").innerHTML += JSON.stringify(member); 
	
	//JSON 객체가 가진 값들을 하나씩 꺼내서 출력 추가하기
	
	document.getElementById("p1").innerHTML +=
		"<b><br><br><br>JSON 객체가 가진 값들을 하나씩 꺼내서 출력하기</b>";
	document.getElementById("p1").innerHTML += "아이디 : " + member.userid + "<br>";
	document.getElementById("p1").innerHTML += "이 름 : " + member.username + "<br>";
	document.getElementById("p1").innerHTML += "나 이  : " + member.age + "<br>";
	document.getElementById("p1").innerHTML += "연락처 : " + member.phone + "<br>";

	//
	
	
	

}

</script>

<hr>
<h2>2. 배열 값을 가진 다루기</h2>
<p id="p2" style="width: 600px; border: 1px solid red"></p>
<button type="button" onclick="jsonTest2();">출력하기</button>

<script type="text/javascript">

var employee = {
		"empid": "test007",
		"empname": "홍길동",
		"age": 30,
		"phone": "010-1234-5678",
		"email": "baku@sin.o",	
		"deptid": "돌진부",	
		"hobby": ["영화감상", "독서", "결투"],	
		
}

function jsonTest2() {
	console.log('member : ' + employee); //[object Object] 확인
	
	//JSON 객체를 문자열로 반환해서 출력하ㅣㄱ : stringfy() 함수 사용
	document.getElementById("p2").innerHTML = "<B>JSON 객체를 문자열로 변환해서 출력하기</B><BR>";

	document.getElementById("p2").innerHTML += JSON.stringify(employee); 
	
	//JSON 객체가 가진 값들을 하나씩 꺼내서 출력 추가하기
	
	document.getElementById("p2").innerHTML +=
		"<b><br><br><br>JSON 객체가 가진 값들을 하나씩 꺼내서 출력하기</b>";
	document.getElementById("p2").innerHTML += "사 번 : " + employee.empid + "<br>";
	document.getElementById("p2").innerHTML += "직원명 : " + employee.empname + "<br>";
	document.getElementById("p2").innerHTML += "급 여  : " + employee.age + "<br>";
	document.getElementById("p2").innerHTML += "전화번호 : " + employee.phone + "<br>";
	document.getElementById("p2").innerHTML += "이메일 : " + employee.email + "<br>";
	document.getElementById("p2").innerHTML += "소속부서 : " + employee.deptid + "<br>";
	document.getElementById("p2").innerHTML += "취미활동 : " + employee.hobby + "<br>";
	
	document.getElementById("p2").innerHTML +=
		"<br><br><br>객체가 가진 배열값들을 하나씩 꺼내서 출력하기</b><br>";
		
	for(i in employee.hobby) {
		document.getElementById("p2").innerHTML += "[" + i + "]:" + employee.hobby[i];
	}
}

</script>

<hr>
<h2>3. JSON 객체 안에 JSON 객체 다루기 </h2>
<p id="p3" style="width: 600px; border: 1px solid red"></p>
<button type="button" onclick="jsonTest3();">출력하기</button>

<script type="text/javascript">

var emplist = {
		"D1" : {
			"empid": "22031521",
			"empname": "홍길동",
			"salary": 380,
			"hobby": ["영화감상", "독서", "여행" ],	
		},
		"D2" : {
			"empid": "22031522",
			"empname": "이쑤신",
			"salary": 4000,
			"hobby": ["요리", "독서", "旅行" ],	
		}
}

function jsonTest3() {
	console.log('emplist : ' + emplist); //[object Object] 확인
	console.log('emplist.D1' + emplist.D1)
	console.log('emplist.D2' + emplist.D2)
	JSON.str
	//JSON 객체를 문자열로 반환해서 출력하ㅣㄱ : stringfy() 함수 사용
	document.getElementById("p3").innerHTML = "<B>JSON 객체를 문자열로 변환해서 출력하기</B><BR>";

	document.getElementById("p3").innerHTML += JSON.stringify(emplist+ "<br>");
	document.getElementById("p3").innerHTML += JSON.stringify(emplist.D1) + "<br>"; 
	document.getElementById("p3").innerHTML += JSON.stringify(emplist.D2) + "<br>"; 
	
	//JSON 객체가 가진 값들을 하나씩 꺼내서 출력 추가하기
	
	document.getElementById("p3").innerHTML +=
		"<b><br><br><br>JSON 객체가 가진 값들을 하나씩 꺼내서 출력하기</b>";
	document.getElementById("p3").innerHTML += "<B>[D1 부서 직원 정보]</B><br>"
	document.getElementById("p3").innerHTML += "사 번 : " + emplist.D1.empid + "<br>";
	document.getElementById("p3").innerHTML += "직원명 : " + emplist.D1.empname + "<br>";
	document.getElementById("p3").innerHTML += "급 여  : " + emplist.D1.salary + "<br>";
	document.getElementById("p3").innerHTML += "취미활동 : " + emplist.D1.hobby + "<br>";
	
	document.getElementById("p3").innerHTML +=
		"<b><br><br><br>JSON 객체가 가진 값들을 하나씩 꺼내서 출력하기</b>";
	document.getElementById("p3").innerHTML += "<B>[D1 부서 직원 정보]</B><br>"
	document.getElementById("p3").innerHTML += "사 번 : " + emplist.D2.empid + "<br>";
	document.getElementById("p3").innerHTML += "직원명 : " + emplist.D2.empname + "<br>";
	document.getElementById("p3").innerHTML += "급 여  : " + emplist.D2.salary + "<br>";
	document.getElementById("p3").innerHTML += "취미활동 : " + emplist.D2.hobby + "<br>";
	
	document.getElementById("p3").innerHTML +=
		"<br><br><b>JSON객체 안의 JSON객체가 가진 배열값들을 하나씩 꺼내서 출력하기</b><br>";
		
	for(i in emplist.D1.hobby) {
		document.getElementById("p3").innerHTML += 
			"[" + i + "]:" + emplist.D1.hobby[i] + "<br>";
	}
	
	for(i in emplist.D2.hobby) {
		document.getElementById("p3").innerHTML +=
			"[" + i + "]:" + emplist.D2.hobby[i] + "<br>";
	}
}

</script>

<hr>
<h2>4. JSON 객체 안에 계층형 다단계 하위 JSON 객체 다루기 </h2>
<p id="p4" style="width: 600px; height:750px; border: 1px solid red"></p>
<button type="button" onclick="jsonTest4();">출력하기</button>

<script type="text/javascript">
	var emphir = {
		"D1" : {
			"J1" : {
				"empid" : "22031521",
				"empname" : "홍길동",
				"salary" : 380,
				"hobby" : [ "영화감상", "독서", "여행" ],
			},
			"J2" : {
				"empid" : "22031522",
				"empname" : "이쑤신",
				"salary" : 4000,
				"hobby" : [ "요리", "독서", "旅行" ],
			}
		}
	}

	function jsonTest4() {
		console.log('emphire : ' + emphir); //[object Object] 확인
		console.log('emphire.D1' + emphir.D1)
		console.log('emphire.D1.J1' + emphir.D1.J1)
		console.log('emphire.D1.J1' + emphir.D1.J2)
		
		
		JSON.str
		//JSON 객체를 문자열로 반환해서 출력하ㅣㄱ : stringfy() 함수 사용
		document.getElementById("p4").innerHTML = "<B>JSON 객체를 문자열로 변환해서 출력하기</B><BR>";

		document.getElementById("p4").innerHTML += JSON.stringify(emphir
				+ "<br>");
		document.getElementById("p4").innerHTML += JSON.stringify(emphir.D1.J1)
				+ "<br>";
		document.getElementById("p4").innerHTML += JSON.stringify(emphir.D1.J2)
				+ "<br>";

		//JSON 객체가 가진 값들을 하나씩 꺼내서 출력 추가하기

		document.getElementById("p4").innerHTML += "<b><br><br><br>JSON 객체가 가진 값들을 하나씩 꺼내서 출력하기</b>";
		document.getElementById("p4").innerHTML += "<B>[D1 부서 J1 직급 직원 정보]</B><br>"
		document.getElementById("p4").innerHTML += "사 번 : " + emphir.D1.J1empid
				+ "<br>";
		document.getElementById("p4").innerHTML += "직원명 : "
				+ emphir.D1.J1.empname + "<br>";
		document.getElementById("p4").innerHTML += "급 여  : "
				+ emphir.D1.J1.salary + "<br>";
		document.getElementById("p4").innerHTML += "취미활동 : " + emphir.D1.J1.hobby
				+ "<br>";

		document.getElementById("p4").innerHTML += "<b><br><br><br>JSON 객체가 가진 값들을 하나씩 꺼내서 출력하기</b>";
		document.getElementById("p4").innerHTML += "<B>[D1 부서 J2 직급 직원 정보]</B><br>"
		document.getElementById("p4").innerHTML += "사 번 : " + emphir.D1.J2.empid
				+ "<br>";
		document.getElementById("p4").innerHTML += "직원명 : "
				+ emphir.D1.J2.empname + "<br>";
		document.getElementById("p4").innerHTML += "급 여  : "
				+ emphir.D1.J2.salary + "<br>";
		document.getElementById("p4").innerHTML += "취미활동 : " + emphir.D1.J2.hobby
				+ "<br>";

		document.getElementById("p4").innerHTML += "<br><br><b>JSON객체 안의 JSON객체가 가진 배열값들을 하나씩 꺼내서 출력하기</b><br>";

		for (i in emphir.D1.J1.hobby) {
			document.getElementById("p4").innerHTML += "[" + i + "]:"
					+ emphir.D1.J1.hobby[i] + "<br>";
		}

		for (i in emphir.D1.J2.hobby) {
			document.getElementById("p4").innerHTML += "[" + i + "]:"
					+ emphir.D1.J2.hobby[i] + "<br>";
		}
	}
	</script>
	
<hr>
<h2>4. JSON 객체 안에 계층형 다단계 하위 JSON 객체를 배열로 다루기 </h2>
<p id="p5" style="width: 600px; height:750px; border: 1px solid red"></p>
<button type="button" onclick="abcda();">출력하기</button>

<script type="text/javascript">
	var emphire2 = {
		"D1" : {
			"J1" : {
				"empid" : "22031521",
				"empname" : "홍길동",
				"salary" : 380,
				"hobby" : [ "영화감상", "독서", "여행" ],
			},
			"J2" : {
				"empid" : "22031522",
				"empname" : "이쑤신",
				"salary" : 4000,
				"hobby" : [ "요리", "독서", "旅行" ],
			}
		}
	}

	function abcda() {
		console.log('emphire2e : ' + emphire2); //[object Object] 확인
		console.log('emphire2e.D1' + emphire2.D1)
		console.log('emphire2e.D1[0]' + emphire2.D1[0])
		console.log('emphire2e.D1[0]' + emphire2.D1["J2"])
		
		
		
		//JSON 객체를 문자열로 반환해서 출력하ㅣㄱ : stringfy() 함수 사용
		document.getElementById("p5").innerHTML = "<B>JSON 객체를 문자열로 변환해서 출력하기</B><BR>";

		document.getElementById("p5").innerHTML += JSON.stringify(emphire2
				+ "<br>");
		document.getElementById("p5").innerHTML += JSON.stringify(emphire2.D1[0])
				+ "<br>";
		document.getElementById("p5").innerHTML += JSON.stringify(emphire2.D1["J2"])
				+ "<br>";

		//JSON 객체가 가진 값들을 하나씩 꺼내서 출력 추가하기

		document.getElementById("p5").innerHTML += "<b><br><br><br>JSON 객체가 가진 값들을 하나씩 꺼내서 출력하기</b>";
		document.getElementById("p5").innerHTML += "<B>[D1 부서 J1 직급 직원 정보]</B><br>"
		document.getElementById("p5").innerHTML += "사 번 : " + emphire2.D1[0].empid
				+ "<br>";
		document.getElementById("p5").innerHTML += "직원명 : "
				+ emphire2.D1[0].empname + "<br>";
		document.getElementById("p5").innerHTML += "급 여  : "
				+ emphire2.D1[0].salary + "<br>";
		document.getElementById("p5").innerHTML += "취미활동 : " + emphire2.D1[0].hobby
				+ "<br>";

		document.getElementById("p5").innerHTML += "<b><br><br><br>JSON 객체가 가진 값들을 하나씩 꺼내서 출력하기</b>";
		document.getElementById("p5").innerHTML += "<B>[D1 부서 J2 직급 직원 정보]</B><br>"
		document.getElementById("p5").innerHTML += "사 번 : " + emphire2.D1["J2"].empid
				+ "<br>";
		document.getElementById("p5").innerHTML += "직원명 : "
				+ emphire2.D1["J2"].empname + "<br>";
		document.getElementById("p5").innerHTML += "급 여  : "
				+ emphire2.D1["J2"].salary + "<br>";
		document.getElementById("p5").innerHTML += "취미활동 : " + emphire2.D1["J2"].hobby
				+ "<br>";

		document.getElementById("p5").innerHTML += "<br><br><b>JSON객체 안의 JSON객체가 가진 배열값들을 하나씩 꺼내서 출력하기</b><br>";

		for (i in emphire2.D1[0].hobby) {
			document.getElementById("p5").innerHTML += "[" + i + "]:"
					+ emphire2.D1[0].hobby[i] + "<br>";
		}

		for (i in emphire2.D1["J2"].hobby) {
			document.getElementById("p5").innerHTML += "[" + i + "]:"
					+ emphire2.D1["J2"].hobby[i] + "<br>";
		}
	}
	
</script>

<hr>
<h2>4. JSON 객체 안에 계층형 다단계 하위 JSON 객체를 배열로 다루기 </h2>
<p id="p6" style="width: 600px; height:750px; border: 1px solid red"></p>
<button type="button" onclick="abcda2();">출력하기</button>

<script type="text/javascript">
	var emphire2 = {
		"D1" : [{
				"empid" : "22031521",
				"empname" : "홍길동",
				"salary" : 380,
				"hobby" : [ "영화감상", "독서", "여행" ],
			},
			 {
				"empid" : "22031522",
				"empname" : "이쑤신",
				"salary" : 4000,
				"hobby" : [ "요리", "독서", "旅行" ],
			}
		
	]
	}
	function abcda2() {
		console.log('emphire2e : ' + emphire2); //[object Object] 확인
		console.log('emphire2e.D1' + emphire2.D1)
		console.log('emphire2e.D1[0]' + emphire2.D1[0])
		console.log('emphire2e.D1[0]' + emphire2.D1[1])
		
		
		
		//JSON 객체를 문자열로 반환해서 출력하ㅣㄱ : stringfy() 함수 사용
		document.getElementById("p6").innerHTML = "<B>JSON 객체를 문자열로 변환해서 출력하기</B><BR>";

		document.getElementById("p6").innerHTML += JSON.stringify(emphire2
				+ "<br>");
		document.getElementById("p6").innerHTML += JSON.stringify(emphire2.D1[0])
				+ "<br>";
		document.getElementById("p6").innerHTML += JSON.stringify(emphire2.D1[1])
				+ "<br>";

		for(var i in emphire2.D1) {	
		//JSON 객체가 가진 값들을 하나씩 꺼내서 출력 추가하기

			document.getElementById("p6").innerHTML += "<b><br><br><br>JSON 객체가 가진 값들을 하나씩 꺼내서 출력하기</b>";
			document.getElementById("p6").innerHTML += "<B>[D1 부서 J1 직급 직원 정보]</B><br>"
			document.getElementById("p6").innerHTML += "사 번 : " + emphire2.D1[i].empid
					+ "<br>";
			document.getElementById("p6").innerHTML += "직원명 : "
					+ emphire2.D1[i].empname + "<br>";
			document.getElementById("p6").innerHTML += "급 여  : "
					+ emphire2.D1[i].salary + "<br>";
			document.getElementById("p6").innerHTML += "취미활동 : " + emphire2.D1[i].hobby
					+ "<br>";
	
			document.getElementById("p6").innerHTML += "<br><br><b>JSON객체 안의 JSON객체가 가진 배열값들을 하나씩 꺼내서 출력하기</b><br>";
	
	
			for ( var j in emphire2.D1[i].hobby) {
				document.getElementById("p6").innerHTML += "[" + j + "]:"
				+ emphire2.D1[i].hobby[j] + "<br>";
			}
		}
}
	
</script>


</body>
</html>
