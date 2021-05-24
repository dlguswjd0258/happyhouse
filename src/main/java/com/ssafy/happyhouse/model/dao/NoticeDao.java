package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.NoticeDto;
/*
 전체 리스트
 번호 조회
 수정, 등록, 삭제
 답글 도 수정, 등록, 삭제
 */
@Mapper
public interface NoticeDao {
	// 게시글
	public List<NoticeDto> selectBoard();
	public NoticeDto selectBoardByNo(int no);
	public int insertBoard(NoticeDto qna);
	public int updateBoard(NoticeDto qna);
	public int deleteBoard(int no);
}