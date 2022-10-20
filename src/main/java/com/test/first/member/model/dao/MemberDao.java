package com.test.first.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.first.common.SearchDate;
import com.test.first.member.model.vo.Member;

@Repository("memberDao") // xml 자동 입력됨
public class MemberDao {
	//마이바티스 매퍼파일 쿼리문 실행
	//root-context.xml 에 생성된 객체를 연결하여 사용함
	
	@Autowired
	private SqlSessionTemplate session;
	
	public Member selectLogin(Member member) {
		return session.selectOne("memberMapper.selectLogin", member);
	}
	
	public Member selectMember(String userid) {
		return session.selectOne("memberMapper.selectMember", userid);
	}
	
	public int insertMember(Member member) {
		return session.insert("memberMapper.insertMember", member);
	}

	public int selectDupCheckId(String userid) {
		return session.selectOne("memberMapper.selectCheckId",userid);
	}
	
	public int updateMember(Member member) {
		return session.update("memberMapper.updateMember", member);
	}
	
	public int deletemember(String userid) {
		return session.delete("memberMapper.deleteMember",userid);
	}
	
	public ArrayList<Member> selectList() {
		List<Member> list = session.selectList("memberMapper.selectList");
		return (ArrayList<Member>) list;
	}
	
	public int updateLoginOK(Member member) {
		return session.update("memberMapper.updateLoginOK", member);
	}
	
	//검색 처리용 -------------------------
	public ArrayList<Member> selectSearchUserid(String keyword) {
		List<Member> list = session.selectList(
				"memberMapper.selectSearchUserid",keyword);
		return (ArrayList<Member>)list;
	}
	
	public ArrayList<Member> selectSearchGender(String keyword) {
		List<Member> list = session.selectList(
				"memberMapper.selectSearchGender",keyword);
		return (ArrayList<Member>)list;
	}
	
	public ArrayList<Member> selectSearchAge(int age) {
		List<Member> list = session.selectList(
				"memberMapper.selectSearchAge",age);
		return (ArrayList<Member>)list;
	}
	
	public ArrayList<Member> selectSearchEnrollDate(SearchDate sDate) {
		List<Member> list = session.selectList(
				"memberMapper.selectSearchEnrollDate",sDate);
		return (ArrayList<Member>)list;
	}
	
	public ArrayList<Member> selectSearchLoginOK(String keyword) {
		List<Member> list = session.selectList(
				"memberMapper.selectSearchLoginOK",keyword);
		return (ArrayList<Member>)list;
	}
	
}
