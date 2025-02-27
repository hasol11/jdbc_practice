package com.school.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.school.model.Subject;
import com.school.model.Teacher;

public class TeacherRepository {

	public static List<Subject> viewSubjects(int id) {
		List<Subject> subjects =new ArrayList<>();
		String query="SELECT * FROM subject s "
				+ " INNER JOIN teacher t ON t.teacher_id = s.teacher_id "
				+ " WHERE login_id= "+id;
		try(Connection conn = DataBaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(query) 
				) {
			while(rs.next()) {
				Subject subject = new Subject(rs.getInt("subject_id"),rs.getString("subject_name"),rs.getInt("teacher_id"));
				subjects.add(subject);
			}
		} catch (Exception e) {
		}
		return subjects;
	}


	//관리자6-2-1 교사 추가 - 교사 존재 확인 
	public static int [] isTeacherExist(int loginId) {
		List<Teacher> list = new ArrayList<>();
		String query="SELECT * FROM teacher WHERE teacher_id = ?";
		try(Connection conn =DataBaseManager.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(query)) {
			pstmt.setInt(1, loginId);
			try (ResultSet rs= pstmt.executeQuery()){
				while(rs.next()) {
					Teacher bvo = new Teacher(rs.getInt("teacher_id"),rs.getString("teacher_name"),rs.getInt("login_id"));
					list.add(bvo);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			int res [] = {0,0};
			if(list.isEmpty()) {
				res[0]=0;
			}else {
				res[0]=1;
			}
			if(list.isEmpty()) {
				res[1]=0;
			}else {
				res[1]=list.get(0).getTeacher_id();
			}
		return res;
	}
	
	public static int [] isTeacherExistName(String teacherName1) {
		List<Teacher> list = new ArrayList<>();
		String query="SELECT * FROM teacher WHERE teacher_name = ?";
		try(Connection conn =DataBaseManager.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(query)) {
			pstmt.setString(1, teacherName1);
			try (ResultSet rs= pstmt.executeQuery()){
				while(rs.next()) {
					Teacher bvo = new Teacher(rs.getInt("teacher_id"),rs.getString("teacher_name"),rs.getInt("login_id"));
					list.add(bvo);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			int res [] = {0,0};
			if(list.isEmpty()) {
				res[0]=0;
			}else {
				res[0]=1;
			}
			if(list.isEmpty()) {
				res[1]=0;
			}else {
				res[1]=list.get(0).getTeacher_id();
			}
		return res;
	}
	
	//관리자6-2-1 교사 추가 - 교사 명단에 추가
	public static int addTeacher(String teacherName, int loginId, int password) {
		
		String query="INSERT INTO login (login_id,password,role) VALUES('"+loginId+ "',"+password+",2)";
		try(Connection conn = DataBaseManager.getConnection();
				Statement stmt = conn.createStatement()){
			//삽입, 수정, 삭제가 성공하면 0보다 크다. 
			stmt.executeUpdate(query);			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		int result=0;
		String query2="INSERT INTO teacher (teacher_name, login_id) VALUES('"+teacherName+"','"+loginId+ "')";
		try(Connection conn = DataBaseManager.getConnection();
				Statement stmt = conn.createStatement()){
			//삽입, 수정, 삭제가 성공하면 0보다 크다. 
			result=stmt.executeUpdate(query2);			
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	//교사  2 - 학생 정보 조회 
	public static int viewStudentInfo(int id) {
		int res= 0;
		String query = " SELECT sd.student_name, s.subject_name, ts.score FROM taken_subject ts "
				+ " INNER JOIN teacher t ON ts.teacher_id=t.teacher_id "
				+ " INNER JOIN subject s ON ts.subject_id=s.subject_id "
				+ " INNER JOIN student sd ON sd.student_id=ts.student_id "
				+ " INNER JOIN login l ON l.login_id=t.login_id "
				+ " WHERE l.login_id=? ";
		try (Connection conn =DataBaseManager.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(query)){
			pstmt.setInt(1, id);	
			
			try (ResultSet rs = pstmt.executeQuery()) {
	            if (!rs.next()) {
	                res=0;
	            } else {
	            	res=1;
	                System.out.println("<학생 정보>");
	                do {
	                    String studentName = rs.getString("student_name");
	                    String subjectName = rs.getString("subject_name");
	                    Object scoreObject = rs.getObject("score");
	                    if(scoreObject==null) { //null이라면
	                    	String scoreString ="null";
		                    
		                    System.out.println("학생명: " + studentName);
		                    System.out.println("과목명: " + subjectName);
		                    System.out.println("성적: " + scoreString);
		                    System.out.println("----------------------------");
	                    }else {
	                    	int score = rs.getInt("score");
		                    
		                    System.out.println("학생명: " + studentName);
		                    System.out.println("과목명: " + subjectName);
		                    System.out.println("성적: " + score);
		                    System.out.println("----------------------------");
	                    }
	                    
	                } while (rs.next());
	            }
	        }
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}
}