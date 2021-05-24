package com.ssafy.happyhouse.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString 
@NoArgsConstructor
public class PageBean {
	/** 검색 조건 */
	@Getter @Setter
	private String key;
	/** 검색 지역 */
	@Getter @Setter
	private String dong;
	/** 검색 단어 */
	@Getter @Setter
	private String word;
	/** 현재 페이지 번호 */
	@Getter @Setter
	private int pageNo;
	@Getter @Setter
	private String user;
	
	/** 한 페이지에 보여주 content 개수 */
	@Getter @Setter
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
