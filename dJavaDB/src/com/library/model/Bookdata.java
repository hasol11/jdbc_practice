package com.library.model;

public class Bookdata {
	private int bookdata_id;
	private String title;
	private int available;
	
	//생성자 
	public Bookdata(int bookdata_id, String title, int available) {
		this.bookdata_id = bookdata_id;
		this.title = title;
		this.available = available;
	}

	public int getBookdata_id() {
		return bookdata_id;
	}

	public void setBookdata_id(int bookdata_id) {
		this.bookdata_id = bookdata_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	@Override
	public String toString() {
		return "책 ID : "+bookdata_id+" | 책 제목 : "+title+" | 상태 : "+(available==1?"대출 가능":"대출 중");
	}
}
