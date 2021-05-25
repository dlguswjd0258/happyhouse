package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dao.NoticeDao;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.dto.PageBean;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;

	@Override
	public List<NoticeDto> selectBoard(PageBean bean) {
		bean.setTotal(noticeDao.totalCount(bean));
		return noticeDao.selectBoard(bean);
	}

	@Override
	public NoticeDto selectBoardByNo(int no) {
		return noticeDao.selectBoardByNo(no);
	}

	@Override
	public boolean insertBoard(NoticeDto qna) {
		return noticeDao.insertBoard(qna) == 1;
	}

	@Override
	@Transactional
	public boolean updateBoard(NoticeDto qna) {
		return noticeDao.updateBoard(qna) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int no) {
		return noticeDao.deleteBoard(no) == 1;
	}

}
