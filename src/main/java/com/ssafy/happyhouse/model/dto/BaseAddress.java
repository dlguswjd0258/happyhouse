package com.ssafy.happyhouse.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
@NoArgsConstructor
public class BaseAddress {
	private String num ;
	private String city;
	private String code;
	private String dongcode;
	private String gugun;
	private String dong;
	private String lat ;
	private String lng;
}
