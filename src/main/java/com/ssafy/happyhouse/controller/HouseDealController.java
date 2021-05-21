package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.PageBean;
import com.ssafy.happyhouse.model.service.HouseService;

@RestController
@RequestMapping("/house")
@CrossOrigin("*")
public class HouseDealController {

	@Autowired
	HouseService houseService;

	@GetMapping("HouseDeal")
	private ResponseEntity<List<HouseDealDto>> getDeal(@RequestBody PageBean bean) {
		return new ResponseEntity<List<HouseDealDto>>(houseService.searchAll(bean), HttpStatus.OK);
	}

	@GetMapping("HouseDeal/{dealno}")
	private ResponseEntity<HouseDealDto> getDeal(@PathVariable("dealno") int dealno) {
		return new ResponseEntity<HouseDealDto>(houseService.getHouseDeal(dealno), HttpStatus.OK);
	}

	@GetMapping("HouseDeal/GuSearch/{city}")
	private ResponseEntity<List<BaseAddress>> getGu(@PathVariable("city") String city) {
		return new ResponseEntity<List<BaseAddress>>(houseService.searchGu(city), HttpStatus.OK);
	}

	@GetMapping("HouseDeal/DongSearch/{gu}")
	private ResponseEntity<List<BaseAddress>> getDong(@PathVariable("gu") String gu) {
		return new ResponseEntity<List<BaseAddress>>(houseService.searchDong(gu), HttpStatus.OK);
	}
}
