package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dao.QnADao;
import com.ssafy.happyhouse.model.dto.QnADto;

@Service
public class QnAServiceImpl implements QnAService {
	
	@Autowired
	private QnADao qnaDao;

	@Override
	public List<QnADto> selectBoard() {
		return qnaDao.selectBoard();
	}

	@Override
	public QnADto selectBoardByNo(int no) {
		return qnaDao.selectBoardByNo(no);
	}

	@Override
	public boolean insertBoard(QnADto qna) {
		return qnaDao.insertBoard(qna) == 1;
	}

	@Override
	@Transactional
	public boolean updateBoard(QnADto qna) {
		return qnaDao.updateBoard(qna) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int no) {
		return qnaDao.deleteBoard(no) == 1;
	}

	@Override
	@Transactional
	public boolean updateReply(QnADto qna) {
		return qnaDao.updateReply(qna) == 1;
	}

	@Override
	@Transactional
	public boolean deleteReply(int no) {
		return qnaDao.deleteReply(no) == 1;
	}

}
