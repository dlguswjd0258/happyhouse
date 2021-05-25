package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SubwayDto {
	private String no;
	private String name;
	private String line;
	private String address;
	private double lat;
	private double lng;
}
