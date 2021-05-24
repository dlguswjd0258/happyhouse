package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.service.HouseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/house")
@CrossOrigin("*")
public class HouseDealController {

	@Autowired
	HouseService houseService;

	@ApiOperation(value ="전체 정보 받아오기")
	@GetMapping("/{pageNo}/{dong}/{word}")
	private ResponseEntity<List<HouseDealDto>> getList(@PathVariable("pageNo") int pageNo,@PathVariable("dong") String dong,
			@PathVariable("word") String word) {
		PageBean bean = new PageBean();
		bean.setPageNo(pageNo);
		bean.setDong(dong);
		bean.setWord(word);
		return new ResponseEntity<List<HouseDealDto>>(houseService.searchAll(bean), HttpStatus.OK);
	}
	
	@GetMapping("/total/{pageNo}/{dong}/{word}")
	private ResponseEntity<Integer> getTotal(@PathVariable("pageNo") int pageNo,@PathVariable("dong") String dong,
			@PathVariable("word") String word) {
		PageBean bean = new PageBean();
		bean.setStartNo(pageNo);
		bean.setDong(dong);
		bean.setWord(word);
		System.out.println(pageNo);
		return new ResponseEntity<Integer>(houseService.getTotal(bean), HttpStatus.OK);
	}

	@GetMapping("/{dealno}")
	private ResponseEntity<HouseDealDto> getDeal(@PathVariable("dealno") int dealno) {
		return new ResponseEntity<HouseDealDto>(houseService.getHouseDeal(dealno), HttpStatus.OK);
	}

	@GetMapping("/GuSearch/{city}")
	private ResponseEntity<List<BaseAddress>> getGu(@PathVariable("city") String city) {
		return new ResponseEntity<List<BaseAddress>>(houseService.searchGu(city), HttpStatus.OK);
	}

	@GetMapping("/DongSearch/{gu}")
	private ResponseEntity<List<BaseAddress>> getDong(@PathVariable("gu") String gu) {
		return new ResponseEntity<List<BaseAddress>>(houseService.searchDong(gu), HttpStatus.OK);
	}
}
