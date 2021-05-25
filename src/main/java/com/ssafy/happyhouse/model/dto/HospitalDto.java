package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class HospitalDto {
	private String id;
	private String address;
	private String name;
	private double lat;
	private double lng;
}
