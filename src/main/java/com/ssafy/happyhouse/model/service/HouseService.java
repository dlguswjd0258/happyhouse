package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.PageBean;

public interface HouseService {
	public HouseDealDto getHouseDeal(int dealno);
	public List<HouseDealDto> searchAll(PageBean bean);
	public int getTotal(PageBean bean);
	public List<BaseAddress> searchGu(String city);
	public List<BaseAddress> searchDong(String gu);
}
