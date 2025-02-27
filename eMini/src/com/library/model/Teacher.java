package com.library.model;

public class Teacher {
	
	private int teacher_id;
	private int login_id;
	private String teacher_name;
	
	

	public Teacher(int teacher_id, int login_id, String teacher_name) {

		this.teacher_id = teacher_id;
		this.login_id = login_id;
		this.teacher_name = teacher_name;
	}




	public int getTeacher_id() {
		return teacher_id;
	}




	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}



	public int getLogin_id() {
		return login_id;
	}



	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}



	public String getTeacher_name() {
		return teacher_name;
	}



	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}








	@Override
	public String toString() {
		return "선생님 ID : " + teacher_id + " | 선생님 이름 :  " + teacher_name + 
				" | 로그인 ID : " + login_id ;
	}
}
