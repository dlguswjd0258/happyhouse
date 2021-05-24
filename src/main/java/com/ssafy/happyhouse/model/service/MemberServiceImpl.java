package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.MemberDao;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.util.PageUtility;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	@Override
	public void registerMember(MemberDto memberDto) {
		memberDto.setAddress(memberDto.getAddress().trim());
		dao.registerMember(memberDto);
	}

//	@Override
//	public MemberDto login(String userId, String userPwd) {
//		MemberDto memberDto = dao.getMember(userId);
//		
//		if(memberDto == null)
//			throw new RuntimeException("등록된 아이디가 없습니다.");
//		
//		if(!userPwd.equals(memberDto.getUserPwd()))
//			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
//		
//		return memberDto;
//	}

	@Override
	public MemberDto getMember(String userId) {
		return dao.getMember(userId);
	}

	@Override
	public void modifyMember(MemberDto memberDto) {
		dao.modifyMember(memberDto);
	}

	@Override
	public void deleteMember(String userId) {
		dao.deleteMember(userId);
	}

	@Override
	public List<MemberDto> searchAll(PageBean bean) {
		int total = dao.totalCount(bean);
		PageUtility util = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "images/");
		return dao.searchAll(bean);
	}
}
