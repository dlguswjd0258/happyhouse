package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;

public interface NoticeService {
	// QnA 전체 조회
	public List<NoticeDto> selectBoard();

	// 번호로 QnA 조회 (상세 조회)
	public NoticeDto selectBoardByNo(int no);

	// QnA 등록
	public boolean insertBoard(NoticeDto qna);

	// QnA 수정
	public boolean updateBoard(NoticeDto qna);

	// QnA 삭제
	public boolean deleteBoard(int no);
}
