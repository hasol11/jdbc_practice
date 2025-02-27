package com.library.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.model.Student;
import com.library.model.Subject;
import com.library.model.TakenSubject;

public class StudentRepository {
	
	
	// 관리자화면 2 전체 학생 출력
	public static List<Student> showStudent() {
		List<Student> Students = new ArrayList<>();

		String query = "SELECT * FROM student";


		try (Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Student Student = new Student(rs.getInt("student_id"),rs.getInt("login_id"),
						rs.getString("student_name"));

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
				"INNER JOIN taken_subject ts ON st.student_id = ts.student_id " +
				"INNER JOIN subject sub ON sub.subject_id = ts.subject_id " +
				"INNER JOIN teacher te ON sub.teacher_id = te.teacher_id " + 
				"WHERE st.student_name= " + "'" + student_name + "'";

		try (Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
						row.put("student_id", rs.getInt("student_id"));
						row.put("student_name", rs.getString("student_name"));
						row.put("login_id", rs.getInt("login_id"));
						row.put("taken_id", rs.getInt("taken_id"));
						row.put("score", rs.getInt("score"));
						row.put("student_id", rs.getInt("student_id"));
						row.put("subject_id", rs.getInt("subject_id"));
						row.put("teacher_id", rs.getInt("teacher_id"));
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
