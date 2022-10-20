package com.test.first.board.model.service;

import java.util.ArrayList;

import com.test.first.board.model.vo.Board;
import com.test.first.common.Paging;

public interface BoardService {
	ArrayList<Board> selectTop3();
	int selectListCount(); //총 게시글 갯수 조회(페이지 수 계산용)
	ArrayList<Board> selectList(Paging page);//게시글 조회용
	Board selectBoard(int board_num); //해당 번호에 대한 게시글 상세 조회용
	int updateAddReadcount(int board_num);// 상세보기 조회시 조회수 1증가시키기
	
	int insertOriginBoard(Board board); //원글 등록용
	int insertReply(Board reply); // 댓글 등록용 (대댓글 등록 포함)
	int updateReplySeq(Board reply); //댓글 등록시 기존 댓글 순번을
	int updateOrigin(Board board); //원글 수정용
	int updateReply(Board reply); //댓글 수정용
	int deleteBoard(Board board); //게시글 삭제용
	
	
}
