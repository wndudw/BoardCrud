package com.board.domain;

import java.util.Date;

public class BoardVo {
////BNO INT PRIMARY KEY,
//    TITLE VARCHAR2(50),
//    CONTENTS NCLOB NOT NULL,
//    WRITER VARCHAR2(30) NOT NULL,
//    BDATE DATE DEFAULT SYSDATE, --작성일
//    VIEWS NUMBER DEFAULT 0   --조회수
	
	private int bno;
	private String title;
	private String contents;
	private String writer;
	private Date bdate;
	private int views;
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
	
}
