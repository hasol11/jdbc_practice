package com.library.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.model.Teacher;

public class TeacherRepository {

	
	// 관리자화면 3 전체 선생 출력
	public static List<Teacher> showTeacher() {
		List<Teacher> Teachers = new ArrayList<>();

		String query = "SELECT * FROM teacher";


		try (Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Teacher Teacher = new Teacher(rs.getInt("teacher_id"),rs.getInt("login_id"),
						rs.getString("teacher_name"));

				Teachers.add(Teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Teachers;
	}
	
	
	//관리자 화면 5 특정 선생님 정보 출력
	public static List<Map<String, Object>> searchTeacher(String teacher_name) {
		List<Map<String, Object>> teachers = new ArrayList<>();

		
		String query = "SELECT * " +
				"FROM teacher te " +
				"INNER JOIN subject sub ON sub.teacher_id = te.teacher_id " +
				"WHERE te.teacher_name = " + "'" + teacher_name + "'";

		try (Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
						row.put("teacher_id", rs.getInt("teacher_id"));
						row.put("teacher_name", rs.getString("teacher_name"));
						row.put("login_id", rs.getInt("login_id"));
						row.put("subject_id", rs.getInt("subject_id"));
						row.put("subject_name", rs.getString("subject_name"));

						teachers.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return teachers;
	}
	
	

}


