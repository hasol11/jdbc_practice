package com.library.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.model.Subject;
import com.library.model.TakenSubject;

public class SubjectRepository {

	// 학생화면  2 수강신청 전 과목목록 출력
	// 관리자화면 1 전체 과목 출력
	public static List<Map<String, Object>> showSubject() {
		List<Map<String, Object>> Subjects = new ArrayList<>();

		
		String query = "SELECT * " +
				"FROM teacher t " +
				"INNER JOIN subject sub ON t.teacher_id = sub.teacher_id";

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
						row.put("teacher_id", rs.getInt("teacher_id"));

				Subjects.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return Subjects;
	}
	

	
	
	// 학생화면 1-2 수강신청에서 과목이 존재하는지 확인
	public static int checkIsSubject(String subject_name) {
		List<Subject> Subjects = new ArrayList<>();
		String query = "SELECT * FROM subject WHERE subject_name = " + "'" + subject_name + "'";
		int result = 0;
		
		try(Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while (rs.next()) {
				Subject Subject = new Subject(
						rs.getInt("subject_id"),rs.getInt("teacher_id"),
						rs.getString("subject_name"));

				Subjects.add(Subject);
			}
			if(Subjects.size()== 0) {
				result = 404;
			}else {
				result = 1;
			}
			
		}catch (Exception e) {
			
		}
		
		return result;
	}

	

	
	
	
	
	
}
