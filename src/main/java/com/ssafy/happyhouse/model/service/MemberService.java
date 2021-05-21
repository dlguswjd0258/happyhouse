package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.dto.PageBean;

public interface MemberService {

//	회원가입
	void registerMember(MemberDto memberDto);
	
//	로그인
//	String login(MemberDto memberDto);
	
//	회원정보 수정을 위한 회원의 모든 정보 얻기
	MemberDto getMember(String userId);
	
//	전체 회원 조회
	List<MemberDto> searchAll(PageBean bean);
	
//	회원정보 수정
	void modifyMember(MemberDto memberDto);
	
//	회원탈퇴
	void deleteMember(String userId);

	
}
