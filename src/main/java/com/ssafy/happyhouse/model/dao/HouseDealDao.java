package com.ssafy.happyhouse.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// 찜 목록
	public List<HouseDealDto> getAllWish(Map<String, String> map);
	public int totalWishCount(Map<String, String> map);
	public int getWish(HashMap<String, String> map);
	public void addWish(HashMap<String, String> map);
	public void deleteWish(HashMap<String, String> map);
}
