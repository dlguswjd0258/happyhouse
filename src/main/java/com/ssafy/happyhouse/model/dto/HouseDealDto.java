package com.ssafy.happyhouse.model.dto;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
@NoArgsConstructor
public class HouseDealDto {
	private int no;
	private String dong;
	private String aptName;
	private String code;
	private String dealAmount;
	private String dealYear;
	private String dealMonth;
	private String dealDay;
	private String dealDate;
	private String buildYear;
	private String area;
	private String floor;
	private String jibun;
	private String lat;
	private String lng;
}
