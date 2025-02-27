package com.library.model;

public class TakenSubject {
	
	private int taken_id;
	private int subject_id;
	private int student_id;
	private int teacher_id;
	private int score;	
	//private Subject subject2;
	
	

	public TakenSubject(int taken_id, int subject_id, int student_id, int teacher_id, int score) {
		super();
		this.taken_id = taken_id;
		this.subject_id = subject_id;
		this.student_id = student_id;
		this.teacher_id = teacher_id;
		this.score = score;
		//this.subject2 = subject2;
	}




	


	public int getTaken_id() {
		return taken_id;
	}







	public void setTaken_id(int taken_id) {
		this.taken_id = taken_id;
	}







	public int getSubject_id() {
		return subject_id;
	}







	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}







	public int getStudent_id() {
		return student_id;
	}







	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}







	public int getTeacher_id() {
		return teacher_id;
	}







	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}







	public int getScore() {
		return score;
	}







	public void setScore(int score) {
		this.score = score;
	}





//
//
//	public Subject getSubject2() {
//		return subject2;
//	}
//
//
//
//
//
//
//
//	public void setSubject2(Subject subject2) {
//		this.subject2 = subject2;
//	}







	@Override
	public String toString() {
		return "수강과목 ID : " + taken_id + " | 과목 ID :  " + subject_id + 
				 "학생 ID : " + student_id + " | 선생님 ID : " + teacher_id +" | 성적 :  " + score;
	}
}
