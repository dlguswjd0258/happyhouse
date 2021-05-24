package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.NotationDto;
/*
 전체 리스트
 번호 조회
 수정, 등록, 삭제
 답글 도 수정, 등록, 삭제
 */
@Mapper
public interface NotationDao {
	// 게시글
	public List<NotationDto> selectBoard();
	public NotationDto selectBoardByNo(int no);
	public int insertBoard(NotationDto qna);
	public int updateBoard(NotationDto qna);
	public int deleteBoard(int no);
	// 답글
	public int updateReply(NotationDto qna);
	public int deleteReply(int no);
}