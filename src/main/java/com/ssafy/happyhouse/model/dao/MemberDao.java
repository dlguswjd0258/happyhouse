package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.dto.PageBean;

@Mapper
public interface MemberDao {

//	회원가입
	void registerMember(MemberDto memberDto);
	
//	로그인 혹은 회원정보 수정을 위한 회원의 모든 정보 얻기
	MemberDto getMember(String userId);
	
//	회원 전체 조회
	List<MemberDto> searchAll(PageBean bean);
	
	int totalCount(PageBean bean);
	
//	회원정보 수정
	void modifyMember(MemberDto memberDto);
	
//	회원탈퇴
	void deleteMember(String userId);


}