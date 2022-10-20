package com.test.first.test.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.first.notice.model.service.NoticeService;
import com.test.first.notice.model.vo.Notice;

@Controller
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value = "test.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String testViewMethod() {
		logger.info("in testViewmethod");
		return "test/testView";
	}

	@RequestMapping(value = "testJSON.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String testJSONViewMethod() {
		logger.info("in testViewmethod");
		return "test/testJSONView";
	}

	@RequestMapping(value = "testJS.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String testJSViewMethod() {
		logger.info("in testViewmethod");
		return "test/testJSView";
	}

	@RequestMapping(value = "testJQuery.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String testJQueryViewMethod() {
		logger.info("in testViewmethod");
		return "test/testJQueryView";
	}
	
	
	@RequestMapping(value = "testAjaxFile.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String testAjaxFileViewMethod() {
		logger.info("in testAjaxFileViewMethod");
		return "test/testAjaxFileView";
	}
	
	@RequestMapping(value = "moveAOP.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String moveAOPViewMethod() {
		return "test/testAOPPage";
	}
	
	// ajax file upload / download test ----------
	@RequestMapping(value="testFileUp.do", method=RequestMethod.POST)
	public void testFileUploadMethod(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam String message,
			@RequestParam(required=false) MultipartFile upfile) throws IllegalStateException, IOException {
		
		logger.info(message);
		logger.info(upfile.getOriginalFilename());
		
		//파일 저장폴더에 대한 저장경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/test_files");
		
		//업로드된 파일을 폴더로 옮기기
		upfile.transferTo(new File(savePath+ "\\" + upfile.getOriginalFilename()));
		
		response.getWriter().append("ok").flush();
	}
	
	@RequestMapping("filedown.do")
	public ModelAndView fileDownMethod(HttpServletRequest request,
			@RequestParam String fname) {
		// 공지사항 첨부파일 저장 폴더경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/test_files");

		// 저장 폴더에서 읽을 파일에 대한 파일 객체 생성함

		File downFile = new File(savePath + "\\" + fname);
		
		/*
		 * ModelAndView(String viewName, String modelName, Object)
		 * model == request + response
		 * modelName == 이름(저장하는 이름)
		 * modelObject == 저장하는 객체
		 * model.addObject("이름",객체) 와 같음
		 * 또는 request.setAttribute("이름", 객체)와도 같음
		 */
			

	
		return new ModelAndView("afiledown", "downFile", downFile);
	}
	
	// ajax test ---------------------------------------------------------
	/*
	 * @RequestMapping(value="test1.do", method= RequestMethod.GET, produces =
	 * "application/text; charset=utf8")
	 * 
	 * @ResponseBody public String test1Method() { //ajax(Asynchronous Javascript
	 * And Xml //자바 스크립트와 xml 을 이용한 비동기식 네트워크 통신 //별도의 입출력 스트림을 가지고 있음 JSONObject
	 * sendjs = new JSONObject();
	 * 
	 * sendjs.put("message", "test1Method 성공"); return sendjs.toJSONString(); }
	 */

	@RequestMapping(value = "test1.do", method = RequestMethod.GET)
	@ResponseBody
	public void test1Method(HttpServletResponse response, HttpServletRequest request) throws IOException {
		// ajax(Asynchronous Javascript And Xml
		// 자바 스크립트와 xml 을 이용한 비동기식 네트워크 통신
		// 별도의 입출력 스트림을 가지고 있음

		// 서비스를 요청한 클라이언트로 값 내보낼 출력스트림 생성
		PrintWriter out = response.getWriter();
		out.append("Served at : ");
		out.append(request.getContextPath());

		out.flush();
		out.close();
	}

	@RequestMapping(value = "test2.do", method = RequestMethod.POST)
	@ResponseBody
	public void test2Method(HttpServletResponse response, HttpServletRequest request) throws IOException {
		logger.info("test2.do run....");
		// request 객체 : 클라이언트측의 전송값을 가지고 오는 객체
		// response 객체 : 서비스를 요청한 클라이언트 정보가 들어있음

		// request 에서 전송값 꺼내기 : getParameter("전송온이름"): String
		String name = request.getParameter("name");
		String ageParam = request.getParameter("age");
		// parsing : String --> 기본자료형으로 반환하는것
		int age = Integer.parseInt(ageParam);
		// ==> spring 에서는 메소드 매개변수에 어노테이션으로 처리
		// @RequestParam("이름") 자료형 변수명
		// @RequestParam("name") String name,
		// @RequestParam("age") int age 표기하면 됨

		logger.info(name + ", " + age);

		// 요청한 클라이언트에게 결과를 전송함 : 출력스트림을 이용함
		// 1. 응답하는 정보에 대한 MimiType 지정함 (권장함)
		response.setContentType("text/html; charset=utf-8");
		// 2. 출력에 사용할 스트림 생성
		// 서비스를 요청한 클라이언트로 값 내보낼 출력스트림 생성
		PrintWriter out = response.getWriter();

		// 전송온 이름이 "홍길동"이면 "ok", 아니면 "fail" 전송
		if (name.trim().equals("홍길동")) {
			out.append("ok");
			out.flush();
		} else {
			out.append("fail");
			out.flush();
		}

		out.close();
	}

	@RequestMapping(value = "test3.do", method = RequestMethod.POST)
	@ResponseBody
	public String test3Method(HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info("test3.do run...");

		Notice n = noticeService.selectLast();

		// response 에 mimitype 지정
		// response.setContentType("application/json; charset=utf-8");

		// json 객체 생성 >> 값 기록 저장 >> json 문자열 내보냄
		JSONObject sendjson = new JSONObject();

		sendjson.put("noticeno", n.getNoticeno());

		// 문자열 값 기록시, 한글이 포함되어 있는 경우는 인코딩 처리함
		sendjson.put("noticetitle", URLEncoder.encode(n.getNoticetitle(), "utf-8"));
		sendjson.put("noticetitle2", n.getNoticetitle());
		sendjson.put("noticewriter", n.getNoticewriter());

		// 날짜데이터는 반드시 문자열로 바꿔서 저장해야 함
		sendjson.put("noticedate", n.getNoticedate().toString());
		sendjson.put("noticecontent", URLEncoder.encode(n.getNoticecontent(), "utf-8"));
		sendjson.put("noticecontent2", n.getNoticecontent());
		// 서비스 요청한 클라이언트로 응답하는 2가지 중 선택
		// 방법 1 : public void 형 >> 직접 추력스트림 만들어서 내보냄
		// 방법 2 : public String 형 >> 설정된 jsonview 로 리턴함

		// 응답시에는 sjon 객체를 string 형으로 바꿔서 응답함
		// 디코드 왜함? 안해도되는데 진짜모름
		return sendjson.toJSONString();
	}

	// 클라이언트의 요청을 처리한 결과로 json 배열을 jsonView로 리턴하는 메소드
	@RequestMapping(value = "test4.do", method = RequestMethod.POST)
	@ResponseBody
	public String test4Method(HttpServletResponse response, HttpServletRequest request,
						@RequestParam("keyword") String keyword) throws UnsupportedEncodingException {
		logger.info("test4.do run...");
		
		// 전송용 json객체
		JSONObject sendJson = new JSONObject();

		ArrayList<Notice> list = noticeService.selectSearchTitle(keyword);

		//response.setContentType("application/json; charset=utf-8"); // 안써도됨
		
		
		// 하나씩 담을 json 배열 객체
		JSONArray jar = new JSONArray();
		for (Notice n : list) {
			JSONObject json = new JSONObject(); //하나씩담을 객체
			json.put("noticeno", n.getNoticeno());
			json.put("noticetitle", n.getNoticetitle());
			//json.put("", URLEncoder.encode( n.getNoticetitle(), "utf-8" ));
			json.put("noticewriter", n.getNoticewriter());
			//json.put("", URLEncoder.encode(n.getNoticewriter(), "utf-8")); //이제 안써도됨
			json.put("noticedate", n.getNoticedate().toString()); 
			
			jar.add(json); //하나씩 담고
		}
		
		sendJson.put("list", jar); // list에 넣음
		return sendJson.toJSONString();
	}
	
	//클라이언트가 보낸 json 객체를 받아서 처리하는 메소드
	// get ; 전송값이 request head 에 기록되서 전송됨(보여짐)
	// post : 전송값이 request body 에 기록되서 전송됨(안보임ㅋ)
	
	@RequestMapping(value="test5.do", method=RequestMethod.POST)
	public ResponseEntity<String> test5Method(@RequestBody String param) throws ParseException {
		//post 로 request body 에 기록된 json 문자열을 꺼내서
		//param 변수에 저장함
		logger.info("run test5Method ...");
		logger.info(param);
		JSONParser jparser = new JSONParser();
		JSONObject job = (JSONObject) jparser.parse(param);
		logger.info(job.toString());
		
		Notice n = new Notice();
		n.setNoticetitle((String)job.get("title"));
		n.setNoticecontent((String)job.get("content"));
		n.setNoticewriter((String)job.get("writer"));
		
		if(noticeService.insertNotice(n) > 0) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("failed", HttpStatus.REQUEST_TIMEOUT);
		}
			
		
		
	}
	
	@RequestMapping(value="test6.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> test6Method(@RequestBody Notice[] params) throws ParseException {
		logger.info("run test6.do ...");
		
		for(Notice n: params) {
			logger.info(n.toString());
			noticeService.insertNotice(n);
		}
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

}
