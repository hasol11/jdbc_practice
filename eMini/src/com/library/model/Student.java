package com.library.model;

import java.time.LocalDate;


public class Student {
	
	private int student_id;
	private int login_id;
	private String student_name;
	
	
	
	
	public Student(int student_id, int login_id, String student_name) {
		this.student_id = student_id;
		this.login_id = login_id;
		this.student_name = student_name;
	}
	
	
	public int getStudent_id() {
		return student_id;
	}


	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}



	public int getLogin_id() {
		return login_id;
	}



	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}



	public String getStudent_name() {
		return student_name;
	}


	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}



	@Override
	public String toString() {
		return "학생 ID : " + student_id + " | 학생 이름 :  " + student_name + 
				" | 로그인 ID : " + login_id ;
	}
	
	
	

}
