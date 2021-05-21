package com.ssafy.happyhouse.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.PageBean;

@Mapper
public interface HouseDealDao {
	public HouseDealDto getHouseDeal(int dealno);
	public List<HouseDealDto> searchDeal(PageBean bean);
	public int totalCount(PageBean bean);
	public List<BaseAddress> searchGu(String city);
	public List<BaseAddress> searchDong(String gu);
}
