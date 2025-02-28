package com.school.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.school.model.Student;



public class StudentRepository {
	
	
	//관리자 6-3 존재유무 
	//login_id가 같은지 확인 
	public static int isStudentExist(int loginId) {
		List<Student> list = new ArrayList<>();
		String query="SELECT * FROM student WHERE login_id = ?";
		try(Connection conn =DatabaseManager.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(query)) {
			pstmt.setInt(1, loginId);
			try (ResultSet rs= pstmt.executeQuery()){
				while(rs.next()) {
					Student bvo = new Student(rs.getInt("student_id"),rs.getString("student_name"),rs.getInt("login_id"));
					list.add(bvo);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			int res = 0;
			if(list.isEmpty()) {
				res=0;
			}else {
				res=1;
			}
		
		return res;
	}

	//관리자 6-3-학생 리스트에 추가 
	public static int addStudent(String studentName, int loginId, String password) {
		String query="INSERT INTO login (login_id,password, role) VALUES('"+loginId+ "', '"+password+"' ,1)";
		try(Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement()){
			//삽입, 수정, 삭제가 성공하면 0보다 크다. 
			stmt.executeUpdate(query);			
		} catch (Exception e) {
		}
		
		int result=0;
		String query2="INSERT INTO student (student_name, login_id) VALUES('"+studentName+"','"+loginId+ "')";
		try(Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement()){
			//삽입, 수정, 삭제가 성공하면 0보다 크다. 
			result=stmt.executeUpdate(query2);			
		} catch (Exception e) {
		}
		return result;
	}

	
	
	
	// 관리자화면 2 전체 학생 출력
	public static List<Student> showStudent() {
		List<Student> Students = new ArrayList<>();

		String query = "SELECT * FROM student";


		try (Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Student Student = new Student(rs.getInt("student_id"),
						rs.getString("student_name"),rs.getInt("login_id"));

				Students.add(Student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Students;
	}
	
	// 관리자화면 4 특정 학생 정보 출력
	public static List<Map<String, Object>> searchStudent(String student_name) {
		List<Map<String, Object>> takenSubjects = new ArrayList<>();

		
		String query = "SELECT * " +
				"FROM student st " +
				"LEFT JOIN taken_subject ts ON st.student_id = ts.student_id " +
				"LEFT JOIN subject sub ON sub.subject_id = ts.subject_id " +
				"LEFT JOIN teacher te ON sub.teacher_id = te.teacher_id " + 
				"WHERE st.student_name= " + "'" + student_name + "'";

		try (Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
						row.put("student_name", rs.getString("student_name"));
						row.put("login_id", rs.getInt("login_id"));
						row.put("taken_id", rs.getInt("taken_id"));
					
						Object scoreObject = rs.getObject("score");
						row.put("score", scoreObject);
						row.put("subject_id", rs.getInt("subject_id"));
						row.put("student_id", rs.getInt("student_id"));
						row.put("subject_name", rs.getString("subject_name"));
						row.put("teacher_id", rs.getInt("teacher_id"));
						row.put("teacher_name", rs.getString("teacher_name"));
						

				takenSubjects.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return takenSubjects;
	}
	
	
}
