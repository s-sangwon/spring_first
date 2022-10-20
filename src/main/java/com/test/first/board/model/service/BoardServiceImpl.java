package com.test.first.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.first.board.model.dao.BoardDao;
import com.test.first.board.model.vo.Board;
import com.test.first.common.Paging;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public ArrayList<Board> selectTop3() {
		return boardDao.selectTop3();
	}

	@Override
	public int selectListCount() {
		return boardDao.selectListCount();
	}

	@Override
	public ArrayList<Board> selectList(Paging page) {
		return boardDao.selectList(page);
	}

	@Override
	public Board selectBoard(int board_num) {
		// TODO Auto-generated method stub
		return boardDao.selectBoard(board_num);
	}

	@Override
	public int updateAddReadcount(int board_num) {
		return boardDao.updateAddReadcount(board_num);
	}

	@Override
	public int insertOriginBoard(Board board) {
		// TODO Auto-generated method stub
		return boardDao.insertOriginBoard(board);
	}

	@Override
	public int insertReply(Board reply) {
		// TODO Auto-generated method stub
		return boardDao.insertReply(reply);
	}

	@Override
	public int updateReplySeq(Board reply) {
		return boardDao.updateReplySeq(reply);
	}

	@Override
	public int updateOrigin(Board board) {
		// TODO Auto-generated method stub
		return boardDao.updateOrigin(board);
	}

	@Override
	public int updateReply(Board reply) {
		return boardDao.updateReply(reply);
	}

	@Override
	public int deleteBoard(Board board) {
		// TODO Auto-generated method stub
		return boardDao.deleteBoard(board);
	}

}
