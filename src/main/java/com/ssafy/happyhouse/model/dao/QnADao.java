package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.QnADto;
/*
 전체 리스트
 번호 조회
 수정, 등록, 삭제
 답글 도 수정, 등록, 삭제
 */
@Mapper
public interface QnADao {
	// 게시글
	public List<QnADto> selectBoard();
	public QnADto selectBoardByNo(int no);
	public int insertBoard(QnADto qna);
	public int updateBoard(QnADto qna);
	public int deleteBoard(int no);
	// 답글
	public int updateReply(QnADto qna);
	public int deleteReply(int no);
}