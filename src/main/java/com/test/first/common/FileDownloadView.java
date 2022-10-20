package com.test.first.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component("filedown")
public class FileDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 파일 다운로드 처리용 코드 구현
		// 컨트롤러 메소드에서 리턴한 모델의 값을 추출해서
		// 폴더에서 파일을 읽어서 클라이언트로 파일을 출력(전송) 처리함
		
		//컨트롤러에서 뷰리졸버(BeanNameViewResolver)를 거쳐
		//전달된 model 의 정보 추출
		File renameFile = (File) model.get("renameFile");
		File downFile = (File)model.get("originFile");
		
		//한글 파일명 깨지지 않도록 인코딩 처리를 위해 파일명만 추출함
		String fileName = downFile.getName();
		
		//클라이언트로 전송하기 위한 설정
		response.setContentType("text/plain; charset=utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=\""+ 
			new String(fileName.getBytes("UTF-8"), "ISO-8859-1") +"\"");
		
		response.setContentLength((int)renameFile.length());
		
		//파일입출력에 대한 스트림 생성
		OutputStream out = response.getOutputStream();
		FileInputStream fin = new FileInputStream(renameFile);
		
		//저장폴더에서 renameFile을 read() 해서
		//response 로 write() | print() 함 -> 스프링엣 ㅓ제공함
		
		FileCopyUtils.copy(fin, out);
		
		
	}
	//스프링에서는 뷰클래스를 만들려면, 스프링이 제공하는 AbstractView 를 상속받아서
	// 오버라이딩한 메소드 안에 기능을 구현하도록 함
}
