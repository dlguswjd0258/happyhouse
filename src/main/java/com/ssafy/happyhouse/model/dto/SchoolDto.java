package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SchoolDto {
	private String id;
	private String name;
	private String kinds; // 초중고 구분 
	private String address;
	private double lat;
	private double lng;
}
