package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.QnADto;

public interface QnAService {
	// QnA 전체 조회
	public List<QnADto> selectBoard();

	// 번호로 QnA 조회 (상세 조회)
	public QnADto selectBoardByNo(int no);

	// QnA 등록
	public boolean insertBoard(QnADto qna);

	// QnA 수정
	public boolean updateBoard(QnADto qna);

	// QnA 삭제
	public boolean deleteBoard(int no);

	// 답글 수정
	public boolean updateReply(QnADto qna);

	// 답글 삭제
	public boolean deleteReply(int no);
}
