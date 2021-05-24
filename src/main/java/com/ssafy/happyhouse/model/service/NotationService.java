package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NotationDto;

public interface NotationService {
	// QnA 전체 조회
	public List<NotationDto> selectBoard();

	// 번호로 QnA 조회 (상세 조회)
	public NotationDto selectBoardByNo(int no);

	// QnA 등록
	public boolean insertBoard(NotationDto qna);

	// QnA 수정
	public boolean updateBoard(NotationDto qna);

	// QnA 삭제
	public boolean deleteBoard(int no);
}
