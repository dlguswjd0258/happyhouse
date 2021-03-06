package com.ssafy.happyhouse.model.dto;

import java.util.LinkedList;
import java.util.List;

public class MemberDto {

	private String userId;
	private String userName;
	private String userPwd;
	private String email;
	private String address;
	private String joindate;
	private List<String> prefer;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public void addPrefer(String dong) {
		if (prefer == null) {
			prefer = new LinkedList<>();
		}
		this.prefer.add(dong);
	}

	public List getPrefer() {
		return prefer;
	}

	@Override
	public String toString() {
		return "MemberDto [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", email=" + email
				+ ", address=" + address + ", joindate=" + joindate + ", prefer=" + prefer + "]";
	}
}
