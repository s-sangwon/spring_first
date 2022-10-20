package com.test.first.board.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import org.springframework.web.servlet.ModelAndView;

import com.test.first.board.model.service.BoardService;
import com.test.first.board.model.vo.Board;
import com.test.first.common.Paging;
import com.test.first.notice.model.vo.Notice;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "btop3.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardReadcountTop3method() throws UnsupportedEncodingException {

		// 조히ㅗ수 많은 게시원글 3개 조회해 옴
		ArrayList<Board> list = boardService.selectTop3();
		logger.info("btop3.do : " + list.size());
		// 전송용 json 객체 준비
		JSONObject sendJson = new JSONObject();
		// list 를 옮길 json 배열 객체 준비
		JSONArray jarr = new JSONArray();

		// list 를 jarr 에 옮기기 (복사)
		for (Board board : list) {
			// notice 객체의 각 필드값 저장할 json 객체생성
			JSONObject jobj = new JSONObject();

			jobj.put("board_num", board.getBoard_num());
			// 한글에 대해서는 인코딩해서 json 에 담도록 함

			jobj.put("board_title", URLEncoder.encode(board.getBoard_title(), "utf-8"));
			// 날짜는 반드시 toString() 으로 문자열로 바꿔서 json 담아야함
			jobj.put("board_readcount", board.getBoard_readcount());

			jarr.add(jobj); // jobj 를 jarr에 저장
		}
		// 전송용 객체에 jarr 을 담음
		sendJson.put("list", jarr);

		// json 을 json string 타입으로 바꿔서 전송함
		return sendJson.toJSONString(); // 뷰리졸버로 리턴함
		// ajax 로 리턴은 여러가지 방법이 있음 : response 객체 이용
		// 1. 출력스트림으로 응답하는 방법 (아이디 중복체크에서 사용)
		// 2. 뷰리졸버로 리턴하는 방법 : respones body에 값을 저장함

	}

	@RequestMapping("blist.do")
	public ModelAndView boardListMethod(@RequestParam(name = "page", required = false) String page, ModelAndView mv) {
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}
		// 한 페이지에 게시글 10개씩 출력
		// 페이징 계산 처리 -- 별도의 클래스로 작성해도 됨 --------
		// 별도의 클래스의 메소드에서 Paging 을 리턴하면 됨
		int limit = 10;
		// 전체 페이지 갯수 계산을 위해 총 목록 갯수 조회해 옴
		int listCount = boardService.selectListCount();
		// 페이지 수 계산
		// 주의 : 목록이 11개이면 페이지 수는 2페이지가 됨
		// 나머지 목록 1개도 1페이지 필요함
		int maxPage = (int) ((double) listCount / limit + 0.9);
		// 현재 페이지에 출력할 시작행과 끝행을 계산
		int startPage = currentPage / 10 * 10 + 1;
		// 현재 페이지가 포함된 페이지 그룹의 끝값 지정
		int endPage = startPage + 10 - 1;

		if (maxPage < endPage) {
			endPage = maxPage;
		}
		// 쿼리문에 전달할 현재 페이지에 적용할 목록의 시작행과 끝행 계산
		int startRow = (currentPage - 1) * limit + 1;
		int endRow = startRow + limit - 1;
		Paging paging = new Paging(startRow, endRow);

		// 페이징 계산 처리 끝 -------------------------------

		ArrayList<Board> list = boardService.selectList(paging);

		if (list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("listCount", listCount);
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startPage", startPage);
			mv.addObject("endPage", endPage);
			mv.addObject("limit", limit);

			mv.setViewName("board/boardListView");
		} else {
			mv.addObject("message", " 페이지 목록 조회 실패.");
			mv.setViewName("common/error");
		}

		return mv;
	}

	@RequestMapping(value = "bdetail.do")
	public ModelAndView boardDetailMethod(
			ModelAndView mv, 
			@RequestParam("board_num") int board_num,
			@RequestParam(value = "page", required = false) String page) {
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 조회수 1증가 처리
		boardService.updateAddReadcount(board_num);

		Board board = boardService.selectBoard(board_num);

		if (board != null) {
			mv.addObject("board", board);
			mv.addObject("currentPage", currentPage);
			mv.setViewName("board/boardDetailView");
		} else {
			mv.addObject("message", board_num + "번 게시글 조회 실패!");
			mv.setViewName("common/error");
		}

		return mv;
	}

	// 게시글 첨부파일 다운로드 처리용
	@RequestMapping("bfdown.do")
	public ModelAndView fileDownMethod(HttpServletRequest request, @RequestParam("ofile") String originalFileName,
			@RequestParam("rfile") String renameFileName, ModelAndView mv) {
		// 공지사항 첨부파일 저장 폴더경로 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");

		// 저장 폴더에서 읽을 파일에 대한 파일 객체 생성함
		
		File renameFile = new File(savePath + "\\" + renameFileName);
		// 파일다운시 내보낼 파일 객체
		File originFile = new File(originalFileName);

		mv.setViewName("filedown");
		mv.addObject("renameFile", renameFile);
		mv.addObject("originFile", originFile);

		return mv;
	}

	@RequestMapping("breplyform.do")
	public String moveReplyForm(Model model, @RequestParam("board_num") int origin_num,
			@RequestParam("page") int currentPage) {

		model.addAttribute("board_num", origin_num);
		model.addAttribute("currentPage", currentPage);

		return "board/boardReplyForm";
	}

	// 댓글 등록 처리용
	@RequestMapping(value = "breply.do", method = RequestMethod.POST)
	public String replyInsertmethod(Board reply, @RequestParam("page") int page, Model model) {
		// 해당 댓글에 대한 원글 조회
		Board origin = boardService.selectBoard(reply.getBoard_ref());

		// 현재 등록할 댓글의 레벨을 설정
		reply.setBoard_lev(origin.getBoard_lev() + 1);

		// 대댓글(댓글의 댓글)일때는 board_reply_ref(참조하는 댓글번호) 값 지정
		if (reply.getBoard_lev() == 3) {
			// 참조 원글 번호
			reply.setBoard_ref(origin.getBoard_ref());
			// 참조 댓글 번호
			reply.setBoard_reply_ref(origin.getBoard_reply_ref());
		}
		// 댓글과 대댓글은 최근 등록글을 seq를 1로 지정
		reply.setBoard_reply_seq(1);

		// 기존의 댓글과 대댓글의 순번 모두 1증가 처리함
		boardService.updateReplySeq(reply);

		if (boardService.insertReply(reply) > 0) {
			return "redirect:blist.do?page=" + page;
		} else {
			model.addAttribute("message", reply.getBoard_ref() + "번 글에 대한 댓글 등록 실패");
			return "common/error";
		}

		// 댓글과 대댓글은 최근 등록글을 board_reply_seq 를 1로 지정함

	}

	@RequestMapping("bwform.do")
	public String moveBoardWriteForm() {

		return "board/boardWriteForm";
	}

	@RequestMapping(value = "binsert.do", method = RequestMethod.POST)
	public String noticeInsertMethod(Board board, HttpServletRequest request,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile, Model model) {
		// 업로드된 파일 저장 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");
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
					return "common/error";
				}

				// notice 객체에 첨부파일명 기록 저장하기
				board.setBoard_original_filename(fileName);
				board.setBoard_rename_filename(renameFileName);
			}

		} // 첨부파일이 있을 때만

		if (boardService.insertOriginBoard(board) > 0) {
			return "redirect:blist.do";
		} else {
			model.addAttribute("message", "새 게시 원글 등록 실패!");
			return "common/error";
		}
	}

	@RequestMapping("bdel.do")
	public String boardDeleteMethod(Board board, HttpServletRequest request, Model model) {
		if (boardService.deleteBoard(board) > 0) {
			// 글삭제가 성공하면, 저장폴더에 첨부파일도 삭제 처리
			if (board.getBoard_rename_filename() != null) {
				new File(request.getSession().getServletContext().getRealPath("resources/board_upfiles") + "\\"
						+ board.getBoard_rename_filename()).delete();

			}

			return "redirect:blist.do?page=1";
		} else {
			model.addAttribute("message", board.getBoard_num() + "번 글 삭제 실패!");
			return "common/error";
		}

	}

	@RequestMapping("bupview.do")
	public String moveBoardUpdateView(@RequestParam("board_num") int board_num, @RequestParam("page") int currentPage,
			Model model) {
		// 수정페이지로 보낼 Board 객체 정보 조회함
		Board board = boardService.selectBoard(board_num);
		if (board != null) {
			model.addAttribute("board", board);
			model.addAttribute("page", currentPage);
			return "board/boardUpdateForm";

		} else {
			model.addAttribute("message", "번 글 수정페이지로 이동 실패!");
			return "common/error";
		}

	}

	// 원글 수정 처리용
	@RequestMapping(value = "boriginup.do", method = RequestMethod.POST)
	public String boardUpdateMethod(
			Board board, 
			@RequestParam("page") int page,
			@RequestParam(name="delFlag", required=false) String delFlag,
			@RequestParam(name = "upfile", required = false) MultipartFile mfile, 
			HttpServletRequest request, Model model) {
		String savePath = request.getSession().getServletContext().getRealPath("resources/board_upfiles");
		

		// 첨부파일 수정 처리된 경우 ---------------------------
		// 1. 원래 첨부파일이 있는데 삭제를 선택한 경우
		if (board.getBoard_original_filename() != null && delFlag != null && delFlag.equals("yes")) {
			// 저장 폴더에서 파일 삭제함
			new File(savePath + "\\" + board.getBoard_rename_filename()).delete();
			// notice 의 파일정보도 제거함
			board.setBoard_original_filename(null);
			board.setBoard_rename_filename(null);
		}

		// 2. 새로운 첨부파일이 있을 때 : 공지글 첨부파일은 1개만 가능한 경우
		if (!mfile.isEmpty()) {
			//저장 폴더의 이전파일은 삭제함
			if(board.getBoard_original_filename() != null) {
				new File(savePath + "\\" + board.getBoard_rename_filename()).delete();
				// notice 의 파일정보도 제거함
				board.setBoard_original_filename(null);
				board.setBoard_rename_filename(null);
			}
			
			//이전 첨부파일이 없을 때 -----
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
					return "common/error";
				}

				// notice 객체에 첨부파일명 기록 저장하기
				board.setBoard_original_filename(fileName);
				board.setBoard_rename_filename(renameFileName);
				
			}

		} // 첨부파일이 있을 때만

		// -----------------------------------------------

		if (boardService.updateOrigin(board) > 0) {
			//원글 수정 성공시 상세보기 페이지를 내보냄
			model.addAttribute("page", page);
			model.addAttribute("board_num", board.getBoard_num());
			return "redirect:bdetail.do";
		} else {
			model.addAttribute("message", board.getBoard_num() + "번 게시 원글 수정 실패!");
			return "common/error";
		}
	}

	// 댓글, 대댓글 수정 처리용
	@RequestMapping(value = "breplyup.do", method = RequestMethod.POST)
	public String replyUpdateMethod(Board reply, @RequestParam("page") int page, Model model) {

		if (boardService.updateReply(reply) > 0) {
			//댓글, 대댓글 수정 성공시 다시 상세페이지가 보여지게 한다면
			model.addAttribute("board_num", reply.getBoard_num());
			model.addAttribute("page", page);
			return "redirect:bdetail.do";
		
		} else {
			model.addAttribute("message", "번 글 수정 실패!");
			return "common/error";
		}
	}
}
