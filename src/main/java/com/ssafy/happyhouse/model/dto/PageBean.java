package com.ssafy.happyhouse.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString 
@NoArgsConstructor
public class PageBean {
	/** 검색 조건 */
	private String key;
	/** 검색 지역 */
	private String dong;
	/** 검색 단어 */
	private String word;
	/** 현재 페이지 번호 */
	private int pageNo;
	private String user;
	private int total;
	
	/** 한 페이지에 보여주 content 개수 */
	private int interval = 10;

	private int startNo = 0;
	private String pageLink;
	
	public int getStartNo() {
		if (pageNo > 0) {
			return (pageNo - 1) * interval;
		} else {
			return 0;
		}
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}


}
