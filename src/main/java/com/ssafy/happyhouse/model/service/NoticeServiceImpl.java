package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dao.NoticeDao;
import com.ssafy.happyhouse.model.dto.NoticeDto;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDao notationDao;

	@Override
	public List<NoticeDto> selectBoard() {
		return notationDao.selectBoard();
	}

	@Override
	public NoticeDto selectBoardByNo(int no) {
		return notationDao.selectBoardByNo(no);
	}

	@Override
	public boolean insertBoard(NoticeDto qna) {
		return notationDao.insertBoard(qna) == 1;
	}

	@Override
	@Transactional
	public boolean updateBoard(NoticeDto qna) {
		return notationDao.updateBoard(qna) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int no) {
		return notationDao.deleteBoard(no) == 1;
	}

}
