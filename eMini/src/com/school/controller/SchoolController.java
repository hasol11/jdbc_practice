package com.school.controller;

import java.util.Scanner;

import com.library.service.SchoolService;

public class SchoolController {
	public static final Scanner scan = new Scanner(System.in);
	//로그인 화면 
	public static void startSchoolSystem() {
		esc: while(true) {
			System.out.println("=== 학사 관리 시스템 ===");
			System.out.print("로그인 ID: ");
			int id = scan.nextInt();
			//int noPass=checkLoginID(id);
			System.out.print("비밀번호: ");
			String password=scan.next();
			
			int roleNum= SchoolService.checkLogin(id, password);
			
			switch(roleNum) {
			case 0:break;
			
			case 1:
				showStudent(id);
				break esc;
			case 2:
				showTeacher(id);
				break esc;
			case 3:
				showAdminister(id);
				break esc;
			default: 
				System.out.println("올바른 번호를 입력하세요.");
				break;
			}
		}
	}
	
	// 학생화면
	private static void showStudent(int id) {
		System.out.println("<학생 화면>");
		System.out.println("1.내 점수 보기");
		System.out.println("2.과목 선택하기");
		System.out.println("3.로그아웃");
		System.out.print("입력 : ");
		
		int choice = scan.nextInt();
		switch(choice) {
		case 1: // 내 점수 보기
			SchoolService.showStudentScore(id);
			
			break;
		case 2: // 과목 선택하기 
			SchoolService.showSubject();
			System.out.println("수강신청하고 싶은 과목 이름을 입력해주세요");
			String subject = scan.next();
			SchoolService.applySubject(subject, id);
			
			break;
		case 3: //로그아웃
			break;
		default: 
			System.out.println("올바른 번호를 입력하세요.");
			break;
		}

	}
	
	// 선생님 화면
	private static void showTeacher(int id) {
		esc: while(true) {
			System.out.println();
			System.out.println("<교사 화면>");
			System.out.println("1.내 과목 보기");
			System.out.println("2.학생 정보 조회 및 성적 수정");
			System.out.println("3.로그아웃");
			System.out.print("입력 : ");
			int choice = scan.nextInt();
			
			switch(choice) {
			case 1: // 내 과목 보기
				SchoolService.viewSubjects(id);
				break;
			case 2: // 학생 정보 조회 
				int res= SchoolService.viewStudentInfo(id);
				System.out.println();
				if (res==1) {
					System.out.println("성적을 수정 및 입력하시겠습니까?(y/n)");
					String select=scan.next();
					if(select.equalsIgnoreCase("y")) {
						System.out.print("학생 이름: ");
						String student_name =scan.next();
						System.out.print("과목명: ");
						String subject_name=scan.next();
						System.out.print("성적: ");
						int score=scan.nextInt();
						SchoolService.updateGrade(student_name,subject_name,score);
						continue;
					}else if(select.equalsIgnoreCase("n")) {
						break;
					}
					else {
						System.out.println("잘못된 값을 입력했습니다.");
						break;
					}
				}
			case 3: //로그아웃
				break esc;
			default: 
				System.out.println("올바른 번호를 입력하세요.");
				break;
			}
		}

	}
	
	// 관리자화면
	private static void showAdminister(int id) {
		esc: while(true) {
	
		System.out.println("<관리자 화면>");
		System.out.println("1.전체 과목 보기");
		System.out.println("2.전체 학생 보기");
		System.out.println("3.전체 선생님 보기");
		System.out.println("4.특정 학생 정보 보기");
		System.out.println("5.특정 선생 정보 보기");
		System.out.println("6.추가");
		System.out.println("7.로그아웃");
		System.out.print("입력 : ");

		int choice = scan.nextInt();
		switch(choice) {
		case 1: // 전체 과목 보기
			SchoolService.showSubject();
			break;
		case 2: // 전체 학생 보기
			SchoolService.showStudent();
			break;
		case 3: // 전체 선생님 보기
	
		SchoolService.showTeacher();
			break;
		case 4: // 특정 학생 정보 보기
			System.out.println("학생 이름 : ");
			String student_name = scan.next();
			SchoolService.searchStudent(student_name);
			break;
		case 5: // 특정 선생 정보 보기

			System.out.println("학생 이름 : ");
			String teacher_name = scan.next();
			SchoolService.searchTeacher(teacher_name);
			break;
		case 6: int k=AddByAdminister(); 
		if(k==0) {
			break esc;
		}else {
			break;
		}
		case 7: 
			break esc;
		default: System.out.println("올바른 번호를 입력하세요."); break;
		
		}
	}
}
	
	// 관리자화면 6 
	private static int AddByAdminister() {
		System.out.println("1.과목 추가");
		System.out.println("2.선생님 추가");
		System.out.println("3.학생 추가");
		System.out.println("4.로그아웃");
		System.out.print("입력: ");
		int choice=scan.nextInt();
		int temp=1;
		switch(choice) {
		case 1:
			System.out.print("추가할 과목명:");
			String subjectName=scan.next();
			SchoolService.addSubject(subjectName);
			break;
		case 2:
			System.out.println("1.교사 명단에 교사 추가");
			System.out.println("2.과목에 교사 할당");
			System.out.println("3. 로그아웃");
			System.out.print("입력: ");
			int choice2=scan.nextInt();
			
			switch(choice2) {
			case 1: 
				System.out.print("교사 이름: ");
				String teacherName=scan.next();
				System.out.print("아이디: ");
				int loginId=scan.nextInt();
				System.out.print("비밀번호: ");
				int password=scan.nextInt();
				SchoolService.addTeacher(teacherName,loginId,password); break;
			case 2: 
				System.out.println("과목 목록");
				//SchoolService.showSubject();
				System.out.println("---------");
				System.out.println("교사 목록");
				//SchoolService.showTeacher();????
				System.out.print("과목 이름: ");
				String subjectName1=scan.next();
				System.out.print("교사 이름: ");
				String teacherName1=scan.next();
				SchoolService.addTeacherToSub(subjectName1,teacherName1); break;
			case 3:
				temp=0; break;
			default: System.out.println("올바른 번호를 입력하세요."); break;
			}
			
			break;
		case 3:
			System.out.print("학생 이름: ");
			String studentName=scan.next();
			System.out.print("아이디: ");
			int loginId=scan.nextInt();
			System.out.print("비밀번호: ");
			int password=scan.nextInt();
			SchoolService.addStudent(studentName,loginId,password);
			break;
		case 4:
			temp=0;
			break;
		default: System.out.println("올바른 번호를 입력하세요."); break;
		}
		return temp;
	}
}
