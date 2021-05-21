package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dao.HouseDealDao;
import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.util.PageUtility;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDealDao dao;

	@Override
	@Transactional
	public HouseDealDto getHouseDeal(int dealno) {
		return dao.getHouseDeal(dealno);
	}

	@Override
	@Transactional
	public List<HouseDealDto> searchAll(PageBean bean) {

		int total = dao.totalCount(bean);
		PageUtility util = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "images/");
		bean.setPageLink(util.getPageBar());
		System.out.println(bean.getDong()+"k"+bean.getWord()+"k");
		for(HouseDealDto h : dao.searchDeal(bean)) {
			System.out.println(h);
		}
		return dao.searchDeal(bean);

	}

	@Override
	@Transactional
	public List<BaseAddress> searchGu(String city) {
		return dao.searchGu(city);
	}

	@Override
	@Transactional
	public List<BaseAddress> searchDong(String gu) {
		return dao.searchDong(gu);
	}

}
