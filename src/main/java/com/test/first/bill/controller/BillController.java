package com.test.first.bill.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.first.bill.model.service.BillService;
import com.test.first.bill.model.vo.Bill;
import com.test.first.common.FileReaderTest;
import com.test.first.common.OCRGeneralAPIDemo2receipt2tofuc;
import com.test.first.notice.controller.NoticeController;

@Controller
public class BillController {
	private static final Logger logger = LoggerFactory.getLogger(BillController.class);
	private static JSONParser jsonParser = new JSONParser();
	
	@Autowired
	private BillService billService;
	// 지출등록 페이지 이동
	@RequestMapping("bill.do")
	public String moveWritePage() {
		return "bill/billRegistForm";

	}

	@RequestMapping(value = "imageUpload.do", method = RequestMethod.POST)
	public void uploadImageMethod(@RequestParam("image") MultipartFile mfile, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		String suc = "ㅁㄹ";
		String savePath = request.getSession().getServletContext().getRealPath("resources/bill");
		logger.info("mFile : " + mfile);
		logger.info("savePath : " + savePath);

		// 첨부파일이 있을 때만 업로드된 파일을 지정된 폴더로 옮기기
		if (!mfile.isEmpty()) {
			String fileName = mfile.getOriginalFilename();

			// 다른 공지글의 첨부파일과
			// 파일명이 중복되어서 오버라이팅되는 것을 막기 위해
			// 파일명을 변경해서 폴더에 저장하는 방식을 사용할 수 있음
			// 변경 파일명 : 년월일시분초.확장자
			if (fileName != null && fileName.length() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				// 변경할 파일이름 만들기
				String renameFileName = sdf.format(new Date(System.currentTimeMillis()));
				// 원본 파일의 확장자를 추출해서, 변경 파일명에 붙여줌
				String ext = "." + fileName.substring(fileName.lastIndexOf(".") + 1);
				logger.info(ext);
				renameFileName += ext;

				// 파일업로드
				// 파일 객체 만들기
				File originFile = new File(savePath + "\\" + fileName);
				File renameFile = new File(savePath + "\\" + renameFileName);
				// if(!originFile.exists()) originFile.mkdir();
				// 업로드된 파일 저장시키고, 바로 이름바꾸기 실행함
				try {
					logger.info(renameFileName);
					logger.info("저장시작");
					mfile.transferTo(renameFile);
					logger.info("끝!!");
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "전송파일 저장 실패!");
					// return "common/error";
				}
				suc = "성공!";
			}
		} // 첨부파일 있을때만
			// ajax 전송
		String returnValue = suc;

		// response 를 이용해서 클라이언트로 출력스트림을 만들고 값 보내기
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out;

		out = response.getWriter();
		out.append(returnValue);
		out.flush();
		out.close();
	}

	@RequestMapping(value = "imageToText.do", method = RequestMethod.POST)
	@ResponseBody
	public String imageToTextMethod(@RequestParam("image") MultipartFile mfile, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		String suc = "ㅁㄹ";
		String savePath = request.getSession().getServletContext().getRealPath("resources/bill");
		logger.info("mFile : " + mfile);
		logger.info("savePath : " + savePath);
		JSONObject sendJson = new JSONObject();
		// 첨부파일이 있을 때만 업로드된 파일을 지정된 폴더로 옮기기
		if (!mfile.isEmpty()) {
			String fileName = mfile.getOriginalFilename();

			// 다른 공지글의 첨부파일과
			// 파일명이 중복되어서 오버라이팅되는 것을 막기 위해
			// 파일명을 변경해서 폴더에 저장하는 방식을 사용할 수 있음
			// 변경 파일명 : 년월일시분초.확장자
			if (fileName != null && fileName.length() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				// 변경할 파일이름 만들기
				String renameFileName = sdf.format(new Date(System.currentTimeMillis()));
				// 원본 파일의 확장자를 추출해서, 변경 파일명에 붙여줌
				String ext = "." + fileName.substring(fileName.lastIndexOf(".") + 1);
				logger.info(ext);
				renameFileName += ext;
				
				// 파일업로드
				// 파일 객체 만들기
				File file = new File(mfile.getOriginalFilename());

				// if(!originFile.exists()) originFile.mkdir();
				// 업로드된 파일 저장시키고, 바로 이름바꾸기 실행함
				try {
					logger.info("저장시작");
					mfile.transferTo(file); // MultipartFile 객체 -> File 객체로 변환
					logger.info("변환 완료");
					System.out.println("print확인");

					System.out.println("텍스트 변환 시작");
					System.out.println("지금은 저장된파일 가져옴");
					// ocr 코드
					// OCRGeneralAPIDemo2receipt2tofuc iTT = new OCRGeneralAPIDemo2receipt2tofuc();
					// JSONObject jobj = iTT.mainMethod(file);
					// -------

					try (BufferedReader reader = new BufferedReader(
							new FileReader("C:\\python_workspace\\testcv\\namecard\\rec\\receiptOCR.txt"));) {
						String bs = "";
						String str;
						ArrayList<String> keys = new

						ArrayList<String>();

						while ((str = reader.readLine()) != null) {
							bs += str;
						} //
						System.out.println(bs);

						JSONObject o = (JSONObject) jsonParser.parse(bs); // System.out.println(o);

						System.out.println("JSONObject 가져오기 완료");

						FileReaderTest fText = new FileReaderTest();
						sendJson = fText.printInfo(o);
						model.addAttribute("test", "문자어캐꺼냄?");
					}
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "전송파일 저장 실패!");
					// return "common/error";
				}
				
			}
		} // 첨부파일 있을때만
			// ajax 전송
		
		return sendJson.toJSONString();
	}
	
	@RequestMapping(value = "insertBill.do", method = RequestMethod.POST)
	public String billInsertMethod(Bill bill, Model model,@RequestParam("bill_timestamp2") String ts) {
		
		ts = ts.replace("T", " ");
		Timestamp t = Timestamp.valueOf(ts);
		logger.info(t.toString());
		bill.setBill_timestamp(t);
		if(billService.insertBill(bill) > 0) {
			logger.info("지출 등록 성공!");
		} else {
			model.addAttribute("message", "지출 등록 실패!");
			return "common/error";
		}
			

		return "common/main";
	}
}
