package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dao.NotationDao;
import com.ssafy.happyhouse.model.dto.NotationDto;

@Service
public class NotationServiceImpl implements NotationService {
	
	@Autowired
	private NotationDao notationDao;

	@Override
	public List<NotationDto> selectBoard() {
		return notationDao.selectBoard();
	}

	@Override
	public NotationDto selectBoardByNo(int no) {
		return notationDao.selectBoardByNo(no);
	}

	@Override
	public boolean insertBoard(NotationDto qna) {
		return notationDao.insertBoard(qna) == 1;
	}

	@Override
	@Transactional
	public boolean updateBoard(NotationDto qna) {
		return notationDao.updateBoard(qna) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int no) {
		return notationDao.deleteBoard(no) == 1;
	}

}
