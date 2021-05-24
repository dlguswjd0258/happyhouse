package com.ssafy.happyhouse.model.dto;

import java.util.Date;

public class QnADto {
	private int no;
	private String title;
	private String content;
	private String writer;
	private String reply;
	private Date regtime;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	@Override
	public String toString() {
		return "QnADto [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", reply="
				+ reply + ", regtime=" + regtime + "]";
	}

}
