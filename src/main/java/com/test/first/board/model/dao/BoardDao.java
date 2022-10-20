package com.test.first.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.first.board.model.vo.Board;
import com.test.first.common.Paging;

@Repository("boardDao")
public class BoardDao {
	@Autowired
	private SqlSessionTemplate session;

	public ArrayList<Board> selectTop3() {
		List<Board> list = session.selectList("boardMapper.selectTop3");
		return (ArrayList<Board>)list;
	}

	public int selectListCount() {
		return session.selectOne("boardMapper.getListCount");
	}

	public ArrayList<Board> selectList(Paging page) {
		List<Board> list = session.selectList("boardMapper.selectList", page);
		return (ArrayList<Board>)list;
	}

	public int updateAddReadcount(int board_num) {
		return session.update("boardMapper.addReadCount", board_num);
	}

	public Board selectBoard(int board_num) {
		return session.selectOne("boardMapper.selectBoard", board_num);
	}

	public int updateReplySeq(Board reply) {
		int result = 0;
		
		if(reply.getBoard_lev() == 2) { //댓글이면
			result = session.update("boardMapper.updateReplySeq1",reply);
		}
		
		if(reply.getBoard_lev() == 3) { //대댓글이면
			result = session.update("boardMapper.updateReplySeq2",reply);
		}
		return result;
	}

	public int insertReply(Board reply) {
		int result = 0;
		
		if(reply.getBoard_lev() == 2) { //댓글이면
			result = session.insert("boardMapper.insertReply1",reply);
		}
		
		if(reply.getBoard_lev() == 3) { //대댓글이면
			result = session.insert("boardMapper.insertReply2",reply);
		}
		return result;
	}

	public int insertOriginBoard(Board board) {
		// TODO Auto-generated method stub
		return session.insert("boardMapper.insertOrigin",board);
	}

	public int deleteBoard(Board board) {
		// TODO Auto-generated method stub
		return session.delete("boardMapper.deleteBoard",board);
	}

	public int updateReply(Board reply) {
		return session.update("boardMapper.updateReply", reply);
	}

	public int updateOrigin(Board board) {
		return session.update("boardmapper.updateOrigin", board);
	}
	
	
}
