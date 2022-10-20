package com.test.first.notice.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.first.common.SearchDate;
import com.test.first.notice.model.vo.Notice;

@Repository("noticeDao")
public class NoticeDao {
	
	@Autowired
	private SqlSessionTemplate session;

	public ArrayList<Notice> selectNewTop3() {
		List<Notice> list = session.selectList("noticeMapper.selectNewTop3");
		return (ArrayList<Notice>)list;
		
	}
	
	public ArrayList<Notice> selectAll() {
		List<Notice> list = session.selectList("noticeMapper.selectAll");
		return (ArrayList<Notice>)list;
	}
	
	public ArrayList<Notice> selectSearchTitle(String keyword) {
		List<Notice> list = session.selectList("noticeMapper.searchTitle", keyword);
		return (ArrayList<Notice>)list;
	}
	
	public ArrayList<Notice> selectSearchWriter(String keyword) {
		List<Notice> list = session.selectList("noticeMapper.searchWriter", keyword);
		return (ArrayList<Notice>)list;
	}
	
	public ArrayList<Notice> selectSearchDate(SearchDate date) {
		List<Notice> list = session.selectList("noticeMapper.searchDate", date);
		return (ArrayList<Notice>)list;
	}
	
	public Notice selectOne(int noticeno) {
		return session.selectOne("noticeMapper.selectNotice", noticeno);
	}
	//공지글 삭제처리
	public int deleteNotice(int noticeno) {
		return session.delete("noticeMapper.deleteNotice",noticeno);
	}
	
	//새 공지글 등록처리
	public int insertNotice(Notice notice) {
		return session.insert("noticeMapper.insertNotice", notice);
	}
	
	//공지글 수정처리
	public int updateNotice(Notice notice) {
		return session.update("noticeMapper.updateNotice", notice);
	}

	public Notice selectLast() {
		// TODO Auto-generated method stub
		return session.selectOne("noticeMapper.selectLast");
	}
	
}
