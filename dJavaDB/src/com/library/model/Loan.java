package com.library.model;

import java.time.LocalDate;

//DB Loan 테이블과 일치하게 만들자.
public class Loan {
	private int load_id;
	private int bookdata_id;
	private int member_id;
	private String status;
	private LocalDate loan_date;
	//나중에는 이름만 똑같으면 getter setter만 사용해도 된다.
	
	//생성자 
	public Loan(int load_id, int bookdata_id, int member_id, String status, LocalDate loan_date) {
		this.load_id = load_id;
		this.bookdata_id = bookdata_id;
		this.member_id = member_id;
		this.status = status;
		this.loan_date = loan_date;
	}

	//getter setter 제작 
	public int getLoad_id() {
		return load_id;
	}

	public void setLoad_id(int load_id) {
		this.load_id = load_id;
	}

	public int getBookdata_id() {
		return bookdata_id;
	}

	public void setBookdata_id(int bookdata_id) {
		this.bookdata_id = bookdata_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getLoan_date() {
		return loan_date;
	}

	public void setLoan_date(LocalDate loan_date) {
		this.loan_date = loan_date;
	}
	
	//결과 내보낼 것 -> 즉 getter로 보여주는 게 아니라 한 번에 보여주기 위함. 
	@Override
	public String toString() {
		return "대출 ID : " +load_id+" | 도서 ID : "+ bookdata_id +" | 회원 ID : "+ member_id+" | 대출일 : "+
				loan_date+" | 상태 : "+status;
	}
	
}
