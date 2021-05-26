package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.PageBean;

public interface HouseService {
	public HouseDealDto getHouseDeal(int dealno);
	public List<HouseDealDto> searchAll(PageBean bean);
	public int getTotal(PageBean bean);
	public List<BaseAddress> searchGu(String city);
	public List<BaseAddress> searchDong(String gu);
	
	// 찜목록
	public List<HouseDealDto> getAllWish(Map<String, String> map);
	public int getWish(HashMap<String, String> map);
	public void addWish(HashMap<String, String> map);
	public void deleteWish(HashMap<String, String> map);
}
