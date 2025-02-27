package com.school.service;

import java.util.Iterator;
import java.util.List;

import com.school.model.Subject;
import com.school.repository.LoginRepository;
import com.school.repository.StudentRepository;
import com.school.repository.SubjectRepository;
import com.school.repository.TakenSubjectRepository;
import com.school.repository.TeacherRepository;

public class SchoolService {

	//로그인 검사
	public static int checkLogin(int id, String password) {
		//1. ID가 존재하는지 확인 / 있으면 1, 없으면 0
		//2. ID에서의 비밀번호가 맞는지 확인 / 맞으면 1, 아니면 0 > 1이라면 role 받기 
		int resPass[]=new int[2];
		int resRole=0;
		int res=LoginRepository.checkLoginID(id);
		if(res==0) {
			System.out.println("없는 아이디입니다.");
			System.out.println();
		}else {
			resPass=LoginRepository.checkLoginPassword(id,password);
			if(resPass[0]==0) {
				System.out.println("비밀번호가 잘못됐습니다.");
				System.out.println();
			}else {
				resRole=resPass[1];
			}
		}
		return resRole;
	}

	//교사1 - 내 과목 조회 
	public static void viewSubjects(int id) {
		List<Subject> subjects = TeacherRepository.viewSubjects(id);
		if(subjects.isEmpty()) {
			System.out.println("가르치는 과목이 없습니다.");
		}else {
			System.out.println("<내 과목 목록>");
			Iterator<Subject> it = subjects.iterator();
			while (it.hasNext()) {
				Subject subject = (Subject) it.next();
				System.out.println(subject.toStringByTeacher());
			}
		}
	}

	//교사2 - 학생 정보 조회 
	public static int viewStudentInfo(int id) {
		//학생 이름(student_name), 수강과목(subject_name), 성적 출력(taken_subject/score)
		int res=0;
		int result= TeacherRepository.viewStudentInfo(id);		
		if(result==0) {
			res=0;
			System.out.println("학생 정보를 찾을 수 없습니다.");
		}else {
			res=1;
		}
		return res;
	}
	
	
	//교사2 - 학생 정보 조회 - 성적 수정 및 입력 
	public static void updateGrade(String student_name, String subject_name, int score) {
		int result=TakenSubjectRepository.updateGrade(student_name,subject_name,score);
		if (result==1) {
			System.out.println("수정 성공");
		}else {
			System.out.println("수정 실패");
		}
	}

	//관리자6 - 1.과목 추가
	public static void addSubject(String subjectName) {
		//과목이 이미 있는 경우 있다고 출력 및 추가 X
		//과목이 없는 경우 추가 후 성공 메시지
		int res= SubjectRepository.isSubjectExist(subjectName);
		if(res==1) {
			System.out.println("이미 존재하는 과목입니다.");
		}else {
			int result=SubjectRepository.addSubject(subjectName);
			if (result==1) {
				System.out.println("과목 추가 성공");
			}else {
				System.out.println("과목 추가 실패");
			}
		}
	}

	//관리자6 - 2.교사 추가 - 교사 명단에 추가
	public static void addTeacher(String teacherName, int loginId, int password) {
		//1. 교사 존재 유무 검사 있으면 1 없으면 0
		//교사 추가(1일 때) ////교사 이름 받아야 함. 
		int []res=new int [2];
		res= TeacherRepository.isTeacherExist(loginId);
		if(res[0]==1) {
			System.out.println("이미 존재하는 교사 ID 입니다.");
		}else {
			int result=TeacherRepository.addTeacher(teacherName,loginId,password);
			if (result==1) {
				System.out.println("교사 추가 성공");
			}else {
				System.out.println("교사 추가 실패");
			}
		}
		  
	}


	//관리자6 - 2.교사 추가 - 과목에 교사 추가 
	public static void addTeacherToSub(String subjectName1, String teacherName1) {
		//2. 과목에 교사 추가
				//교사이름, 과목명 받아야 함.
				//과목이 없을 때 출력 : 없는 과목입니다.
				//과목이 있을 때 : 과목명으로 교사이름 불러왔을 때 null이면 그 과목에 교사 추가
				//만약 null이 아니면 이미 해당 과목 교사가 존재합니다.
		int res=SubjectRepository.isSubjectExist(subjectName1);
		if(res==0) {
			System.out.println("없는 과목입니다.과목부터 생성해주세요"); 
			System.out.println();
		}else {
			int resp=SubjectRepository.isTeacherNull(subjectName1);
			//교사가 현재 존재하는지 확인 
			int[] resTea=TeacherRepository.isTeacherExistName(teacherName1);
			if(resTea[0]==1) {
				if(resp==1) {
					int result=SubjectRepository.addTeacherToSubject(subjectName1,teacherName1);
					if (result==1) {
						System.out.println("과목에 교사 추가 성공");
					}else {
						System.out.println("과목에 교사 추가 실패");
					}
				}else {
					System.out.println("교사가 존재하는 과목입니다.");
				}
			}else {
				System.out.println("존재하지 않는 교사입니다. 교사부터 추가해주세요.");
			}
		}
	}

	//관리자6 - 학생 추가  
	//학생 이름이랑 학생 id 받기, 이미 id가 존재하면 존재하는 학생 >> 나중에 id로 검사하도록 바꾸기
	public static void addStudent(String studentName, int loginId, int password) { 	
		int res= StudentRepository.isStudentExist(loginId);
		if(res==1) {
			System.out.println("이미 존재하는 ID입니다.");
		}else {
			int result=StudentRepository.addStudent(studentName,loginId,password);
			if (result==1) {
				System.out.println("학생 추가 성공");
			}else {
				System.out.println("학생 추가 실패");
			}
		}
	}
}
