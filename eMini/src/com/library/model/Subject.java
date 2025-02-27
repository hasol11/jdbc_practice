package com.library.model;

public class Subject {
	
private int subject_id;
	private int teacher_id;
	private String subject_name;


	public Subject(int subject_id, int teacher_id, String subject_name) {
		this.subject_id = subject_id;
		this.teacher_id = teacher_id;
		this.subject_name = subject_name;
	}



	public int getSubject_id() {
		return subject_id;
	}


	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}


	public int getTeacher_id() {
		return teacher_id;
	}



	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}


	public String getSubject_name() {
		return subject_name;
	}


	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}



	@Override
	public String toString() {
		return "과목 ID : " + subject_id + " | 과목 이름 :  " + subject_name + 
				" | 선생님 ID : " + teacher_id ;
	}
}
