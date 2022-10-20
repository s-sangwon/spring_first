package com.test.first.member.model.service;
import java.util.ArrayList;

import com.test.first.common.SearchDate;
import com.test.first.member.model.vo.Member;

// 스프링에서는 모델의 service 는 반드시 interface로 작업

public interface MemberService {
	Member selectLogin(Member member);
	int insertMember(Member member);
	
	int selectDupCheckId(String userid);
	
	Member selectMember(String userid);
	int updateMember(Member member);
	int deleteMember(String userid);
	int updateLoginOK(Member member);
	
	ArrayList<Member> selectList();
	
	//검색
	ArrayList<Member> selectSearchUserid(String keyword);
	ArrayList<Member> selectSearchGender(String keyword);
	ArrayList<Member> selectSearchAge(int age);
	ArrayList<Member> selectSearchEnrollDate(SearchDate sDate);
	ArrayList<Member> selectSearchLoginOK(String keyword);
	//
}
